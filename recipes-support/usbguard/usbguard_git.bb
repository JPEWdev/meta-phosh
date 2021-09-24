SUMMARY = "USB device allowlisting tool"
DESCRIPTION = "USBGuard is a software framework for implementing USB device \
authorization policies (what kind of USB devices are authorized) as well as \
method of use policies (how a USB device may interact with the system). \
Simply put, it is a USB device allowlisting tool."
HOMEPAGE = "https://usbguard.github.io/"
BUGTRACKER = "https://github.com/USBGuard/usbguard/issues"
LICENSE = "GPL-2.0-or-later & BSL-1.0 & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8264535c0c4e9c6c335635c4026a8022 \
                    file://src/ThirdParty/Catch/LICENSE_1_0.txt;md5=e4224ccaecb14d942c71d31bef20d78c \
                    file://src/ThirdParty/PEGTL/LICENSE;md5=5b6701671289d12b7ffa69138832c006"

SRC_URI = "gitsm://github.com/USBGuard/usbguard.git;protocol=https"

PV = "1.0.0+git${SRCPV}"
SRCREV = "d71d5faa7340baa25b25745026f581c82adbb0c7"

S = "${WORKDIR}/git"

# NOTE: the following prog dependencies are unknown, ignoring: a2x xsltproc protoc aspell xmllint gdbus-codegen
# NOTE: unable to map the following pkg-config dependencies: libqb libsodium audit umockdev-1.0 protobuf
#       (this is based on recipes that have previously been built and packaged)
DEPENDS = " \
    audit \
    bash-completion \
    dbus \
    glib-2.0 \
    glib-2.0-native \
    libcap-ng \
    libgcrypt \
    libqb \
    libseccomp \
    libsodium \
    libxml2-native \
    libxslt-native \
    openssl \
    polkit \
    protobuf \
    protobuf-native \
    umockdev \
    "

# NOTE: if this software is not capable of being built in a separate build directory
# from the source, you should replace autotools with autotools-brokensep in the
# inherit line
inherit pkgconfig autotools

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF = "--with-bundled-catch --with-bundled-pegtl"

