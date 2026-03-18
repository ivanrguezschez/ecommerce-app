# Etapa 1: Build
#FROM eclipse-temurin:21-jdk-alpine AS build
#WORKDIR /app

#RUN apk add --no-cache maven

#COPY . .

#RUN mvn clean package -DskipTests -pl gateway-service -am

# Etapa 2: Runtime (Imagen final optimizada)
FROM eclipse-temurin:21-jre-alpine
RUN apk add --no-cache curl bash
WORKDIR /app

#COPY --from=build /app/gateway-service/target/*.jar app.jar
COPY target/*.jar app.jar

EXPOSE 8222

ENTRYPOINT ["java", "-jar", "app.jar"]
