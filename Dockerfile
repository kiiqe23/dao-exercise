FROM jboss/wildfly

ADD classes/* /opt/jboss/wildfly/standalone/deployments/

RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365 --silent

USER root

RUN yum install postgresql -y
RUN passwd --delete root

USER jboss

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
