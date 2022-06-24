create table manufacturer(
    id serial primary key,
    name text
);

create table product(
    id serial primary key,
    name text
);

create table manufacturer_product(
    id serial primary key,
    manufacturer_id int references manufacturer(id),
    product_id int references product(id)
);