DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS users;

-- Table: cards
CREATE TABLE cards (
  id             INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name           VARCHAR(255) NOT NULL,
  number_of_card VARCHAR(255) NOT NULL,
  birth_date     VARCHAR(255) NOT NULL,
  sex            VARCHAR(255) NOT NULL,
  address        VARCHAR(255) NOT NULL
);

-- Table: users
CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  password VARCHAR(255) NOT NULL,
  card     VARCHAR(255) NOT NULL
)