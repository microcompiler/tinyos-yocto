DESCRIPTION = "Microsoft .NET Core Debugger (v17.2.10518.1)"
HOMEPAGE = "https://learn.microsoft.com/en-us/dotnet/iot/debugging?tabs=self-contained&pivots=vscode"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_FILE:arm ?= "vsdbg-linux-arm.tar.gz"
SRC_FETCH_ID:arm ?= "2344ad1d-ce80-4d98-bf9c-f935576deb39/591ea75057045e2284a7d70d5dd01bc5"
SRC_SHA256SUM:arm ?= "2932776ba1c31d89de3dab2cf215536599fd133c2c31b873a6f55e470f090227"

SRC_FILE:aarch64 ?= "vsdbg-linux-arm64.tar.gz"
SRC_FETCH_ID:aarch64 ?= "3bebb4ec-8bb7-4854-b0a2-064bf50805eb/38e6972473f83f11963245ffd940b396"
SRC_SHA256SUM:aarch64 ?= "2932776ba1c31d89de3dab2cf215536599fd133c2c31b873a6f55e470f090227"

SRC_URI[sha256sum] = "${SRC_SHA256SUM}"
SRC_URI = "https://vsdebugger.azureedge.net/vsdbg-${PV}/${SRC_FILE};unpack=0"

COMPATIBLE_HOST ?= "(arm|aarch64).*-linux"

DEPENDS += "\
    zlib \
"

RDEPENDS:${PN} = "\
    glibc \
    icu \
    krb5 \
    libgcc \
    libstdc++ \
    ca-certificates \
    openssl \
"

FILES:${PN} += "\
    ${datadir}/vsdbg \
"

INSANE_SKIP:${PN} = "libdir"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/vsdbg
    tar --no-same-owner -xpvzf ${WORKDIR}/vsdbg-linux-arm64.tar.gz -C ${D}${datadir}/vsdbg
    chmod +x ${D}${datadir}/vsdbg/vsdbg

    # Symlinks
    install -d ${D}${bindir}
    ln -rs ${D}${datadir}/vsdbg/vsdbg ${D}${bindir}/vsdbg
}