drop table reviews;

create table reviews (
	review_id int(10) unsigned unique not null auto_increment,
    user_id int(10) unsigned not null,
    posting_id int(10) unsigned not null, 
    review_date timestamp not null default current_timestamp on update current_timestamp,
    review_rating tinyint not null,
    review_text text,
    primary key (review_id),
    foreign key (user_id) references usertest(user_id)
);