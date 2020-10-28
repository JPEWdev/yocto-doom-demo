# Copyright (C) 2020 Joshua Watt <JPEWhacker@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "GZDoom Music Library"
HOMEPAGE = "https://github.com/coelckers/ZMusic"
SECTION = "games"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://licenses/gplv3.txt;md5=1ebbd3e34237af26da5dc08a4e440464"

DEPENDS = "alsa-lib zlib"

SRC_URI = "git://github.com/coelckers/ZMusic.git;protocol=http;branch=${BRANCH}"
SRCREV = "bff02053bea30bd789e45f60b90db3ffc69c8cc8"
BRANCH = "master"

inherit cmake

S = "${WORKDIR}/git"

BBCLASSEXTEND += "native"
