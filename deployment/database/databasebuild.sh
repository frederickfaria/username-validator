#!/bin/bash

docker run --name postgres -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -d postgres

docker exec postgres mkdir /home/postgres

docker cp structure-generator.sql postgres:/home/postgres

docker cp restricted-words.sql postgres:/home/postgres

docker cp usernames.sql postgres:/home/postgres

docker exec postgres chown -R postgres:postgres /home/postgres

sleep 5;

docker exec -u postgres postgres psql -d postgres -a -f /home/postgres/structure-generator.sql

docker exec -u postgres postgres psql -d postgres -a -f /home/postgres/restricted-words.sql

docker exec -u postgres postgres psql -d postgres -a -f /home/postgres/usernames.sql