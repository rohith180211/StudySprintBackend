CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    role VARCHAR(20) NOT NULL,
    points INT NOT NULL DEFAULT 0,
    current_streak INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE tasks (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(200) NOT NULL,
    subject VARCHAR(100),
    completed BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    completed_at TIMESTAMP,

    CONSTRAINT fk_tasks_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);


CREATE INDEX idx_users_email ON users(email);



CREATE TABLE study_sessions (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP,
    duration_minutes INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_sessions_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);


CREATE INDEX idx_sessions_user_start
    ON study_sessions(user_id, start_time);

CREATE INDEX idx_sessions_user_created
    ON study_sessions(user_id, created_at);


CREATE TABLE break_activities (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT TRUE
);

INSERT INTO break_activities (title, description) VALUES
('Stretch Break', 'Stand up and stretch your arms, neck, and back for 2 minutes'),
('Hydration Check', 'Drink a glass of water slowly'),
('Breathing Reset', 'Close your eyes and take 5 deep breaths'),
('Desk Reset', 'Tidy your desk or study space'),
('Eye Rest', 'Look at something far away for 1 minute'),
('Quick Walk', 'Walk around your room or hallway for 2 minutes'),
('Reflection', 'Write one sentence about what you just studied'),
('Posture Fix', 'Adjust your sitting posture and relax your shoulders');


