USE `worksher_db`;

insert into posting (user_id, jobCategory, description, compensation, status, portfolio) values
(2, 'Design', 'I can design posters', '2 cups of coffee', 'active', ''),
(1, 'Photography', 'Hi every one!,\n, I am a second-year photography student.', 'Photography posters', 'inactive','');

insert into orders (requestOrderUser_id, postOrderUser_id, posting_id, dateRequested, dateResponsed, dateCompleted, status) values
(1, 2, 1, '2018-12-02 00:00:00', null, null, 'pending'), 
(2, 1, 2, '2018-11-29 00:00:00', '2018-11-30 00:00:00', null, 'approved');