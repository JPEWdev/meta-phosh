# Phosh Shell Layer
[![Build is Gated](https://zuul-ci.org/gated.svg)](https://zuul.wattissoftware.com/t/wattissoftware-zuul/buildsets?project=JPEWdev%2Fmeta-phosh)
[![Build Status](https://zuul.wattissoftware.com/api/tenant/wattissoftware-zuul/badge?project=JPEWdev/meta-phosh&pipeline=periodic-daily&branch=master)](https://zuul.wattissoftware.com/t/wattissoftware-zuul/buildsets?JPEWdev/meta-phosh&pipeline=periodic-daily&branch=master)

Build [Phosh Shell](https://puri.sm/projects/phosh/) for OpenEmbedded

## Downloading

Prebuilt images for Raspberry Pi 4 and QEMU (built daily) can be downloaded
[here](http://downloads.wattissoftware.com/publish/)

## Building
The easiest way to build is with `kas`. You will need `docker` or `podman` installed.

```shell
$ git clone https://github.com/siemens/kas
$ git clone https://github.com/moto-timo/meta-phosh
$ KAS_BUILD_DIR=build-phosh ./kas/kas-container build ./meta-phosh/kas/raspberrypi4-64.yml
```

The default PIN is `1234` as set in `kas/base.yml` (for the `phosh` user).
