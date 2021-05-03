SUMMARY = "Open Source Doom Engine"
HOMEPAGE = "http://zdoom.org"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://README.md;md5=50fd7ab18ad5cf588cb30e795cb7fffa"
SECTION = "games"

DEPENDS = "\
    libsdl2 \
    bzip2-replacement-native \
    "

PROVIDES = "virtual/zdoom"

SRC_URI = "https://github.com/drfrag666/gzdoom/archive/${PV}.zip"
SRC_URI[sha256sum] = "52e8150a86076f644790834eafef3d2630ac07a2144a45e4b80b846d23005d1b"

S = "${WORKDIR}/gzdoom-${PV}"

require zdoom.inc
