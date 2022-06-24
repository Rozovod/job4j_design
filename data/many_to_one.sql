create table manufacturer(
    id serial primary key,
    name text
);

create table product(
    id serial primary key,
    name text,
    manufacturer_id int references manufacturer(id)
);