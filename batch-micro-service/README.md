# Email
Micro serviço responsável por disparar emails.

Dependencias:

Config Server
https://github.com/BrunoSoftbank/config-server

Service Discovery
https://github.com/BrunoSoftbank/eureka-service-discovery


* Criar uma fila chamada: SOFTBANK-EMAIL_WELLCOME
http://localhost:15672/#

..........................................................................

Ordem de execução:

1° Executar este comando: sudo sysctl -w vm.max_map_count=262144
2° docker-compose
3° Config Server
4° Eureka


5° Este micro serviço ( Não contém base de dados, apenas le as mensagens da fila e dispara por email )
Obs: Cada micro serviço sobe com uma porta aleatória gerada pelo Eureka e Ribbon.

..........................................................................



