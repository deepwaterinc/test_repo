-- 1) Заходим в PostgreSQL UI и удаляем схему edo, если она есть
-- 2) Запускаем приложение:
-- Для запуска у вас должен быть запущен rabbitmq и postgreSQL.
-- Порядок запуска микросервисов: сервер, сервис, репозиторий, рест
-- Теперь появились таблицы, надо их заполнить тестовыми данными:
-- 1) В этом файле выбираем PostgreSQL диалект, если IDE предлагает это сделать
-- 2) Выбираем схему edo в бд edo_db(сверху справа, наводим на edo_db, нажимаем на клавиатуре вправо, выбираем edo, жмем ок)
-- 3) Выделяем весь текст, нажимаем кнопку play в верхней левой части экрана

-- Проверить работоспособность:
-- GET запрос на http://127.0.0.1:8080/api/rest/appeal/appealById/1
-- POST запрос на http://127.0.0.1:8080/api/rest/appeal    json можно найти в файле json_for_appeal_save_with_region


-- Создаем таблицу federal_district
create table if not exists federal_district
(
    id                    bigserial not null primary key,
    federal_district_name varchar(255),
    website               varchar(255)
);
comment on column federal_district.federal_district_name is 'Название Федерального округа';
comment on column federal_district.website is 'URL вебсайта';

-- Создаем таблицу region
create table if not exists region
(
    id                         bigserial not null primary key,
    external_id                varchar(255),
    region_name                varchar(255),
    archived_date              timestamptz,
    quantity                   varchar(255),
    number_of_primary_branches varchar(15),
    number_of_local_branches   varchar(15),
    federal_district_id bigint references federal_district (id)
);

comment on column region.external_id is 'Идентификатор региона из внешних систем';
comment on column region.region_name is 'Название региона';
comment on column region.archived_date is 'Дата архивации';
comment on column region.quantity is 'Количество';
comment on column region.number_of_primary_branches is 'Количество первичных отделений в регионе';
comment on column region.number_of_local_branches is 'Количество местных отделений в регионе';
comment on column region.federal_district_id is 'ID федерального округа региона';



-- Добавляем в таблицу Appeal поле region_id

ALTER TABLE IF EXISTS appeal
    ADD COLUMN IF NOT EXISTS region_id bigint references region (id);



-- Заполнение тестовыми данными

insert into federal_district (federal_district_name, website)
VALUES ('Северо-Западный', 'example.org');

insert into region (external_id, region_name, quantity, number_of_primary_branches, number_of_local_branches, federal_district_id)
values ('123', 'Ленинградская область', '5', '2', '3', 1);
