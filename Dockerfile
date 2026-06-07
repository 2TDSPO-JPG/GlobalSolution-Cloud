FROM ubuntu:latest
LABEL authors="pedro"

ENTRYPOINT ["top", "-b"]