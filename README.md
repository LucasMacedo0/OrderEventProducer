# OrderEventProducer

## Visão Geral

`OrderEventProducer` é uma API desenvolvida para enviar eventos de pedidos para um tópico Kafka. Esta API é parte de um sistema de gestão de pedidos e é responsável por publicar informações detalhadas sobre novos pedidos para um tópico Kafka, facilitando a comunicação e integração com outros serviços.

## Funcionalidades

- **Publicação de Pedidos**: Recebe informações de novos pedidos e publica mensagens para o tópico Kafka.
- **Configuração Simples**: Configuração fácil através de propriedades no arquivo `application.yml`. e utilização do docker-compose
- **Documentação**: Utiliza o Swagger para documentação e teste das APIs.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para construção da API REST.
- **Apache Kafka**: Plataforma de mensageria para publicação e consumo de eventos.
- **JUnit 5**: Framework para testes.
- **Swagger**: Ferramenta para documentação e teste interativo da API.

## Instalação

### Pré-requisitos

Antes de começar, você precisa ter instalado:

- **Docker** e **Docker Compose** (para configurar o ambiente de Kafka e Zookeeper)
- **Java 17**
- **Maven**

### Clonando o Repositório

Clone o repositório para sua máquina local:

```sh
git clone https://github.com/LucasMacedo0/OrderEventProducer.git
