create table if not exists author
(
    id bigserial not null primary key,
    first_name     varchar(255) not null,
    last_name      varchar(255) not null,
    middle_name    varchar(255) not null,
    address        varchar(255) not null,
    snils          varchar(11)  not null,
    mobile_phone   varchar(15)  not null,
    email          varchar(255) not null,
    employment     varchar(255) not null,
    fio_dative     varchar(255) not null,
    fio_genitive   varchar(255) not null,
    fio_nominative varchar(255) not null
);
comment on column author.first_name is 'Имя автора';
comment on column author.last_name is 'Фамилия автора';
comment on column author.middle_name is 'Отчество автора';
comment on column author.address is 'Адрес';
comment on column author.snils is 'СНИЛС';
comment on column author.mobile_phone is 'Мобильный номер телефона';
comment on column author.email is 'Электронная почта';
comment on column author.employment is 'Рабочий статус';
comment on column author.fio_dative is 'ФИО в дательном падеже';
comment on column author.fio_genitive is 'ФИО в родительном падеже';
comment on column author.fio_nominative is 'ФИО в именительном падеже';