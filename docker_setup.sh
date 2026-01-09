az acr login --name prporegistry
docker build -t prporegistry.azurecr.io/sort-service:latest .
docker push prporegistry.azurecr.io/sort-service:latest


