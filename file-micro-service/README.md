# Consultas
Micro serviço responsável pelo crud de consultas.
OBS: Esse micro serviço é apenas um orquestrador, não acessa nenhuma base, quem realmente faz o crud de consultas é o consulta_web_service.

Dependencias:

Config Server
https://github.com/BrunoSoftbank/config-server

Service Discovery
https://github.com/BrunoSoftbank/eureka-service-discovery

Oauth 2
https://github.com/BrunoSoftbank/oauth2

Api Gateway
https://github.com/BrunoSoftbank/zull-api-gateway

Consulta Web Service
https://github.com/BrunoSoftbank/consulta-web-service

Micro serviço de usuários
https://github.com/BrunoSoftbank/usuario-micro-service

Micro serviço de exames
https://github.com/BrunoSoftbank/exame-micro-service

Micro serviço de laboratórios
https://github.com/BrunoSoftbank/laboratorio-micro-service

..........................................................................

Ordem de execução:

1° Executar este comando: sudo sysctl -w vm.max_map_count=262144
2° docker-compose
3° Config Server
4° Eureka
5° Oauth
6° Zull
7° consulta_web_service
8° Micro serviço de usuários
9° Micro serviço de exames
10° Micro serviço de Laboraórios

11° Este micro serviço ( Não acessa base de dados, consulta os dados em cada micro serviço e no soap web service )
Obs: Cada micro serviço sobe com uma porta aleatória gerada pelo Eureka e Ribbon.


..........................................................................

Swagger:
http://localhost:TROCAR_PORTA_AQUI/swagger-ui.html#
Este é o link do swagger onde contem os end points que gera os relatórios, é necessário trocar a porta, pois a mesma é gerada dinamicamente para poder realizar o load balance.

Swagger Zull:
http://localhost:8765/swagger-ui.html#
Recomendado utilizar este swagger, onde são centralizados todos os end points de todos os micro serviços.



sftp -P 2222 admin@localhost

