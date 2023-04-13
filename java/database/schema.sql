BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS properties;
DROP TABLE IF EXISTS maintenance;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS user_details;
DROP TABLE IF EXISTS maintenance_status;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS account_balances
DROP TABLE IF EXISTS property_photos
CREATE TABLE users (
user_id SERIAL,
username varchar(50) NOT NULL UNIQUE,
password_hash varchar(200) NOT NULL,
role varchar(50) NOT NULL,
CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE properties (
property_id SERIAL,
rental_price INTEGER,
address VARCHAR(255),
is_available BOOLEAN,
landlord_id INTEGER,
due_date  DATE,
CONSTRAINT pk_property_id PRIMARY KEY (property_id),
CONSTRAINT fk_landlord_id FOREIGN KEY (landlord_id) REFERENCES users(user_id)
);

CREATE TABLE maintenance (
maintenance_request_id SERIAL,
description VARCHAR(255),
status_id INTEGER,
property_id INTEGER,
maintenance_worker_id INTEGER,
CONSTRAINT pk_maintenance_request_id PRIMARY KEY (maintenance_request_id),
CONSTRAINT fk_status_id FOREIGN KEY (status_id) REFERENCES maintenance_status(status_id),
CONSTRAINT fk_property_id FOREIGN KEY (property_id) REFERENCES properties(property_id),
CONSTRAINT fk_maintenance_worker_id FOREIGN KEY (maintenance_worker_id) REFERENCES users(user_id)
);

CREATE TABLE payments (
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
  CONSTRAINT fk_property_id FOREIGN KEY (property_id) REFERENCES properties(property_id)
);

CREATE TABLE maintenance_status (
status_id SERIAL,
status_description VARCHAR(255),
CONSTRAINT pk_status_id PRIMARY KEY (status_id)
);

INSERT INTO maintenance_status (status_description) VALUES ('REQUESTED');
INSERT INTO maintenance_status (status_description) VALUES ('IN PROGRESS');
INSERT INTO maintenance_status (status_description) VALUES ('COMPLETED');
INSERT INTO maintenance_status (status_description) VALUES ('CANCEL');

CREATE TABLE property_photos (
  photo_id SERIAL,
  property_id INTEGER,
  photo_url VARCHAR,
  CONSTRAINT pk_photo_id PRIMARY KEY (photo_id),
  CONSTRAINT fk_property_id FOREIGN KEY (property_id) REFERENCES properties(property_id)
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

CREATE TABLE account_balances (
  balance_id SERIAL,
  user_id INTEGER,
  balance_amount INTEGER,
  CONSTRAINT pk_balance_id PRIMARY KEY (balance_id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

COMMIT TRANSACTION;