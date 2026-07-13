CREATE TABLE cart
(
    id         UUID NOT NULL,
    user_id    UUID,
    showing    BOOLEAN,
    quantity   INTEGER,
    product_id UUID,
    CONSTRAINT pk_cart PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id                 UUID NOT NULL,
    name               VARCHAR(255),
    amount_of_products INTEGER,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE deliveries
(
    id                       UUID NOT NULL,
    completed                BOOLEAN,
    order_id                 UUID,
    retailer_id              UUID,
    shipping_address         VARCHAR(255),
    product_start_address_id UUID,
    CONSTRAINT pk_deliveries PRIMARY KEY (id)
);

CREATE TABLE discounts
(
    id         UUID NOT NULL,
    product_id UUID,
    offer      DOUBLE PRECISION,
    name       VARCHAR(255),
    CONSTRAINT pk_discounts PRIMARY KEY (id)
);

CREATE TABLE locations
(
    id      UUID NOT NULL,
    address VARCHAR(255),
    CONSTRAINT pk_locations PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id        UUID NOT NULL,
    user_id   UUID,
    completed BOOLEAN,
    status    VARCHAR(255),
    timestamp TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE orders_cart
(
    cart_id   UUID,
    orders_id UUID NOT NULL,
    CONSTRAINT pk_orders_cart PRIMARY KEY (orders_id)
);

CREATE TABLE products
(
    id             UUID NOT NULL,
    name           VARCHAR(255),
    description    VARCHAR(255),
    price_in_cents INTEGER,
    stock          INTEGER,
    is_available   BOOLEAN,
    is_showing     BOOLEAN,
    location_id    UUID,
    owner_id       UUID,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE retailers
(
    id           UUID NOT NULL,
    name         VARCHAR(255),
    account_id   VARCHAR(255),
    user_id      UUID,
    date_created TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_retailers PRIMARY KEY (id)
);

CREATE TABLE revchanges
(
    rev        BIGINT NOT NULL,
    entityname VARCHAR(255)
);

CREATE TABLE revinfo
(
    rev      BIGINT NOT NULL,
    revtstmp BIGINT,
    CONSTRAINT pk_revinfo PRIMARY KEY (rev)
);

CREATE TABLE users
(
    id           UUID NOT NULL,
    user_id      VARCHAR(255),
    email        VARCHAR(255),
    ip_address   VARCHAR(255),
    name         VARCHAR(255),
    logins_count INTEGER,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE deliveries
    ADD CONSTRAINT uc_deliveries_order UNIQUE (order_id);

ALTER TABLE retailers
    ADD CONSTRAINT uc_retailers_accountid UNIQUE (account_id);

ALTER TABLE retailers
    ADD CONSTRAINT uc_retailers_user UNIQUE (user_id);

ALTER TABLE users
    ADD CONSTRAINT uc_users_user UNIQUE (user_id);

ALTER TABLE cart
    ADD CONSTRAINT FK_CART_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE cart
    ADD CONSTRAINT FK_CART_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE deliveries
    ADD CONSTRAINT FK_DELIVERIES_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE deliveries
    ADD CONSTRAINT FK_DELIVERIES_ON_PRODUCTSTARTADDRESS FOREIGN KEY (product_start_address_id) REFERENCES locations (id);

ALTER TABLE deliveries
    ADD CONSTRAINT FK_DELIVERIES_ON_RETAILER FOREIGN KEY (retailer_id) REFERENCES retailers (id);

ALTER TABLE discounts
    ADD CONSTRAINT FK_DISCOUNTS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_LOCATION FOREIGN KEY (location_id) REFERENCES locations (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_OWNER FOREIGN KEY (owner_id) REFERENCES retailers (id);

ALTER TABLE retailers
    ADD CONSTRAINT FK_RETAILERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE orders_cart
    ADD CONSTRAINT fk_ordcar_on_cart_model FOREIGN KEY (cart_id) REFERENCES cart (id);

ALTER TABLE orders_cart
    ADD CONSTRAINT fk_ordcar_on_orders_model FOREIGN KEY (orders_id) REFERENCES orders (id);

ALTER TABLE revchanges
    ADD CONSTRAINT fk_revchanges_on_default_tracking_modified_entities_changelog FOREIGN KEY (rev) REFERENCES revinfo (rev);