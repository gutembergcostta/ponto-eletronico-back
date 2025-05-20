CREATE TABLE IF NOT EXISTS tb_user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    work_shift_type VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tb_worklog (
    id SERIAL PRIMARY KEY,
    work_log_selected TIMESTAMP NOT NULL,
    delayed_check_in BOOLEAN NOT NULL,
    user_id INT NOT NULL,
    work_log_status VARCHAR(10) DEFAULT 'OPEN',
    FOREIGN KEY (user_id) REFERENCES tb_user(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO tb_user (name, email, password, role, work_shift_type) 
VALUES ('Administrador', 'admin@worklog.com', '$2a$10$nOZ7QoPR./3nVQNQ5h81N.LqHv7fBK4jd8f7cQQznAmJQSZ3gPK4O', 'ADMIN', 'EIGHT_HOURS');