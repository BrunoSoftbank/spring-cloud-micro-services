# Laboratorios
Micro serviço responsável pelo crud de laboratórios.

Dependencias:

Config Server
https://github.com/BrunoSoftbank/config-server

Service Discovery
https://github.com/BrunoSoftbank/eureka-service-discovery

Oauth 2
https://github.com/BrunoSoftbank/oauth2

Api Gateway
https://github.com/BrunoSoftbank/zull-api-gateway

..........................................................................

Ordem de execução:

1° Executar este comando: sudo sysctl -w vm.max_map_count=262144
2° docker-compose
3° Config Server
4° Eureka
5° Oauth
6° Zull

7° Este micro serviço ( Cada micro serviço tem sua propria base de dados )
Obs: Cada micro serviço sobe com uma porta aleatória gerada pelo Eureka e Ribbon.

..........................................................................

Swagger:
http://localhost:TROCAR_PORTA_AQUI/swagger-ui.html#
Este é o link do swagger onde contem os end points de laboratórios, é necessário trocar a porta, pois a mesma é gerada dinamicamente para poder realizar o load balance.

Swagger Zull:
http://localhost:8765/swagger-ui.html#
Recomendado utilizar este swagger, onde são centralizados todos os end points de todos os micro serviços.
