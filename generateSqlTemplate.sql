
drop table sports_club;

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