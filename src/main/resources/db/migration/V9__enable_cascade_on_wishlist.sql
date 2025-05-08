alter table wishlist
   drop FOREIGN KEY fk_wishlist_on_product;

alter table wishlist
   add constraint fk_wishlist_on_product 
   FOREIGN Key (product_id) REFERENCES products(id) 
   on delete cascade;