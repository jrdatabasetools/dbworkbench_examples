#!/bin/sh
docker build -t dbw_ora_xe_21_3:2.5.39 .
docker compose up $@
