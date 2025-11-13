CREATE SCHEMA iso;

CREATE TABLE iso.country (
    name TEXT NOT NULL,
    two_letter TEXT PRIMARY KEY,
    country_id integer NOT NULL
);

CREATE TABLE iso.subcountry (
    country TEXT NOT NULL REFERENCES country(two_letter),
    subcountry_name TEXT NOT NULL,
    subdivision TEXT,
    subcountry_level TEXT,
    UNIQUE(country, subcountry_name)
);