ALTER TABLE IF EXISTS employee
    ADD COLUMN IF NOT EXISTS work_email VARCHAR(20);

COMMENT ON COLUMN edo.employee.work_email IS 'Рабочая почта';