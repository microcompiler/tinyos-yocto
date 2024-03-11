DESCRIPTION = ".NET application debug packagegroup"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
	packagegroup-tinyos-shell \
	packagegroup-tinyos-vscode \
	packagegroup-tinyos-dotnet-sdk \
	packagegroup-tinyos-dotnet-debug \
"

RDEPENDS:${PN} = " \
	packagegroup-tinyos-base \
	packagegroup-tinyos-shell \
	packagegroup-tinyos-vscode \
	packagegroup-tinyos-dotnet-sdk \
	packagegroup-tinyos-dotnet-debug \
"

RDEPENDS:packagegroup-tinyos-shell = "\
	git \
	curl \
	lrzsz \
"

RDEPENDS:packagegroup-tinyos-vscode = "\
	ldd \
	procps \
"

RDEPENDS:packagegroup-tinyos-dotnet-sdk = "\
	vsdbg \
	dotnet-sdk-8.0 \
	dotnet-sdk-8.0-dev \
	dotnet-sdk-8.0-dbg \
"