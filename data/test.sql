create table mobilePhone(
    id serial primary key,
    name text,
    model varchar(32),
    memory integer,
    russified boolean
);

insert into mobilePhone
values
(1, 'iPhone', 'XS', '128', true);

update mobilePhone set memory = '64';

delete from mobilePhone;