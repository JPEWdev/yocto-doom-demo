import json
import pytest
import time
from labgrid.protocol import CommandProtocol
from labgrid.driver import (
    USBStorageDriver,
    USBSDWireDriver,
    USBSDMuxDriver,
    Mode,
    SSHDriver,
)
from labgrid.resource import NetworkService


@pytest.fixture(scope="function")
def switch_off(target, strategy, capsys):
    with capsys.disabled():
        strategy.transition("off")


@pytest.fixture(scope="function")
def shell_command(target, strategy, capsys):
    with capsys.disabled():
        strategy.transition("shell")
    return target.get_active_driver(CommandProtocol)


@pytest.fixture(scope="function")
def ssh_command(target, strategy, capsys):
    with capsys.disabled():
        strategy.transition("shell")
    shell = target.get_active_driver(CommandProtocol)

    net = target.get_resource(NetworkService)
    address = net.address.split("%", 1)[0]

    interfaces = json.loads("\n".join(shell.run_check("ip -j addr")))

    assign_intf = None
    found = False
    for intf in interfaces:
        if not intf["ifname"].startswith("eth") and not intf["ifname"].startswith("en"):
            continue

        for addr in intf["addr_info"]:
            if addr["family"] == "inet6" and addr["local"] == address:
                found = True
                break

        if found:
            break

        if assign_intf is None and intf["operstate"] == "UP":
            assign_intf = intf

    if not found:
        if assign_intf is not None:
            shell.run_check(
                "ip addr add dev %s scope link %s" % (assign_intf["ifname"], address)
            )
            # Wait for device to configure services
            time.sleep(3)
        else:
            pytest.fail("No active interface found to assign address %s" % address)

    return target.get_driver(SSHDriver)


@pytest.fixture(scope="session")
def image(target, strategy):
    mux = None
    for d in (USBSDWireDriver, USBSDMuxDriver):
        try:
            mux = target.get_driver(d)
            break
        except NoDriverFoundError:
            pass

    if mux is None:
        ptest.skip("No SD Mux found")

    try:
        mass_storage = target.get_driver(USBStorageDriver)
    except NoDriverFoundError:
        ptest.skip("No USB Mass storage device found")

    strategy.transition("off")
    mux.set_mode("host")
    mass_storage.write_image(mode=Mode.BMAPTOOL)
    time.sleep(2)
    mux.set_mode("dut")
    time.sleep(2)
