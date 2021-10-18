SUMMARY = "A daemon to provide haptic feedback on events"
HOMEPAGE = "https://source.puri.sm/Librem5/feedbackd"
LICENSE = "GPLv3 & LGPLv2.1"
LIC_FILES_CHKSUM = "\
    file://${S}/COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    file://${S}/COPYING.LIB;md5=4fbd65380cdd255951079008b364516c \
    "

DEPENDS = "\
    glib-2.0 \
    glib-2.0-native \
    gsound \
    json-glib \
    libgudev \
    "

SRC_URI = "git://source.puri.sm/Librem5/feedbackd.git;protocol=https;branch=${BRANCH}"
SRCREV = "aac3306ad5e24ca46e1ca402a478f505b01b351e"
BRANCH = "master"

S = "${WORKDIR}/git"

inherit meson gobject-introspection gsettings vala pkgconfig

GIR_MESON_ENABLE_FLAG = 'enabled'
GIR_MESON_DISABLE_FLAG = 'disabled'

FILES:${PN} += "${datadir}/dbus-1/*"

