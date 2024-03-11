
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

DEPENDS += "\
    zlib \
"

RDEPENDS:${PN} = "\
    glibc \
    icu \
    krb5 \
    libgcc \
    libstdc++ \
    ca-certificates \
    openssl \
"

SRC_URI[sha512sum] = "${SRC_SHA512SUM}"
SRC_URI = " \
    https://download.visualstudio.microsoft.com/download/pr/${SRC_FETCH_ID}/${SRC_FILE};unpack=0 \
"

INSANE_SKIP:${PN} += "already-stripped staticdev libdir file-rdeps"