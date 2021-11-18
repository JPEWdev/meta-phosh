SUMMARY = "A Wayland virtual keyboard"
DESCRIPTION = "Squeekboard is a virtual keyboard supporting Wayland, built \
primarily for the Librem 5 phone. \
It squeaks because some Rust got inside."
HOMEPAGE = "https://developer.puri.sm/projects/squeekboard/"
BUGTRACKER = "https://gitlab.gnome.org/World/Phosh/squeekboard/-/issues"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://debian/copyright;md5=03d0c6e3f2e8ec62fb33c983433ce7e5"

SRC_URI = "git://gitlab.gnome.org/World/Phosh/squeekboard.git;protocol=https;branch=${BRANCH}"

# Modify these as desired
PV = "1.14.0+git${SRCPV}"
SRCREV = "fc411d680b0138042b95b8a630401607726113d4"
BRANCH = "master"

S = "${WORKDIR}/git"

inherit cargo meson
