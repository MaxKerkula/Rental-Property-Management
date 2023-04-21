BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS property;
DROP TABLE IF EXISTS maintenance_request;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS user_details;
DROP TABLE IF EXISTS maintenance_status;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS account_balances;
DROP TABLE IF EXISTS property_photos;

CREATE TABLE users (
user_id SERIAL,
username varchar(50) NOT NULL UNIQUE,
password_hash varchar(200) NOT NULL,
role varchar(50) NOT NULL,
CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE property (
property_id SERIAL,
rental_price INTEGER,
address VARCHAR(255),
available BOOLEAN,
landlord_id INTEGER,
due_date DATE,
CONSTRAINT pk_property_id PRIMARY KEY (property_id),
CONSTRAINT fk_landlord_id FOREIGN KEY (landlord_id) REFERENCES users(user_id)
);

CREATE TABLE maintenance_status (
status_id SERIAL,
status_description VARCHAR(255),
CONSTRAINT pk_status_id PRIMARY KEY (status_id)
);

CREATE TABLE maintenance_request (
maintenance_request_id SERIAL,
description VARCHAR(255),
status_id INTEGER,
property_id INTEGER,
maintenance_worker_id INTEGER,
CONSTRAINT pk_maintenance_request_id PRIMARY KEY (maintenance_request_id),
CONSTRAINT fk_status_id FOREIGN KEY (status_id) REFERENCES maintenance_status(status_id),
CONSTRAINT fk_property_id FOREIGN KEY (property_id) REFERENCES property(property_id),
CONSTRAINT fk_maintenance_worker_id FOREIGN KEY (maintenance_worker_id) REFERENCES users(user_id)
);

CREATE TABLE payment (
payment_id SERIAL,
payment_date DATE,
payment_amount INTEGER,
user_id INTEGER,
CONSTRAINT pk_payment_id PRIMARY KEY (payment_id),
CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE user_details (
tenant_id SERIAL,
user_id INTEGER,
property_id INTEGER NOT NULL,
first_name VARCHAR(25) NOT NULL,
last_name VARCHAR(25) NOT NULL,
email VARCHAR(150) NOT NULL,
phone VARCHAR(11),
has_pets BOOLEAN,
CONSTRAINT pk_tenant_id PRIMARY KEY (tenant_id),
CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
CONSTRAINT fk_property_id FOREIGN KEY (property_id) REFERENCES property(property_id)
);

CREATE TABLE property_photos (
photo_id SERIAL,
property_id INTEGER,
photo_url VARCHAR,
CONSTRAINT pk_photo_id PRIMARY KEY (photo_id),
CONSTRAINT fk_property_id FOREIGN KEY (property_id) REFERENCES property(property_id)
);

CREATE TABLE messages (
message_id SERIAL,
landlord_user_id INTEGER,
sender_email VARCHAR(150),
subject VARCHAR(100),
message_text TEXT,
sent_at TIMESTAMP,
CONSTRAINT pk_message_id PRIMARY KEY (message_id),
CONSTRAINT fk_landlord_user_id FOREIGN KEY (landlord_user_id) REFERENCES users(user_id)
);

CREATE TABLE account_balance (
balance_id SERIAL,
user_id INTEGER,
balance_amount INTEGER,
CONSTRAINT pk_balance_id PRIMARY KEY (balance_id),
CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Maintenance statuses
INSERT INTO maintenance_status (status_description) VALUES ('REQUESTED');
INSERT INTO maintenance_status (status_description) VALUES ('IN PROGRESS');
INSERT INTO maintenance_status (status_description) VALUES ('COMPLETED');
INSERT INTO maintenance_status (status_description) VALUES ('CANCEL');

-- Users
INSERT INTO users (username, password_hash, role) VALUES ('user1', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user2', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user3', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user5', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user6', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user7', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user8', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user9', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('user10', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('maintenance1', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_MAINTENANCE');
INSERT INTO users (username, password_hash, role) VALUES ('maintenance2', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_MAINTENANCE');
INSERT INTO users (username, password_hash, role) VALUES ('maintenance3', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_MAINTENANCE');
INSERT INTO users (username, password_hash, role) VALUES ('landlord1', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_LANDLORD');
INSERT INTO users (username, password_hash, role) VALUES ('landlord2', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_LANDLORD');
INSERT INTO users (username, password_hash, role) VALUES ('landlord3', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_LANDLORD');
INSERT INTO users (username, password_hash, role) VALUES ('admin1', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN');
INSERT INTO users (username, password_hash, role) VALUES ('admin2', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN');
INSERT INTO users (username, password_hash, role) VALUES ('admin3', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN');

-- Properties
INSERT INTO property (rental_price, address, available, landlord_id, due_date) VALUES (1000, '123 Main St', true, 1, '2023-03-15');
INSERT INTO property (rental_price, address, available, landlord_id, due_date) VALUES (1200, '456 Oak St', false, 1, '2023-03-15');
INSERT INTO property (rental_price, address, available, landlord_id, due_date) VALUES (950, '789 Pine St', true, 1, '2023-03-15');
INSERT INTO property (rental_price, address, available, landlord_id, due_date) VALUES (1000, '987 Main St', true, 2, '2023-03-15');
INSERT INTO property (rental_price, address, available, landlord_id, due_date) VALUES (1200, '654 Oak St', false, 2, '2023-03-15');
INSERT INTO property (rental_price, address, available, landlord_id, due_date) VALUES (950, '432 Pine St', true, 2, '2023-03-15');
INSERT INTO property (rental_price, address, available, landlord_id, due_date) VALUES (1000, '234 Main St', true, 3, '2023-03-15');
INSERT INTO property (rental_price, address, available, landlord_id, due_date) VALUES (1200, '345 Oak St', false, 3, '2023-03-15');
INSERT INTO property (rental_price, address, available, landlord_id, due_date) VALUES (950, '651 Pine St', true, 3, '2023-03-15');
INSERT INTO property (rental_price, address, available, landlord_id, due_date) VALUES (950, '135 Pine St', true, 1, '2023-03-15');

-- Tenant details
INSERT INTO user_details (user_id, property_id, first_name, last_name, email, phone, has_pets) VALUES (1, 1, 'John', 'Uno', 'johnuno1@example.com', '5551234567', false);
INSERT INTO user_details (user_id, property_id, first_name, last_name, email, phone, has_pets) VALUES (2, 2, 'John', 'Dos', 'johndos2@example.com', '5551234567', true);
INSERT INTO user_details (user_id, property_id, first_name, last_name, email, phone, has_pets) VALUES (3, 3, 'John', 'Tres', 'johntres3@example.com', '5551234567', false);
INSERT INTO user_details (user_id, property_id, first_name, last_name, email, phone, has_pets) VALUES (4, 4, 'John', 'Quatro', 'johnuno4@example.com', '5551234567', false);
INSERT INTO user_details (user_id, property_id, first_name, last_name, email, phone, has_pets) VALUES (5, 5, 'John', 'Five', 'johndos5@example.com', '5551234567', true);
INSERT INTO user_details (user_id, property_id, first_name, last_name, email, phone, has_pets) VALUES (6, 6, 'John', 'Six', 'johntres6@example.com', '5551234567', false);
INSERT INTO user_details (user_id, property_id, first_name, last_name, email, phone, has_pets) VALUES (7, 7, 'John', 'Seven', 'johnuno7@example.com', '5551234567', false);
INSERT INTO user_details (user_id, property_id, first_name, last_name, email, phone, has_pets) VALUES (8, 8, 'John', 'Eight', 'johndos8@example.com', '5551234567', true);
INSERT INTO user_details (user_id, property_id, first_name, last_name, email, phone, has_pets) VALUES (9, 9, 'John', 'Nine', 'johntres8@example.com', '5551234567', false);
INSERT INTO user_details (user_id, property_id, first_name, last_name, email, phone, has_pets) VALUES (10, 10, 'John', 'Ten', 'johnuno7@example.com', '5551234567', false);

-- Maintenance requests
INSERT INTO maintenance_request (description, status_id, property_id, maintenance_worker_id) VALUES ('Leaky faucet', 1, 1, 1);
INSERT INTO maintenance_request (description, status_id, property_id, maintenance_worker_id) VALUES ('Broken window', 1, 2, 2);
INSERT INTO maintenance_request (description, status_id, property_id, maintenance_worker_id) VALUES ('Leaky faucet', 1, 3, 3);
INSERT INTO maintenance_request (description, status_id, property_id, maintenance_worker_id) VALUES ('Broken window', 1, 4, 1);
INSERT INTO maintenance_request (description, status_id, property_id, maintenance_worker_id) VALUES ('Leaky faucet', 1, 5, 2);
INSERT INTO maintenance_request (description, status_id, property_id, maintenance_worker_id) VALUES ('Broken window', 1, 6, 3);
INSERT INTO maintenance_request (description, status_id, property_id, maintenance_worker_id) VALUES ('Leaky faucet', 1, 7, 1);
INSERT INTO maintenance_request (description, status_id, property_id, maintenance_worker_id) VALUES ('Broken window', 1, 8, 2);
INSERT INTO maintenance_request (description, status_id, property_id, maintenance_worker_id) VALUES ('Leaky faucet', 1, 9, 3);
INSERT INTO maintenance_request (description, status_id, property_id, maintenance_worker_id) VALUES ('Broken window', 1, 10, 1);

-- Payments
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-02-15', 1200, 1);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-03-15', 1200, 1);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-02-15', 1200, 2);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-03-15', 1200, 2);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-02-15', 1200, 3);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-03-15', 1200, 3);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-02-15', 1200, 4);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-03-15', 1200, 4);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-02-15', 1200, 5);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-03-15', 1200, 5);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-02-15', 1200, 6);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-03-15', 1200, 6);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-02-15', 1200, 7);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-03-15', 1200, 7);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-02-15', 1200, 8);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-03-15', 1200, 8);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-02-15', 1200, 9);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-03-15', 1200, 9);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-02-15', 1200, 10);
INSERT INTO payment (payment_date, payment_amount, user_id) VALUES ('2023-03-15', 1200, 10);

-- Property photos
INSERT INTO property_photos (property_id, photo_url) VALUES (1, 'https://img.money.com/2021/01/Beware-Rental-Scams-1.jpg');
INSERT INTO property_photos (property_id, photo_url) VALUES (2, 'https://img.money.com/2021/01/Beware-Rental-Scams-1.jpg');
INSERT INTO property_photos (property_id, photo_url) VALUES (3, 'https://img.money.com/2021/01/Beware-Rental-Scams-1.jpg');
INSERT INTO property_photos (property_id, photo_url) VALUES (4, 'https://img.money.com/2021/01/Beware-Rental-Scams-1.jpg');
INSERT INTO property_photos (property_id, photo_url) VALUES (5, 'https://img.money.com/2021/01/Beware-Rental-Scams-1.jpg');
INSERT INTO property_photos (property_id, photo_url) VALUES (6, 'https://img.money.com/2021/01/Beware-Rental-Scams-1.jpg');
INSERT INTO property_photos (property_id, photo_url) VALUES (7, 'https://img.money.com/2021/01/Beware-Rental-Scams-1.jpg');
INSERT INTO property_photos (property_id, photo_url) VALUES (8, 'https://img.money.com/2021/01/Beware-Rental-Scams-1.jpg');
INSERT INTO property_photos (property_id, photo_url) VALUES (9, 'https://img.money.com/2021/01/Beware-Rental-Scams-1.jpg');
INSERT INTO property_photos (property_id, photo_url) VALUES (10, 'https://img.money.com/2021/01/Beware-Rental-Scams-1.jpg');


-- Messages
INSERT INTO messages (landlord_user_id, sender_email, subject, message_text, sent_at) VALUES (1, 'johndoe@example.com', 'Rent question', 'Hi, I have a question about my rent payment.', '2023-02-20 14:30:00');
INSERT INTO messages (landlord_user_id, sender_email, subject, message_text, sent_at) VALUES (1, 'johndoe@example.com', 'Rent question', 'Hi, I have a question about my rent payment.', '2023-02-20 14:30:00');
INSERT INTO messages (landlord_user_id, sender_email, subject, message_text, sent_at) VALUES (1, 'johndoe@example.com', 'Rent question', 'Hi, I have a question about my rent payment.', '2023-02-20 14:30:00');

-- Account balance
INSERT INTO account_balance (user_id, balance_amount) VALUES (1, 20000);
INSERT INTO account_balance (user_id, balance_amount) VALUES (2, 20000);
INSERT INTO account_balance (user_id, balance_amount) VALUES (3, 20000);
INSERT INTO account_balance (user_id, balance_amount) VALUES (4, 20000);
INSERT INTO account_balance (user_id, balance_amount) VALUES (5, 20000);
INSERT INTO account_balance (user_id, balance_amount) VALUES (6, 20000);
INSERT INTO account_balance (user_id, balance_amount) VALUES (7, 20000);
INSERT INTO account_balance (user_id, balance_amount) VALUES (8, 20000);
INSERT INTO account_balance (user_id, balance_amount) VALUES (9, 20000);
INSERT INTO account_balance (user_id, balance_amount) VALUES (10, 20000);

COMMIT TRANSACTION;