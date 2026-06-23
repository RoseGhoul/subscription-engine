CREATE TABLE payment_method (
    id VARCHAR(255) PRIMARY KEY,
    href VARCHAR(255),
    name VARCHAR(255),
    description VARCHAR(255),
    type VARCHAR(50),
    status VARCHAR(50),
    status_date VARCHAR(255),
    masked_pan VARCHAR(255),
    gateway_token VARCHAR(255),
    expiry_date VARCHAR(50),
    account_holder_name VARCHAR(255),
    version BIGINT,
    created_date DATETIME(6),
    last_modified_date DATETIME(6)
);
