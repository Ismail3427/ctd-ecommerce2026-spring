ALTER TABLE products
    ADD category VARCHAR(255);

ALTER TABLE products
    ADD location_id UUID;

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_LOCATION FOREIGN KEY (location_id) REFERENCES locations (id);

ALTER TABLE products
DROP
COLUMN delivery_address;