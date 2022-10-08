--Пример из задания

create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--Создание хранимой процедуры для вставки данных

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$

--Вызов хранимой процедуры

call insert_data('product_9', 'producer_9', 15, 32);

--Создание хранимой процедуры для обновления данных

create or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
        end if;
    end;
$$

--Вызов хранимой процедуры и работа с данными

call update_data(10, 0, 1);

call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_3', 'producer_3', 8, 115);

call update_data(0, 0.2, 0);

--Изменение имени процедуры

alter procedure update_data(u_count integer, tax float, u_id integer) rename to update;

--Удаление хранимой процедуры

drop procedure update(u_count integer, tax double precision, u_id integer);

--Очистка таблицы перед использованием хранимых функций

delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

--Создание хранимой функции на добавление записей

create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
    language 'plpgsql'
as $$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$

--Вызов хранимой функции

select f_insert_data('product_1', 'producer_1', 25, 50);

--Процедура для редактирования записей с возвращаемым значением

create or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    DECLARE
        result integer;
    begin
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
            select into result count from products where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
            select into result sum(price) from products;
        end if;
        return result;
    end;
$$;

--Вызов хранимой функции и работа с данными

select f_update_data(10, 0, 1);

select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);

select f_update_data(0, 0.2, 0);

--Выполнение задания. Создание хранимой процедуры удаления записи по id

create or replace procedure delete_data(d_id integer)
language 'plpgsql'
as $$
    BEGIN
        delete from products where id = d_id;
    end;
$$

--Вызов хранимой процедуры удаления

call delete_data(1);

--Создание хранимой функции удаления записи по id. Функция возвращает имя удаленного товара.

create or replace function f_delete_data(d_id integer)
returns varchar(50)
language 'plpgsql'
as
$$
    DECLARE
        result_name varchar(50);
    begin
        select into result_name name from products where id = d_id;
        delete from products where id = d_id;
        return result_name;
    end;
$$;

--Выполнение хранимой функции удаления по id

select f_delete_data(2);
