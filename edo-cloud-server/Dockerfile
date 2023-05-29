# Перед запуском докерфайла в консоли переключиться на директорию edo-cloud-server

# Выполнить команду mvn clean install для появления jar файла в target

# Собираем образ docker командой
# docker build -t cloudserver .

# Запускаем образ docker командой
# docker run -p 8761:8761 cloudserver

# Начинаем с базового образа, содержащего JDK
FROM openjdk:17

# Устанавливаем директорию для приложения
WORKDIR /app

# Копируем файлы приложения в Docker-контейнер
COPY target/edo-cloud-server-0.0.1.jar /app/

# Указываем порт, на котором работает приложение
EXPOSE 8761

# Запускаем приложение
CMD ["java", "-jar", "edo-cloud-server-0.0.1.jar"]