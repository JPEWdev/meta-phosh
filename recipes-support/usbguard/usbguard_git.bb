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

SRC_URI = "gitsm://github.com/USBGuard/usbguard.git;protocol=https;branch=${BRANCH} \
           file://0001-Link-against-libpthread.patch \
           file://0002-Write-PIDFile-to-run-instead-of-var-run.patch \
           file://0003-Fix-test-Source-check-driver.sh.patch \
           file://0004-Fix-linker-issues-with-disable-static.patch \
           file://0005-Increase-C-version-to-c-17.patch \
           file://0006-Fix-Compilation-errors-with-PEGTL-3.2.0-453.patch \
           file://0007-Add-treelike-list-devices-output.patch \
           file://0008-Add-CAP_AUDIT_WRITE-capability-to-service-file.patch \
           file://0009-Apply-PEGTL-3.2.0-fixes-to-fuzzers.patch \
           file://0010-Create-build.yml.patch \
           file://0011-Fixed-ansible-playbook.patch \
           file://0012-Fixed-PEGTL-CFLAGS-propagating-for-Makefile.patch \
           file://0013-Remove-.travis.yml.patch \
           file://0014-Add-include-tao-pegtl.hpp-to-fuzzers.patch \
           file://0015-Add-support-for-lower-OpenSSL-versions-prior-to-1.1..patch \
           file://0016-Refactoring-remove-unused-variable-and-dead-code-464.patch \
           file://0017-Update-completion-script-470.patch \
           file://0018-Improve-group-user-name-checking-479.patch \
           file://0019-Added-a-new-signal-DevicePolicyApplied-to-inform-abo.patch \
           file://0020-Added-some-more-logging.patch \
           file://0021-configure.ac-Use-standard-with-bash-completion-dir-o.patch \
           file://0022-Fix-IPC-files-override.patch \
           file://0023-Update-docs-491.patch \
           "

PV = "1.0.0"
SRCREV = "b9ea78e195941423c7b797624fc139a0e3c38c66"
BRANCH = "master"

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
inherit pkgconfig autotools features_check

REQUIRED_DISTRO_FEATURES = "polkit"

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF = "--with-bundled-catch --with-bundled-pegtl"

