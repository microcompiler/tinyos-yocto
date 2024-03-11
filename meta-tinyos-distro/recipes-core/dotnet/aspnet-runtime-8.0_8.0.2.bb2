DESCRIPTION = "Microsoft ASP.NET Core 8.0 Runtime including .NET Runtime"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/8.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

#https://download.visualstudio.microsoft.com/download/pr/bdfd0216-539e-4dfd-81ea-1b7a77dda929/59a62884bdb8684ef0e4f434eaea0ca3/aspnetcore-runtime-8.0.2-linux-arm64.tar.gz
ASPNET_FETCH_ID = "bdfd0216-539e-4dfd-81ea-1b7a77dda929/59a62884bdb8684ef0e4f434eaea0ca3"

SRC_URI[sha256sum] = "107074b613c48ffcd311670bc34df93f63fce33938ac35770c3db1969da770ef"
SRC_URI = " \
    https://download.visualstudio.microsoft.com/download/pr/${ASPNET_FETCH_ID}/aspnetcore-runtime-${PV}-linux-arm64.tar.gz;unpack=0 \
    file://dotnet-runtime.sh \
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
    tar --no-same-owner -xpvzf ${WORKDIR}/aspnetcore-runtime-${PV}-linux-arm64.tar.gz -C ${D}${datadir}/dotnet
    chmod +x ${D}${datadir}/dotnet/dotnet

    # Symlinks
    install -d ${D}${bindir}
    ln -rs ${D}${datadir}/dotnet/dotnet ${D}${bindir}/dotnet
}

do_install:append() {
    install -d ${D}${sysconfdir}/profile.d
    install -m 644 ${WORKDIR}/dotnet-runtime.sh ${D}${sysconfdir}/profile.d
}