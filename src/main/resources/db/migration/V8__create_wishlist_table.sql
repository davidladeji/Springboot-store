create table wishlist
(
    user_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    PRIMARY KEY (user_id, product_id),
    constraint fk_wishlist_on_user FOREIGN KEY (user_id) REFERENCES users (id),
    constraint fk_wishlist_on_product FOREIGN KEY (product_id) REFERENCES products (id)
);