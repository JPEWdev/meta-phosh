SUMMARY = "A Phosh image with GNOME applications"

IMAGE_FEATURES += "splash package-management ssh-server-dropbear hwcodecs"

LICENSE = "MIT"

inherit core-image

CORE_IMAGE_BASE_INSTALL += "\
    packagegroup-phosh-essential \
    epiphany \
    packagegroup-gnome-apps \
    "
CORE_IMAGE_BASE_INSTALL += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland', '', d)}"


QB_MEM = "-m 1024"
