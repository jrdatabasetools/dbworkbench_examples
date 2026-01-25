#!/bin/sh
podman pod create --name oracle_stack -p 1521:1521
podman run -d --pod oracle_stack --name oracle_db dbw_ora_xe_21_3:26.1.0