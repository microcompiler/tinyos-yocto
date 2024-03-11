DESCRIPTION = "Microsoft .NET Core Install Scripts"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "\
    https://dot.net/v1/dotnet-install.sh;sha256sum=5eb82d8578f55cdadcb2edfd35ec649a2c6fc11a682e876b1cd68077badbf794 \
    https://aka.ms/getvsdbgsh;sha256sum=827b0f965072c06afcc2bb7cf50af35952951b0475a0cd1ee83f927de39b2fc7 \
"

RDEPENDS:${PN} = "\
    curl \
    icu \
    libgssapi-krb5 \
    zlib \
"

FILES:${PN} += "\
    ${datadir}/ \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {    
    install -d ${D}${datadir}
    install -m 0770 ${WORKDIR}/dotnet-install.sh ${D}/${datadir}/dotnet-install.sh
    install -m 0770 ${WORKDIR}/getvsdbgsh ${D}/${datadir}/getvsdbg.sh
}