#!/bin/bash

mvn clean install -DskipTests=true ../../pom.xml

cp ../../target/validator-1.0-SNAPSHOT.war validator.war

docker build -t frederickfaria/username-validator .

#docker push frederickfaria/username-validator

rm validator.war