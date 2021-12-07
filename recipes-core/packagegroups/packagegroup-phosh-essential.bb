SUMMARY = "Phosh Shell"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup features_check
REQUIRED_DISTRO_FEATURES = "wayland opengl polkit systemd"

RDEPENDS:${PN} = "\
    gnome-control-center \
    phoc \
    phosh \
    squeekboard \
    glibc-localedatas \
    "
