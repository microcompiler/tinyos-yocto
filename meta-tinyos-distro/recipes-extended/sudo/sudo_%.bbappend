FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://sudoers"

do_install:append () {
        install -m 0644 ${WORKDIR}/sudoers ${D}/${sysconfdir}/sudoers.d/allow-sudo-group
}