yes "" | gcloud container clusters resize  $1 --num-nodes=3 --zone $2
