#!/bin/bash

passwd=12345678

echo -n 'input the action(start restart stop):'
read act

sudo systemctl $act docker << eof
$passwd
eof

sudo chmod 777 /var/run/docker.sock << eof
$passwd
eof

if [[ $act == 'start' || $act == 'restart' ]]; then
	sudo systemctl stop firewalld.service
elif [[ $act == 'stop' ]]; then
	sudo systemctl restart firewalld.service
fi
