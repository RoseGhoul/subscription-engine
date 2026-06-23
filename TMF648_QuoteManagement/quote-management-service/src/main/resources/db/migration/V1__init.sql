CREATE TABLE quote (
    id VARCHAR(36) PRIMARY KEY,
    href VARCHAR(255),
    description VARCHAR(255),
    category VARCHAR(255),
    state VARCHAR(50),
    quote_date DATETIME(6),
    effective_quote_date DATETIME(6),
    expected_fulfillment_start_date DATETIME(6),
    version VARCHAR(50),
    valid_for_start_date_time DATETIME(6),
    valid_for_end_date_time DATETIME(6),
    version_lock BIGINT,
    created_date DATETIME(6),
    last_modified_date DATETIME(6)
);

CREATE TABLE quote_item (
    id VARCHAR(36) PRIMARY KEY,
    quote_id VARCHAR(36),
    action VARCHAR(50),
    quantity_amount FLOAT,
    quantity_units VARCHAR(50),
    product_offering_id VARCHAR(255),
    product_offering_href VARCHAR(255),
    product_offering_name VARCHAR(255),
    FOREIGN KEY (quote_id) REFERENCES quote(id) ON DELETE CASCADE
);
