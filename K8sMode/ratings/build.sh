kubectl delete deploy ratings --force --grace-period=0 || \
sleep 3 && \
mvn package fabric8:deploy -Pkubernetes && \
sleep 6 && \
kubectl get pod |grep ratings |awk '{print $1}'|xargs kubectl logs -f