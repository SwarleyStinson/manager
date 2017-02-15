DROP table client IF EXISTS;
DROP table bank IF EXISTS;
DROP table order IF EXISTS;

CREATE TABLE CLIENT (
    name varchar(40) NOT NULL,
    login varchar(40) NOT NULL,
    password varchar(40) NOT NULL,
    email varchar(40) NOT NULL,
    type varchar(40) NOT NULL,
    PRIMARY KEY(login)
);

CREATE TABLE BANK (
    id INT PRIMARY KEY,
    name varchar(40) NOT NULL,
    address varchar(40) NOT NULL,
    paysystem varchar(40) NOT NULL
);

CREATE TABLE ORDER (
    number INT ,
    firstContrAgent varchar(40),
    firstContrAgentType varchar(40),
    secondContrAgent varchar(40),
    secondContrAgentType varchar(40),
    dateOfStart DATE,
    dateOfFinish DATE ,
    type varchar(40),
    overdue boolean,
    PRIMARY KEY(number)
);