# Выполнить команду mvn clean install для всего проекта для появления jar файла в target

# Собираем образ docker находясь в командной строке на этом микросервисе
# docker build -t scheduler .

# Запустить еврека сервер, открыть его UI по адресу http://localhost:8761 скопировать его ip адрес (поле ipAddr) и вставить в строку ниже вместо YOUR_IP
# Запустить образ
# docker run -e EUREKA_URI=http://YOUR_IP:8761/eureka/ scheduler

# Начинаем с базового образа, содержащего JDK
FROM openjdk:17

# Устанавливаем директорию для приложения
WORKDIR /app

# Копируем файлы приложения в Docker-контейнер
COPY target/edo-scheduler-0.0.1.jar /app/

# Запускаем приложение
CMD ["java", "-jar", "edo-scheduler-0.0.1.jar"]
