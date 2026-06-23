CREATE TABLE payment (
    id VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    name VARCHAR(255),
    status VARCHAR(255),
    authorization_code VARCHAR(255),
    correlator_id VARCHAR(255),
    payment_date DATETIME(6),
    amount_value FLOAT,
    amount_unit VARCHAR(255),
    created_date DATETIME(6),
    last_modified_date DATETIME(6),
    created_by VARCHAR(255),
    last_modified_by VARCHAR(255),
    version BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE refund (
    id VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    name VARCHAR(255),
    status VARCHAR(255),
    authorization_code VARCHAR(255),
    correlator_id VARCHAR(255),
    refund_date DATETIME(6),
    amount_value FLOAT,
    amount_unit VARCHAR(255),
    created_date DATETIME(6),
    last_modified_date DATETIME(6),
    created_by VARCHAR(255),
    last_modified_by VARCHAR(255),
    version BIGINT,
    PRIMARY KEY (id)
);
