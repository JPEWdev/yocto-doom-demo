SUMMARY = "ChexQuest3"
HOMEPAGE = "http://www.chucktropolis.com/gamers.htm"
LICENSE = "CLOSED"
SECTION = "games"

INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = "http://www.chucktropolis.com/downloads/ChexQuest3.zip;subdir=${BPN}-${PV}"
SRC_URI[sha256sum] = "df2dac71615124672176489ef459d27df04981bd4e04d988e3107ead12cf8541"

#TODO: License restrictions

do_install() {
    install -d ${D}/usr/share/games/doom/
    install -m 644 ${S}/chex3.wad ${D}/usr/share/games/doom/
}

FILES_${PN} += "/usr/share/games/doom/chex3.wad"
RDEPENDS_${PN} += "zdoom"
