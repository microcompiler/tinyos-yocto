SUMMARY = "SysV init scripts"
HOMEPAGE = "https://github.com/fedora-sysv/initscripts"
DESCRIPTION = "Initscripts provide the basic system startup initialization scripts for the system.  These scripts include actions such as filesystem mounting, fsck, RTC manipulation and other actions routinely performed at system startup.  In addition, the scripts are also used during system shutdown to reverse the actions performed at startup."
SECTION = "base"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://functions;beginline=7;endline=7;md5=829e563511c9a1d6d41f17a7a4989d6a"
PR = "r157"

INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = " \
	file://bootmisc \
	file://dmesg \
	file://dmesg.conf \
	file://functions \
	file://halt \
    file://hostname \
	file://mountall \
	file://usbgadget \
	file://sysfs \
	file://populate-volatile \
    file://volatiles \
	file://checkroot \
	file://pseudo-ttys \
    file://devpts \
	file://reboot \
	file://service \
	file://invoke-rc.d \
"

S = "${WORKDIR}"

KERNEL_VERSION = ""

DEPENDS:append = " update-rc.d-native"

RDEPENDS:${PN} = "initd-functions"

PACKAGES =+ "${PN}-functions"

RRECOMMENDS:${PN} = "${PN}-functions"
RPROVIDES:${PN}-functions = "initd-functions"
RCONFLICTS:${PN}-functions = "lsbinitscripts"
FILES:${PN}-functions = "${sysconfdir}/init.d/functions*"


HALTARGS ?= "-d -f"

do_configure() {
	sed -i -e "s:SED_HALTARGS:${HALTARGS}:g" ${WORKDIR}/halt
	sed -i -e "s:SED_HALTARGS:${HALTARGS}:g" ${WORKDIR}/reboot
}

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/rcS.d
	install -d ${D}${sysconfdir}/rc0.d
	install -d ${D}${sysconfdir}/rc1.d
	install -d ${D}${sysconfdir}/rc2.d
	install -d ${D}${sysconfdir}/rc3.d
	install -d ${D}${sysconfdir}/rc4.d
	install -d ${D}${sysconfdir}/rc5.d
	install -d ${D}${sysconfdir}/rc6.d
	install -d ${D}${sysconfdir}/default
	install -d ${D}${sysconfdir}/default/volatiles

	install -m 0644 ${WORKDIR}/functions	${D}${sysconfdir}/init.d
	
	install -m 0755 ${WORKDIR}/bootmisc ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/hostname ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/mountall ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/usbgadget ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/sysfs ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/checkroot ${D}${sysconfdir}/init.d

	install -m 0755 ${WORKDIR}/populate-volatile ${D}${sysconfdir}/init.d
	install -m 0644 ${WORKDIR}/volatiles ${D}${sysconfdir}/default/volatiles/00_core

	install -m 0755 ${WORKDIR}/pseudo-ttys	${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/devpts ${D}${sysconfdir}/default

	install -m 0755 ${WORKDIR}/dmesg ${D}${sysconfdir}/init.d
	install -m 0644 ${WORKDIR}/dmesg.conf ${D}${sysconfdir}/
	
	install -m 0755 ${WORKDIR}/halt ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/reboot ${D}${sysconfdir}/init.d

	install -d -m 0755 ${D}${sbindir}
	install -m 0755 ${WORKDIR}/invoke-rc.d ${D}${sbindir}
	install -m 0755 ${WORKDIR}/service ${D}${sbindir}

	if [ ${@ oe.types.boolean('${VOLATILE_LOG_DIR}') } = True ]; then
		sed -i -e '\@^d root root 0755 /var/volatile/log none$@ a\l root root 0755 /var/log /var/volatile/log' \
			${D}${sysconfdir}/default/volatiles/00_core
	fi

	update-rc.d -r ${D} bootmisc start 36 S .
	update-rc.d -r ${D} hostname start 39 S .
	update-rc.d -r ${D} mountall start 03 S .
	update-rc.d -r ${D} usbgadget start 98 S .
	update-rc.d -r ${D} sysfs start 02 S .
	update-rc.d -r ${D} dmesg start 38 S .
	update-rc.d -r ${D} populate-volatile start 37 S .
	update-rc.d -r ${D} checkroot start 05 S .
	update-rc.d -r ${D} pseudo-ttys start 06 S .
	update-rc.d -r ${D} reboot start 90 6 .
	update-rc.d -r ${D} halt start 90 0 .
}

CONFFILES:${PN} += "${sysconfdir}/init.d/checkroot.sh"