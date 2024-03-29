#!/bin/bash

# Abort script if any command returns error
set -e

BUILD_DIR="build"
IMAGE_NAME="tinyos-debug-image"

declare -a machines=("raspberrypi0-2w" "raspberrypi4" "raspberrypi5")
declare -a recipes=("tinyos-image" "package-index")

install() {
    sudo apt-get update
    sudo apt-get install -y language-pack-en
    sudo apt-get install -y gawk wget git diffstat unzip texinfo gcc build-essential \
        chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils \
        iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev \
        xterm python3-subunit mesa-common-dev zstd liblz4-tool file
}

init() {
  git clone git://git.yoctoproject.org/poky poky -b kirkstone
  git clone git://git.openembedded.org/meta-openembedded poky/meta-openembedded -b kirkstone
  _source
}

bake() {
  _source
  bitbake $IMAGE_NAME
}

bakeall() {
  _source

  for machine in ${machines[*]}; do
    for recipe in ${recipes[*]}; do
      MACHINE="$machine" bitbake "$recipe" $1
    done
  done
}

cleanall() {
  _source

  for machine in ${machines[*]}; do
    for recipe in ${recipes[*]}; do
      MACHINE="$machine" bitbake "$recipe" -c clean
    done
  done
}

busybox() {
  _source
  bitbake -c menuconfig busybox
}

flash() {
  _source
# bitbake package-index
  bitbake bmap-tools-native -caddto_recipe_sysroot
  sudo chmod 666 /dev/sdb

 # umount /dev/sdb

  oe-run-native \
      bmap-tools-native bmaptool copy \
      ./tmp/deploy/images/raspberrypi4/$IMAGE_NAME-raspberrypi4.wic.gz \
      /dev/sdb

  udisksctl power-off -b /dev/sdb
}

update() {
  git fetch --all
}

package() {
  _source
  bitbake package-index
  bitbake -g $IMAGE_NAME 
  cat pn-buildlist | grep -v native | sort
}

clean() {
  _source
  bitbake $IMAGE_NAME -c clean
}

qemu() {
  _source
  bitbake package-index
  runqemu $IMAGE_NAME nographic
}

_source() {
  source ./poky/oe-init-build-env $BUILD_DIR
}

if [[ $# -eq 0 ]] ; then
  bake
fi

case $1 in
  install)
    shift
    install "$@"
    ;;
  init)
    shift
    init "$@"
    ;;
  bake)
    shift
    bake "$@"
    ;;
  busybox)
    shift
    busybox "$@"
    ;;
  flash)
    shift
    flash "$@"
    ;;
  clean)
    shift
    clean "$@"
    ;;
  qemu)
    shift
    qemu "$@"
    ;;
  update)
    shift
    update "$@"
    ;;
  package)
    shift
    package "$@"
    ;;
  bakeall)
    shift
    bakeall "$@"
    ;;
esac