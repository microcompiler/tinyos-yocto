DESCRIPTION = "Microsoft .NET Core 8.0 SDK including .NET Runtime"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/8.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

require dotnet.inc

SRC_FILE:x86-64 ?= "dotnet-sdk-${PV}-linux-x64.tar.gz"
SRC_FETCH_ID:x86-64 = "85bcc525-4e9c-471e-9c1d-96259aa1a315/930833ef34f66fe9ee2643b0ba21621a"
SRC_SHA512SUM:x86-64 = "310cf54f595698435b533931b12f86d49f89d27243cf7c87a5b926e0c676b80e869aa58aaff17b5095536c432f377c67d92bf0ca8941b9d891d4b3879637d488"

inherit native
BBCLASSEXTEND = "native"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${bindir}
    tar --no-same-owner -xvzf ${WORKDIR}/${SRC_FILE} -C ${D}${bindir}
    chmod +x ${D}${bindir}/dotnet
}