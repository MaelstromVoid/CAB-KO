CREATE SCHEMA IF NOT EXISTS sc_cabko;

CREATE TYPE user_role_enum AS ENUM (
  'ADMIN',
  'CLIENT'
);

CREATE TABLE users (
  id UUID PRIMARY KEY,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  roles user_role_enum[] NOT NULL
);

CREATE TABLE products (
  id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(4096),
  price DECIMAL(10,2) NOT NULL,
  quantity INT NOT NULL CHECK (quantity >= 0)
);
