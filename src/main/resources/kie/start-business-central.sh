sh /opt/wildfly14/bin/standalone.sh --server-config=standalone-full.xml -Dorg.kie.server.user=kieserver -Dorg.kie.server.pwd=kieserver1! -Dorg.kie.server.controller.user=kieadmin -Dorg.kie.server.controller.pwd=kieserver1! -Dorg.kie.server.location=http://127.0.0.1:8080/kie-server/services/rest/server -Dorg.kie.server.controller=http://127.0.0.1:8080/business-central/rest/controller -Dorg.kie.server.id=wildfly-kieserver