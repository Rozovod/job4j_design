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

--Начало транзакции

begin transaction;

--Вставка данных в таблицу

insert into orders_products (name, count, price) values ('product_5', 15, 500);

--Создание точки сохранения

savepoint first_savepoint;

--Другие команды изменения таблицы

delete from orders_products where price = 100;
update orders_products set price = 2000 where name = 'product_2';

--Еще одна точка сохранения

savepoint second_savepoint;

--Удаление всех данных в таблице

delete from orders_products;

--Возврат к первой точке сохранения

rollback to first_savepoint;

--Возврат ко второй точке сохранения(в зависимости от необходимых действий)

rollback to second_savepoint;

--
--Возврат к исходному виду таблицы до изменений в транзакции(при необходимости)
--Она же завершает транзакцию
--

rollback;

--Завершение транзакции с сохранением изменений. Не работает после rollback.

commit;