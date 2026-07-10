CREATE TABLE locations
(
    id      UUID NOT NULL,
    address VARCHAR(255),
    CONSTRAINT pk_locations PRIMARY KEY (id)
);