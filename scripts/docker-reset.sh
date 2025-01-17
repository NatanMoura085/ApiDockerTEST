#!/bin/bash

# Desmonta os containers e remove volumes
docker-compose down --volumes

# Construa as imagens novamente e inicie os containers
docker-compose up --build
