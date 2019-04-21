FROM ubuntu:cosmic

RUN apt update && apt install -y maven silversearcher-ag jq emacs ripgrep software-properties-common\
 &&  add-apt-repository -y ppa:openjdk-r/ppa\
 && apt install -y openjdk-8-jdk

WORKDIR /app
COPY pom.xml /app
