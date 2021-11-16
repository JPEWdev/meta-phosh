import pytest


def test_boot(image, shell_command):
    shell_command.run_check("true")


@pytest.mark.xfail(reason="No ip addr -j in core-image-minimal", strict=True)
def test_ssh(image, ssh_command):
    ssh_command.run_check("true")
