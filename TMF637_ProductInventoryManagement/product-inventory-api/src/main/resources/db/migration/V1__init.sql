CREATE TABLE product_record (
    id VARCHAR(36) PRIMARY KEY,
    version BIGINT NOT NULL,
    name VARCHAR(255),
    description VARCHAR(1000),
    product_serial_number VARCHAR(255),
    start_date TIMESTAMP(6),
    termination_date TIMESTAMP(6),
    status VARCHAR(50),
    is_bundle BOOLEAN,
    is_customer_visible BOOLEAN,
    product_offering_id VARCHAR(36),
    product_offering_name VARCHAR(255),
    product_specification_id VARCHAR(36),
    product_specification_name VARCHAR(255),
    product_specification_version VARCHAR(50),
    created_date TIMESTAMP(6),
    last_modified_date TIMESTAMP(6)
);
