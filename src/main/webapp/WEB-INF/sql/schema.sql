DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS bank;
DROP TABLE IF EXISTS orders;

CREATE TABLE client (
  id       INT PRIMARY KEY AUTO_INCREMENT,
  name     VARCHAR(20) NOT NULL,
  login    VARCHAR(20) NOT NULL,
  password VARCHAR(40) NOT NULL,
  email    VARCHAR(40) NOT NULL,
  type     VARCHAR(10) NOT NULL
);

CREATE TABLE bank (
  id         INT PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(40) NOT NULL,
  address    VARCHAR(40) NOT NULL,
  paysystems VARCHAR(40) NOT NULL
);

CREATE TABLE orders (
  number               INT PRIMARY KEY AUTO_INCREMENT,
  firstContrAgent      VARCHAR(40),
  firstContrAgentType  VARCHAR(10),
  secondContrAgent     VARCHAR(40),
  secondContrAgentType VARCHAR(10),
  dateOfStart          DATETIME,
  dateOfFinish         DATETIME,
  type                 VARCHAR(10),
  overdue              BOOLEAN
);

