create table users (
                       id                      bigserial primary key,
                       username                varchar(30) not null unique,
                       password                varchar(80) not null,
                       email                   varchar(50) unique,
                       created_at              timestamp default current_timestamp,
                       updated_at              timestamp default current_timestamp
);

create table roles (
                       id                      bigserial primary key,
                       name                    varchar(50) not null unique,
                       created_at              timestamp default current_timestamp,
                       updated_at              timestamp default current_timestamp
);

CREATE TABLE users_roles (
                             user_id                 bigint not null references users (id),
                             role_id                 bigint not null references roles (id),
                             primary key (user_id, role_id)
);

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email)
values
('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);

create table products (
                          id bigserial                primary key,
                          title                       varchar(255),
                          cost                        int,
                          created_at                  timestamp default current_timestamp,
                          updated_at                  timestamp default current_timestamp
);
INSERT INTO products (title, cost) VALUES ('Apple', 40), ('Orange', 55),
 ('Pasta', 45), ('Milk', 60), ('Meat', 90), ('Potaro', 20),('Beet', 15), ('Onion', 16),
('Cucumber', 20), ('Chocolate', 65), ('Marmalade', 50), ('Biscuits', 45),  ('Cake', 65),
('Jam', 55), ('Tea', 60), ('Coffee', 85), ('Bread', 25),('Fish', 95), ('Shrimp', 90),
('Squid', 55);

create table orders (
                         id                      bigserial primary key,
                         user_id                 bigint references users (id),
                         price                   int,
                         created_at              timestamp default current_timestamp,
                         updated_at              timestamp default current_timestamp
);

create table order_items (
                         id                      bigserial primary key,
                         title                   varchar(255),
                         product_id              bigint references products (id),
                         order_id                bigint references orders (id),
                         count                   int,
                         cost                    int,
                         totalCost               int,
                         created_at              timestamp default current_timestamp,
                         updated_at              timestamp default current_timestamp
);