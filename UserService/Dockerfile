# Используем официальный образ OpenJDK в качестве базового образа
FROM openjdk:17-jdk-alpine

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем Maven wrapper и pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Копируем исходный код
COPY src ./src

# Собираем приложение с помощью Maven
RUN ./mvnw clean package -DskipTests

# Указываем порт, который будет использоваться приложением
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "target/UserService-0.0.1-SNAPSHOT.jar"]