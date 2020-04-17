# Creates a one to many db for testing

DROP DATABASE demodb_db;
CREATE DATABASE demodb_db;
USE demodb_db;

CREATE TABLE demodb_db.warehouses(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40)
);

INSERT INTO demodb_db.warehouses(name) VALUES
("First Store"),
("Second Store"),
("Third Store"),
("Fourth Store");

CREATE TABLE demodb_db.books(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40),
    warehouse_id INT,
	CONSTRAINT fk_books_warehouses
	FOREIGN KEY (warehouse_id)
    REFERENCES warehouses(id)
    ON UPDATE SET NULL
);

INSERT INTO demodb_db.books(name, warehouse_id) VALUES
("First book", 1),
("Second book", 1),
("Third book", 1),
("Fourth book", 2),
("Fifth book", 2),
("Sixth book", 3);