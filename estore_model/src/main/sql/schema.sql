CREATE DATABASE `estore`;
USE `estore`;

DROP TABLE `estore`.`users`;
DROP TABLE `estore`.`products`;
DROP TABLE `estore`.`prices`;
DROP TABLE `estore`.`inentory`;
DROP TABLE `estore`.`transactions`;

CREATE TABLE `estore`.`users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(32) NULL,
  `last_name` VARCHAR(32) NULL,
  `username` VARCHAR(32) NOT NULL UNIQUE,
  `password` VARCHAR(32) NULL,
  `login_token` VARCHAR(512) NULL,
  PRIMARY KEY (id),
  INDEX (username)
) ENGINE=INNODB;

INSERT INTO `estore`.`users` (id,first_name, last_name, username, password) VALUES (1, 'douglas', 'ahlquist', 'dahlquist', ' password');

CREATE TABLE `estore`.`products` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(32) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `variants` JSON NOT NULL,
  `image_url` VARCHAR(512) NULL, 
  PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE `estore`.`prices` (
 `id` BIGINT(20) NOT NULL AUTO_INCREMENT,  
 `product_id` BIGINT(20) NOT NULL,
 `variation_uuid` VARCHAR(64) NULL,
 `start_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `end_time` TIMESTAMP NOT NULL,
 `amount` DECIMAL(6,2),
 PRIMARY KEY (id),
 FOREIGN KEY (product_id) REFERENCES `estore`.`products` (id)
) ENGINE=INNODB;

CREATE TABLE `estore`.`inventory` (
 `id` BIGINT(20) NOT NULL AUTO_INCREMENT,  
 `product_id` BIGINT(20) NOT NULL,
 `variation_id` BIGINT(20) NOT NULL,
 `count` INT,
 PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE `estore`.`selection` (
 `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
 `user_id` BIGINT(20) NOT NULL,  
 `product_id` BIGINT(20) NOT NULL,
 `variation_uuid` VARCHAR(64) NOT NULL,
 `count` INT,
 PRIMARY KEY (id),
 FOREIGN KEY (user_id) REFERENCES `estore`.`users` (id),
 FOREIGN KEY (product_id) REFERENCES `estore`.`products` (id)
) ENGINE=INNODB;

CREATE TABLE `estore`.`cart` (
`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
`user_id` BIGINT(20) NOT NULL,
`open_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`items` JSON NOT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY (user_id) REFERENCES `estore`.`users` (id)
) ENGINE=INNODB; 

CREATE TABLE `estore`.`transactions` (
 `id` BIGINT(20) NOT NULL AUTO_INCREMENT, 
 `type` VARCHAR(1) NOT NULL,
 `user_id` BIGINT(20) NOT NULL, 
 `payment_info` JSON NOT NULL,
 `product_info` JSON NOT NULL,
 `cost` DECIMAL(6,2),
 `trnsaction_time` timestamp NOT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY (user_id) REFERENCES `estore`.`users` (id)
) ENGINE=INNODB;
