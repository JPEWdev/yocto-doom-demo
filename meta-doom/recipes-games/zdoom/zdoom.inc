PROVIDES = "virtual/zdoom"

STAGING_NATIVE_BUILDDIR = "${TMPDIR}/work-shared/native/${BPN}"

B_class-native = "${STAGING_NATIVE_BUILDDIR}"

inherit cmake

EXTRA_OECMAKE_append_class-target = " -DIMPORT_EXECUTABLES=${STAGING_NATIVE_BUILDDIR}/ImportExecutables.cmake"

CONFIGURE_DEPS = "${BPN}-native:do_compile"
CONFIGURE_DEPS_class-native = ""
do_configure[depends] = "${CONFIGURE_DEPS}"

do_compile_prepend_class-target() {
    # This is pretty gross, but the -native recipe's executable will have the
    # RPATH hard coded to it's original build location, which won't work exist
    # if the recipe is restored from sstate
    export LD_LIBRARY_PATH="${STAGING_LIBDIR_NATIVE}:${STAGING_BASE_LIBDIR_NATIVE}:$LD_LIBRARY_PATH"
}

do_install_append() {
    ln -s ${BPN} ${D}${bindir}/zdoom
}

BBCLASSEXTEND += "native"

FILES_${PN} += "/usr/share/games"
RPROVIDES_${PN}_class-target = "zdoom"

