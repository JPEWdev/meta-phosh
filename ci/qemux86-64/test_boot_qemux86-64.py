import time


def test_boot(shell_command):
    shell_command.run_check("true")


def test_phoc(shell_command):
    time.sleep(5)
    output, _, _ = shell_command.run("ps")
    print("\n".join(output))
    shell_command.run_check("pidof phoc")


def test_phosh(shell_command):
    time.sleep(5)
    output, _, _ = shell_command.run("ps")
    print("\n".join(output))
    shell_command.run_check("pidof phosh")


def test_squeekboard(shell_command):
    time.sleep(5)
    output, _, _ = shell_command.run("ps")
    print("\n".join(output))
    shell_command.run_check("pidof squeekboard")
