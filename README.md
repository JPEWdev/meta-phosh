# meta-phosh

The easiest way to build is with `kas`. You will need `docker` or `podman` installed.

```shell
$ git clone https://github.com/siemens/kas
$ git clone https://github.com/moto-timo/meta-phosh
$ KAS_BUILD_DIR=build-phosh ./kas/kas-container build ./meta-phosh/kas/raspberrypi4-64.yml
```
