ALTER TABLE IF EXISTS appeal
    ADD COLUMN IF NOT EXISTS nomenclature_id bigint       not null references nomenclature (id), -- Номенклатура
    ADD COLUMN IF NOT EXISTS resolution_id   bigint       not null references resolution (id),   -- Резолюция
    ADD COLUMN IF NOT EXISTS theme_id        bigint       not null references theme (id),        -- Тема
    ADD COLUMN IF NOT EXISTS appeal_status   varchar,                                            -- Статус
    ADD COLUMN IF NOT EXISTS way_to_receive  varchar;                                           -- Способ получения

ALTER TABLE IF EXISTS appeal_addressee
    DROP COLUMN IF EXISTS employee_id,
    ADD employee_id  bigint references employee (id);

create table if not exists appeal_author
(
    appeal_id bigint references appeal (id),
    author_id bigint references author (id)
); --Таблица one to many appeal_author

create table if not exists appeal_file_pool
(
    appeal_id    bigint references appeal (id),
    file_pool_id bigint references file_pool (id)
); --Таблица one to many appeal_file_pool

create table if not exists appeal_question
(
    appeal_id   bigint references appeal (id),
    question_id bigint references question (id)
); --Таблица one to many appeal_question

comment on column appeal_addressee.employee_id is 'id employee';

comment on column appeal_author.appeal_id is 'id appeal';
comment on column appeal_author.author_id is 'id author';

comment on column appeal_file_pool.appeal_id is 'id appeal';
comment on column appeal_file_pool.file_pool_id is 'id file_pool';

comment on column appeal_question.appeal_id is 'id appeal';
comment on column appeal_question.question_id is 'id question';

comment on column appeal.nomenclature_id is 'Номенклатура';
comment on column appeal.resolution_id is 'Резолюция';
comment on column appeal.theme_id is 'Тема';
comment on column appeal.appeal_status is 'Статус';
comment on column appeal.way_to_receive is 'Способ получения';
