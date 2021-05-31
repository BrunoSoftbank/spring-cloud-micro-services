Url's de acesso aos arquivos de configuração

http://localhost:8888/eureka/default
http://localhost:8888/zull/default
http://localhost:8888/oauth2/default
http://localhost:8888/usuarios/default
http://localhost:8888/email/default
http://localhost:8888/exames/default
http://localhost:8888/laboratorios/default
http://localhost:8888/relatorios/default


Spring Cloud Bus:
Responsável por atualizar automaticamente as aplicações que estejam executando e que tenham sofrido alguma alteração no application.yml, sem a necessidade de ter que reiniciar o micro serviço.

POST
http://localhost:8888/actuator/bus-refresh

A collection com este end point esta na raiz do projeto.
