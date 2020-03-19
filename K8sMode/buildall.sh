#!/bin/bash

kubectl delete namespaces  mybookinfo
kubectl apply -f mybookinfo-k8s.yml

projects=( ratings configreload reviews details productpage )

for project in "${projects[@]}"
do
  cd ${project}
  ./build.sh
  cd ..
done
kubectl get deploy,svc,pod -o wide -n mybookinfo
