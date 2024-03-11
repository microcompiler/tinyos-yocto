DESCRIPTION = ".NET application packagegroup"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-tinyos-dotnet \
	packagegroup-tinyos-dotnet-runtime \
"

RDEPENDS:${PN} = " \
	packagegroup-tinyos-base \
	packagegroup-tinyos-dotnet \
	packagegroup-tinyos-dotnet-runtime \
"

RDEPENDS:packagegroup-tinyos-dotnet-runtime = "\
	vsdbg \
	aspnet-runtime"