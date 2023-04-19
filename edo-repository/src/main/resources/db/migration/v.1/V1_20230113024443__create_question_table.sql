create table if not exists question
(
    id              bigserial not null primary key,    -- id
    creation_date   timestamptz not null,              -- дата создания обращения
    archived_date   timestamptz,                       -- дата архивирования обращения
    summary         varchar(255) not null              -- краткое содержание обращения
);

comment on table question is 'хранит краткое содержание обращений';
comment on column question.id is 'id обращения';
comment on column question.creation_date is 'дата создания обращения';
comment on column question.archived_date is 'дата архивирования обращения';
comment on column question.summary is 'краткое содержание обращения';