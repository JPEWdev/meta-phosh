SUMMARY = "Wayland compositor for mobile phones like the Librem 5"
HOMEPAGE = "https://puri.sm/projects/phoc/"
LICENSE = "GPLv3"
SECTION = "wayland/compositor"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "\
    glib-2.0 \
    glib-2.0-native \
    gnome-desktop3 \
    libdrm \
    libinput \
    libxkbcommon \
    pixman \
    virtual/libgles2 \
    wayland \
    wayland-native \
    wayland-protocols \
    weston \
    "

SRC_URI = "gitsm://gitlab.gnome.org/World/Phosh/phoc.git;protocol=http;branch=${BRANCH}"
SRCREV = "0563907a884f4edb9f0a84dcefb52dccc3407c10"
BRANCH = "master"

S = "${WORKDIR}/git"

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES','x11','xwayland','',d)}"

PACKAGECONFIG[xwayland] = "-Dxwayland=enabled,-Dxwayland=disabled,"

inherit meson features_check gsettings pkgconfig

REQUIRED_DISTRO_FEATURES = "wayland opengl"

# TODO: determine what is actually needed here
#RDEPENDS:${PN} += "mutter-gsettings"
RDEPENDS:${PN} += " \
    iso-codes \
    libweston-9 \
    mutter \
    "
