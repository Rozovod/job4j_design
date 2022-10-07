--Пример из задания

create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--Триггерная функция

create or replace function discount()
    returns trigger as
$$
BEGIN
    update products
    set price = price - price * 0.2
    where count <= 5 AND id = new.id;
    return NEW;
END;
$$
    LANGUAGE 'plpgsql';

--Триггер на row уровне

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

--Проверяем работу триггера

insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

--Отключение триггера

alter table products disable trigger discount_trigger;

--Проверка после отключения

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

--Удаление триггера

drop trigger discount_trigger on products;

--Функция для нового триггера

create or replace function tax()
returns trigger as
    $$
        BEGIN
            update products
            set price = price - price * 0.2
            where id = (select id from inserted) and count <=5;
            return new;
        END;
    $$
LANGUAGE 'plpgsql';

--Триггер на statement уровне

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

drop trigger tax_trigger on products;

--Решение задания под пунктом 1)

--Функция для прибаления налога после вставки

create or replace function tax_increase_statement()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from increase);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

--Триггер на statement уровень

create trigger tax_increase_trigger_statement
    after insert on products
    referencing new table as increase
    for each statement
    execute procedure tax_increase_statement();

insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_2', 8, 100);

--Решение задания под пунктом 2)

--Триггерная функция уровня row для прибавления налога

create or replace function tax_increase_row()
    returns trigger as
$$
    BEGIN
        update products
        new.price = new.price + new.price * 0.2
        where id = new.id;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

--Триггер на row уровень

create trigger tax_increase_trigger_row
    before insert
    on products
    for each row
    execute procedure tax_increase_row();

insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 3, 200);
insert into products (name, producer, count, price) VALUES ('product_6', 'producer_6', 8, 250);

--Решение задания под пунктом 3)

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

--Триггерная функция

create or replace function history_stamp()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
        VALUES (new.name, new.price, now());
        return new;
    end;
$$
LANGUAGE 'plpgsql';

--Триггер

create trigger history_trigger
    after insert on products
    for each row
    execute procedure history_stamp();

insert into products (name, producer, count, price) VALUES ('product_7', 'producer_7', 4, 250);
insert into products (name, producer, count, price) VALUES ('product_8', 'producer_8', 10, 300);