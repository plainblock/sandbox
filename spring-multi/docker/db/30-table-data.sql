--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = data, pg_catalog;

--
-- Name: country; Type: TABLE; Schema: data; Owner: plainblock; Tablespace:
--

CREATE TABLE country (
    name TEXT NOT NULL,
    two_letter TEXT PRIMARY KEY,
    country_id integer NOT NULL
);
ALTER TABLE data.country OWNER TO plainblock;

--
-- Name: subcountry; Type: TABLE; Schema: data; Owner: plainblock; Tablespace:
--

CREATE TABLE subcountry (
    country TEXT NOT NULL REFERENCES country(two_letter),
    subcountry_name TEXT NOT NULL,
    subdivision TEXT,
    subcountry_level TEXT,
    UNIQUE(country, subcountry_name)
);
ALTER TABLE data.subcountry OWNER TO plainblock;

--
-- PostgreSQL database dump complete
--
