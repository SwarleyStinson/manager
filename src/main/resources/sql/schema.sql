DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS bank;
DROP TABLE IF EXISTS orders;

CREATE TABLE client (
  id       INT PRIMARY KEY AUTO_INCREMENT,
  name     VARCHAR(80) NOT NULL,
  login    VARCHAR(80) NOT NULL,
  password VARCHAR(80) NOT NULL,
  email    VARCHAR(80) NOT NULL,
  type     VARCHAR(80) NOT NULL
);

CREATE TABLE bank (
  id         INT PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(80) NOT NULL,
  address    VARCHAR(80) NOT NULL,
  paysystem VARCHAR(80) NOT NULL
);

CREATE TABLE orders (
  id               INT PRIMARY KEY AUTO_INCREMENT,
  firstcontragent      VARCHAR(80),
  secondcontragent     VARCHAR(80),
  dateofstart          VARCHAR(80),
  dateoffinish         VARCHAR(80),
  type                 VARCHAR(80)
);

