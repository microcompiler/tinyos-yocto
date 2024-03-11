DESCRIPTION = "Microsoft .NET Core 8.0 SDK including .NET Runtime"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/8.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS:prepend += "dotnet-sdk-native"

RDEPENDS:${PN} = "\
    glibc \
    icu \
    krb5 \
    libgcc \
    libstdc++ \
    ca-certificates \
    openssl \
"

dotnet_do_configure() {

}

dotnet_do_compile()  {

}

dotnet_do_install() {

}

EXPORT_FUNCTIONS do_configure do_compile do_install