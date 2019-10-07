#!/bin/bash

if [ "$1" = "--hard" ] ; then
  echo "------------------------";
  echo 'Hard restart activated';

  cd dao-service

  mvn clean install -Dmaven.test.skip=true

  if [[ "$?" -ne 0 ]] ; then
    echo "------------------------";
    echo 'Compilation failed on dao-service - restart stopped'; exit $rc
  fi

  cd ..
fi

echo "------------------------";
cd rest-api
mvn clean install

if [[ "$?" -ne 0 ]] ; then
  echo "\n\n ------------------------";
  echo 'Compilation failed rest-api - restart stopped'; exit $rc
fi

echo "------------------------";
echo " Removing old docker image and container";

docker rmi tomcat:latest -f
docker rm projet_tomcat_1 -f

echo "------------------------";
echo "Starting docker container";

docker-compose up