# Relatorios
Micro serviço responsável por gerar relatórios.

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

7° Este micro serviço ( Não acessa base de dados, consulta os dados em cada micro serviço )
Obs: Cada micro serviço sobe com uma porta aleatória gerada pelo Eureka e Ribbon.

OBS: Se um dos micro serviços abaixo não estiver executando, e for solicitado um relatório deles, o Hystrix irá executar como circuit Braker e irá imprimir um relatório vazio.

8° Micro serviço de Usuários - https://github.com/BrunoSoftbank/usuario-micro-service
9° Micro serviço de Exames - https://github.com/BrunoSoftbank/exame-micro-service
10° Micro serviço de Laboratórios - https://github.com/BrunoSoftbank/laboratorio-micro-service
11° Micro serviço de Consultas - https://github.com/BrunoSoftbank/consulta-micro-service

..........................................................................

Swagger:
http://localhost:TROCAR_PORTA_AQUI/swagger-ui.html#
Este é o link do swagger onde contem os end points que gera os relatórios, é necessário trocar a porta, pois a mesma é gerada dinamicamente para poder realizar o load balance.

Swagger Zull:
http://localhost:8765/swagger-ui.html#
Recomendado utilizar este swagger, onde são centralizados todos os end points de todos os micro serviços.


OBS: Especificamente nesse serviço, quando a requisição é feita pelo swagger do Zull é gerado um relatório em branco.
Porém se a requisição foir feita pelo swagger do próprio serviço, o relatório é gerado corretamente.
Se a requisição for feita através do zull, mas sem o swagger, por exemplo pelo browser ou pelo postmam o relatório é gerado corretamente.
