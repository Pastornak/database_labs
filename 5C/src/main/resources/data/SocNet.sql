
CREATE SCHEMA IF NOT EXISTS pasternak_5_20 DEFAULT CHARACTER SET utf8 ;
USE pasternak_5_20 ;

CREATE TABLE IF NOT EXISTS gender (
  id_gender BIGINT NOT NULL AUTO_INCREMENT,
  gender_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_gender)
  ) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS gender (
  id_messanger BIGINT NOT NULL AUTO_INCREMENT,
  messanger_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_messanger)
  ) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS person (
  id_person BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  surname VARCHAR(45) NOT NULL,
  id_gender BIGINT NOT NULL,
  PRIMARY KEY (id_person),
  INDEX FK_gender_idx (id_gender ASC),
  CONSTRAINT FK_gender
    FOREIGN KEY (id_gender)
    REFERENCES pasternak_5_20.gender (id_gender)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS available (
  id_person BIGINT NOT NULL,
  id_messanger BIGINT NOT NULL,
  PRIMARY KEY (id_person, id_messanger),
  INDEX FK_messanger_idx (id_messanger ASC),
  CONSTRAINT FK_person
    FOREIGN KEY (id_person)
    REFERENCES pasternak_5_20.person (id_person)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_messanger
    FOREIGN KEY (id_messanger)
    REFERENCES pasternak_5_20.messanger (id_messanger)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
