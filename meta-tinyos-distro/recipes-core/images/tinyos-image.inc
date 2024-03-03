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
    date "+%m%d%H%M%Y" > ${IMAGE_ROOTFS}/etc/timestamp

    # board type
    echo 'export PATH=$PATH:/usr/local/sbin:/usr/sbin:/sbin' > ${IMAGE_ROOTFS}/etc/profile.d/board.sh
    echo 'export BOARD='${MACHINE} >> ${IMAGE_ROOTFS}/etc/profile.d/board.sh 
}

# ROOTFS_POSTPROCESS_COMMAND += "rootfs_postprocess;"