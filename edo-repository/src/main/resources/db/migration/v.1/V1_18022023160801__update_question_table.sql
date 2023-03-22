ALTER TABLE IF EXISTS question
    ADD COLUMN IF NOT EXISTS resolution_id bigint references resolution (id), -- Резолюция
    ADD COLUMN IF NOT EXISTS theme_id      bigint references theme (id) ;      -- Тема

