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
