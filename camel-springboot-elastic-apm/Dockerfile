## build stage
FROM maven:3-jdk-11-slim as build-stage
WORKDIR /usr/src/mymaven

# Install dependencies on this layer, so in next rebuitls if pom haven't changet it will be faster
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B

# Copy the source code and build
COPY ./src ./src
RUN mvn clean package -DskipITs -DskipTests

## production stage
FROM adoptopenjdk/openjdk11-openj9:alpine-jre

RUN mkdir /my-app
## If we like to attach apm agent without using apm-agent-attach in pom.xml uncomment this line
# ADD  https://repo1.maven.org/maven2/co/elastic/apm/elastic-apm-agent/1.15.0/elastic-apm-agent-1.15.0.jar /my-app/elastic-apm-agent.jar
COPY ./src /my-app/src
COPY ./target/*.jar /my-app/app.jar
WORKDIR /my-app
ENTRYPOINT java -jar /my-app/app.jar

# # The app uses elasticapm.properties, but this is an example of definit O.S. Environment in a dockerfile
# # Then you can override it in docker-compose or docker run command
# ENV ELASTIC_APM_SERVICE_NAME=camel-springboot-elastic-apm
# ENV ELASTIC_APM_APPLICATION_PACKAGES=org.example
# ENV ELASTIC_APM_SERVER_URLS=http://elastic-apm-server:8200
# ENTRYPOINT java -javaagent:/my-app/elastic-apm-agent.jar -jar /my-app/app.jar
