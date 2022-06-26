create table fauna(
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values('Anopheles', 14, date '1818-01-01');
insert into fauna(name, avg_age, discovery_date)
values('white stork', 7300, date '1758-01-01');
insert into fauna(name, avg_age, discovery_date)
values('alligator', 18250, date '1802-01-01');
insert into fauna(name, avg_age, discovery_date)
values('hippo', 14600, date '1758-01-01');
insert into fauna(name, avg_age, discovery_date)
values('polar bear', 10950, date '1774-01-01');
insert into fauna(name, avg_age, discovery_date)
values('pike fish', 7300, date '1758-01-01');
insert into fauna(name, avg_age, discovery_date)
values('dolphin fish', 18250, date '1821-01-01');

select * from fauna where name like '%fish%';

select * from fauna where avg_age > 10000 and avg_age < 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '1950-01-01';