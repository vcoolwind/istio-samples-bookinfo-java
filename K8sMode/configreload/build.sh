#!/bin/sh
project=configreload

echo '-------------build '${project}' start-------------------'
kubectl delete deploy ${project} -n otherns --force --grace-period=0 || \
sleep 3 && \
mvn  clean package fabric8:deploy -Pkubernetes
#mvn  clean package fabric8:deploy -Pkubernetes -Dfabric8.skipHealthCheck=true
# -Dfabric8.skipHealthCheck=true ignore heath now

kubectl apply -f rbac.yml
echo '------------------------waiting----------------------------------------'
sleep 10
#kubectl get pod |grep ${project} |head -n 1|awk '{print $1}'|xargs kubectl logs -f
kubectl get pod  -n otherns |grep ${project} |head -n 1|awk '{print "kubectl logs -n otherns -f " $1}'|xargs echo
sleep 2
echo '-------------build '${project}' over-------------------'