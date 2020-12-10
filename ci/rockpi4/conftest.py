import pytest

from labgrid.protocol import CommandProtocol


@pytest.fixture(scope="function")
def bootloader_command(target, strategy, capsys):
    with capsys.disabled():
        strategy.transition("uboot")
    return target.get_active_driver(CommandProtocol)
