DESCRIPTION = "Microsoft .NET Core 8.0 SDK including .NET Runtime"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/8.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

include dotnet.inc

SRC_FILE:arm ?= "aspnetcore-runtime-${PV}-linux-arm64.tar.gz"
SRC_FETCH_ID:arm ?= "272dbea2-057e-4032-9857-7e00b476ceec/3c472df94b1c3f5e0d009cbccc9256a6"
SRC_SHA512SUM:arm ?= "427e60fb1ed636ed6c8b8be22b125019f0cf8cad3ab07bfc362d56a05f12206eaf245c8003b5a1c1b342c65d703f3f401982aaab92f86de5d73650f742a2fdba"

SRC_FILE:aarch64 ?= "aspnetcore-runtime-${PV}-linux-arm64.tar.gz"
SRC_FETCH_ID:aarch64 ?= "bdfd0216-539e-4dfd-81ea-1b7a77dda929/59a62884bdb8684ef0e4f434eaea0ca3"
SRC_SHA512SUM:aarch64 ?= "9e5733a0d40705df17a1c96025783fd2544ad344ac98525f9d11947ea6ef632a23b0d2bf536314e4aeda8ae9c0f65b8f8feee184e1a1aabfda30059f59b1b9a6"

COMPATIBLE_HOST ?= "(arm|aarch64).*-linux"

SRC_URI += " \
    file://dotnet-runtime.sh \
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
    install -m 644 ${WORKDIR}/dotnet-runtime.sh ${D}${sysconfdir}/profile.d
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