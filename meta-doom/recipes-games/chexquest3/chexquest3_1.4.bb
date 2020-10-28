# Copyright (C) 2020 Joshua Watt <JPEWhacker@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "ChexQuest3"
HOMEPAGE = "http://www.chucktropolis.com/gamers.htm"
LICENSE = "CLOSED"
SECTION = "games"

INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = "http://www.chucktropolis.com/downloads/ChexQuest3.zip;subdir=${BPN}-${PV} \
           file://chexquest3.service \
           "
SRC_URI[sha256sum] = "df2dac71615124672176489ef459d27df04981bd4e04d988e3107ead12cf8541"

#TODO: License restrictions

inherit systemd useradd

do_install() {
    install -d ${D}/usr/share/games/doom/
    install -m 644 ${S}/chex3.wad ${D}/usr/share/games/doom/
    install -d ${D}${systemd_system_unitdir}
    install -m 644 ${WORKDIR}/chexquest3.service ${D}${systemd_system_unitdir}/
}

PACKAGES =+ "${PN}-service"
SYSTEMD_PACKAGES = "${PN}-service"
USERADD_PACKAGES = "${PN}-service"

FILES_${PN} += "/usr/share/games/doom/chex3.wad"
RDEPENDS_${PN} += "zdoom"

FILES_${PN}-service = "${systemd_system_unitdir}"
RDEPENDS_${PN}-service = "weston-init chexquest3"
SYSTEMD_SERVICE_${PN}-service = "chexquest3.service"
USERADD_PARAM_${PN}-service = "--user-group -G video,input chex"
