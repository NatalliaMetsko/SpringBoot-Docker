#!/bin/bash
sudo docker rm -f postgres || true
sudo docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=12345 -v /mnt_pg_data:/var/lib/postgresql/data --name postgres postgres
