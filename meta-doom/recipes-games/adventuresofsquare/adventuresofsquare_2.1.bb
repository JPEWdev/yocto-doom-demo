SUMMARY = "A standalone first-person shooter using the ZDoom Engine"
HOMEPAGE = "http://adventuresofsquare.com/"
LICENSE = "CLOSED"
SECTION = "games"

INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = "http://adventuresofsquare.com/downloads/square-ep2-pk3-${PV}.zip"
SRC_URI[sha256sum] = "6cd3167f7d9eeb1ca288997d5a9d7d3a489563e4576ba36a9fc35501c91a0ea5"

#TODO: License restrictions

do_install() {
    install -d ${D}/usr/share/games/doom/
    install -m 644 ${WORKDIR}/square1.pk3 ${D}/usr/share/games/doom/
}

FILES_${PN} += "/usr/share/games/doom/square1.pk3"
RDEPENDS_${PN} += "zdoom"
