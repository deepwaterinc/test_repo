CREATE TABLE if not exists file_pool
(   id bigserial not null primary key,      --ID
    storage_file_id UUID not null,          --ID хранилища файла
    file_name varchar(255),                 --Имя хранилища файла
    extension varchar(255),                 --Расширение файла
    file_size int,                          --Размер файла
    page_count int,                         --Количество страниц файла
    upload_date timestamp with timezone,    --Дата загрузки файла
    archived_date timestamp with timezone,  --Дата архивирования файла
    creator_id bigint                       --Автор файла
);
comment on column file_pool.id is 'ID'
comment on column file_pool.storage_file_id is 'ID хранилища файла';
comment on column file_pool.file_name is 'Имя хранилища файла';
comment on column file_pool.extension is 'Расширение файла';
comment on column file_pool.size is 'Размер файла';
comment on column file_pool.page_count is 'Количество страниц файла';
comment on column file_pool.upload_date is 'Дата загрузки файла';
comment on column file_pool.archived_date is 'Дата архивирования файла';
comment on column file_pool.creator_id is 'Автор файла';
