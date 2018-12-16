-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: worksher_db
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `orders`
--
USE worksher_db;
DROP TABLE IF EXISTS `jobCategory`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `posting`;
DROP TABLE IF EXISTS `reviews`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `user_pass` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `date_joined` datetime NOT NULL,
  `role` varchar(10) NOT NULL,
  PRIMARY KEY (`user_id`)
) ;



create table `reviews` (
	`review_id` int(10) unsigned unique not null auto_increment,
    `for_user_id` int(10) unsigned not null,
    `from_user_id` int(10) unsigned not null,
    `posting_id` int(10) unsigned not null, 
    `review_date` timestamp not null default current_timestamp on update current_timestamp,
    `review_rating` decimal(2,1) not null,
    `review_text` text,
    primary key (`review_id`),
    foreign key (`for_user_id`) references `users`(`user_id`),
    foreign key (`from_user_id`) references `users`(`user_id`)
);

create table `posting`(
`posting_id` int(10) unsigned not null auto_increment,
`user_id` int(10) unsigned not null,
`username` varchar(20) NOT NULL,
`jobCategory` varchar(60) not null,
`title` varchar(30),
`description` varchar(200),
`compensation` varchar(30),
`status` varchar(10),
`portfolio` longblob,
`portfoliotype` varchar(20),
`portfoliolength` int(10) unsigned, 
`portfoliothumb` blob,
`dateCreated` timestamp not null default current_timestamp,
`dateUpdated` timestamp not null default current_timestamp,
primary key(`posting_id`),
foreign key(`user_id`) references `users`(`user_id`)
);
CREATE TABLE `orders` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `requestOrderUser_id` int(10) unsigned NOT NULL,
  `postOrderUser_id` int(10) unsigned NOT NULL,
  `posting_id` int(10) unsigned NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `dateRequested` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateResponsed` date DEFAULT NULL,
  `dateCompleted` date DEFAULT NULL,
  `status` varchar(30) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `requestOrderUser_id` (`requestOrderUser_id`),
  KEY `postOrderUser_id` (`postOrderUser_id`),
  KEY `posting_id` (`posting_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`requestOrderUser_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`postOrderUser_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`posting_id`) REFERENCES `posting` (`posting_id`)
);

CREATE TABLE `jobCategory` (
	`jobCategoryId` CHAR(6) NOT NULL,
	`jobCategoryDesc` VARCHAR(60) NOT NULL
);
    



INSERT INTO `users` VALUES 
	(1,'Dizzle','1234','Danielle','d@sheridancollege.ca','2018-11-29 00:00:00','admin'),
	(2,'Cue','4321','Q','q@sheridancollege.ca','2018-11-30 00:00:00','admin'),
	(3,'Eh','54321','Abb','a@sheridancollege.ca','2018-11-29 00:00:00','admin'),
	(4,'Gee','12345','Jorge','g@sheridancollege.ca','2018-11-29 00:00:00','user'),
	(5,'Peetz','1111','Peter','p@sheridancollege.ca','2018-12-02 00:00:00','user'),
	(6,'DrAbstract','hallo','Dan','da@sheridancollege.ca','2018-12-01 00:00:00','user'),
    (7,'Bruno','honey','Ursus','u@sheridancollege.ca','2018-12-02 00:00:00','user');
    
INSERT INTO posting (posting_id, user_id, username, jobCategory, title, description, compensation, status, dateCreated, dateUpdated) VALUES
	(1, 6, "DrAbstract", "MATART", "Cat jackets", "The finest jackets made from free-range cats", "10-12 cats", "active", "2018-12-03 00:00:00", "2018-12-07 00:00:00"),
	(2, 4, "Gee", "ANIGAM", "Character Designs", "Traditional. Please stop asking if I will do pixel art for you", "Ceramics", "active", "2018-12-03 00:00:00",  "2018-12-05 00:00:00"),
	(3, 7, "Bruno", "APPHEA", "Massage", "Not that kind of massage. I'm a professional", "Website", "active", "2018-12-03 00:00:00", "2018-12-12 00:00:00"),
	(4, 5, "Peetz", "VISPER", "1 person show", "This is my thesis project. Please come. My mark depends on it", "Something good?", "active", "2018-12-03 00:00:00",  "2018-12-09 00:00:00"),
	(5, 1, "Dizzle", "PUBSAF", "Security", "I will enforce your lines. No bollards needd", "Website", "active", "2018-12-03 00:00:00", "2018-12-11 00:00:00");

INSERT INTO reviews (review_id, for_user_id, from_user_id, posting_id, review_date, review_rating, review_text) VALUES
	(1, 6, 1, 1, "2018-12-05 00:00:00", 0.5, "This feels more like coyote than cat" ),
	(2, 5, 2, 4, "2018-12-06 00:00:00", 1.0, "Not loud enough" ),
	(3, 6, 5, 1, "2018-12-07 00:00:00", 4.0, "This is a fine jacket. I would and have recommended this service" ),
	(4, 4, 5, 2, "2018-12-07 00:00:00", 1.0, "This ain't pixel art, my guy" ),
	(5, 1, 5, 5, "2018-12-08 00:00:00", 0.5, "I did not feel secured" ),
	(6, 7, 5, 3, "2018-12-08 00:00:00", 0.0, "Bear claws scratch. :(" );
    
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
    
INSERT INTO orders (order_id, requestOrderUser_id, postOrderUser_id, posting_id, description, dateRequested, dateResponsed, dateCompleted, status) VALUES
	(1, 1, 6, 1, "The finest jackets made from free-range cats", "2018-12-04 00:00:00", "2018-12-05 00:00:00", "2018-12-07 00:00:00", "active"),
	(2, 2, 5, 4, "This is my thesis project. Please come. My mark depends on it", "2018-12-04 00:00:00", "2018-12-05 00:00:00", "2018-12-07 00:00:00", "active"),
	(3, 5, 6, 1, "The finest jackets made from free-range cats", "2018-12-05 00:00:00", "2018-12-07 00:00:00", "2018-12-10 00:00:00", "active"),
	(4, 5, 4, 2, "Traditional. Please stop asking if I will do pixel art for you", "2018-12-05 00:00:00", "2018-12-07 00:00:00", "2018-12-10 00:00:00", "active"),
	(5, 5, 1, 5, "I will enforce your lines. No bollards needd", "2018-12-05 00:00:00", "2018-12-07 00:00:00", "2018-12-11 00:00:00", "active"),
	(6, 5, 7, 3, "Not that kind of massage. I'm a professional", "2018-12-06 00:00:00", "2018-12-09 00:00:00", "2018-12-12 00:00:00", "active");




