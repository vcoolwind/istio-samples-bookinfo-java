kubectl delete deploy ratings --force --grace-period=0 || \
sleep 3 && \
mvn package fabric8:deploy -Pkubernetes && \
sleep 10 && \
kubectl get pod |grep ratings |head -n 1|awk '{print $1}'|xargs kubectl logs -f