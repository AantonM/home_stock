drop schema homestock CASCADE;

CREATE SCHEMA homestock;

CREATE TABLE homestock.product
(
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(2000) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE homestock.unit
(
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE homestock.stock
(
    id INT GENERATED ALWAYS AS IDENTITY,
    product_id INT NOT NULL REFERENCES homestock.product(id),
    unit_id INT NOT NULL REFERENCES homestock.unit(id),
    current_quantity INT NOT NULL CHECK (current_quantity => 0),
    PRIMARY KEY (id)
);

CREATE TABLE homestock.stock_movement
(
    id INT GENERATED ALWAYS AS IDENTITY,
    stock_id INT NOT NULL REFERENCES homestock.stock(id),
    date DATE NOT NULL,
    quantity INT NOT NULL,
    up BOOLEAN NOT NULL CHECK( up != down),
    down BOOLEAN NOT NULL CHECK( down != up),
    PRIMARY KEY (id)
);

-- CREATE EXAMPLE DATA
INSERT INTO homestock.product(name) VALUES ('Toothpaste');
INSERT INTO homestock.product(name) VALUES ('Dishwashing liquid');
INSERT INTO homestock.product(name) VALUES ('Laundry Pods');

INSERT INTO homestock.unit(name) VALUES ('ml');
INSERT INTO homestock.unit(name) VALUES ('piece');
INSERT INTO homestock.unit(name) VALUES ('Length');
INSERT INTO homestock.unit(name) VALUES ('pack');

INSERT INTO homestock.stock(product_id, unit_id, current_quantity) VALUES (1, 1, 50);
INSERT INTO homestock.stock(product_id, unit_id, current_quantity) VALUES (2, 1, 100);
INSERT INTO homestock.stock(product_id, unit_id, current_quantity) VALUES (3, 2, 28);
