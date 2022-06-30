#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim
COPY . /home/app
WORKDIR /home/app
#RUN mvn dependency:go-offline
RUN mvn clean package

#
# Package stage
#
#FROM openjdk:11-jdk
FROM gcr.io/distroless/java 
COPY --from=0 /home/app/application/target/*.jar /usr/local/lib/app.jar
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]