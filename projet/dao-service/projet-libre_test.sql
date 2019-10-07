DROP DATABASE IF EXISTS `projet-libre_test`;
DROP USER IF EXISTS 'application'@'localhost';
CREATE DATABASE `projet-libre_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'application'@'localhost' IDENTIFIED BY 'passw0rd';
GRANT ALL ON `projet-libre_test`.* TO 'application'@'localhost' IDENTIFIED BY 'passw0rd';
USE `projet-libre_test`;


SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Role;
DROP TABLE IF EXISTS Marker;

CREATE TABLE Role (
	idRole INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100),
	version INTEGER DEFAULT 1
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE User (
	idUser INTEGER PRIMARY KEY AUTO_INCREMENT,
	idRole INTEGER NOT NULL,
	civility VARCHAR(100),
	firstname VARCHAR(100),
	lastname VARCHAR(100),
	username VARCHAR(100),
	password VARCHAR(100),
	phoneNumber VARCHAR(100),
	dateBirth TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	dateCreation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	dateModification TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	active TINYINT(1) DEFAULT 1,
	version INTEGER DEFAULT 1,
	CONSTRAINT `FK_User_Role` FOREIGN KEY (idRole) referenceS Role (idRole) ON DELETE CASCADE
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE Marker (
	idMarker INTEGER PRIMARY KEY AUTO_INCREMENT,
	idUser INTEGER NOT NULL,
	name VARCHAR(100),
	description VARCHAR(255),
	x VARCHAR(100),
	y VARCHAR(100),
	isPrivate TINYINT(1),
	version INTEGER DEFAULT 1,
	CONSTRAINT `FK_Marker_User` FOREIGN KEY (idUser) referenceS User (idUser) ON DELETE CASCADE
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Role(name) VALUES ("Admin");
INSERT INTO Role(name) VALUES ("User");
INSERT INTO User(idRole, civility,firstname,lastname,username,password,phoneNumber) VALUES (1, 'Mr','Jerome','Cantin','admin@gmail.com','$2a$04$BSKvJAe5ZymbKkzwgvj1Cerfi6mEEqbGPUdDJDhgcHlQJcBBYfpk.','0655554896');
INSERT INTO Marker(idUser, name, description) VALUES ((SELECT MAX(idUser) FROM User), "Restaurant", "C'est un restaurant avec service rapide");
