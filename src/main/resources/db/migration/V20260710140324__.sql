ALTER TABLE deliveries
    ADD product_start_address_id UUID;

ALTER TABLE deliveries
    ADD CONSTRAINT FK_DELIVERIES_ON_PRODUCTSTARTADDRESS FOREIGN KEY (product_start_address_id) REFERENCES locations (id);

ALTER TABLE deliveries
DROP
COLUMN product_address;