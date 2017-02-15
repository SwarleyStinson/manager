
CREATE TABLE  client (
    id int primary key auto_increment,
    name varchar(40) not null,
    login varchar(40) not null,
    password varchar(40) not null,
    email varchar(40) not null,
    type varchar(40)  not null
);

CREATE TABLE  bank (
    id int primary key auto_increment,
    name varchar(40) not null,
    address varchar(40)  not null,
    paysystems varchar(40)  not null
);

