#!/bin/bash

VERSION=9.8.1
INSTALL_FOLDER=`pwd`/flyway-$VERSION
HOME_FOLDER=`pwd`/bin
URL=https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/$VERSION/flyway-commandline-$VERSION-linux-x64.tar.gz
JAVA11=$JAVA_HOME_11

wget -qO- $URL | tar xvz

mv $INSTALL_FOLDER $HOME_FOLDER

echo "Remove unused files ..."
cd $HOME_FOLDER
shopt -s extglob
rm -fr drivers/!(postgresql*)
rm -r jars
rm -r jre
rm -r sql
# rm -r conf
rm flyway.cmd

echo "Setup java 11 ..."
sed -i "s|JAVA_CMD=java|JAVA_CMD=$JAVA11/bin/java|g" flyway

echo "Done"