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
    "

SRC_URI = "gitsm://source.puri.sm/Librem5/phoc.git;protocol=http;branch=${BRANCH}"
SRCREV = "f26fa0123742ba95d303ad552fc1f8d2d0117288"
BRANCH = "master"

S = "${WORKDIR}/git"

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES','x11','xwayland','',d)}"

PACKAGECONFIG[xwayland] = "-Dxwayland=enabled,-Dxwayland=disabled,"

inherit meson features_check gsettings

REQUIRED_DISTRO_FEATURES = "wayland opengl"

RDEPENDS_${PN} += "mutter-gsettings"
