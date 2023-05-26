create table if not exists notification
(
    id bigserial not null primary key,                          -- id
    employee_id bigint references employee (id),     -- Для кого уведомление
    notification_name varchar(255)                             -- Оповещение пользователя
    ); --Таблица описывающая оповещения пользователя one to many

comment on column notification.id is 'id';
comment on column notification.employee_id is 'Id работника, для которого уведомление';
comment on column notification.notification_name is 'Тип оповещения';

