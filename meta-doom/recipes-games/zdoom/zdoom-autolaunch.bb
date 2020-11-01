SUMMARY = "ZDoom"
# TODO: Change this
LICENSE = "CLOSED"
SECTION = "games"

INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = "file://zdoom.service"

inherit systemd

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 644 ${WORKDIR}/zdoom.service ${D}${systemd_system_unitdir}/
}

#FILES_${PN} = "${systemd_system_unitdir}"
RDEPENDS_${PN} = "weston-init zdoom"
SYSTEMD_SERVICE_${PN} = "zdoom.service"
