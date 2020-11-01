SUMMARY = "Doom On Wayland"

IMAGE_FEATURES += "splash ssh-server-dropbear hwcodecs"

LICENSE = "MIT"

inherit core-image features_check

REQUIRED_DISTRO_FEATURES = "wayland"

CORE_IMAGE_BASE_INSTALL += "\
    kernel-modules \
    zdoom-autolaunch \
    weston \
    weston-init \
    "

QB_MEM = "-m 512"
