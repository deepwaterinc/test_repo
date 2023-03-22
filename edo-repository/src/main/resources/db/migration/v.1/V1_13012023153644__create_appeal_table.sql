create table if not exists appeal
(
    id            bigserial    not null primary key,             -- id
    creation_date timestamptz  not null,                         -- Дата создания обращения
    archived_date timestamptz,                                   -- Дата архивирования обращения
    number        varchar(255) not null,                         -- Номер обращения
    annotation    varchar(255) not null,                         -- Описание обращения
    creator_id    bigint       not null references employee (id)); -- Автор
 --Таблица описывающая обращение

create table if not exists appeal_signer
(
    appeal_id   bigint references appeal (id),
    employee_id bigint references employee (id)
); --Таблица one to many appeal_signer
create table if not exists appeal_addressee
(
    appeal_id    bigint references appeal (id),
    employee_id  bigint references address (id)
); --Таблица one to many appeal_addressee

comment on column appeal_signer.appeal_id is 'id appeal';
comment on column appeal_signer.employee_id is 'id employee';

comment on column appeal_addressee.appeal_id is 'id appeal';
comment on column appeal_addressee.employee_id is 'id employee';

comment on column appeal.id is 'id';
comment on column appeal.creation_date is 'Дата создания обращения';
comment on column appeal.archived_date is 'Дата архивирования обращения';
comment on column appeal.number is 'Номер обращения';
comment on column appeal.annotation is 'Описание обращения';
comment on column appeal.creator_id is 'Автор';

