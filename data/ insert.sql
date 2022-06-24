insert into role(name) values('Покупатель');
insert into category(name_category) values('Заказ');
insert into state(status) values('В обработке');
insert into users(name, phone, role_id) values('Петр', '+79998887766', 1);
insert into rules(name) values('Оформление заказа');
insert into role_rules(role_id, rules_id) values(1, 1);
insert into item(number, users_id, category_id, state_id) values('1', 1, 1, 1);
insert into comments(comment, item_id) values('Добавьте, пожалуйста, к заказу товар из прикрепленного видео', 1);
insert into attachs(link_on_file, item_id) values('https://www.youtube.com/watch?v=NoKnGpj6E68', 1);