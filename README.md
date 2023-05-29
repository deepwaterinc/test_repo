<h2>Описание проекта "EDO"</h2>

- [Summary](#summary)
- [Stack](#stack)
- [MVP](#mvp)
- [Backlog](#backlog)
- [Структура проекта](#структура-проекта)
    - [Бэкенд](#бэкенд)
    - [Фронтенд](#фронтенд)
- [Работа на проекте](#работа-на-проекте)
    - [С чего начинать](#с-чего-начинать)
    - [О таскборде](#о-таскборде)
    - [Как выполнять задачи](#как-выполнять-задачи)
    - [Проверка задач](#проверка-задач)
    - [Требования к коду](#требования-к-коду)
    - [Созвоны по проекту](#созвоны-по-проекту)
- [Дополнительные материалы](#дополнительные-материалы)
  - [Flyway](#flyway)
  - [RabbitMQ](#RabbitMQ)
  - [Аутентификация](#аутентификация)
  - [Minio](#minio)
### Summary

Реализуем функционал обработки обращений граждан и обменом электроными документами между ведомствами

Проект рассчитан на студентов, успешно завершивших этап Pre-Project в Kata Academy.

### Stack

Проект пишется на базе `Java 17`, `Spring Boot 3`, `Maven` и архитектуре REST.
Работаем с базой данных `PostgreSQL 12` через `Spring Data` и `Hibernate`.

Чтобы не писать boilerplate-код, используем на проекте [Lombok](https://projectlombok.org/features/all).

Все контроллеры и их методы нужно сразу описывать
аннотациями [Swagger](https://docs.swagger.io/swagger-core/v1.5.0/apidocs/allclasses-noframe.html).

Таск-борд находится прямо [тут](https://gitlab.com/__-__/edo_v1/-/boards/)

Dev-stand отсутствует будем поднимать и разворачивать локально

### MVP

[MVP](https://ru.wikipedia.org/wiki/%D0%9C%D0%B8%D0%BD%D0%B8%D0%BC%D0%B0%D0%BB%D1%8C%D0%BD%D0%BE_%D0%B6%D0%B8%D0%B7%D0%BD%D0%B5%D1%81%D0%BF%D0%BE%D1%81%D0%BE%D0%B1%D0%BD%D1%8B%D0%B9_%D0%BF%D1%80%D0%BE%D0%B4%D1%83%D0%BA%D1%82)
-
API (полностью описанное в Swagger), которое будет уметь создавать обращения вручную, получать по интеграционным каналам
и оповещать пользователей о новых документах
Работать с таким API можно будет через веб-интерфейс Swagger и Postman.

### Backlog

Фичи:
<ul>
<li>Создание, редактирование, удаление обращений</li>
<li>Создание личного кабинета работника, добавление аутентификации через логин/пароль, Google, социальные сети</li>
<li>Интеграция с Keycloak</li>
<li>реализация функционала обратной связи с гражданами через e-mail и Telegram</li>
<li>создание ролевой модели для взаимодействия с API</li>
<li>внедрение взаимодействия с яндекс.картами для парсинга адресов</li>
<li>внедрение интеграции с внешними источниками данных для синхронизации данных пользователей</li>
</ul>

Импрувменты:

<ul>
<li>логирование через Slf4j + log4j2</li>
</ul>

## Структура проекта

### Бэкенд

Проект основан на архетипе webapp.
Слои:
<ul>
<li><code>config</code> конфигурационные классы, в т.ч. Spring Security, инструменты аутентификации</li>
<li><code>entity</code> сущности базы данных</li>
<li><code>dto</code> специальные сущности для передачи/получения данных в/с апи</li>
<li><code>repository</code> dao-слой приложения, реализуем в виде интерфейсов Spring Data, имплементирующих JpaRepository.</li>
<li><code>service</code> бизнес-логика приложения, реализуем в виде интерфейсов и имплементирующих их классов.</li>
<li><code>controller</code> обычные и rest-контроллеры приложения.</li>
<li><code>util</code> пакет для утилитных классов: валидаторов, шаблонов, хэндлеров, эксепшнов.</li>
<li><code>feign</code> Feign клиенты для взаимодействия между микросервисами</li>
<li><code>listener</code> слушатели очередей для выборки данных</li>
<li><code>publisher</code> отправители в очереди</li>
</ul>

### Фронтенд

Фронтенда нет. Когда будет, непонятно.

## Работа на проекте

### С чего начинать

Доступы. Если ты читаешь это, значит доступ к проекту у тебя уже есть )
<ol>
<li>загрузи проект себе в среду разработки</li>
<li>изучи весь проект - начни с pom, properties файлов и конфигурационных классов</li>
<li>создай локальную базу данных PostgeSQL с названием <code>edo_db</code>. Можешь изменить параметры доступа (логин, пароль) в конфиге проекта под свои нужды, 
но не отправляй эти данные в Git</li>
<li>добейся успешного запуска проекта. Первым запускается модуль edo-cloud-server. <a href="http://localhost:8761/"> Проверить</a>.</li>
<li>изучи <a href="https://gitlab.com/__-__/edo_v1/-/boards/">таск-борд</a>
</ol>

[//]: # (Для отправки почты используется Google аккаунт.<br/>)

[//]: # (Вход в него с именем "uxair1.kata" и паролем "Mail4@Uxair1" &#40;без кавыек&#41;.<br/>)

[//]: # (Важно учитывать, что для отправки почты использзуется отдельный пароль &#40;т.н пароль приложения&#41;,)

[//]: # (так как в случае наличи двухфакторной аутентификации использование пароля от эккаунта не позволит отправить почту.<br/>)

[//]: # (Именно данный пароль и указан в найстройке в .yml файле.)

[//]: # (При отправке почты всегда можно зайти в эккаунт через браузер и проверить, что она в папке Отправленные.)

### О таскборде

Таск-борд строится по принципу Kanban - он разделён на столбцы, каждый из которых соответствует определённому этапу
работы с задачей:
<ul>
<li><code>Backlog</code> задачи на <b>новый функционал</b>, корзина функционала приложения. Здесь можете создавать карточки на таски, которые считаете необходимыми</li>
<li><code>TODO</code> задачи, требующие выполнения</li>
<li><code>In Progress</code> выполняемые в данный момент задачи, обязательно должны иметь исполнителя</li>
<li><code>Cross-review </code> задачи на этапе перекрёстной проверки студентами</li>
<li><code>Final Review</code> задачи на проверке у техлида</li>
<li><code>Closed</code> выполненные задачи</li>
</ul>

У каждой задачи есть теги:
<ul>
<li><code>Feature/Refactor</code> - новый функционал или переработка существующего</li>
<li><code>Bug</code> - таска на исправление бага до или после тестирования</li>
<li><code>Reworking</code> - таска на исправлении замечаний после кросс или файнал ревью</li>

[//]: # (<li><code>InQA</code> - задача с такой меткой находится у тестировщика в работе </li>)
<li><code>Backlog, ToDo, InProgress, CrossReview, FinalReview</code> - этапы прохождения задачи по борде</li>
</ul>

### Как выполнять задачи

<ul>
<li>в графе <code>TODO</code> на таск-борде выбери карточку с задачей и назначь её себе для исполнения</li>
<li>загрузи себе последнюю версию ветки <code>develop</code></li>
<li>создай от <code>develop</code> свою собственную ветку для выполнения взятой задачи. Свою ветку назови так, чтобы было понятно, чему посвящена задача. В начале имени ветки проставь номер задачи с Gitlab. Например, <code>313_adding_new_html_pages</code></li>
<li>выполни задачу, обязательно сделай юнит-тесты на методы и, если всё ок, залей её в репозиторий проекта</li>
<li>создай на своей ветке merge request, в теле реквеста укажи <code><i>Closes #здесь-номер-таски"</i></code>. Например, <code>Closes #313</code></li>
<li>перенеси задачу в столбец <code>Cross-review</code></li>
</ul>

### Проверка задач

На этапе кросс-ревью студенты проверяют задачи, выполненные друг другом.
В случае, если к коду есть замечания, проверяющий пишет замечания в мердж реквесте и оставляет комментарий в карточке.
Если к коду претензий нет, проверяющий студент ставит к карточке лайк.

**Каждая карточка (студенческая задача) должна быть проверена как минимум 2 другими студентами и одобрена ими (т.е.
собрать не менее 2 лайков).**

Только после этого карточку можно переносить в столбец `Final Review`.

Затем код проверяет техлид (ментор) и в случае обнаружения ошибок ставит тег `Reworking` и переносит её в
столбец `InProgress`.
Если всё ок - merge request принимается, ветка студента сливается с основной веткой проекта, а карточка переносится в
столбец `Closed`.

### Требования к коду

- сделайте себе понятные никнеймы (имя + фамилия) в Git. Не хочу гадать, кто, где и что писал.

#### Команды для гита (выполнять по очереди): <code>git config --global user.name "Фамилия Имя"<br> git config --global user.email "Email"</code>

- при использовании Stream, Optional, Builder необходимо писать каждый метод с новой строки

#### Пример:

````java
Stream.of("a","b","c")
        .filter(string->string.equals("a"))
        .sorted()
        .collect(toSet());
````

- для каждого класса и (желательно) методов пишите комментарии в формате <b>Javadoc</b>:
    - над классом: что это за класс, зачем нужен. Описывайте поля.
    - над методом: что делает, какие параметры принимает (и что это такое), что возвращает.
- свободно создавайте собственные вспомогательные классы в пакете Util - типа утилиток для страховки от null и типа
  того. Модуль edo-common
- в REST-контроллерах и DTO пользоваться аннотациями Swagger - причём как сами контроллеры в целом, так и их отдельные
  методы.
  Посмотрите пример в проекте
- на полях сущностей можно и нужно расставлять констрейнты для проверки формата, длины введённых значений, проверки
  чисел на положительность и т.д.
- пишите Commit message как можно более подробно! На английском языке - пользуйтесь переводчиком при необходимости.
  также указывайте номер задачи
- при объявлении переменной используйте ключевое слово <code>var</code>
- используйте STATIC импорт для статических методов и переменных(Alt+Enter в помощь)
- всегда пользуйтесь Ctrl+Alt+L

### Требования к логированию работы контроллеров:

1. В каждый метод необходимо добавить логирование с описанием произведенной операции на уровне info.

2. Если объект не найден, вывести сообщение уровня warning ("not found" или "does not exist") с описанием произведенной
   операции.

### Созвоны по проекту

Созвоны проходят по понедельникам и средам в оговорённое время.
Регламент:

- длительность до 15 минут
- формат: доклады по 3 пунктам:
    - что сделано с прошлого созвона
    - какие были/есть трудности
    - что будешь делать до следующего созвона
- техлид (ментор) на созвонах код не ревьюит

Любые другие рабочие созвоны команда проводит без ограничений, т.е. в любое время без участия техлида.
Договаривайтесь сами :)

## Дополнительные материалы

### Аутентификация

Документация по аутентификации находится [здесь]("https://gitlab.com/__-__/edo_v1/-/tree/develop/edo-common-config")

### Flyway

#### Инструкция для разработчика по созданию скриптов миграции

1. все операции с БД происходят в модуле edo-repository:

- сохранение, обновление, удаление происходит при получении данных по очереди или при необходимости через Feign Client.
- Получение данных через Feign клиенты

2. При необходимости изменить настройки подключения к бд в файле application.yml
3. В папке resources/db/migration/v.1 создать файл с именем по шаблону
   v_1_год|месяц|день|час|минута|секунда__краткое_описание.sql
4. Внутри файла написать инструкцию для выполнения

- Всегда использовать <code>if not exists</code> при создании или обновлении таблицы

5. Если миграция некорректно накатилась, то необходимо руками удалить внесенные изменения и удалить запись в
   таблице <code>flyway_schema_history</code> после чего исправить скрипт

### RabbitMQ

Для работы установите Erlang и RabbitMQ или используйте докер образ RabbitMQ:

+ [rabbitmq.com](https://www.rabbitmq.com/download.html)

После запуска приложения можно зайти в web версию http://localhost:15672
Логин и пароль - guest

#### Инструкция по созданию очереди

В конфигурационном файле создайте бин новой очереди:

````java
@Bean
public Queue nameNewQueue(){
        return new Queue(RabbitConstant.nameNewQueue,false);
        }
````

И свяжите очередь с routing key

````java
@Bean
public Binding binding(Queue nameNewQueue,DirectExchange exchange){
        return BindingBuilder
        .bind(nameNewQueue)
        .to(exchange)
        .with(RabbitConstant.nameRoutingKey);
        }
````

Название очереди и routing key можно сделать одинаковым и оно должно отражать суть задачи,
например "rest.appeal.create".

Названия очередей создавайте как константы в модуле edo-common

#### Инструкция по созданию Producer

````java
@Autowired
private AmqpTemplate template;

        template.convertAndSend(RabbitConstant.exchange,RabbitConstant.newRoutingKey,data);
````

RabbitConstant.exchange - это название Exchange, он общий для всех очередей.

#### Инструкция по созданию Listener

над методом укажите аннотацию с названием очереди, которую необходимо слушать

````java
@RabbitListener(queues = RabbitConstant.nameNewQueue)
````

### Minio
Запустить Minio на локальном компьютере можно с помощью докера   
<code>docker run -p 9000:9000 --name myminio -e "MINIO_ACCESS_KEY=access_key" -e "MINIO_SECRET_KEY=secret_key" -v minio\data:/data minio/minio server /data</code>

В этой команде мы:
+ привязываем порт 9000 (для доступа к интерфейсу Minio) хоста и контейнера;
+ задаем имя контейнера (в данном случае myminio);
+ устанавливаем переменные среды MINIO_ACCESS_KEY и MINIO_SECRET_KEY, которые будут использоваться для аутентификации при подключении к Minio;
+ создаем том minio\data на хосте для хранения данных Minio(можно любое название);
+ запускаем команду server /data, которая запускает Minio и использует каталог /data на томе для хранения данных.

После запуска контейнера вы сможете получить доступ к интерфейсу Minio, открыв веб-браузер и перейдя по адресу http://localhost:9000. \
Введите свой MINIO_ACCESS_KEY и MINIO_SECRET_KEY, чтобы войти в интерфейс Minio и начать использовать его для хранения и получения данных. \
Если вы хотите обратиться к интерфейсу Minio с другого компьютера, замените localhost на IP-адрес хоста, где запущен контейнер Minio.

Чтобы создать bucket, для хранения файлов нужно:
+ открыть терминал
+ перейти в том для хранения данных <code>cd C:\minio\data</code>
+ создать bucket <code>mkdir test</code>

### Docker
- В первую очередь нужно скачать и установить докер на компьютер. https://www.docker.com/products/docker-desktop/
- Запустить приложение докер-десктоп.
- Подключить его в IDE во вкладке сервисы. Теперь докер готов к работе.

Запуск приложения с помощью докера:
1) Перед запуском docker-compose или docker файла выполняем команду mvn clean package(минуя тесты, иначе не упакуется)
   Чтобы миновать тесты:
   в идее справа вкладка maven, в открывшемся окне нажать на кнопку "Toggle 'skip tests' Mode"(перечеркнутый кружок)
   далее edo/lifecycle/clean и edo/lifecycle/package  - это нужно, чтобы появился jar файл в папке target

2) Идем в модуль, который хотим запустить с помощью докера.
   Dockerfile или docker-compose.yml должен находится рядом с pom.xml
   Если файла нет, то придется писать самим. Ситуация, когда файл есть:
3) Если файл один, то это dockerfile. Заходим в него, читаем инструкцию внутри, выполняем.
4) Если файла два, то один dockerfile, а второй docker-compose.yml . Заходим во второй, нажимаем кнопку 'play'.


Как создать докерфайл(+docker-compose.yml) самому(пример):
- в корне проекта создаем файл и называем его dockerfile
- пишем туда следующее:

FROM openjdk:17-alpine

- В строке выше мы говорим: "Docker, создай образ Java-17 облегченной версии"
- это нужно чтобы наш контейнер компилировался

ADD /target/edo-repository-0.0.1.jar repository.jar


- В строке выше мы говорим 'Docker, скопируй себе jar файл из папки таргет'
- edo-repository - это название модуля, в котором вы работаете(его надо заменить на соотв.).
- repository.jar - и сверху и снизу, название файла(вы сами заново называете скопированный только что файл),
- можете назвать его какхотите.jar
- главное чтобы сверху и снизу названия были одинаковыми, написаны на английской раскладке, имели расширение .jar

ENTRYPOINT ["java","-jar","repository.jar"]


- В строке выше мы говорим 'Docker, начни работу с файла repository.jar'

Если сейчас мы запустим этот докерфайл, то приложение либо не запустится, либо запустится, но не сможет коммуницировать с другими.
А у нас микросервисы и они хотят общаться. Так что нам нужно создать docker-compose.yml файл на том же уровне, что и наш докерфайл.

Зачем создавать docker-compose.yml ? - Чтобы передать переменные окружения, без которых приложение не сможет работать:
- по умолчанию докер запускает приложение в своей внутренней сети, мы же хотим чтобы приложение работало на стандартной сети, для этого будем передавать соответствующие переменные окружения
- если мы хотим запустить бд в контейнере, то нам точно нужно передать пароль и юзера, если они не дефолтные, + порт как сказано выше(гуглить: проброс порта)
- в докер-компоуз можно по идее прописать все приложения(микросервисы) и запускать с одной кнопки

Можно не писать docker-compose.yml , а запустить контейнер с помощью командной строки передав там переменные окружения(этого в здесь поисано не будет:||)

Пример файла docker-compose.yml:

    version: '3'

    services: #какие сервисы будем запускать
    
      db: #название сервиса, в данном случае это база данных
        container_name:repository-db #имя которым назовем контейнер
        ports: - "5432" #наружный порт, по которому будет доступен этот сервис
        image: postgres:alpine #образ postgeresql 
        environment: #переменные окружения, в данном случае для бд
          - POSTGRES_USER=postgres 
          - POSTGRES_PASSWORD=root
          - POSTGRES_DB=edo_db
      app: #приложение, которое мы запускаем(в данном случае repository).
        build: . # " . " - точка запускает наш докерфайл, но с теми переменными окружения, которые мы передаем в docker-compose.yml
        environment: 
          SPRING_FLYWAY_HOST: host.docker.internal #важно вынести в отдельные переменные все указания URL типа "localhost" в файле application.yml
          # как это сделать опишу ниже
          SPRING_RABBITMQ_HOST: host.docker.internal

Заходим в файл application.yml и выносим все упоминания localhost в отдельные переменные:

    spring:
        jpa:
            properties:
                hibernate:
                default_schema: edo
                flyway:
                    enabled: true
                    user: postgres #Should be changed (local database username)
                    password: root
                    host: localhost #теперь запись localhost хранится в отдельной переменной и мы сможем ее заменить своей переменной окружения
                    url: jdbc:postgresql://${spring.flyway.host}:5432/edo_db?currentSchema=${spring.flyway.default-schema}
                    default-schema: edo
                    locations: db/migration
                    out-of-order: true


Когда все написали. Нажать кнопку 'play'.