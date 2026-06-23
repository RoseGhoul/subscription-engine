CREATE TABLE product_characteristic (
    product_id VARCHAR(36) NOT NULL,
    name VARCHAR(255),
    value_type VARCHAR(255),
    char_value VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES product_record(id)
);

CREATE TABLE product_price (
    product_id VARCHAR(36) NOT NULL,
    name VARCHAR(255),
    description VARCHAR(1000),
    price_type VARCHAR(255),
    recurring_charge_period VARCHAR(255),
    unit_of_measure VARCHAR(255),
    price_value FLOAT,
    price_unit VARCHAR(50),
    FOREIGN KEY (product_id) REFERENCES product_record(id)
);
