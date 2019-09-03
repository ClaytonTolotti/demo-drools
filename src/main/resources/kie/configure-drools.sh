#!/bin/bash

ZIP=zip
WAR=war
#VERSION=7220
VERSION_WILD_HOMOL=wildfly14
VERSION_WILDFLY=14.0.0.Final
VERSION_DROOLS=7.19.0.Final
PACKAGE_WILDFLY=wildfly-$VERSION_WILDFLY
#PACKAGE_DROOLS_WB=kie-drools-wb-$VERSION_DROOLS-$VERSION_WILD_HOMOL
PACKAGE_DROOLS_WB=business-central-$VERSION_DROOLS-$VERSION_WILD_HOMOL
PACKAGE_DROOLS_KIE=kie-server-distribution-$VERSION_DROOLS
JAVA_HOME="/usr/java/jdk1.8.0_201-amd64/jre"
JBOSS_HOME="/opt/$VERSION_WILD_HOMOL"
STANDALONE_CONF=standalone.conf
STANDALONE_XML=standalone.xml
STANDALONE_FULL_XML=standalone-full.xml

#if [ $VERSION >=  ]; then
#    PACKAGE_DROOLS_WB=kie-drools-wb-$VERSION_DROOLS-$VERSION_WILD_HOMOL
#else	
#    PACKAGE_DROOLS_WB=business-central-$VERSION_DROOLS-$VERSION_WILD_HOMOL
#fi
#

echo "downloading widfly $VERSION_WILDFLY"
wget -c https://download.jboss.org/wildfly/$VERSION_WILDFLY/$PACKAGE_WILDFLY.$ZIP

echo "downloading kie-server $VERSION_DROOLS"
wget -c https://download.jboss.org/drools/release/$VERSION_DROOLS/$PACKAGE_DROOLS_KIE.$ZIP

echo "downloading drools-workbench $VERSION_DROOLS"
wget -c http://download.jboss.org/drools/release/$VERSION_DROOLS/$PACKAGE_DROOLS_WB.$WAR

echo "unzip packages"
unzip $PACKAGE_WILDFLY.$ZIP
unzip $PACKAGE_DROOLS_KIE.$ZIP

echo "coping..."
cp -R $PACKAGE_WILDFLY $JBOSS_HOME

echo "JAVA_HOME=\"$JAVA_HOME\"" >> $JBOSS_HOME/bin/$STANDALONE_CONF
echo "JBOSS_HOME=\"$JBOSS_HOME\"" >> $JBOSS_HOME/bin/$STANDALONE_CONF

sed -i 's/127.0.0.1/0.0.0.0/g' $JBOSS_HOME/standalone/configuration/$STANDALONE_XML

sh $JBOSS_HOME/bin/add-user.sh manager manager

sed -i 's/Xms64m/Xms256m/g' $JBOSS_HOME/bin/$STANDALONE_CONF
sed -i 's/Xmx512m/Xmx4096m/g' $JBOSS_HOME/bin/$STANDALONE_CONF
sed -i 's/MetaspaceSize=96M/MetaspaceSize=1024M/g' $JBOSS_HOME/bin/$STANDALONE_CONF
sed -i 's/MaxMetaspaceSize=256m/MaxMetaspaceSize=4096m/g' $JBOSS_HOME/bin/$STANDALONE_CONF

cp kie-server-$VERSION_DROOLS-ee7.$WAR $JBOSS_HOME/standalone/deployments/kie-server.$WAR

sh $JBOSS_HOME/bin/add-user.sh -a -u kieserver -p kieserver1! -ro admin,kie-server
sh $JBOSS_HOME/bin/add-user.sh -a -u kieadmin -p kieserver1! -ro admin,kie-server

sed -i 's/127.0.0.1/0.0.0.0/g' $JBOSS_HOME/standalone/configuration/$STANDALONE_FULL_XML

#sed -i 's/SERVER_OPTS=\"\"/SERVER_OPTS=\"--server-config=standalone-full.xml\"/g' $JBOSS_HOME/bin/standalone.sh

cp $PACKAGE_DROOLS_WB.$WAR $JBOSS_HOME/standalone/deployments/business-central.$WAR

#sed -i 's/SERVER_OPTS=\"\"/\KIE_SERVER_USER=-Dorg.kie.server.user=kieserver \
#KIE_SERVER_PWD=-Dorg.kie.server.pwd=kieserver1! \
#KIE_CONTROLLER_USER=-Dorg.kie.server.controller.user=kieserver \
#KIE_CONTROLLER_PWD=-Dorg.kie.server.controller.pwd=kieserver1! \
#KIE_SERVER=-Dorg.kie.server.location=http:\/\/127.0.0.1:8080\/kie-server\/services\/rest\/server \
#KIE_CONTROLLER=-Dorg.kie.server.controller=http:\/\/127.0.0.1:8080\/kie-drools-wb\/rest\/controller \
#KIE_SERVER_ID=-Dorg.kie.server.id=wildfly-kieserve \
#SERVER_OPTS=\"--server-config=standalone-full.xml\" $KIE_SERVER_USER $KIE_SERVER_PWD $KIE_CONTROLLER_USER $KIE_CONTROLLER_PWD $KIE_SERVER $KIE_CONTROLLER $KIE_SERVER_ID\
#/g' $JBOSS_HOME/bin/standalone.sh
