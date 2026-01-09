az acr login --name prporegistry
docker build -t prporegistry.azurecr.io/sortdrivers:latest .
docker push prporegistry.azurecr.io/sortdrivers:latest


