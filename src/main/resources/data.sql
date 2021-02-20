insert into user(first_name, last_name, pesel)
values ('Jan', 'Kowalski', '90101222457'),
       ('Maciej','Zalewski', '87112242456'),
       ('Aneta','Korczyńska', '76061536749');

insert into category(name)
values ( 'Laptopy' ),
       ('Pojazdy');
insert into assets(name, description, serial_number, category_id)
values ( 'Asus MateBook D', '15 calowy laptop, i5, 8GB DDR3, kolor czarny', 'ASMBD198723', 1),
       ('Apple MacBook Pro 2015','13 calowy laptop, i5, 16GB DDR3, SSD256GB, kolor srebrny', 'MBP15X0925336', 1),
       ('Dell Inspirion 3576','13 calowy laptop, i7, 8GB DDR4, SSD 512GB, kolor czarny','DI3576XO526716', 2);

insert into assignment(start, end, asset_id, user_id) values
('2017-10-08 15:00:00', '2018-10-08 15:00:00', 1, 1),
('2018-10-09 12:00:00', null, 3, 1),
('2018-10-10 16:00:00', null, 2, 1);
