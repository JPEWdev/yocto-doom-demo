PROVIDES = "virtual/zdoom"

STAGING_NATIVE_BUILDDIR = "${TMPDIR}/work-shared/native/${BPN}"

B_class-native = "${STAGING_NATIVE_BUILDDIR}"

inherit cmake

EXTRA_OECMAKE_append_class-target = " -DIMPORT_EXECUTABLES=${STAGING_NATIVE_BUILDDIR}/ImportExecutables.cmake"

CONFIGURE_DEPS = "${BPN}-native:do_compile"
CONFIGURE_DEPS_class-native = ""
do_configure[depends] = "${CONFIGURE_DEPS}"

do_install_append() {
    ln -s ${bindir}/${BPN} ${D}${bindir}/zdoom
}

BBCLASSEXTEND += "native"

FILES_${PN} += "/usr/share/games"
RPROVIDES_${PN}_class-target = "zdoom"

