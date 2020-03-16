#!/bin/bash

projects=( ratings reviews details productpage )

for project in "${projects[@]}"
do
  cd ${project}
  ./build.sh
  cd ..
done
kubectl get deploy,svc,pod -o wide
