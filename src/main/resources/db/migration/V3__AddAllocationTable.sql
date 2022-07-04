CREATE TABLE IF NOT EXISTS parkshark."ALLOCATION"
(
    id serial,
    fk_member_id int NOT NULL,
    fk_license_plate_id int NOT NULL,
    fk_parking_lot_id int NOT NULL ,
    begin_date date NOT NULL,
    begin_time  timestamp NOT NULL,
    end_date date,
    end_time timestamp,
    PRIMARY KEY (id)
);