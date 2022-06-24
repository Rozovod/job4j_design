create table imei(
    id serial primary key,
    number varchar(32)
);

create table mobilePhone(
    id serial primary key,
    name varchar(32),
    model varchar(32),
    imei_id int references imei(id) unique
);