kubectl delete deploy busyboxplus --force --grace-period=0 || \
docker build . -t busyboxplus:1.0 && \
kubectl apply -f busyboxplus.yaml  && \
sleep 6 && \
kubectl get pod |grep busyboxplus |grep Running |awk '{print $1 " /bin/sh"}'|xargs kubectl exec -it