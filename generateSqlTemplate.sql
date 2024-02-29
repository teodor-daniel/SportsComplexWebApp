
drop table sports_club;
drop table athlete;
drop table sponsor;

CREATE TABLE sports_club(
    id SERIAL PRIMARY KEY,
    owner_name VARCHAR(200) NOT NULL,
    sports_club_name VARCHAR(200) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
);


CREATE TABLE athlete(
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    birthdate DATE NOT NULL,    
    gen CHAR(1) NOT NULL,
    phone_number VARCHAR(12) UNIQUE NOT NULL,
    id_sports_club INTEGER REFERENCES sports_club(id) ON DELETE CASCADE,
    CONSTRAINT athlete_gen_nn CHECK (gen IN ('M', 'F')),
    CONSTRAINT athlete_phone_number CHECK (LENGTH(phone_number) = 12)
);


CREATE TABLE sponsor (
  id SERIAL PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  address VARCHAR(30) NOT NULL,
  phone VARCHAR(12) UNIQUE NOT NULL,
  email VARCHAR(30) UNIQUE NOT NULL,
  CHECK (LENGTH(phone) = 12)
);

INSERT INTO sponsor (id, name, address, phone, email)
VALUES 
(1, 'Dedeman SRL', 'Bucuresti, Romania', '+40712345678', 'dedeman_sport@gmail.ro'),
(2, 'Aqua Company', 'Timisoara, Romania', '+40798765432', 'Aqua@yahoo.com'),
(3, 'Decathlon SA', 'Bucuresti, Romania', '+40712365878', 'decathlon@hotmail.net');