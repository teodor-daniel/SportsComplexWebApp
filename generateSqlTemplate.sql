
drop table sports_club;
drop table athlete;
drop table sponsor;
drop table sponsorship_contract;
drop table competition;

CREATE TABLE sports_club(
    id SERIAL PRIMARY KEY,
    owner_name VARCHAR(200) NOT NULL,
    sports_club_name VARCHAR(200) NOT NULL UNIQUE,
    email VARCHAR(200) NOT NULL UNIQUE,
);


CREATE TABLE athlete(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    birthdate DATE NOT NULL,    
    gen CHAR(1) NOT NULL,
    phone_number VARCHAR(12) UNIQUE NOT NULL,
    id_sports_club INTEGER REFERENCES sports_club(id) ON DELETE CASCADE,
    CONSTRAINT athlete_gen_nn CHECK (gen IN ('M', 'F')),
    CONSTRAINT athlete_phone_number CHECK (LENGTH(phone_number) = 12)
);


CREATE TABLE sponsor (
  id SERIAL PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  address VARCHAR(200) NOT NULL,
  phone VARCHAR(12) UNIQUE NOT NULL,
  email VARCHAR(200) UNIQUE NOT NULL,
  CHECK (LENGTH(phone) = 12)
);

CREATE TABLE sponsorship_contract (
    id_sponsorship_contract SERIAL PRIMARY KEY,
    id_athlete INT REFERENCES athlete(id_athlete) ON DELETE CASCADE,
    id_sponsor INT REFERENCES sponsor(id_sponsor) ON DELETE CASCADE,
    start_date_sponsorship_contract DATE NOT NULL,
    end_date_sponsorship_contract DATE NOT NULL,
    amount NUMERIC(6) NOT NULL,
    CONSTRAINT start_end_date_sponsorship_contract_ck CHECK (start_date_sponsorship_contract <= end_date_sponsorship_contract)
);

CREATE TABLE competition (
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL, 
    organizer VARCHAR(200) NOT NULL,
    sport VARCHAR(200) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    CONSTRAINT data_inceput_competitie_ck CHECK (start_date <= end_date)
);

CREATE TABLE coach (
  id SERIAL PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  salary INTEGER NOT NULL,
  sport VARCHAR(200) NOT NULL,
  birth_date DATE NOT NULL,
  gender CHAR(1) NOT NULL CHECK (gender IN ('M', 'F')),
  phone VARCHAR(12) UNIQUE,
  CONSTRAINT phone_check CHECK (phone IS NULL OR LENGTH(phone) = 12)
);

CREATE TABLE gym (
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) UNIQUE NOT NULL,
    sport VARCHAR(200) NOT NULL,
	size  INTEGER NOT NULL
);
