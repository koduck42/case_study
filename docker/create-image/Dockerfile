#
# Source docker image: 
#
# ubuntu 16.04
#
# Should run with any image. Chose ubuntu 16.04 because
# it has LTS.
#
FROM ubuntu:16.04

#
# Ensure libraries are all up to date
#
RUN apt-get -y update 
RUN apt-get upgrade -y 

#
# Create user docker
#
# NOTE: You might need to adapt gid and uid to the host system.
#
#       But this is still better than running constantly as 'root'
#       in the docker container.
#
RUN addgroup --gid 1000 docker
RUN useradd -m -u 1000 -g 1000 -s /bin/bash docker

#
# Install java and ant
#
#ADD jdk-8u77-linux-x64.tar.gz /opt/
#RUN echo "export JAVA_HOME=/opt/jdk1.8.0_77" >> /home/docker/.bashrc
ADD server-jre-8u45-linux-x64.tar.gz /opt/
RUN echo "export JAVA_HOME=/opt/jdk1.8.0_45" >> /home/docker/.bashrc

ADD apache-ant-1.9.9-bin.tar.gz /opt/
RUN echo "export ANT_HOME=/opt/apache-ant-1.9.9" >> /home/docker/.bashrc

RUN echo 'export PATH=${PATH}:${JAVA_HOME}/bin:${ANT_HOME}/bin' >> /home/docker/.bashrc

