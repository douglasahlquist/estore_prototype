CREATE DATABASE `estore`;
USE `estore`;

DROP TABLE `estore`.`users`;
DROP TABLE `estore`.`products`;
DROP TABLE `estore`.`prices`;
DROP TABLE `estore`.`inentory`;
DROP TABLE `estore`.`selections`;
DROP TABLE `estore`.`carts`;
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
  `image_url` VARCHAR(512) NULL, 
  PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE `estore`.`variation` (
  `id` VARCHAR(64) NOT NULL UNIQUE,
  `product_id` BIGINT(20) NOT NULL,
  `attributes` JSON NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (product_id) REFERENCES `estore`.`products` (id)
) ENGINE=INNODB;


CREATE TABLE `estore`.`prices` (
 `id` BIGINT(20) NOT NULL AUTO_INCREMENT,  
 `product_id` BIGINT(20) NOT NULL,
 `variation_id` VARCHAR(64) NULL,
 `start_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `end_time` TIMESTAMP NOT NULL,
 `amount` DECIMAL(6,2) NOT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY (product_id) REFERENCES `estore`.`products` (id),
 FOREIGN KEY (variation_id) REFERENCES `estore`.`variation` (id)
) ENGINE=INNODB;

CREATE TABLE `estore`.`inventory` (
 `id` BIGINT(20) NOT NULL AUTO_INCREMENT,  
 `product_id` BIGINT(20) NOT NULL,
 `variation_id` VARCHAR(64) NULL,
 `count` INT,
 PRIMARY KEY (id),
 FOREIGN KEY (product_id) REFERENCES `estore`.`products` (id),
 FOREIGN KEY (variation_id) REFERENCES `estore`.`variation` (id)
) ENGINE=INNODB;

CREATE TABLE `estore`.`selections` (
 `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
 `user_id` BIGINT(20) NOT NULL,  
 `product_id` BIGINT(20) NOT NULL,
 `variation_id` VARCHAR(64) NOT NULL,
 `count` INT,
 PRIMARY KEY (id),
 FOREIGN KEY (user_id) REFERENCES `estore`.`users` (id),
 FOREIGN KEY (product_id) REFERENCES `estore`.`products` (id),
 FOREIGN KEY (variation_id) REFERENCES `estore`.`variation` (id)
) ENGINE=INNODB;

CREATE TABLE `estore`.`carts` (
`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
`user_id` BIGINT(20) NOT NULL,
`open_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
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

CREATE VIEW `estore`.`productinventoryprice` AS
SELECT 
  pd.id AS id, 
  pd.category AS category, 
  pd.description AS description, 
  pd.image_url AS image_url,
  v.id AS variation_uuid,
  v.attributes AS attributes,
  pc.start_time AS sale_start_time,
  pc.end_time AS sale_end_time,
  pc.amount AS amount, 
  i.count AS count 
	FROM products pd 
	JOIN prices pc ON pd.id = pc.product_id 
	JOIN inventory i ON pd.id = i.product_id
	JOIN variation v ON pd.id = v.product_id;


