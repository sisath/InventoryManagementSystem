drop database if exists ims;
create database if not exists ims;
use ims;

drop table if exists order_product;
drop table if exists orders;
drop table if exists customer;
drop table if exists product;

create table if not exists ims.customer (
    customer_id bigint not null auto_increment,
    first_name varchar(80) default null,
    surname varchar(80) default null,
    email varchar(80) default null,
    postcode varchar(20) default null,
    primary key (customer_id)
);

create table if not exists ims.product (
    product_id bigint not null auto_increment,
    price decimal(10,2) default null,
    product_name varchar(80) default null,
    description varchar(100) default null,
    primary key (product_id)
);

create table if not exists ims.orders (
    order_id bigint not null auto_increment,
    fk_customer_id bigint default null,
    primary key (order_id),
    foreign key (fk_customer_id) references customer(customer_id) on delete cascade
);

create table if not exists ims.order_product (
order_product_id bigint not null auto_increment,
fk_product_id bigint default null,
fk_order_id bigint default null,
quantity bigint default null,
primary key (order_product_id),
foreign key(fk_product_id) references product(product_id) on delete cascade,
foreign key(fk_order_id) references orders(order_id) on delete cascade
);
