INSERT INTO employee (id, name, team) VALUES (1, 'Adam', 'Team A');
INSERT INTO employee (id, name, team) VALUES (2, 'Taslim', 'Team A');
INSERT INTO employee (id, name, team) VALUES (3, 'Shilpa', 'Team A');

INSERT INTO employee (id, name, team) VALUES (4, 'Amanda', 'Team B');
INSERT INTO employee (id, name, team) VALUES (5, 'Gabby', 'Team B');
INSERT INTO employee (id, name, team) VALUES (6, 'Chloe', 'Team B');

INSERT INTO location (id, name, building_number, city) VALUES (1, 'Tudor Room', '101', 'Bloomington');
INSERT INTO location (id, name, building_number, city) VALUES (2, 'Sycamore Room', '254', 'Ashburn');
INSERT INTO location (id, name, building_number, city) VALUES (3, 'Panther Room', '93', 'Fairfax');
INSERT INTO location (id, name, building_number, city) VALUES (4, 'Halloween Room', '787', 'Sleepy Hallow');

INSERT INTO meeting (id, location, start_time, end_time, created_by) VALUES (1, 1, '2012-11-15 14:30:00', '2012-11-15 15:30:00', 1);
INSERT INTO meeting (id, location, start_time, end_time, created_by) VALUES (2, 2, '2012-11-17 13:00:00', '2012-11-17 15:00:00', 2);
INSERT INTO meeting (id, location, start_time, end_time, created_by) VALUES (3, 3, '2012-11-18 12:30:00', '2012-11-18 15:30:00', 5);
INSERT INTO meeting (id, location, start_time, end_time, created_by) VALUES (4, 4, '2012-11-19 10:00:00', '2012-11-19 11:30:00', 6);

INSERT INTO invited (id, meeting_id, employee_id, response) VALUES (1, 1, 1, false);
INSERT INTO invited (id, meeting_id, employee_id, response) VALUES (2, 1, 2, false);
INSERT INTO invited (id, meeting_id, employee_id, response) VALUES (3, 2, 2, false);
INSERT INTO invited (id, meeting_id, employee_id, response) VALUES (4, 2, 3, false);
INSERT INTO invited (id, meeting_id, employee_id, response) VALUES (5, 3, 3, false);
INSERT INTO invited (id, meeting_id, employee_id, response) VALUES (6, 3, 4, false);
INSERT INTO invited (id, meeting_id, employee_id, response) VALUES (7, 4, 5, false);
INSERT INTO invited (id, meeting_id, employee_id, response) VALUES (8, 4, 6, false);
