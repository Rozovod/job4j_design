create table type(
    id serial primary key,
    name text
);

create table product(
    id serial primary key,
    name text,
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type(name) 
values ('Хлеб'), ('Овощи'), ('Фрукты'), ('Сыр'), ('Молоко'), ('Мороженое'), ('Крупы');

insert into product(name, type_id, expired_date, price) values 
('Пшеничный хлеб', 1, date '2022-06-26', 50.00), 
('Ржаной хлеб', 1, date '2022-06-29', 40.00),
('Цельнозерновой хлеб', 1, date '2022-06-30', 45.00),
('Отрубной хлеб', 1, date '2022-06-28', 35.00),
('Бездрожжевой хлеб', 1, date '2022-07-01', 50.00),
('Капуста новый урожай', 2, date '2022-07-30', 49.00),
('Картофель белый', 2, date '2022-10-30', 69.00),
('Кабачки', 2, date '2022-07-10', 79.00),
('Баклажан', 2, date '2022-07-11', 129.00),
('Болгарский перец', 2, date '2022-06-26', 199.00),
('Морковь', 2, date '2022-07-30', 49.00),
('Яблоки Голден', 3, date '2022-07-12', 99.00),
('Бананы', 3, date '2022-07-10', 79.00),
('Груши', 3, date '2022-07-09', 129.00),
('Персики', 3, date '2022-06-26', 149.00),
('Апельсин', 3, date '2022-07-13', 119.00),
('Cыр Адыгейский', 4, date '2022-07-14', 845.00),
('Сыр Моцарелла', 4, date '2022-07-15', 600.00),
('Сыр Рикотта', 4, date '2022-07-14', 360.00),
('Сыр Филадельфия', 4, date '2022-07-12', 740.00),
('Сыр Гауда', 4, date '2022-07-10', 759.00),
('Сыр Сулугуни', 4, date '2022-06-26', 610.00),
('Молоко коровье цельное', 5, date '2022-06-30', 150.00),
('Молоко козье цельное', 5, date '2022-06-29', 250.00),
('Молоко коровье 3,2%', 5, date '2022-06-30', 80.00),
('Мороженое пломбир в вафельном стаканчике', 6, date '2022-07-30', 67.00),
('Мороженое рожок сливочное', 6, date '2022-07-31', 89.00),
('Мороженое эскимо в шоколадной глазури', 6, date '2022-06-26', 79.00),
('Мороженое пломбир в вафелях', 6, date '2022-07-29', 70.00),
('Крупа гречневая', 7, date '2022-12-30', 149.00),
('Крупа перловая', 7, date '2022-12-30', 49.00),
('Булгур', 7, date '2022-12-28', 179.00),
('Крупа манная', 7, date '2023-02-12', 189.00),
('Киноа', 7, date '2023-02-01', 799.00),
('Рис черный', 7, date '2023-02-07', 250.00),
('Чечевица колотая красная', 7, date '2023-03-07', 360.00),
('Кускус', 7, date '2023-02-03', 389.00),
('Рис бурый', 7, date '2023-04-20', 170.00),
('Полба пшеничная', 7, date '2022-06-25', 160.00),
('Рис красный рубин', 7, date '2023-02-18', 380.00);

select * from product p
join type t on p.type_id = t.id
where t.name like 'Сыр%';

select * from product where name like 'Мороженое%';

select * from product where expired_date < '2022-06-27';

select * from product
where price = (select max(price) from product);

select t.name, count(t.name) from product p
join type t on p.type_id = t.id
group by t.name;

select * from product p
join type t on p.type_id = t.id
where t.name like 'Сыр%' or t.name like 'Молоко%';

select t.name, count(t.name) from product p
join type t on p.type_id = t.id
group by t.name
having count(t.name) < 10;

select p.name, t.name from product p
join type t on p.type_id = t.id;