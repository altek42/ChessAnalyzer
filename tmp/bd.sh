#!/usr/bin/env bash

#usage
usage()
{
	echo "Befor run this script, you must configure firebase target with \"firebase target:apply hosting\" command. Look at: https://firebase.google.com/docs/hosting/multisites"
	echo ""	
	echo "Commands:"
	echo "-v, --version: Set application version. Acceptable values: major, minor, patch. Default value is 'patch'. Runs the 'npm version' command."
	echo "-d, --deploy: Skip change version. Runs only deployment command."
	echo "-s, --site: Set target site, which must be in the configuration file 'firebase.json'. If set, runs the 'firebase deploy --only hosting:<param>' command. Default value is 'dev'"
	echo "-h, --help: Shows help."
}

## params
version="patch"
site="dev"
deploy=0

while [ "$1" != "" ]; do
    case $1 in
	-d | --deploy )		deploy=1
				;;
        -v | --version )        shift
                                version=$1
                                ;;
        -s | --site )    	shift
				site=$1
                                ;;
        -h | --help )           usage
                                exit
                                ;;
        * )                     usage
                                exit 1
    esac
    shift
done

##run script
if [ $deploy -eq 0 ]; then
	./version.sh $version
	yarn build
fi

firebase deploy --only hosting:$site


