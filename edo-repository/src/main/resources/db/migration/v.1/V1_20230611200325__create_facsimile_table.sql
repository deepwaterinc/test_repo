create table if not exists facsimile
(
    id              bigserial not null primary key,                        --id факсимиле
    employee_id     bigint not null references employee (id),                   --Id Работника
    department_id   bigint not null references department (id),                 --Id департамента
    file_id         bigint not null references file_pool (id),                  --id файла
    isArchived      boolean not null default false                         --Признак архивности
);

comment on column facsimile.id is 'id факсимиле';
comment on column facsimile.employee_id is 'Id работника';
comment on column facsimile.department_id is 'Id департамента';
comment on column facsimile.file_id is 'Id файла';
comment on column facsimile.isArchived is 'Факсимиле архивен';
