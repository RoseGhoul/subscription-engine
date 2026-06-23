CREATE TABLE usage_record (
    id VARCHAR(36) PRIMARY KEY,
    href VARCHAR(255),
    description VARCHAR(255),
    usage_date DATETIME(6) NOT NULL,
    usage_type VARCHAR(100) NOT NULL,
    status VARCHAR(50),
    at_base_type VARCHAR(255),
    at_schema_location VARCHAR(255),
    at_type VARCHAR(255),
    created_date DATETIME(6) NOT NULL,
    last_modified_date DATETIME(6) NOT NULL,
    version BIGINT NOT NULL
);
