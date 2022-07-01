CREATE SCHEMA IF NOT EXISTS parkshark;

CREATE TABLE IF NOT EXISTS parkshark."DIVISION"
(
    id serial NOT NULL,
    name character varying NOT NULL,
    original_name character varying NOT NULL,
    fk_director_id serial NOT NULL,
    fk_head_division_id serial,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS parkshark."ADDRESS"
(
    id serial NOT NULL,
    street_name character varying(50) NOT NULL,
    street_number integer NOT NULL,
    fk_postal_code_id serial NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS parkshark."POSTAL_CODE"
(
    id serial NOT NULL,
    zip_code integer NOT NULL,
    city character varying(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS parkshark."PARKING_LOT"
(
    id serial NOT NULL,
    name character varying(50) NOT NULL,
    fk_division_id serial NOT NULL,
    fk_category_id serial NOT NULL,
    max_capacity integer NOT NULL,
    fk_contact_id serial NOT NULL,
    fk_address_id serial NOT NULL,
    price_per_hour double precision NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS parkshark."EMPLOYEE"
(
    id serial NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    mobile_phone_number character varying(50),
    phone_number character varying(50),
    email character varying(50) NOT NULL,
    fk_address_id serial NOT NULL,
    role character varying(50) NOT NULL,
    PRIMARY KEY (id)
);