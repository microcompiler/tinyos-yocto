FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://account-disable.cfg \
    file://i2c-enable.cfg \
    file://ifconfig-disable.cfg \
    file://ntpd-enable.cfg \
    file://telnet-disable.cfg \
    file://tftp-disable.cfg \
    file://wget-enable.cfg \
"