#!/bin/bash

if [[ ! -f setup.lock ]]; then
  rabbitmqadmin --bash-completion
fi

function setup() {

  declare -A users
  users["chessanalizer"]=qwerty123
  for key in ${!users[@]}; do
    rabbitmqctl add_user ${key} ${users[${key}]}
    rabbitmqctl set_user_tags ${key} application
    rabbitmqctl set_permissions -p / ${key} ".*" ".*" ".*"
  done

  echo "" > setup.lock
}

if [[ ! -f setup.lock ]]; then
  ( rabbitmqctl wait --timeout 60 $RABBITMQ_PID_FILE ; \
  setup ) &
fi

rabbitmq-server $@
