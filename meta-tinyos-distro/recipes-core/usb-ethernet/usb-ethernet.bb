SUMMARY = "Automatically setup USB Ethernet at startup"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
	file://udhcpd-enable.cfg \
    file://udhcpd.conf \
    file://usb-ethernet.sh \
"

inherit update-rc.d

INITSCRIPT_NAME = "usb-ethernet.sh"
INITSCRIPT_PARAMS = "defaults 80"

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/usb-ethernet.sh ${D}${sysconfdir}/init.d/usb-ethernet.sh
	install -m 0644 ${WORKDIR}/udhcpd.conf ${D}${sysconfdir}/udhcpd.conf
}