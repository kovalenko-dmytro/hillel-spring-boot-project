CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45)  NOT NULL,
  `login` varchar(45)  NOT NULL,
  `password` varchar(255)  NOT NULL,
  PRIMARY KEY (`user_id`)
);