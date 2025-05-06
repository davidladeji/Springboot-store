create table categories (
    id      TINYINT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL
);

create table products (
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    price   DECIMAL(10, 2) NOT NULL,
    category_id   TINYINT,
    Constraint fk_category
        Foreign Key (category_id) REFERENCES categories (id)
            On Delete Restrict
);