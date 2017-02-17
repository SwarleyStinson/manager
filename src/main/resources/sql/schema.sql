drop table if EXISTS client;
drop table if EXISTS bank;
drop table if EXISTS order;
CREATE TABLE  client (id int primary key auto_increment, name varchar(20) not null, login varchar(20) not null, password varchar(40) not null, email varchar(20) not null, type varchar(10)  not null);
CREATE TABLE  bank (id int primary key auto_increment,    name varchar(40) not null,    address varchar(40)  not null,    paysystems varchar(40)  not null);
CREATE TABLE order (    number int PRIMARY KEY AUTO_INCREMENT,    firstContrAgent VARCHAR(40),    firstContrAgentType VARCHAR(10),    secondContrAgent VARCHAR(40),    secondContrAgentType VARCHAR(10),    dateOfStart DATETIME,    dateOfFinish DATETIME,    type VARCHAR(10),    overdue BOOLEAN);

