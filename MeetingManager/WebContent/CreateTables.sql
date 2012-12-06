CREATE TABLE employee (
id INTEGER PRIMARY KEY,
name VARCHAR,
team VARCHAR,
build_id INTEGER);

CREATE TABLE location (
id INTEGER PRIMARY KEY,
name VARCHAR,
building_number INTEGER,
city VARCHAR);

CREATE TABLE meeting (
id INTEGER PRIMARY KEY,
location INTEGER REFERENCES location(id),
start_time timestamp,
end_time timestamp,
minutes VARCHAR,
created_by INTEGER REFERENCES employee(id));

CREATE TABLE invited (
id INTEGER PRIMARY KEY,
meeting_id INTEGER REFERENCES meeting(id),
employee_id INTEGER REFERENCES employee(id),
response boolean);

CREATE SEQUENCE employee_seq START 100;

CREATE SEQUENCE location_seq START 100;

CREATE SEQUENCE meeting_seq START 100;

CREATE SEQUENCE invited_seq START 100;
