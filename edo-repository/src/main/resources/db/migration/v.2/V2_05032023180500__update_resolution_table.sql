ALTER TABLE IF EXISTS resolution
    DROP CONSTRAINT if exists resolution_creator_id_key,
    DROP CONSTRAINT if exists resolution_signer_id_key,
    DROP CONSTRAINT if exists resolution_curator_id_key,
    ALTER COLUMN creator_id SET NOT NULL,
    ADD COLUMN IF NOT EXISTS question_id   bigint  not null references question (id);




