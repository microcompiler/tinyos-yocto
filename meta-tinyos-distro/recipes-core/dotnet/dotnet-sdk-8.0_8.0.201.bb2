DESCRIPTION = "Microsoft .NET Core 8.0 SDK including .NET Runtime"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/8.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

#https://download.visualstudio.microsoft.com/download/pr/3bebb4ec-8bb7-4854-b0a2-064bf50805eb/38e6972473f83f11963245ffd940b396/dotnet-sdk-8.0.201-linux-arm64.tar.gz
DOTNET_FETCH_ID = "3bebb4ec-8bb7-4854-b0a2-064bf50805eb/38e6972473f83f11963245ffd940b396"

SRC_URI[sha256sum] = "69c7f6d3f8439ca13d9d81cf5e6f68b12b0cbffb01e7124b8a35f0de2575cd5b"
SRC_URI = " \
    https://download.visualstudio.microsoft.com/download/pr/${DOTNET_FETCH_ID}/dotnet-sdk-${PV}-linux-arm64.tar.gz;unpack=0 \
    file://dotnet-sdk.sh \
"

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
    ${datadir}/dotnet \
"

INSANE_SKIP:${PN} = "file-rdeps staticdev libdir"
INSANE_SKIP:${PN}-dbg = "libdir"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/dotnet
    tar --no-same-owner -xpvzf ${WORKDIR}/dotnet-sdk-${PV}-linux-arm64.tar.gz -C ${D}${datadir}/dotnet
    chmod +x ${D}${datadir}/dotnet/dotnet

    # Symlinks
    install -d ${D}${bindir}
    ln -rs ${D}${datadir}/dotnet/dotnet ${D}${bindir}/dotnet
}

do_install:append() {
    install -d ${D}${sysconfdir}/profile.d
    install -m 644 ${WORKDIR}/dotnet-sdk.sh ${D}${sysconfdir}/profile.d
}