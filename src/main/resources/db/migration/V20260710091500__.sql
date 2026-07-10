CREATE TABLE deliveries
(
    id          UUID NOT NULL,
    completed   BOOLEAN,
    order_id    UUID,
    retailer_id UUID,
    CONSTRAINT pk_deliveries PRIMARY KEY (id)
);

ALTER TABLE deliveries
    ADD CONSTRAINT uc_deliveries_order UNIQUE (order_id);

ALTER TABLE deliveries
    ADD CONSTRAINT FK_DELIVERIES_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE deliveries
    ADD CONSTRAINT FK_DELIVERIES_ON_RETAILER FOREIGN KEY (retailer_id) REFERENCES retailers (id);