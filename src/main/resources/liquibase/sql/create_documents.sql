CREATE TABLE `documents` (
  `document_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(45)  NOT NULL,
  `created` datetime NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`document_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`)
);