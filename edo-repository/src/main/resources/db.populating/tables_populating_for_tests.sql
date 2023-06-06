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
-- POST запрос на http://127.0.0.1:8080/api/rest/appeal    json можно найти в файле json_for_appeal_save_test или в таске#90


-- Заполнение таблицы employee тестовыми данными

insert into employee (first_name, last_name, middle_name, address, photo_url, fio_dative, fio_nominative, fio_genitive,
                      external_id, phone, work_phone, birth_date, username, work_email)
values ('Иван', 'Иванов', 'Иванович', 'Невский 28', 'example.org', 'Иванову Ивану Ивановичу', 'Иванов Иван Иванович',
        'Иванова Ивана Ивановича', '123', '89993332211', '89992221133', now(), 'ivan123', 'iv@ya.ru'),
       ('Игорь', 'Игорев', 'Игоревич', 'Невский 29', 'example1.org', 'Игореву Игорю Игоревичу', 'Игорев Игорь Игоревич',
        'Игорева Игоря Игоревича', '124', '89993332212', '89992221132', now(), 'igor123', 'ig@ya.ru'),
       ('Иван', 'Петров', 'Иванович', 'Невский 28', 'example2.org', 'Петрову Ивану Ивановичу', 'Петров Иван Иванович',
        'Петрова Ивана Ивановича', '124', '89993332213', '89992221133', now(), 'petrov123', 'petrov@ya.ru');


-- Заполнение таблицы author тестовыми данными
insert into author (first_name, last_name, middle_name, address, snils, mobile_phone, email, employment, fio_dative,
                    fio_genitive, fio_nominative)
values ('Иван', 'Иванов', 'Иванович', 'Невский 28', '888999111', '89991112233', 'iv@ya.ru', 'WORKER',
        'Иванову Ивану Ивановичу', 'Иванова Ивана Ивановича', 'Иванов Иван Иванович');


-- Заполнение таблицы address тестовыми данными
insert into address (full_address, street, house, "index", housing, building, city, region, country, flat)
values ('Ленинградская область, Поселок Кузнечное, улица Ленина, дом 7, кв 12', 'Ленина', '7', '333213', '2', '2',
        'Кузнечное', 'Ленинградская область', 'РФ', '12');


-- Заполнение таблицы question тестовыми данными
--
insert into question (creation_date, summary, theme_id)
values (now(), 'Осы залетают в окно', 6);


-- Заполнение таблицы resolution тестовыми данными

insert into resolution (creation_date, resolution_name, creator_id, signer_id,
                        curator_id, question_id)
values (now(), 'Запрос', '1', '1', '1', '1');

-- Заполнение таблицы file_pool тестовыми данными

insert into file_pool (storage_file_id, file_name, extension, file_size, page_count, upload_date, creator_id)
values ('0fd52378-819e-47f3-bd58-293edd25ed0a', 'cat', 'jpg', '1', '1', now(), 1);


-- Заполнение таблицы nomenclature тестовыми данными

insert into nomenclature (creation_date, template, current_value, "index")
values (now(), '%ЧИС%ГОД-%ЗНАЧ/2', 1, '617234');

-- Заполнение таблицы department тестовыми данными


insert into department (short_name, full_name, address_id, creation_date, external_id, phone, department_id)
values ('УВУ', 'Управление всеми управлениями', 1, now(), 1, '88889993322', 1);

-- Заполнение таблицы appeal тестовыми данными
insert into appeal (creation_date, number, annotation, creator_id, nomenclature_id, appeal_status, way_to_receive,
                    way_to_answer)
values (now(), '12', 'Обращение', 1, 1, 'NEW', 'ON_PAPER', 'BY_RUSSIAN_POST');


--Добавление строк в таблицу theme тема 15
INSERT INTO theme (theme_name, code)
VALUES ('социальные льготы для ветеранов', '3.4');

--Добавление внешнего ключа в таблицу theme
UPDATE theme
SET parent_id = (SELECT theme.id FROM theme where code = '3')
where code in ('3.4');


