#!/bin/bash
set -eu

BASE_DIR=$(readlink -m $(dirname $0)/..)
echo "BASE_DIR: ${BASE_DIR}"
pushd ${BASE_DIR} >> /dev/null

DOCKER_IMAGE=casestudy

DOCKER_OPTS=""
DOCKER_OPTS="${DOCKER_OPTS} -t" # terminate after exit
DOCKER_OPTS="${DOCKER_OPTS} --rm" # cleanup after running
DOCKER_OPTS="${DOCKER_OPTS} -u docker" # user docker
DOCKER_OPTS="${DOCKER_OPTS} -v ${BASE_DIR}:/home/docker/casestudy" # mount basedir as /home/docker/casestudy
DOCKER_OPTS="${DOCKER_OPTS} -w /home/docker/casestudy" # working dir is basedir dir
DOCKER_OPTS="${DOCKER_OPTS} -p 8080:8080" # expose port 8080



DOCKER_CMD="docker run ${DOCKER_OPTS} ${DOCKER_IMAGE} env JAVA_HOME=/opt/jdk1.8.0_45 /bin/bash /opt/apache-ant-1.9.9/bin/ant test"
echo "DOCKER_CMD: ${DOCKER_CMD}"
${DOCKER_CMD}

popd >> /dev/null
