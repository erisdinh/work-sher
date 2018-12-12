drop table orders;
drop table posting;
create table posting(
posting_id int(10) unsigned not null auto_increment,
user_id int(10) unsigned not null,
jobCategory varchar(60) not null,
title varchar(30),
description varchar(100),
compensation varchar(30),
status varchar(10),
portfolio varchar(100),
dateCreated timestamp not null default current_timestamp,
primary key(posting_id),
foreign key(user_id) references usertest(user_id)
);
DROP TABLE jobCategory;
CREATE TABLE jobCategory (
	jobCategoryId CHAR(6) NOT NULL,
	jobCategoryDesc VARCHAR(60) NOT NULL
);
INSERT INTO jobCategory (jobCategoryId, jobCategoryDesc) VALUES 
	("BUSGEN", "Business"),
    ("ENGSCI", "Engineering Sciences"),
    ("FILMTV", "Film TV, and Journalism"),
    ("APPHEA", "Applied Health"),
    ("ANIGAM", "Animation and Game Design"),
    ("TRADES", "Skilled Trades"),
    ("ARCHIT", "Architectural Studies"),
    ("VISPER", "Visual and Performing Arts"),
    ("MATART", "Material Art and Design"),
    ("APPCOM", "Applied Computing"),
    ("CHEMEN", "Chemical and Environmental Sciences"),
    ("COMSTU", "Community Studies"),
    ("PUBSAF", "Public Safety"),
    ("HUMSOC", "Humanities & Social Sciences"),
    ("EDUCAT", "Education"),
    ("DESILL", "Design, Illustration and Photography"),
    ("TECFUN", "Technology Fundamentals");
create table orders(
order_id int(10) unsigned auto_increment not null,
requestOrderUser_id int(10) unsigned not null,
postOrderUser_id int(10) unsigned not null,
posting_id int(10) unsigned not null,
description varchar(100),
dateRequested timestamp not null default current_timestamp,
dateResponsed Date,
dateCompleted Date,
status varchar(30) not null,
primary key(order_id),
foreign key(requestOrderUser_id) references usertest(user_id),
foreign key(postOrderUser_id) references usertest(user_id),
foreign key(posting_id) references posting(posting_id)
);
