create table if not exists facsimile
(
    id           bigserial not null primary key,    --id факсимиле
    employee     bigint not null,                   --Id Работника
    department   bigint not null,                   --Id департамента
    file         bigint not null,                   --id файла
    isArchived   boolean not null default false     --Признак архивности
);

comment on column facsimile.id is 'id факсимиле';
comment on column facsimile.employee is 'Id работника';
comment on column facsimile.department is 'Id департамента';
comment on column facsimile.file is 'Id файла';
comment on column facsimile.isArchived is 'Факсимиле архивен';
