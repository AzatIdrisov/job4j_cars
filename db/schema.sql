create table car_body (
    id serial primary key,
    name text
);

create table car_mark (
                          id serial primary key,
                          name text
);

create table car (
                     id             serial primary key,
                     description    text,
                     car_mark_id    int not null references car_mark(id),
                     car_body_id    int not null references car_body(id),
                     user_id        int not null references users (id),
                     status         bool,
                     created        timestamp
);

create table users
(
    id      serial primary key,
    name    varchar(2000),
    email text,
    password text
);

insert into car_mark(name) values ('Toyota');
insert into car_mark(name) values ('BMW');
insert into car_mark(name) values ('Audi');
insert into car_mark(name) values ('Volkswagen');
insert into car_mark(name) values ('Scoda');
insert into car_mark(name) values ('Volkswagen');
insert into car_mark(name) values ('Porsche');
insert into car_mark(name) values ('Mercedes-Benz');

insert into car_body(name) values ('Седан');
insert into car_body(name) values ('Хетчбек');
insert into car_body(name) values ('Кросовер');
insert into car_body(name) values ('Универсал');
insert into car_body(name) values ('Минивэн');
insert into car_body(name) values ('Пикап');
insert into car_body(name) values ('Другое');
