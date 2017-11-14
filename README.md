# spring-cloud-app-base

A Aplicação foi criada com o intuito de demonstrar de forma pratica um sistema baseado em micro serviços.
Para isso nos optamos em utilizar a steck spring-cloud/ netflix.

O Cenário desenvolvido basicamente foi um CRUD de campanha, CRUD de usuário onde um usuário pode cadastrar e associar campanhas ao mesmo,
Assim foi desenvolvido um serviço para gerenciar as informações das campanhas, um serviço para gerenciar os usuários e 
um serviço para gerenciar a associação entre usuário e campanha.
Como nosso cenário é único, optamos por criar uma base de dados única, mais a arquitetura permite que cada serviço possua sua base própria, de forma independente inclusive de tecnologia.
A divisão entre os serviços foi baseado nos conceitos de domain driven design(DDD), mais especificamente no contexto de cada funcionalidade.

Como subdividimos os serviços em vários partes específicas, optamos por implementar um padrão que consiste em ter um outro serviço de fachada para gerenciar a comunicação entre as partes.

Uma das principais vantagem em desenvolver uma aplicação baseada em micro serviços está na divisão dos pontos de falhas. 
Por exemplo no caso do nosso cenário, se o serviço de campanha falhar, a aplicação continuaria rodando e as outras funcionalidades não sofreriam nenhum impacto. 
Outra questão grande vantagem na arquitetura SOA é a escalabilidade, pois cada serviço possui funções especificas. 
Assim podem facilmente serem acessados e reutilizados por varias aplicações.  

Baseando na arquitetura SOA a netflix criou o projeto "Netflix OSS" e para facilitar o desenvolvimento das aplicações utilizando as bibliotecas do netflix 
realizada a integração Spring Boot + Netflix OSS, surgindo assim o Spring Cloud.

# Detalhamento dos serviços e tecnologias

-> Servidor de configuração (config-server) 

    Serve para gerenciar as configurações base da aplicação.
    O Spring Cloud facilitou bastante a implementação desse serviço. 
    Basicamente toda a mágica é feita pela anotação @EnableConfigServer.
    
    - Dependencias utilizadas
    
        spring-cloud-config-server
        spring-cloud-config-monitor
        spring-cloud-starter-bus-amqp
        spring-cloud-starter-eureka
        spring-boot-starter-actuator
  
  
-> Netflix Eureka (eureka-server) 

    Serve para gerenciar os registros dos micro serviços, ou seja, 
    permitir ques os serviços sejam registrados em tempo de execução.
    
    - Dependencias 
        
        spring-cloud-starter-eureka-server
        spring-boot-starter-actuator
        
-> Netflix Zuul (zuul) 

    É o nosso servidor de borda, funciona como gateway, como cada servidor sobe em uma porta especifica
    sem um servidor de bosda não seria possível termos apenas um ponto de entrada. O Zuul serve como ponto de entrada 
    conecido para os micro serviços da aplicação.
    No nosso cenário o zuul redireciona as requisições para o serviço de orquestrador (orchestrator-service  ) que analisa requisição e encaminha para o serviço especifico.
    
    - Dependencias 
            
        spring-cloud-starter-eureka
        spring-cloud-starter-zuul
        spring-boot-starter-actuator
        spring-cloud-starter-zipkin
            
-> Netflix Hystrix (hystrix-dashboard)

    Usada para controlar as interações entre os serviços, tolerância a latência e lógica de tolerância a falhas.
    Como os pontos de acesso aos serviços são isolados, é possível interromper falhas em cascata fornecendo opções de retorno.
    
    - Dependencias
        
        spring-cloud-starter-eureka
        spring-boot-starter-actuator
        spring-cloud-starter-hystrix-dashboard
        spring-cloud-netflix-turbine
        
-> WebAPP (Client)

    Foi criado uma aplicação web unico de forma independente aos micro serviços.
    Optamos por utilizar o AngularJS Material por possuir uma interface amigavel e responsiva.
    O Client envia as requisições para o serviço do Zuul que direciona para o "orchestrator-service" 
    analisar e encaminar para os serviços responsaveis pela execução da tarefa.
    
-> Bando de dados relacional Postgres 9.6