CREATE TABLE retailer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    retailer_name VARCHAR(255),
    purchase_date DATE,
    purchase_time TIME,
    response VARCHAR(255),
    points int,
    total DECIMAL(10, 2)
);

CREATE TABLE items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    retailer_id BIGINT,
    short_description VARCHAR(255),
    price DECIMAL(10, 2),
    FOREIGN KEY (retailer_id) REFERENCES retailer(id)
);

