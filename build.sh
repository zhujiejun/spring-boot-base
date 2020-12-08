#!/bin/bash

version='1.0.0'
cleanCache='--cleanCache=true'
builder='--builder=paketobuildpacks/builder:base'
runImage='--runImage=paketobuildpacks/run:base-cnb'
imageName='--imageName=registry.cn-shenzhen.aliyuncs.com/zhujiejun/service-'

#echo "12345678" | sudo -S docker pull paketobuildpacks/run:full-cnb;
#echo "12345678" | sudo -S docker pull paketobuildpacks/builder:full;

echo -n 'input the act(clean build bootJar bootBuildImage DistTar):'; read act;
echo -n 'input the module(000 001 002 003 ...):'; read mod;

if [[ -z $act ]]; then
	./gradlew -q clean;
elif [[ -z $mod ]]; then
	for j in 000 001 002 003 004 005 010 011
	do
		./gradlew -q -x test :service-$j:$act;
	done
else 
	for i in $mod
	do
		if [[ $act == 'bootBuildImage' ]]; then
			./gradlew -q -x test :service-$i:$act $builder $runImage $imageName$i:$version;
		else
			./gradlew -q -x test :service-$i:$act;
		fi
	done
fi
