#!/bin/bash

docker run -d --rm --name postgres \
    --publish 5432:5432 \
    --env POSTGRES_USER=postgres \
    --env POSTGRES_PASSWORD=postgres \
    --env POSTGRES_DB=parking-control \
    postgres