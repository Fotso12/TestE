# Étape 1 : Build de l'application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copie du pom.xml et téléchargement des dépendances
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie du code source et build (on ignore les tests pendant le build Docker car ils nécessitent MySQL)
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Image de runtime légère
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copie du JAR depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Exposition du port (correspond à server.port=8082 dans application.properties)
EXPOSE 8082

# Lancement de l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
