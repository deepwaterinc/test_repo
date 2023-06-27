CREATE TABLE approval_sheet
(
    id                    bigserial NOT NULL,
    approval_urgency      VARCHAR(255)                            NOT NULL,
    parent_approval_sheet BIGINT,
    initiator_comment     VARCHAR(255),
    creation_date         TIMESTAMP with time zone                NOT NULL,
    referral_date         TIMESTAMP with time zone,
    signing_date          TIMESTAMP with time zone,
    archived_date         TIMESTAMP with time zone,
    number                VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_approval_sheet PRIMARY KEY (id)
);

ALTER TABLE approval_sheet
    ADD CONSTRAINT FK_APPROVAL_SHEET_ON_PARENT_APPROVAL_SHEET FOREIGN KEY (parent_approval_sheet) REFERENCES approval_sheet (id);

comment on column approval_sheet.approval_urgency is 'Срочность рассмотрения';
comment on column approval_sheet.parent_approval_sheet is 'Ссылка на родительский лист согласования';
comment on column approval_sheet.initiator_comment is 'Комментарий инициатора';
comment on column approval_sheet.creation_date is 'Дата создания листа согласования';
comment on column approval_sheet.referral_date is 'Дата перевода направления на согласование';
comment on column approval_sheet.signing_date is 'Дата подписания';
comment on column approval_sheet.archived_date is 'Дата перевода в архив';
comment on column approval_sheet.number is 'Номер листа согласования';