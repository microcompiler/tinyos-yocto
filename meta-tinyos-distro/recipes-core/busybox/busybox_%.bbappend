FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://i2c-enable.cfg \
    file://ntpd-enable.cfg \
    file://telnet-disable.cfg \
    file://tftp-disable.cfg \
    file://wget-enable.cfg \
    file://shell.sh \
"

do_install:append() {
    install -d ${D}${sysconfdir}/profile.d
    install -m 644 ${WORKDIR}/shell.sh ${D}${sysconfdir}/profile.d
}