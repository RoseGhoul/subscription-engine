CREATE TABLE usage_related_party (
    usage_id VARCHAR(36) NOT NULL,
    party_id VARCHAR(100),
    party_href VARCHAR(255),
    party_name VARCHAR(255),
    party_role VARCHAR(100),
    at_referred_type VARCHAR(255),
    CONSTRAINT fk_usage_related_party_usage FOREIGN KEY (usage_id) REFERENCES usage_record(id)
);

CREATE TABLE usage_rated_product_usage (
    usage_id VARCHAR(36) NOT NULL,
    is_billed BOOLEAN,
    is_tax_exempt BOOLEAN,
    offer_tariff_type VARCHAR(100),
    rating_amount_type VARCHAR(100),
    rating_date DATETIME(6),
    tax_rate FLOAT,
    usage_rating_tag VARCHAR(100),
    tax_excluded_amount_unit VARCHAR(50),
    tax_excluded_amount_value FLOAT,
    tax_included_amount_unit VARCHAR(50),
    tax_included_amount_value FLOAT,
    product_ref_id VARCHAR(100),
    product_ref_href VARCHAR(255),
    product_ref_name VARCHAR(255),
    CONSTRAINT fk_usage_rated_prod_usage FOREIGN KEY (usage_id) REFERENCES usage_record(id)
);
