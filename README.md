* Increase memory
sudo sysctl -w vm.max_map_count=262144

* Swagger Zull
http://localhost:8765/swagger-ui.html#!

* Swagger Each Micro Service
http://localhost:MICRO_SERVICE_PORT_HERE/swagger-ui.html#!

* Eureka
http://localhost:8761/
http://localhost:8761/eureka/apps

* Queue
http://localhost:15672/#

* Wsdl ( Consulta web Service )
http://localhost:8080/ws/consultas.wsdl

* Generate a soap client ( Consulta Micro Service )
wsimport -s src -p br.com.softbank.consulta.soap http://localhost:8080/ws/consultas.wsdl

* Sftp Login ( File Micro Service and Batch Micro Service )
sftp -P 2222 admin@localhost


