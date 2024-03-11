SUMMARY = "A dotnet debug console-only TinyOS image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

include tinyos-image.inc

# Additional image configuration defaults
EXTRA_IMAGE_FEATURES ?= "\ 
    ssh-server-openssh \
"

# Adds 2GB extra free space to the root filesystem
IMAGE_ROOTFS_EXTRA_SPACE = "2097152"

# Additional application configuration
CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-tinyos-dotnet-debug \
"