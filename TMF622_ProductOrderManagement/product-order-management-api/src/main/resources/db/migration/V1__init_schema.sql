CREATE TABLE product_order (
    id VARCHAR(255) NOT NULL,
    href VARCHAR(255),
    external_id VARCHAR(255),
    description VARCHAR(255),
    category VARCHAR(255),
    priority VARCHAR(255),
    order_date DATETIME(6),
    requested_start_date DATETIME(6),
    requested_completion_date DATETIME(6),
    expected_completion_date DATETIME(6),
    completion_date DATETIME(6),
    cancellation_date DATETIME(6),
    cancellation_reason VARCHAR(255),
    state VARCHAR(255),
    notification_contact VARCHAR(255),
    billing_account_href VARCHAR(255),
    billing_account_id VARCHAR(255),
    billing_account_name VARCHAR(255),
    created_date DATETIME(6),
    last_modified_date DATETIME(6),
    PRIMARY KEY (id)
);

CREATE TABLE product_order_item (
    id VARCHAR(255) NOT NULL,
    product_order_id VARCHAR(255),
    action VARCHAR(255),
    product_offering_href VARCHAR(255),
    product_offering_id VARCHAR(255),
    product_offering_name VARCHAR(255),
    quantity INT,
    state VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_order_item_order FOREIGN KEY (product_order_id) REFERENCES product_order(id) ON DELETE CASCADE
);

CREATE TABLE product_order_related_party (
    product_order_id VARCHAR(255) NOT NULL,
    party_id VARCHAR(255),
    party_href VARCHAR(255),
    party_name VARCHAR(255),
    party_role VARCHAR(255),
    CONSTRAINT fk_related_party_order FOREIGN KEY (product_order_id) REFERENCES product_order(id) ON DELETE CASCADE
);

CREATE TABLE product_order_note (
    product_order_id VARCHAR(255) NOT NULL,
    note_id VARCHAR(255),
    note_author VARCHAR(255),
    note_date DATETIME(6),
    note_text VARCHAR(1000),
    CONSTRAINT fk_note_order FOREIGN KEY (product_order_id) REFERENCES product_order(id) ON DELETE CASCADE
);

CREATE TABLE product_order_price (
    id VARCHAR(255) NOT NULL,
    product_order_id VARCHAR(255),
    product_order_item_id VARCHAR(255),
    name VARCHAR(255),
    description VARCHAR(255),
    price_type VARCHAR(255),
    recurring_charge_period VARCHAR(255),
    duty_free_value FLOAT,
    duty_free_unit VARCHAR(255),
    tax_included_value FLOAT,
    tax_included_unit VARCHAR(255),
    tax_rate FLOAT,
    percentage FLOAT,
    item_price_type VARCHAR(255),
    unit_of_measure VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_price_order FOREIGN KEY (product_order_id) REFERENCES product_order(id) ON DELETE CASCADE
);
CREATE TABLE product_order_price_alteration (
    id VARCHAR(255) NOT NULL,
    order_price_id VARCHAR(255),
    name VARCHAR(255),
    description VARCHAR(255),
    price_type VARCHAR(255),
    priority INT,
    application_duration INT,
    recurring_charge_period VARCHAR(255),
    unit_of_measure VARCHAR(255),
    percentage FLOAT,
    tax_rate FLOAT,
    duty_free_value FLOAT,
    duty_free_unit VARCHAR(255),
    tax_included_value FLOAT,
    tax_included_unit VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_alteration_order_price FOREIGN KEY (order_price_id) REFERENCES product_order_price(id) ON DELETE CASCADE
);
