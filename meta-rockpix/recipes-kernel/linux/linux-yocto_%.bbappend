FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_rock-pi-x = " file://rock-pi-x.cfg"

COMPATIBLE_MACHINE_append_rock-pi-x = "|rock-pi-x"
