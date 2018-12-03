USE `worksher_db`;

create table posting(
posting_id int(10) unsigned not null auto_increment,
user_id int(10) unsigned not null,
jobCategory varchar(30) not null,
description varchar(100),
compensation varchar(30),
status varchar(10),
portfolio varchar(100),
dateCreated date not null,
primary key(posting_id),
foreign key(user_id) references usertest(user_id)
);

create table orders(
order_id int(10) unsigned auto_increment not null,
requestOrderUser_id int(10) unsigned not null,
postOrderUser_id int(10) unsigned not null,
posting_id int(10) unsigned not null,
description varchar(100),
dateRequested Date not null,
dateResponsed Date,
dateCompleted Date,
status varchar(30) not null,
primary key(order_id),
foreign key(requestOrderUser_id) references usertest(user_id),
foreign key(postOrderUser_id) references usertest(user_id),
foreign key(posting_id) references posting(posting_id)
);