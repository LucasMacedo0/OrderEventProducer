# OrderEventProducer

## Visão Geral

`OrderEventProducer` é uma API desenvolvida para enviar eventos de pedidos para um tópico Kafka. Esta API é parte de um sistema de gestão de pedidos e é responsável por publicar informações detalhadas sobre novos pedidos para um tópico Kafka, facilitando a comunicação e integração com outros serviços.

## Funcionalidades

- **Publicação de Pedidos**: Recebe informações de novos pedidos e publica mensagens para o tópico Kafka.
- **Documentação**: Utiliza o Swagger para documentação e teste das APIs.
- **Validações de Campos:**: Verifica a validade dos dados fornecidos para garantir que atendam aos critérios necessários antes de enviar a mensagem para o tópico Kafka.
- **Tratamento de Exceções:**: Gerencia e retorna erros de forma estruturada, com mensagens claras e códigos de status apropriados.
## Tecnologias Utilizadas

- **Spring Boot**: Framework para construção da API REST.
- **Apache Kafka**: Plataforma de mensageria para publicação e consumo de eventos.
- **JUnit 5**: Framework para testes.
- **Swagger**: Ferramenta para documentação e teste interativo da API.

## Instalação

### Pré-requisitos

Antes de começar, você precisa ter instalado:

- **Docker** (para configurar o ambiente de Kafka e Zookeeper)
- **Java 17**
- **Maven**
## Configuração do Ambiente
- Clone o repositório para sua máquina local:
```sh
git clone https://github.com/LucasMacedo0/OrderEventProducer.git 
```
## Configuração do Kafka e Zookeeper
- Utilize o Docker Compose para iniciar o Kafka e o Zookeeper
```sh
docker-compose up -d
```
## Executando a Aplicação
```sh
mvn spring-boot:run
```


### Swagger
-  Criação de um Swagger basico
- http://localhost:8080/swagger-ui/index.html
