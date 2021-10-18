SUMMARY = "A pure Wayland shell prototype for GNOME on mobile devices"
HOMEPAGE = "https://puri.sm/projects/phosh/"
LICENSE = "GPLv3"
SECTION = "wayland/shell"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "\
    feedbackd \
    gcr \
    glib-2.0 \
    glib-2.0-native \
    gnome-desktop3 \
    graphviz \
    gtk+3 \
    libhandy \
    libsecret \
    networkmanager \
    polkit \
    systemd \
    upower \
    wayland-native \
    "

SRC_URI = "\
    gitsm://source.puri.sm/Librem5/phosh.git;protocol=https;branch=${BRANCH} \
    file://phosh-user-override.conf \
    file://phoc.ini \
    file://phosh.pam \
    "
SRCREV = "44f447ab6e3d4943311fa13db6f65a6d5627b34c"
BRANCH = "master"

S = "${WORKDIR}/git"

inherit meson features_check gettext gsettings systemd useradd pkgconfig

REQUIRED_DISTRO_FEATURES = "wayland opengl systemd"

do_install:append() {
    install -Dm 644 ${S}/data/phosh.service \
        ${D}${systemd_system_unitdir}/phosh.service

    install -Dm 644 ${WORKDIR}/phosh-user-override.conf \
        ${D}${systemd_system_unitdir}/phosh.service.d/phosh-user-override.conf

    install -dm 755 -o phosh -g phosh ${D}/home/phosh

    install -Dm 644 ${WORKDIR}/phoc.ini \
        ${D}${sysconfdir}/phosh/phoc.ini

    install -Dm 644 ${WORKDIR}/phosh.pam \
        ${D}${sysconfdir}/pam.d/phosh
}

USERADD_PACKAGES = "${PN}"

USERADD_PARAM:${PN} = "--system --home /home/phosh -M --shell /bin/sh --user-group phosh"
SYSTEMD_SERVICE:${PN} = "phosh.service"
FILES:${PN} += "\
    ${datadir}/gnome-session \
    ${datadir}/wayland-sessions \
    ${systemd_system_unitdir}/phosh.service.d \
    /home/phosh \
    ${sysconfdir}/phosh/phoc.ini \
    "
RDEPENDS:${PN} += "\
    gnome-session \
    gnome-shell-gsettings \
    phoc \
    xkeyboard-config \
    "
