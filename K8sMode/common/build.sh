#!/bin/sh
project=common

echo '-------------build '${project}' start-------------------'
mvn  clean package install
echo '-------------build '${project}' over-------------------'