#!/bin/bash

CONF_FILE='local.conf'

./bin/flyway -configFiles=local.conf migrate
