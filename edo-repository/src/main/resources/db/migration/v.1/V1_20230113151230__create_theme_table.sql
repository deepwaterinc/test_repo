create table if not exists theme
(
    id bigserial not null primary key,
    theme_name text,                   -- название темы
    creation_date timestamptz,         -- дата создания темы
    archived_date timestamptz,         -- дата архивации темы
    code       text,                   -- код темы
    parent_id  bigint references theme -- родительская тема
);
comment on table theme is 'Тема обращения';
comment on column theme.id is 'id темы обращения';
comment on column theme.theme_name is 'Название темы обращения';
comment on column theme.creation_date is 'Дата создания темы';
comment on column theme.archived_date is 'Дата архивации темы';
comment on column theme.code is 'Код темы';
comment on column theme.parent_id is 'id родительской темы';