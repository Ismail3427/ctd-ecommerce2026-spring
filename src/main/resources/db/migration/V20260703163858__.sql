CREATE TABLE carts
(
    id      UUID NOT NULL,
    user_id VARCHAR(255),
    CONSTRAINT pk_carts PRIMARY KEY (id)
);

CREATE TABLE carts_products
(
    carts_id    UUID NOT NULL,
    products_id UUID NOT NULL
);

ALTER TABLE carts_products
    ADD CONSTRAINT uc_carts_products_products UNIQUE (products_id);

ALTER TABLE carts
    ADD CONSTRAINT uc_carts_userid UNIQUE (user_id);

ALTER TABLE carts_products
    ADD CONSTRAINT fk_carpro_on_cart_model FOREIGN KEY (carts_id) REFERENCES carts (id);

ALTER TABLE carts_products
    ADD CONSTRAINT fk_carpro_on_product_model FOREIGN KEY (products_id) REFERENCES products (id);