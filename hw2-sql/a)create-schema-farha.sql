CREATE SCHEMA `hw3_jawed_farha_fall_2017` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `hw3_jawed_farha_fall_2017`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `dob` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));

CREATE TABLE `hw3_jawed_farha_fall_2017`.`user` (
  `id` INT NOT NULL,
  `userAgreement` TINYINT NOT NULL,
  `userKey` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `userKey_UNIQUE` (`userKey` ASC),
  CONSTRAINT `user_person_generalization`
    FOREIGN KEY (`id`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

 CREATE TABLE `hw3_jawed_farha_fall_2017`.`developer` (
  `id` INT NOT NULL,
  `developerKey` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `developerKey_UNIQUE` (`developerKey` ASC),
  CONSTRAINT `developer_person_generalization`
    FOREIGN KEY (`id`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

 CREATE TABLE `hw3_jawed_farha_fall_2017`.`website` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NULL,
  `created` DATE NULL,
  `updated` DATE NULL,
  `visits` INT NULL,
  `developerId` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE KEY `name_UNIQUE` (`name`),
  INDEX `developer_idx` (`developerId` ASC),
  CONSTRAINT `website-developer`
    FOREIGN KEY (`developerId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`developer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `hw3_jawed_farha_fall_2017`.`page` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NULL,
  `created` DATE NULL DEFAULT '2017-09-06',
  `updated` DATE NULL DEFAULT '2017-10-30',
  `visits` INT NULL,
  `websiteId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `website_idx` (`websiteId` ASC),
  CONSTRAINT `page-website`
    FOREIGN KEY (`websiteId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`website` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `hw3_jawed_farha_fall_2017`.`widget` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `width` INT NULL,
  `height` INT NULL,
  `cssClass` VARCHAR(45) NULL,
  `cssStyle` VARCHAR(45) NULL,
  `text` VARCHAR(45) NULL,
  `order` INT NULL,
  `size` INT NULL DEFAULT 2,
  `html` VARCHAR(45) NULL,
  `url` VARCHAR(45) NULL,
  `shareable` TINYINT NULL,
  `expandable` TINYINT NULL,
  `src` VARCHAR(45) NULL,
  `pageId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `page_idx` (`pageId` ASC),
  CONSTRAINT `widget-page`
    FOREIGN KEY (`pageId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`page` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `hw3_jawed_farha_fall_2017`.`phone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phone` VARCHAR(45) NOT NULL,
  `primary` TINYINT NULL,
  `personId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `person_idx` (`personId` ASC),
  CONSTRAINT `phone-person`
    FOREIGN KEY (`personId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `hw3_jawed_farha_fall_2017`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street1` VARCHAR(45) NULL,
  `street2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  `primary` TINYINT NULL,
  `personId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `address-person_idx` (`personId` ASC),
  CONSTRAINT `address-person`
    FOREIGN KEY (`personId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `hw3_jawed_farha_fall_2017`.`priviledge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name`));



CREATE TABLE `hw3_jawed_farha_fall_2017`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name`));


CREATE TABLE `hw3_jawed_farha_fall_2017`.`websiterole` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `developerId` INT NOT NULL,
  `websiteId` INT NOT NULL,
  `roleId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `websiterole_developer_idx` (`developerId` ASC),
  INDEX `websiterole_website_idx` (`websiteId` ASC),
  INDEX `websiterole_role_idx` (`roleId` ASC),
  CONSTRAINT `websiterole_developer`
    FOREIGN KEY (`developerId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`developer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `websiterole_website`
    FOREIGN KEY (`websiteId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`website` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `websiterole_role`
    FOREIGN KEY (`roleId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `hw3_jawed_farha_fall_2017`.`websitepriviledge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `developerId` INT NOT NULL,
  `websiteId` INT NOT NULL,
  `priviledgeId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `websitepriviledge_developer_idx` (`developerId` ASC),
  INDEX `websitepriviledge_website_idx` (`websiteId` ASC),
  INDEX `websitepriviledge_priviledge_idx` (`priviledgeId` ASC),
  CONSTRAINT `websitepriviledge_developer`
    FOREIGN KEY (`developerId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`developer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `websitepriviledge_website`
    FOREIGN KEY (`websiteId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`website` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `websitepriviledge_priviledge`
    FOREIGN KEY (`priviledgeId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`priviledge` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `hw3_jawed_farha_fall_2017`.`pagerole` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `developerId` INT NOT NULL,
  `pageId` INT NOT NULL,
  `roleId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `pagerole_developer_idx` (`developerId` ASC),
  INDEX `pagerole_page_idx` (`pageId` ASC),
  INDEX `pagerole_role_idx` (`roleId` ASC),
  CONSTRAINT `pagerole_developer`
    FOREIGN KEY (`developerId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`developer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagerole_page`
    FOREIGN KEY (`pageId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`page` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagerole_role`
    FOREIGN KEY (`roleId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `hw3_jawed_farha_fall_2017`.`pagepriviledge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `developerId` INT NOT NULL,
  `pageId` INT NOT NULL,
  `priviledgeId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `pagepriviledge_developer_idx` (`developerId` ASC),
  INDEX `pagepriviledge_page_idx` (`pageId` ASC),
  INDEX `pagepriviledge_priviledge_idx` (`priviledgeId` ASC),
  CONSTRAINT `pagepriviledge_developer`
    FOREIGN KEY (`developerId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`developer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagepriviledge_page`
    FOREIGN KEY (`pageId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`page` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagepriviledge_priviledge`
    FOREIGN KEY (`priviledgeId`)
    REFERENCES `hw3_jawed_farha_fall_2017`.`priviledge` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

