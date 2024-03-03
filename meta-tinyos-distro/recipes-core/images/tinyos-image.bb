SUMMARY = "A minimal console-only TinyOS image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

include tinyos-image.inc

# Adds 1GB extra free space to the root filesystem
IMAGE_ROOTFS_EXTRA_SPACE = "1048576"