#!/bin/bash

VERSION=2.0.2
INSTALL_FOLDER=`pwd`/flyway-$VERSION
HOME_FOLDER=`pwd`/bin
ROOT_FOLDER="neo4j-migrations-$VERSION-linux-x86_64"
URL=https://github.com/michael-simons/neo4j-migrations/releases/download/$VERSION/$ROOT_FOLDER.zip

wget -q -O file.zip $URL
unzip -q file.zip
mv $ROOT_FOLDER/bin/neo4j-migrations .

rm -rf $ROOT_FOLDER
rm file.zip

./neo4j-migrations -V
mkdir -p migrations
MF=`pwd`/migrations

MP=.migrations.properties
echo "autocrlf=false
transaction-mode=PER_MIGRATION
address=bolt\://localhost\:7687
validate-on-migrate=true
username=neo4j
password=qwerty123
location=file:$MF" > $MP


