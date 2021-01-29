yes "" | gcloud container clusters resize  $1 --num-nodes=0 --zone $2
