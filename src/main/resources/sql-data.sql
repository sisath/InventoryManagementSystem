insert into customer (first_name, surname, email, postcode) values ('Siso', 'A', 'siso@gmail.com', 'RM93HJ');
insert into product (price, product_name, description) values (1, 'Notebook', 'Very nice notebook');
insert into orders (fk_customer_id) values (1);
insert into order_product (fk_order_id,fk_product_id,quantity) values (1,1,5);