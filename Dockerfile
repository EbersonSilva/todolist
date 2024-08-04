# Configuração para fazer o Deploy do projeto no Render.com

# Imagem criada
FROM ubuntu:latest AS build 

# Execução de comando
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

# Copia do projeto para o render
COPY . .

# Execução para instalação do Maven
RUN apt-get install maven -y
RUN mvn clean install
FROM openjdk:17-jdk-slim

# Exposição da porta 8080
EXPOSE 8080

COPY --from=build /target/todolist-1.0.0.jar app.jar

# Execução da aplicação. exemplo: "java -jar target/todolist-1.0.0.jar"
ENTRYPOINT [ "java", "-jar", "app.jar" ]