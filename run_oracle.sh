#!/bin/sh
docker build -t dbw_ora_xe_21_3:25.2.0 .
docker compose up $@
