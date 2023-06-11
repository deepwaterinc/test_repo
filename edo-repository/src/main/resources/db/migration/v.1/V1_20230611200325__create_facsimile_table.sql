create table if not exists facsimile
(
    id           bigserial not null primary key, --id факсимиле
    employee     bigserial,                      --Id Работника
    department   bigserial,                      --Id департамента
    file         bigserial,                      --id файла
    isArchived   boolean                         --Признак архивности
);

comment on column facsimile.id is 'id факсимиле';
comment on column facsimile.employee is 'Id работника';
comment on column facsimile.department is 'Id департамента';
comment on column facsimile.file is 'Id файла';
comment on column facsimile.isArchived is 'Факсимиле архивен';
