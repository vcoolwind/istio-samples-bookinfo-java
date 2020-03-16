#!/bin/sh
project=busyboxplus

echo '-------------build '${project}' start-------------------'
kubectl delete deploy ${project} --force --grace-period=0 || \
sleep 3 && \
docker build . -t ${project}:1.0 && \
kubectl apply -f ${project}.yaml

echo '----------------------------------------------------------------'
sleep 6
kubectl get pod |grep ${project} |head -n 1 |grep Running |awk '{print "kubectl exec -it " $1 " /bin/sh"}' |xargs echo
sleep 2
echo '---------------------------------------------build '${project}' over-------------------'