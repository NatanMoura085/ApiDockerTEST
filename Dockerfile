# Etapa de Build
FROM maven:3.8-openjdk-17 AS build

WORKDIR /app

# Copia o arquivo pom.xml para resolver dependências offline
COPY pom.xml .

# Baixa as dependências Maven
RUN mvn dependency:go-offline

# Copia o código-fonte para a imagem
COPY src /app/src

# Compila e empacota a aplicação
RUN mvn clean package -DskipTests -X

# Etapa de Runtime
FROM openjdk:17-slim

WORKDIR /app

# Copia o JAR da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Copia o Elastic APM Agent para a imagem
# ADD https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/1.52.1/elastic-apm-agent-1.52.1.jar /app/elastic-apm-agent-1.52.1.jar

# Expõe a porta da aplicação
EXPOSE 8081

# Comando para iniciar a aplicação com o Elastic APM Agent
# CMD ["java", "-javaagent:/app/elastic-apm-agent-1.52.1.jar", \
#     "-Delastic.apm.service_name=my-service-name", \
#     "-Delastic.apm.server_url=http://apm-server:8200", \
#     "-Delastic.apm.environment=production", \
#     "-Delastic.apm.application_packages=com.docker.ApiDockerTEST", \
#     "-jar", "/app/app.jar"]


CMD ["java", "-jar","app.jar"]