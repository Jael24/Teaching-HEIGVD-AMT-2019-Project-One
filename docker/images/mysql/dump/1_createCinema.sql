SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

DROP SCHEMA IF EXISTS `cinema`;
CREATE SCHEMA IF NOT EXISTS `cinema` DEFAULT CHARACTER SET utf8 ;
USE `cinema` ;

DROP TABLE IF EXISTS `actor`;
CREATE TABLE IF NOT EXISTS `actor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(100) NULL,
  `lastName` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `movie`;
CREATE TABLE IF NOT EXISTS `movie` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(250) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `character`;
CREATE TABLE IF NOT EXISTS `character` (
  `actor_id` INT NOT NULL,
  `movie_id` INT NOT NULL,
  `completeName` VARCHAR(45) NULL,
  PRIMARY KEY (`actor_id`, `movie_id`),
  CONSTRAINT `fk_Character_Actor`
    FOREIGN KEY (`actor_id`)
    REFERENCES `actor` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Character_Movie1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;
