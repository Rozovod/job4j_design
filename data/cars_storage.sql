create table body(
    id serial primary key,
    body_type_name text
);

create table engine(
    id serial primary key,
    engine_type_name text
);

create table transmission(
    id serial primary key,
    transmission_type_name text
);

create table car(
    id serial primary key,
    car_brand text,
    body_id int references body(id),
    engine_id int references engine(id),
    transmission_id int references transmission(id)
);

insert into body(body_type_name) values 
('Седан'), ('Универсал'), ('Хэтчбек'), ('Купе'),
('Внедорожник'), ('Кроссовер'), ('Лимузин');

insert into engine(engine_type_name) values
('Бензиновый двигатель 1.6'), ('Бензиновый двигатель 2.5'), ('Дизельный двигатель 2.0'), 
('Бензиновый двигатель 1.3'), ('Бензиновый двигатель 1.8'), ('Дизельный двигатель 3.0'), 
('Бензиновый двигатель 2.0');

insert into transmission(transmission_type_name) values 
('Механика'), ('Автомат'), ('Вариативная'), ('Роботизированная');

insert into car(car_brand, body_id, engine_id, transmission_id) values
('BMW X5', 5, 6, 2), ('Mazda RX-7', 4, 4, 1), ('Nissan Skyline X (R34)', 4, 2, 2),
('Honda Civic', 3, 5, 4), ('Lada Largus', 2, 1, 1);

select c.car_brand, b.body_type_name, e.engine_type_name, t.transmission_type_name 
from car c left join body b on c.body_id = b.id
left join engine e on c.engine_id = e.id
left join transmission t on c.transmission_id = t.id;

select * from engine e left join car c on e.id = c.engine_id where c.engine_id is null;
select * from body b left join car c on b.id = c.body_id where c.body_id is null;
select * from transmission t left join car c on t.id = c.transmission_id where c.transmission_id is null; 