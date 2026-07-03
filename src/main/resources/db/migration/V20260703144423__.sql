CREATE SEQUENCE IF NOT EXISTS revinfo_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE products
(
    id             UUID NOT NULL,
    name           VARCHAR(255),
    description    VARCHAR(255),
    price_in_cents INTEGER,
    user_id        UUID,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE retailers
(
    id           UUID NOT NULL,
    name         VARCHAR(255),
    account_id   VARCHAR(255),
    user_id      VARCHAR(255),
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

ALTER TABLE retailers
    ADD CONSTRAINT uc_retailers_accountid UNIQUE (account_id);

ALTER TABLE retailers
    ADD CONSTRAINT uc_retailers_userid UNIQUE (user_id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_USER FOREIGN KEY (user_id) REFERENCES retailers (id);

ALTER TABLE revchanges
    ADD CONSTRAINT fk_revchanges_on_default_tracking_modified_entities_changelog FOREIGN KEY (rev) REFERENCES revinfo (rev);