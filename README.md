# microsserviços de cartões

## Sobre o projeto
Projeto com varios microservicos para fazer validacoes de cartoes de creditos a partir de um cliente, e verificar se eles estar habito a consumir aquele cartao de acordo com sua renda.

Nesse projeto foi utilizado Discovery service eureka para encontrar os serviços, ele vai está se comunicando com o Spring Cloud Gateway que vai fazer as destribuções de requisições. Foi utilizado RabbitMQ como mensageria que é responsavel pela comunicação entre os microsserviços e Docker que foi responsavel pela a arquitetura.

## Exemplo de Funcionamento
![image](https://user-images.githubusercontent.com/83541826/181349438-0c7606a0-8878-43f1-a09b-49edf91d6814.png)

## Fila Mensageria
![image](https://user-images.githubusercontent.com/83541826/181349679-78700a97-e360-461a-867d-5f17c778a850.png)

## RabbitMQ Mensageria
![image](https://user-images.githubusercontent.com/83541826/181349907-990a3294-7feb-4d43-b13b-c1748dfb5e6c.png)

# Tecnologias utilizadas
## Backend
- Java
- Spring Boot
- Eureka
- Docker
- RabbitMQ
- Actuator
- Amqp
- lombok
- Open feign
## Banco de dados
- H2 (banco de testes)

# Como executar o projeto

## Backend
Pré-requisitos: Java 17

```bash
# clonar repositório
git clone https://github.com/GuilhermeMelo01/microservice-cartoes

# executar o projeto
./mvnw spring-boot:run

# Autor

Guilherme Augusto Pereira Melo

https://www.linkedin.com/in/guillherme-melo/



