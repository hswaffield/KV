#!/usr/bin/env bash

docker build . -t kv:latest
docker run -v `pwd`/src:/app/src -it --rm kv
