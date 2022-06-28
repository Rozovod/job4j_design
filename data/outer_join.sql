create table departments(
    id serial primary key,
    name text
);

create table employees(
    id serial primary key,
    name text,
    department_id int references departments(id)
);

insert into departments(name) values ('Отдел_1'), ('Отдел_2'), ('Отдел_3'), ('Отдел_4');

insert into employees(name, department_id)
values ('Сотрудник_1', 1), ('Сотрудник_2', 1), ('Сотрудник_3', 1), ('Сотрудник_4', 2), ('Сотрудник_5', 2), ('Сотрудник_6', 3);

select * from employees e left join departments d on e.department_id = d.id;

select * from employees e right join departments d on e.department_id = d.id;

select * from employees e full join departments d on e.department_id = d.id;

select * from employees e cross join departments d;

select * from departments d left join employees e on d.id = e.department_id where e.name is null;

select * from employees e left join departments d on e.department_id = d.id;
select * from employees e right join departments d on e.department_id = d.id;

create table teens(
    name varchar(32),
    gender varchar(1)
);

insert into teens(name, gender) values
('Иван', 'м'),
('Степан', 'м'),
('Александр', 'м'),
('Мария', 'ж'),
('Дарья', 'ж'),
('Валентина', 'ж');

select t1.name as male, t2.name as female, (t1.name || '+' || t2.name) as pair
from teens t1 cross join teens t2 where t1.gender != t2.gender;