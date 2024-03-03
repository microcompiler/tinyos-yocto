#@TYPE: Machine
#@NAME: Generic Raspberry Pi
#@DESCRIPTION: Machine configuration for generic Raspberry Pi

SOC_FAMILY = "rpi"
include conf/machine/include/soc-family.inc

PREFERRED_VERSION_linux-rpi ??= "6.1.%"
PREFERRED_PROVIDER_virtual/kernel ??= "linux-rpi"
PREFERRED_PROVIDER_virtual/firmware ??= "firmware-rpi"

KERNEL_IMAGETYPE = "Image"
EXTRA_IMAGEDEPENDS += "firmware-rpi"

WKS_FILE ?= "raspberrypi.wks"
IMAGE_FSTYPES  ?= "tar.gz wic.gz wic.bmap"

SERIAL_CONSOLES_CHECK ??= "${SERIAL_CONSOLES}"

MACHINE_FEATURES = " \
    usbgadget \
    usbhost \
    vfat \
    ext2 \
    alsa \
"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += " \
    kernel-modules \
"

KERNEL_DEVICETREE_OVERLAYS ?= " \
    overlays/overlay_map.dtb \
    overlays/disable-bt.dtbo \
    overlays/disable-wifi.dtbo \
"

KERNEL_DEVICETREE ?= " \
    ${KERNEL_DEVICETREE_OVERLAYS} \
"

WKS_FILE_DEPENDS ?= " \
    virtual/bootloader \
    e2fsprogs-native \
    bmap-tools-native \
"

def make_dtb_boot_files(d):

    alldtbs = d.getVar('KERNEL_DEVICETREE')

    if not alldtbs:
        return ''

    def transform(dtb):
        base = os.path.basename(dtb)
        if dtb.endswith('dtbo') or base == 'overlay_map.dtb':
            return '{};{}'.format(base, dtb)
        elif dtb.endswith('dtb'):
            return base

    return ' '.join([transform(dtb) for dtb in alldtbs.split(' ') if dtb])

IMAGE_BOOT_FILES ?= " \
    boot/* \
    ${@make_dtb_boot_files(d)} \
    ${KERNEL_IMAGETYPE};${KERNEL_IMAGE} \
"

do_image_wic[depends] += " \
    virtual/kernel:do_deploy \
    virtual/bootloader:do_deploy \
"

do_image_wic[recrdeps] = "do_build"

RDEPENDS:${KERNEL_PACKAGE_NAME}-base = ""














# RPI_KERNEL_DEVICETREE ?= " \
#     bcm2712-rpi-5-b.dtb \
#     bcm2711-rpi-4-b.dtb \
#     bcm2710-rpi-zero-2w.dtb \
# "

# KERNEL_DEVICETREE = " \
#     bcm2712-rpi-5-b.dtb \
#     bcm2711-rpi-4-b.dtb \
#     bcm2710-rpi-zero-2-w.dtb \
# "

# IMAGE_BOOT_FILES = " \
#     virtual/firmware/* \
#     ${IMGDEPLOYDIR}/${IMAGE_BASENAME}-${MACHINE}.tar.bz2;rootfs.img \
# "


# SERIAL_CONSOLES ?= "115200;ttyS0"

# MACHINE_FEATURES += "pci"

# MACHINE_ESSENTIAL_EXTRA_RDEPENDS += " \
#     kernel-modules \
# "
# RRECOMMENDS:${KERNEL_PACKAGE_NAME}-base = ""

# KERNEL_DEVICETREE = " \
#     bcm2712-rpi-5-b.dtb \
#     bcm2711-rpi-4-b.dtb \
#     bcm2710-rpi-zero-2-w.dtb \
# "

# WKS_FILE_DEPENDS ?= " \
#     virtual/kernel \
#     virtual/firmware \
#     e2fsprogs-native \
#     bmap-tools-native \
# "

# IMAGE_BOOT_FILES = " \
#     virtual/firmware/* \
#     ${IMGDEPLOYDIR}/${IMAGE_BASENAME}-${MACHINE}.tar.bz2;rootfs.img \
# "

# KERNEL_IMAGETYPE = "Image"
# KERNEL_BOOTCMD ?= "booti"

# WKS_FILE ?= "raspberrypi.wks"
# IMAGE_FSTYPES  ?= "tar.bz2 wic.gz wic.bmap"

# SDIMG_ROOTFS = "${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.${SDIMG_ROOTFS_TYPE}"

# RDEPENDS:${KERNEL_PACKAGE_NAME}-base = "${KERNEL_PACKAGE_NAME}-image"

# FILES:${KERNEL_PACKAGE_NAME}-image += "/boot/Image*"

# RRECOMMENDS:${KERNEL_PACKAGE_NAME}-base = ""

# RASPBERRYPI_v7_KERNEL_PACKAGE_NAME = "kernel-v7"

# RDEPENDS:${KERNEL_PACKAGE_NAME}:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}"
# RDEPENDS:${KERNEL_PACKAGE_NAME}-base:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-base"
# RDEPENDS:${KERNEL_PACKAGE_NAME}-image:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-image"
# RDEPENDS:${KERNEL_PACKAGE_NAME}-dev:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-dev"
# RDEPENDS:${KERNEL_PACKAGE_NAME}-vmlinux:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-vmlinux"
# RDEPENDS:${KERNEL_PACKAGE_NAME}-modules:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-modules"
# RDEPENDS:${KERNEL_PACKAGE_NAME}-dbg:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-dbg"

# DEPLOYDEP = ""
# DEPLOYDEP:raspberrypi-armv7 = "${RASPBERRYPI_v7_KERNEL}:do_deploy"
# do_deploy[depends] += "${DEPLOYDEP}"