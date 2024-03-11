DESCRIPTION = "Microsoft .NET Core Debugger (v17.2.10518.1) - Linux x64 Binaries"
HOMEPAGE = "https://learn.microsoft.com/en-us/dotnet/iot/debugging?tabs=self-contained&pivots=vscode"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI[sha256sum] = "2932776ba1c31d89de3dab2cf215536599fd133c2c31b873a6f55e470f090227"
SRC_URI = "https://vsdebugger.azureedge.net/vsdbg-${PV}/vsdbg-linux-arm64.tar.gz;unpack=0"

COMPATIBLE_HOST ?= "(aarch64).*-linux"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = '1' 

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