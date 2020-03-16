#!/bin/sh
project=ratings

echo '-------------build '${project}' start-------------------'
kubectl delete deploy ${project} --force --grace-period=0 || \
sleep 3 && \
mvn  clean package fabric8:deploy -Pkubernetes

echo '------------------------waiting----------------------------------------'
sleep 10
#kubectl get pod |grep ${project} |head -n 1|awk '{print $1}'|xargs kubectl logs -f
kubectl get pod |grep ${project} |head -n 1|awk '{print "kubectl logs -f " $1}'|xargs echo
sleep 2
echo '-------------build '${project}' over-------------------'