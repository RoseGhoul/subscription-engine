CREATE TABLE usage_characteristic (
    usage_id VARCHAR(36) NOT NULL,
    name VARCHAR(255) NOT NULL,
    value_type VARCHAR(100),
    char_value VARCHAR(255),
    CONSTRAINT fk_usage_char_usage FOREIGN KEY (usage_id) REFERENCES usage_record(id)
);

ALTER TABLE usage_record ADD COLUMN usage_spec_id VARCHAR(100);
ALTER TABLE usage_record ADD COLUMN usage_spec_href VARCHAR(255);
ALTER TABLE usage_record ADD COLUMN usage_spec_name VARCHAR(255);
