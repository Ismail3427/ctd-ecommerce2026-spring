CREATE TABLE cart
(
    id         UUID NOT NULL,
    user_id    UUID,
    quantity   INTEGER,
    product_id UUID,
    CONSTRAINT pk_cart PRIMARY KEY (id)
);

CREATE TABLE products
(
    id             UUID NOT NULL,
    name           VARCHAR(255),
    description    VARCHAR(255),
    price_in_cents INTEGER,
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
    logins_count INTEGER,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE retailers
    ADD CONSTRAINT uc_retailers_accountid UNIQUE (account_id);

ALTER TABLE users
    ADD CONSTRAINT uc_users_userid UNIQUE (user_id);

ALTER TABLE cart
    ADD CONSTRAINT FK_CART_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE cart
    ADD CONSTRAINT FK_CART_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_OWNER FOREIGN KEY (owner_id) REFERENCES retailers (id);

ALTER TABLE retailers
    ADD CONSTRAINT FK_RETAILERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE revchanges
    ADD CONSTRAINT fk_revchanges_on_default_tracking_modified_entities_changelog FOREIGN KEY (rev) REFERENCES revinfo (rev);