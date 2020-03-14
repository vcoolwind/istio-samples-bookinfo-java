> kubectl create sa super-sa

>  kubectl create clusterrolebinding cluster-super-sa --clusterrole=cluster-admin --serviceaccount=default:super-sa 

>  kubectl patch deploy  ratings -p '{"spec":{"template":{"spec":{"serviceAccount":"super-sa"}}}}' --namespace default
