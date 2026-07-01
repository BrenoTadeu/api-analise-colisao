FROM ubuntu:latest
LABEL authors="brenotadu"

ENTRYPOINT ["top", "-b"]