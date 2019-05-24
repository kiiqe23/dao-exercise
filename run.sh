#!/bin/bash

#docker run -it -p 8080:8080 -p 9990:9990 -v `pwd`/classes:/opt/jboss/wildfly/standalone/deployments/ jboss/wildfly /opt/jboss/wildfly/bin/standalone.sh -bmanagement 0.0.0.0 bash
#docker run -p 8080:8080 -p 9990:9990 -it jboss/wildfly /opt/jboss/wildfly/bin/standalone.sh -bmanagement 0.0.0.0 bash
#docker run -p 8080:8080 -p 9990:9990  -v `pwd`/classes:/opt/jboss/wildfly/standalone/deployments/ -it jboss/wildfly /opt/jboss/wildfly/bin/standalone.sh -bmanagement 0.0.0.0 bash    
#docker run -p 8080:8080 -p 9990:9990  -v `pwd`/classes/*:/opt/jboss/wildfly/standalone/deployments/ -it jboss/wildfly /opt/jboss/wildfly/bin/standalone.sh -bmanagement 0.0.0.0 bash    

docker image rm wildfly-app

docker build --tag=wildfly-app .

docker run -it -p 8080:8080 -p 9990:9990 wildfly-app

