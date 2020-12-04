import pytest
import time
from labgrid.protocol import CommandProtocol
from labgrid.driver import USBStorageDriver, USBSDWireDriver, USBSDMuxDriver, Mode


@pytest.fixture(scope="function")
def switch_off(target, strategy, capsys):
    with capsys.disabled():
        strategy.transition("off")


@pytest.fixture(scope="function")
def shell_command(target, strategy, capsys):
    with capsys.disabled():
        strategy.transition("shell")
    return target.get_active_driver(CommandProtocol)


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
