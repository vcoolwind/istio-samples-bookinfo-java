#!/bin/sh
project=productpage

echo '-------------build '${project}' start-------------------'
kubectl delete deploy ${project} -n mybookinfo --force --grace-period=0
sleep 3
docker build . -t ${project}:1.0.0
kubectl apply -f ${project}.yaml

echo '---------------------waiting-------------------------------------------'
sleep 10
#kubectl get pod |grep ${project} |head -n 1|awk '{print $1}'|xargs kubectl logs -f
kubectl get pod -n mybookinfo |grep ${project} |head -n 1|awk '{print "kubectl logs -f " $1}'|xargs echo
sleep 2
echo '-------------build '${project}' over-------------------'
