# Copyright (C) 2020 Joshua Watt <JPEWhacker@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Open Source Doom Engine"
HOMEPAGE = "http://zdoom.org"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"
SECTION = "games"

DEPENDS = "\
    libsdl2 \
    zmusic \
    "


SRC_URI = "https://github.com/coelckers/gzdoom/archive/g${PV}.zip"
SRC_URI[sha256sum] = "ba6942b16bd98e131bcf2db7a9ef15434adf12767fe732dbab27d97a483af32a"

S = "${WORKDIR}/gzdoom-g${PV}"

require zdoom.inc

