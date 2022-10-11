--Создание таблицы

create table orders_products (
    id serial primary key,
    name varchar(50),
    count integer default 0,
    price integer
);

--Наполнение таблицы

insert into orders_products (name, count, price) values ('product_1', 25, 100),
                                                        ('product_2', 30, 200),
                                                        ('product_3', 45, 250),
                                                        ('product_4', 50, 300);

--Команды для проверки разных уровней транзакции

insert into orders_products (name, count, price) values ('product_5', 15, 500);
delete from orders_products where price = 100;
update orders_products set price = 2000 where name = 'product_2';

--Запросы для уровня serializable

select sum(count) from orders_products;
update orders_products set count = 150 where name = 'product_1';