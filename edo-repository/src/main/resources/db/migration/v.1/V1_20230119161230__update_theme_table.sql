--Добавление строк в таблицу theme
INSERT INTO theme (theme_name, code)
VALUES ('Вопросы по благоустройству', '1'),
       ('благоустройство парков', '1.1'),
       ('благоустройство улиц', '1.2'),
       ('пешеходная часть', '1.2.1'),
       ('автомобильная часть', '1.2.2'),
       ('благоустройство дворов', '1.3'),
       ('Вопросы по работе коммунальных служб', '2'),
       ('обращение с тко', '2.1'),
       ('содержание придомовых территорий', '2.2'),
       ('содержание коммунальных сетей', '2.3'),
       ('Вопросы по социальным льготам', '3'),
       ('социальные льготы для многодетных семей', '3.1'),
       ('социальные льготы для инвалидов', '3.2'),
       ('социальные льготы для пенсионеров', '3.3');

--Добавление внешнего ключа в таблицу theme
UPDATE theme
SET parent_id = (SELECT theme.id FROM edo.theme where code = '1')
where code in ('1.1', '1.2', '1.3');
UPDATE theme
SET parent_id = (SELECT theme.id FROM theme where code = '1.2')
where code in ('1.2.1', '1.2.2');
UPDATE theme
SET parent_id = (SELECT theme.id FROM theme where code = '2')
where code in ('2.1', '2.2', '2.3');
UPDATE theme
SET parent_id = (SELECT theme.id FROM theme where code = '3')
where code in ('3.1', '3.2', '3.3');