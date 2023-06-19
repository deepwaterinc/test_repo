-- Создаем таблицу deadline
create table if not exists deadline
(
    id bigserial not null primary key,
    deadline_date timestamptz,
    comment varchar(255),
    resolution_id bigint references resolution (id)
);

comment on table deadline is 'Хранит дедлайны';
comment on column deadline.id is 'ID';
comment on column deadline.deadline_date is 'Дата дедлайна';
comment on column deadline.comment is 'Комментарий о причине переноса';
comment on column deadline.resolution_id is 'Резолюция, к которой относится дедлайн';


