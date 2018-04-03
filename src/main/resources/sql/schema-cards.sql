DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS transfer;

-- Table: cards
CREATE TABLE cards (
  id             INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name           VARCHAR(255) NOT NULL,
  number_of_card VARCHAR(255) NOT NULL,
  birth_date     VARCHAR(255) NOT NULL,
  sex            VARCHAR(255) NOT NULL,
  address        VARCHAR(255) NOT NULL,
  balance        DOUBLE       NOT NULL,
  UNIQUE (number_of_card)
);

-- Table: users
CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  password VARCHAR(255) NOT NULL,
  card     VARCHAR(255) NOT NULL
);

-- Table: transfer
CREATE TABLE transfer (
  id                  INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  numberOfSendersCard VARCHAR(255) NOT NULL,
  senders             VARCHAR(255) NOT NULL,
  recipients_card     VARCHAR(255) NOT NULL,
  amount              DOUBLE       NOT NULL
)