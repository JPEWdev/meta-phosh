ROOTFS_POSTPROCESS_COMMAND:prepend = "set_systemd_image_locale; "

python set_systemd_image_locale() {
    lang = d.getVar("IMAGE_DEFAULT_LANG")
    if lang:
        locale_conf = d.expand("${IMAGE_ROOTFS}/${sysconfdir}/locale.conf")
        with open(locale_conf, "w") as f:
            f.write("LANG=%s\n" % lang)

    keymap = d.getVar("IMAGE_DEFAULT_KEYMAP")
    if keymap:
        vconsole_conf = d.expand("${IMAGE_ROOTFS}/${sysconfdir}/vconsole.conf")
        with open(vconsole_conf, "w") as f:
            f.write("KEYMAP=%s\n" % keymap)
}
