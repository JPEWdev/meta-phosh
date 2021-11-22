def test_boot(shell_command):
    shell_command.run_check("true")


def test_phoc(shell_command):
    shell_command.run_check("pidof phoc")


def test_phosh(shell_command):
    shell_command.run_check("pidof phosh")


def test_squeekboard(shell_command):
    shell_command.run_check("pidof squeekboard")
