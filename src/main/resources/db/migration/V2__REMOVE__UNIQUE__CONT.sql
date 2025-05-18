-- Removendo a restrição UNIQUE da coluna work_shift_id
ALTER TABLE tb_user DROP CONSTRAINT tb_user_work_shift_id_key;

-- Garantindo que work_shift_id ainda pode ter valores nulos, se necessário
ALTER TABLE tb_user ALTER COLUMN work_shift_id DROP NOT NULL;