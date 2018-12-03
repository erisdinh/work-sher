USE `worksher_db`;

INSERT INTO `usertest` VALUES 
(2,'test2','test2','test2','test2','2018-12-02 00:00:00','user');

insert into posting (user_id, jobCategory, description, compensation, status, portfolio, dateCreated) values
(2, 'Design', 'I can design posters', '2 cups of coffee', 'active', '', '2018-11-29 00:00:00'),
(1, 'Photography', 'Hi every one!,\n, I am a second-year photography student.', 'Photography posters', 'inactive','', '2018-11-29 00:00:00');

insert into orders (requestOrderUser_id, postOrderUser_id, posting_id, description, dateResponsed, dateCompleted, status) values
(1, 2, 1, '2 posters for Academic events', null, null, 'pending'), 
(2, 1, 2, 'A yearbook for my class', '2018-11-30 00:00:00', null, 'approved');