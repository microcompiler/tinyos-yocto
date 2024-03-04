SUMMARY = "Automatically setup USB Ethernet at startup"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

INITSCRIPT_NAME = "usb-ethernet.sh"
# INITSCRIPT_PARAMS = "defaults 80"
INITSCRIPT_PARAMS = "start 98 S ."

SRC_URI = " \
	file://${INITSCRIPT_NAME} \
	file://udhcpd-enable.cfg \
    file://udhcpd.conf \
"
inherit update-rc.d

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/${INITSCRIPT_NAME} ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
	install -m 0644 ${WORKDIR}/udhcpd.conf ${D}${sysconfdir}/udhcpd.conf
}