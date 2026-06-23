CREATE TABLE product_relationship (
    product_id VARCHAR(36) NOT NULL,
    relationship_type VARCHAR(255),
    rel_product_id VARCHAR(255),
    rel_product_href VARCHAR(255),
    rel_product_name VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES product_record(id)
);

CREATE TABLE product_place (
    product_id VARCHAR(36) NOT NULL,
    place_id VARCHAR(255),
    place_href VARCHAR(255),
    place_name VARCHAR(255),
    place_role VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES product_record(id)
);

CREATE TABLE product_related_party (
    product_id VARCHAR(36) NOT NULL,
    party_id VARCHAR(255),
    party_href VARCHAR(255),
    party_name VARCHAR(255),
    party_role VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES product_record(id)
);
