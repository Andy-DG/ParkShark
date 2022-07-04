insert into postal_code (id, city, zip_code)
values (1, 'Brugge', '8000');

insert into address (id, street_name, street_number, fk_postal_code_id)
values (1, 'Daverlostraat', 61, 1);

insert into employee(id, first_name, last_name, mobile_phone_number, phone_number, email, fk_address_id, role)
values (1, 'Andy', 'De Gheldere', '+111 636 85 67 89', '+111 636 85 67 89', 'andy@shark.com', 1, 'director');

insert into division(id, name, original_name, fk_director_id, fk_head_division_id)
values (1, 'SharkDiv', 'DumbPark', 1, 1);

insert into parking_lot(id, name, fk_division_id, fk_category_id, max_capacity, fk_contact_id, fk_address_id, price_per_hour)
values (1, 'SharkLot', 1, 'ABOVE_GROUND', 10, 1, 1, 10);