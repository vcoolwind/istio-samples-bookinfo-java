#!/bin/sh
project=parent

echo '-------------build '${project}' start-------------------'
mvn  clean package install
echo '-------------build '${project}' over-------------------'