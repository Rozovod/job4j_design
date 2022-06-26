create table manufacturer (
    id serial primary key,
    name text
);

create table mobile_phone (
    id serial primary key,
    model text,
    memory int,
    manufacturer_id int references manufacturer(id)
);

insert into manufacturer(name) values('iPhone');
insert into manufacturer(name) values('Xiaomi');
insert into manufacturer(name) values('Samsung');

insert into mobile_phone(model, memory, manufacturer_id)
values('XR', 64, 1);
insert into mobile_phone(model, memory, manufacturer_id)
values('XS', 128, 1);
insert into mobile_phone(model, memory, manufacturer_id)
values('13', 256, 1);
insert into mobile_phone(model, memory, manufacturer_id)
values('Redmi 4X', 16, 2);
insert into mobile_phone(model, memory, manufacturer_id)
values('Mi 10 Pro', 32, 2);
insert into mobile_phone(model, memory, manufacturer_id)
values('Black Shark 4 Pro', 64, 2);
insert into mobile_phone(model, memory, manufacturer_id)
values('Galaxy S22', 64, 3);
insert into mobile_phone(model, memory, manufacturer_id)
values('Galaxy Z Flip3 5G', 256, 3);
insert into mobile_phone(model, memory, manufacturer_id)
values('Galaxy A52', 128, 3);

select * from mobile_phone
join manufacturer m
on mobile_phone.manufacturer_id = m.id;

select m.name, mp.model, mp.memory
from mobile_phone as mp
join manufacturer as m
on manufacturer_id = m.id;

select m.name || '; ' || mp.model || '; ' 
|| mp.memory || ' Gb.' as "full information"
from mobile_phone as mp
join manufacturer as m
on manufacturer_id = m.id;