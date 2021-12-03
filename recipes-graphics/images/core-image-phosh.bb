SUMMARY = "A basic Phosh image"

IMAGE_FEATURES += "splash package-management ssh-server-dropbear hwcodecs"

LICENSE = "MIT"

inherit core-image features_check

CORE_IMAGE_BASE_INSTALL += "\
    packagegroup-phosh-essential \
    gtk+3-demo \
    epiphany \
    puzzles \
    pcmanfm \
    l3afpad \
    gst-examples \
    "
CORE_IMAGE_BASE_INSTALL += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)}"

REQUIRED_DISTRO_FEATURES = "wayland opengl polkit systemd"

QB_MEM = "-m 512"
