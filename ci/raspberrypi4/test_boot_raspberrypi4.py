import pytest


def test_boot(image, shell_command):
    shell_command.run_check("true")


def test_ssh(image, ssh_command):
    ssh_command.run_check("true")


def test_phoc(image, shell_command):
    shell_command.run_check("pidof phoc")


def test_phosh(image, shell_command):
    shell_command.run_check("pidof phosh")


def test_squeekboard(image, shell_command):
    shell_command.run_check("pidof squeekboard")
