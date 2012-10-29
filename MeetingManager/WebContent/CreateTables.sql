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
end_type timestamp,
minutes VARCHAR,
created_by INTEGER REFERENCES employee(id));

CREATE TABLE invited (
id INTEGER PRIMARY KEY,
meeting_id INTEGER REFERENCES meeting(id),
employee_id INTEGER REFERENCES employee(id),
response boolean);