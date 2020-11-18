#!/bin/bash

version=1.0.0

echo -n 'input the action(clean build bootJar bootBuildImage DistTar):'
read act

for i in 000 001 002 003 004 010 011
do
	if [[ $act == 'bootBuildImage' ]]; then
		./gradlew clean :service-$i:$act --imageName=zhujiejun.com/service-$i:$version -q -x test;
	else
		./gradlew clean :service-$i:$act -q -x test;
	fi
done
