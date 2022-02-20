SUMMARY = "Call audio routing daemon"
DESCRIPTION = "callaudiod is a daemon for dealing with audio routing during \
phone calls. \
It provides a D-Bus interface allowing other programs to: \
* switch audio profiles \
* output audio to the speaker or back to its original port \
* mute the microphone \
"
HOMEPAGE = "https://gitlab.com/mobian1/callaudiod"
BUGTRACKER = "https://gitlab.com/mobian1/callaudiod/-/issues"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://gitlab.com/mobian1/callaudiod.git;protocol=https"

SRCREV = "4cb8bd9d228d13ea67e79f4c37c674f412b32d20"

S = "${WORKDIR}/git"

inherit meson gobject-introspection-data pkgconfig

DEPENDS = "glib-2.0-native alsa-lib pulseaudio"

RDEPENDS:${PN} += " pulseaudio-server"

FILES:${PN} += " \
    ${datadir}/dbus-1 \
"
