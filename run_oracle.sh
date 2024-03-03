#!/bin/sh
docker build -t dbw_ora_xe_21_3:2.5.37 .
docker compose up $@
