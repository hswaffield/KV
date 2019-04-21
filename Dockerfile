FROM ubuntu:cosmic

RUN apt update && apt install -y netcat maven silversearcher-ag jq emacs ripgrep software-properties-common\
 &&  add-apt-repository -y ppa:openjdk-r/ppa\
 && apt install -y openjdk-8-jdk\
 && sed -i 's/#force_color_prompt=yes/force_color_prompt=yes/' /root/.bashrc

WORKDIR /app
COPY pom.xml /app
