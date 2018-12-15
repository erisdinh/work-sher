use worksher_db;

drop table reviews;

create table reviews (
	review_id int(10) unsigned unique not null auto_increment,
    for_user_id int(10) unsigned not null,
    from_user_id int(10) unsigned not null,
    order_id int(10) unsigned not null,
    posting_id int(10) unsigned not null, 
    review_date timestamp not null default current_timestamp on update current_timestamp,
    review_rating decimal(2,1) not null,
    review_text text,
    primary key (review_id),
    foreign key (for_user_id) references usertest(user_id),
    foreign key (from_user_id) references usertest(user_id),
    foreign key (order_id) references orders(order_id),
    foreign key (posting_id) references posting(posting_id)
);