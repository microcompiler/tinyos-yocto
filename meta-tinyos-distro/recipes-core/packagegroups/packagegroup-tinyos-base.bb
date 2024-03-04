DESCRIPTION = "Base application packagegroup"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-tinyos-base \
	packagegroup-tinyos-core \
"

RDEPENDS:${PN} = " \
	packagegroup-tinyos-base \
	packagegroup-tinyos-core \
"

RDEPENDS:packagegroup-tinyos-core = "\
    icu \
	sudo \
    krb5 \
    opkg \
	glibc \
    libgcc \
	openssl \
	watchdog \
    libstdc++ \
    ca-certificates \
"