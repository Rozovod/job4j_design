create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) 
values ('mobile_phone', 25000), ('electric_scooter', 40000), ('notebook', 80000);

insert into people(name) values ('Аня'), ('Иван'), ('Степан');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (1, 2), (2, 2);
insert into devices_people(device_id, people_id) values (1, 3), (3, 3);

select avg(price) from devices;

select p.name, avg(d.price)
from people p
join devices_people dp on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from people p
join devices_people dp on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;