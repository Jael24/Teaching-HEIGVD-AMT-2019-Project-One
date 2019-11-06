SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

DROP SCHEMA IF EXISTS `cinema`;
CREATE SCHEMA IF NOT EXISTS `cinema` DEFAULT CHARACTER SET utf8 ;
USE `cinema` ;

DROP TABLE IF EXISTS `actor`;
CREATE TABLE IF NOT EXISTS `actor` (
  `idActor` INT NOT NULL,
  `fullname` VARCHAR(200) NULL,
  `password` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idActor`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `movie`;
CREATE TABLE IF NOT EXISTS `movie` (
  `idMovie` INT NOT NULL,
  `title` VARCHAR(400) NULL,
  PRIMARY KEY (`idMovie`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `character`;
CREATE TABLE IF NOT EXISTS `character` (
  `idChar` INT NOT NULL,
  `idActor` INT NOT NULL,
  `idMovie` INT NOT NULL,
  `charName` VARCHAR(1000) NULL,
  PRIMARY KEY (`idChar`),
  CONSTRAINT `fk_Character_Actor`
    FOREIGN KEY (`idActor`)
    REFERENCES `actor` (`idActor`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Character_Movie1`
    FOREIGN KEY (`idMovie`)
    REFERENCES `movie` (`idMovie`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;
