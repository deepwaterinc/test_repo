create table if not exists notification
(
    id bigserial not null primary key,                          -- id
    notification_name varchar(255)                             -- Оповещение пользователя
); --Таблица описывающая оповещения пользователя

comment on column notification.id is 'id';
comment on column notification.notification_name is 'Тип оповещения';

create table if not exists employees_notifications
(
    employee_id   bigint references employee (id),
    notification_id bigint references notification (id)
); --Таблица one to many employees_notifications

comment on column employees_notifications.employee_id is 'id employee';
comment on column employees_notifications.notification_id is 'id notification';

