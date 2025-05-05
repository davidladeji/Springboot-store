create table profiles
(
    id               bigint primary key,
    bio              text,
    phone_no         varchar(15),
    date_of_birth    DATE,
    loyalty_points   int unsigned default 0,
    Foreign Key (id) REFERENCES users(id)
);