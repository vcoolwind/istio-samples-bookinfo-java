> kubectl create sa super-sa

>  kubectl create clusterrolebinding cluster-super-sa --clusterrole=cluster-admin --serviceaccount=default:super-sa 

>  kubectl patch deploy  ratings -p '{"spec":{"template":{"spec":{"serviceAccount":"super-sa"}}}}' --namespace default

>  kubectl patch deploy  reviews -p '{"spec":{"template":{"spec":{"serviceAccount":"super-sa"}}}}' --namespace default
>


> minikube start --image-mirror-country=cn   --image-repository=registry.cn-hangzhou.aliyuncs.com/google_containers --vm-driver=none  && \
> eval $(minikube docker-env) \
> docker load < /home/stone/soft/backup/busybox.tar  && \
> docker load < /home/stone/soft/backup/jdk-centos-1.5.tar && \
> docker load < /home/stone/soft/backup/python.tar && \
> docker images|grep none|grep -v "grep"|awk '{print " rmi " $3}'|xargs docker  


> #!/bin/bash
> minikube start --image-mirror-country=cn   --image-repository=registry.cn-hangzhou.aliyuncs.com/google_containers --vm-driver=none  
> eval $(minikube docker-env) 

> kubectl delete deploy --all -n default
> kubectl delete pod --all --force --grace-period=0 -n default
> docker images|grep none|grep -v "grep"|awk '{print " rmi " $3 " -f"}'|xargs docker  

> docker load < /home/stone/soft/backup/busybox.tar  
> docker load < /home/stone/soft/backup/jdk-centos-1.5.tar 
> docker load < /home/stone/soft/backup/python.tar


productpage --> details
            --> reviews --> DB
                        --> ratings        -->DB
                        --> configreload 