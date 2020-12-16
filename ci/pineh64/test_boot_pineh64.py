def test_boot(image, shell_command):
    shell_command.run_check("true")


def test_ssh(image, ssh_command):
    ssh_command.run_check("true")
