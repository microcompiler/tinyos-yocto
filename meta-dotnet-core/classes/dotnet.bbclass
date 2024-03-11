DESCRIPTION = "Microsoft .NET Core 8.0 SDK Cross Compiler"
HOMEPAGE = "https://dotnet.microsoft.com/en-us/download/dotnet/8.0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "dotnet-sdk-native"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_PACKAGE_STRIP = "1"

B = "${WORKDIR}/artifacts"

dotnet_do_configure() {
    
    if [ -z ${DOTNET_PROJECT} ] ; then
        bberror "DOTNET_PROJECT must be specified!"
        exit -1
    fi
}

do_compile[network] = "1" 
dotnet_do_compile()  {

    if [ "${TARGET_ARCH}" = "x86_64" ]; then
        BUILD_TARGET="linux-x64"
    elif [ "${TARGET_ARCH}" = "aarch64" ]; then
	    BUILD_TARGET="linux-arm64"
    else
        BUILD_TARGET="linux-arm"
    fi

    dotnet publish \
        ${WORKDIR}/${DOTNET_PROJECT} \
        --configuration Release \
        --runtime ${BUILD_TARGET} \
        --self-contained true \
        --output ${B}      
}

# dotnet publish -c Release -p:PublishTrimmed=true -o ${B} -r ${BUILD_TARGET} --self-contained true ${DOTNET_PROJECT}

FILES:${PN} += "\
    ${datadir}/console \
"

dotnet_do_install() {
    install -d ${D}${datadir}/console
#    install -m ${B} ${D}${datadir}/console
#    cp -rf ${B}/* ${D}${datadir}/console
#    chmod +x ${D}${datadir}/console/Console
}

EXPORT_FUNCTIONS do_configure do_compile do_install