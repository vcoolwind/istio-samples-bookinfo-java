kubectl delete deploy productpage --force --grace-period=0 || \
sleep 3 && \
docker build . -t productpage:1.0 && \
kubectl apply -f productpage.yaml  && \
sleep 6 && \
kubectl get pod |grep productpage |awk '{print $1}'|xargs kubectl logs -f 