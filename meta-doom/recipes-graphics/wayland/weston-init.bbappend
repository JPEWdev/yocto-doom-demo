FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://10-start-on-demand.conf"

do_install_append() {
    install -Dm 644 ${WORKDIR}/10-start-on-demand.conf \
        ${D}${systemd_unitdir}/system/weston.service.d/10-start-on-demand.conf
}


FILES_${PN} += "${systemd_unitdir}/system/weston.service.d/10-start-on-demand.conf"

