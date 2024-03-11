DESCRIPTION = "Microsoft .NET Core 8.0 SDK including .NET Runtime"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/8.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

include dotnet.inc

SRC_FILE:arm ?= "dotnet-sdk-${PV}-linux-arm.tar.gz"
SRC_FETCH_ID:arm ?= "2344ad1d-ce80-4d98-bf9c-f935576deb39/591ea75057045e2284a7d70d5dd01bc5"
SRC_SHA512SUM:arm ?= "92760c4a4f3bf559daa41b8b87d7f10995aa5ae11783af053d854e8b9e8b042cf6e984bda40490aff051e4463f7cc8ed25d905090e5cee029c81afdb7f8b32c2"

SRC_FILE:aarch64 ?= "dotnet-sdk-${PV}-linux-arm64.tar.gz"
SRC_FETCH_ID:aarch64 ?= "3bebb4ec-8bb7-4854-b0a2-064bf50805eb/38e6972473f83f11963245ffd940b396"
SRC_SHA512SUM:aarch64 ?= "37e230970cfeffdc3873e42595b79ecdf6bfe266a01ace6953725e69a2b64313ce144bf4d4f861130f61f680ead9b4d8a819dd5543c5470c37bbc13d88a78c80"

COMPATIBLE_HOST ?= "(arm|aarch64).*-linux"

SRC_URI += " \
    file://dotnet-sdk.sh \
"

FILES:${PN} += "\
    ${datadir}/dotnet \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/dotnet
    tar --no-same-owner -xpvzf ${WORKDIR}/${SRC_FILE} -C ${D}${datadir}/dotnet
    chmod +x ${D}${datadir}/dotnet/dotnet

    # Symlinks
    install -d ${D}${bindir}
    ln -rs ${D}${datadir}/dotnet/dotnet ${D}${bindir}/dotnet
}

do_install:append() {
    install -d ${D}${sysconfdir}/profile.d
    install -m 644 ${WORKDIR}/dotnet-sdk.sh ${D}${sysconfdir}/profile.d
}









# S = "${WORKDIR}"

# INHIBIT_SYSROOT_STRIP = "1"
# INHIBIT_PACKAGE_STRIP = "1"
# INHIBIT_PACKAGE_DEBUG_SPLIT = "1" 

# INSANE_SKIP:${PN} += "file-rdeps staticdev libdir"
# INSANE_SKIP:${PN}-dbg += "libdir"



# INSANE_SKIP:${PN}:append += "already-stripped"
# INSANE_SKIP:${PN}-dev:append += "already-stripped"
# INSANE_SKIP:${PN}-dbg:append += "already-stripped"


# INSANE_SKIP:${PN}-dev += "already-stripped staticdev libdir file-rdeps"
# INSANE_SKIP:${PN}-dbg += "already-stripped staticdev libdir file-rdeps"

# INSANE_SKIP:${PN} += "already-stripped arch staticdev build-deps file-rdeps ldflags libdir"
# INSANE_SKIP:${PN}-dev += "already-stripped arch staticdev build-deps file-rdeps ldflags libdir"
# INSANE_SKIP:${PN}-dbg += "already-stripped arch staticdev build-deps file-rdeps ldflags libdir"