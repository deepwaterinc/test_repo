ALTER TABLE IF EXISTS appeal
    ADD COLUMN IF NOT EXISTS way_to_answer  varchar;

comment on column appeal.way_to_answer is 'Способ ответа';