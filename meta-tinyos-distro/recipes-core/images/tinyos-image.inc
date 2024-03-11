#@TYPE: Image
#@NAME: Generic TinyOS Image
#@DESCRIPTION: Image configuration for generic TinyOS

# Core boot image configuration
inherit core-image

# Setup default user and passwords
inherit setup-users

# Specifies the list of locales to install into the image
LINGUAS_EN_GB = "en-gb en-gb.iso-8859-1"
LINGUAS_EN_US = "en-us en-us.iso-8859-1"
IMAGE_LINGUAS = "${LINGUAS_EN_GB} ${LINGUAS_EN_US}"

# Additional application configuration
CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-base \
    packagegroup-core-boot \
"

# Perform some changes to the rootfs
rootfs_postprocess() {

    # image timastamp
    date -u "+%4Y%2m%2d%2H%2M%2S" > ${IMAGE_ROOTFS}/etc/timestamp

    # board type
    echo 'export BOARD='${MACHINE} >> ${IMAGE_ROOTFS}/etc/profile.d/board.sh 

    # clean up unused directories
    rm -fr ${IMAGE_ROOTFS}/usr/games

}

ROOTFS_POSTPROCESS_COMMAND += "rootfs_postprocess;"