#! /usr/bin/env python3

import argparse
import sys
import yaml
import textwrap
from pathlib import Path

THIS_DIR = Path(__file__).absolute().parent


def main():
    parser = argparse.ArgumentParser(description="Generate products")
    args = parser.parse_args()

    with (THIS_DIR.parent / "whisk.yaml").open("r") as f:
        whisk = yaml.safe_load(f)

    products = sorted(whisk.get("products", {}).keys())
    modes = sorted(whisk.get("modes", {}).keys())
    versions = sorted(whisk.get("versions", {}).keys()) + ["default"]

    with (THIS_DIR.parent / ".zuul.d" / "50_product_jobs.yaml").open("w") as f:
        for product in products:
            for mode in modes:
                for version in versions:
                    f.write(
                        textwrap.dedent(
                            f"""
                            - job:
                                name: {product}-{mode}-{version}
                                parent: whisk-base
                                vars:
                                  whisk_product: {product}
                                  whisk_version: {version}
                                  whisk_mode: {mode}
                            """
                        )
                    )


if __name__ == "__main__":
    sys.exit(main())
