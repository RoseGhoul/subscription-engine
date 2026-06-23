CREATE TABLE communication_message (
    id VARCHAR(36) PRIMARY KEY,
    href VARCHAR(255),
    description VARCHAR(255),
    send_time DATETIME(6),
    state VARCHAR(50),
    subject VARCHAR(255),
    content TEXT,
    channel VARCHAR(50),
    receiver JSON,
    template JSON,
    characteristic JSON,
    base_type VARCHAR(100),
    schema_location VARCHAR(255),
    type VARCHAR(100),
    created_date DATETIME(6),
    last_modified_date DATETIME(6),
    version_lock BIGINT
);
