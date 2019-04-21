#!/usr/bin/env bash

docker build . -t kv:latest
docker run -v `pwd`/src:/app/src -p 8080:8080 -it --rm kv
