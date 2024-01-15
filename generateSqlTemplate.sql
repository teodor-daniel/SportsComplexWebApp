
drop table sports_club;

CREATE TABLE sports_club(
                            id SERIAL PRIMARY KEY,
                            owner_name VARCHAR(200) NOT NULL,
                            sports_club_name VARCHAR(200) NOT NULL UNIQUE,
                            email VARCHAR(100) NOT NULL UNIQUE,
                            phoneNumber VARCHAR(12) NOT NULL UNIQUE CHECK (LENGTH(phoneNumber) = 12)
);


