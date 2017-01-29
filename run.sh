#!/bin/bash
#mkdir log4j_config

#cd log4j_config
#echo "$url"

wget -O /tmp/code/demo1/log4j.properties "$url"

#/tmp/code/demo1

java -jar -Dlog4j.configuration=file:/tmp/code/demo1/log4j.properties log-demo-ms2.jar
