# Copyright (C) 2020 Joshua Watt <JPEWhacker@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Open Source Doom Engine"
HOMEPAGE = "http://zdoom.org"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://README.md;md5=d1ce2cab6944f51f12525731f841a15e"
SECTION = "games"

DEPENDS = "\
    libsdl2 \
    "

PROVIDES = "virtual/zdoom"

SRC_URI = "https://github.com/drfrag666/gzdoom/archive/${PV}.zip"
SRC_URI[sha256sum] = "74d3c2f604b19355250a67d778271eb16255ccccf9a6999f340c9ff737e2da2c"

S = "${WORKDIR}/gzdoom-${PV}"

require zdoom.inc
