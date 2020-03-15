kubectl delete deploy reviews --force --grace-period=0 || \
sleep 3 && \
mvn package fabric8:deploy -Pkubernetes && \
sleep 6 && \
kubectl get pod |grep reviews |head -n 1|awk '{print $1}'|xargs kubectl logs -f