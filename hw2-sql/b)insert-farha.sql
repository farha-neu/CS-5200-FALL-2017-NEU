INSERT INTO `hw2_jawed_farha_fall_2017`.`person`
(`id`,
`firstname`,
`lastname`,
`username`,
`password`,
`email`,
`dob`)
VALUES
(12,'Alice','Wonder','alice','alice','alice@wonder.com',null),
(23,'Bob','Marley','bob','bob','bob@marley.com',null),
(34,'Charles','Garcia','charlie','charlie','chuch@garcia.com',null),
(45,'Dan','Martin','dan','dan','dan@martina.com',null),
(56,'Ed','Karaz','ed','ed','ed@kar.com',null);

INSERT INTO `hw2_jawed_farha_fall_2017`.`developer`
(`id`,
`developerKey`)
VALUES
((SELECT `id` FROM `person` WHERE `person`.`username`='alice'),
'4321rewq'),
((SELECT `id` FROM `person` WHERE `person`.`username`='bob'),
'5432trew'),
((SELECT `id` FROM `person` WHERE `person`.`username`='charlie'),
'6543ytre');


INSERT INTO `hw2_jawed_farha_fall_2017`.`user`
(`id`,
`userAgreement`,
`userKey`)
VALUES
((SELECT `id` FROM `person` WHERE `person`.`username`='dan'),
1,
'7654fda'),
((SELECT `id` FROM `person` WHERE `person`.`username`='ed'),
1,
'5678dfgh');


INSERT INTO `hw2_jawed_farha_fall_2017`.`website`
(`id`,
`name`,
`description`,
`created`,
`updated`,
`visits`,
`developer`)
VALUES
(123,'Facebook','an online social media and social networking service',
curdate(),curdate(),1234234,
(SELECT `id` FROM `person` WHERE `person`.`username`='alice')),
(234,'Twitter','an online news and social networking service',
curdate(),curdate(),4321543,
(SELECT `id` FROM `person` WHERE `person`.`username`='bob')),
(345,'Wikipedia','a free online encyclopedia',curdate(),curdate(),3456654,
(SELECT `id` FROM `person` WHERE `person`.`username`='charlie')),
(456,'CNN','an American basic cable and satellite television news channel',
curdate(),curdate(),65433454,
(SELECT `id` FROM `person` WHERE `person`.`username`='alice')),
(567,'CNET','an American media website that publishes reviews, news, 
articles, blogs, podcasts and videos on technology and consumer electronics',
curdate(),curdate(),5433455, (SELECT `id` FROM `person` WHERE `person`.`username`='bob')),
(678,'Gizmodo','a design, technology, science and science fiction website that also 
writes articles on politics'
,curdate(),curdate(),4322345, (SELECT `id` FROM `person` WHERE `person`.`username`='charlie'));

INSERT INTO `hw2_jawed_farha_fall_2017`.`websiterole`
(`developer`,
`website`,
`role`)
SELECT  `website`.`developer`, `website`.`id`,`role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`website`,
`hw2_jawed_farha_fall_2017`.`role`
WHERE `website`.`name`='Facebook'
AND
`role`.`name`='owner';

INSERT INTO `hw2_jawed_farha_fall_2017`.`websiterole`
(`developer`,
`website`,
`role`)
SELECT  `website`.`developer`, `website`.`id`,`role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`website`,
`hw2_jawed_farha_fall_2017`.`role`
WHERE `website`.`name`='Twitter'
AND
`role`.`name`='owner';

INSERT INTO `hw2_jawed_farha_fall_2017`.`websiterole`
(`developer`,
`website`,
`role`)
SELECT  `website`.`developer`, `website`.`id`,`role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`website`,
`hw2_jawed_farha_fall_2017`.`role`
WHERE `website`.`name`='Wikipedia'
AND
`role`.`name`='owner';

INSERT INTO `hw2_jawed_farha_fall_2017`.`websiterole`
(`developer`,
`website`,
`role`)
SELECT  `website`.`developer`, `website`.`id`,`role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`website`,
`hw2_jawed_farha_fall_2017`.`role`
WHERE `website`.`name`='CNN'
AND
`role`.`name`='owner';

INSERT INTO `hw2_jawed_farha_fall_2017`.`websiterole`
(`developer`,
`website`,
`role`)
SELECT  `website`.`developer`, `website`.`id`,`role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`website`,
`hw2_jawed_farha_fall_2017`.`role`
WHERE `website`.`name`='CNET'
AND
`role`.`name`='owner';

INSERT INTO `hw2_jawed_farha_fall_2017`.`websiterole`
(`developer`,
`website`,
`role`)
SELECT  `website`.`developer`, `website`.`id`,`role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`website`,
`hw2_jawed_farha_fall_2017`.`role`
WHERE `website`.`name`='Gizmodo'
AND
`role`.`name`='owner';

INSERT INTO `hw2_jawed_farha_fall_2017`.`websiterole`
(`developer`,
`website`,
`role`)
VALUES

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='bob'),
(SELECT  `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website`
WHERE `website`.`name`='Facebook'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='editor')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='charlie'),
(SELECT  `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website`
WHERE `website`.`name`='Twitter'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='editor')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='alice'),
(SELECT  `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website`
WHERE `website`.`name`='Wikipedia'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='editor')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='bob'),
(SELECT  `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website`
WHERE `website`.`name`='CNN'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='editor')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='charlie'),
(SELECT  `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website`
WHERE `website`.`name`='CNET'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='editor')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='alice'),
(SELECT  `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website`
WHERE `website`.`name`='Gizmodo'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='editor')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='charlie'),
(SELECT  `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website`
WHERE `website`.`name`='Facebook'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='admin')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='alice'),
(SELECT  `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website`
WHERE `website`.`name`='Twitter'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='admin')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='bob'),
(SELECT  `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website`
WHERE `website`.`name`='Wikipedia'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='admin')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='charlie'),
(SELECT  `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website`
WHERE `website`.`name`='CNN'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='admin')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='alice'),
(SELECT  `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website`
WHERE `website`.`name`='CNET'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='admin')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='bob'),
(SELECT  `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website`
WHERE `website`.`name`='Gizmodo'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='admin'));


INSERT INTO `hw2_jawed_farha_fall_2017`.`page`
(`id`,
`title`,
`description`,
`visits`,
`website`)
VALUES
(123, 'Home','Landing Page',123434,
(SELECT `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website` 
WHERE `website`.`name`="CNET")),
(234, 'About','Website description',234545,
(SELECT `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website` 
WHERE `website`.`name`="Gizmodo")),
(345, 'Contact','Addresses, phones, and contact info',345656,
(SELECT `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website` 
WHERE `website`.`name`="Wikipedia")),
(456, 'Preferences','Where users can configure their preferences',456776,
(SELECT `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website` 
WHERE `website`.`name`="CNN")),
(567, 'Profile','Users can configure their personal information',567878,
(SELECT `website`.`id` FROM `hw2_jawed_farha_fall_2017`.`website` 
WHERE `website`.`name`="CNET"));


INSERT INTO `hw2_jawed_farha_fall_2017`.`pagerole`
(`developer`,
`page`,
`role`)
VALUES
((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='alice'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Home' AND `website`.`name`='CNET'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='editor')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='bob'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='About' AND `website`.`name`='Gizmodo'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='editor')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='charlie'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Contact' AND `website`.`name`='Wikipedia'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='editor')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='alice'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Preferences' AND `website`.`name`='CNN'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='editor')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='bob'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Profile' AND `website`.`name`='CNET'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='editor')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='bob'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Home' AND `website`.`name`='CNET'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='reviewer')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='charlie'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='About' AND `website`.`name`='Gizmodo'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='reviewer')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='alice'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Contact' AND `website`.`name`='Wikipedia'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='reviewer')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='bob'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Preferences' AND `website`.`name`='CNN'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='reviewer')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='charlie'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Profile' AND `website`.`name`='CNET'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='reviewer')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='charlie'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Home' AND `website`.`name`='CNET'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='writer')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='alice'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='About' AND `website`.`name`='Gizmodo'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='writer')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='bob'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Contact' AND `website`.`name`='Wikipedia'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='writer')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='charlie'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Preferences' AND `website`.`name`='CNN'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='writer')),

((SELECT  `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='alice'),
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Profile' AND `website`.`name`='CNET'),
(SELECT `role`.`id`
FROM `hw2_jawed_farha_fall_2017`.`role`
WHERE
`role`.`name`='writer'));


INSERT INTO `hw2_jawed_farha_fall_2017`.`widget`
(`id`,`name`,`type`,`width`,`height`,`cssClass`,`cssStyle`,`text`,`order`,`html`,
`url`,`shareable`,`expandable`,`src`,`page`)
VALUES
(123,'head123','heading',null,null,null,null,'Welcome',0,null,null,null,null,null,
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Home' AND `website`.`name`='CNET')),

(234,'post234','html',null,null,null,null,'<p>Lorem</p>',0,null,null,null,null,null,
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='About' AND `website`.`name`='Gizmodo')),

(345,'head345','heading',null,null,null,null,'Hi',1,null,null,null,null,null,
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Contact' AND `website`.`name`='Wikipedia')),

(456,'intro456','html',null,null,null,null,'<h1>Hi</h1>',2,null,null,null,null,null,
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Contact' AND `website`.`name`='Wikipedia')),

(567,'image345','image',50,100,null,null,null,3,null,'/img/567.png',null,null,null,
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Contact' AND `website`.`name`='Wikipedia')),

(678,'video456','youtube',400,300,null,null,null,0,null,'https://youtu.be/h67VX51QXiQ',null,null,null,
(SELECT `page`.`id`
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `page`.`title`='Preferences' AND `website`.`name`='CNN'));

INSERT INTO `hw2_jawed_farha_fall_2017`.`phone`
(`phone`,
`primary`,
`person`)
VALUES
('123-234-3456',1,
(SELECT `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='alice')),
('234-345-4566',0,
(SELECT `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='alice')),
('345-456-5677',1,
(SELECT `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='bob')),
('321-432-5435',1,
(SELECT `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='charlie')),
('432-432-5433',0,
(SELECT `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='charlie')),
('543-543-6544',0,
(SELECT `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='charlie'));

INSERT INTO `hw2_jawed_farha_fall_2017`.`address`
(`street1`,`street2`,`city`,`state`,`zip`,`primary`,`person`)
VALUES
('123 Adam St.',null,'Alton',null,'01234',1,
(SELECT `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='alice')),
('234 Birch St.',null,'Boston',null,'02345',0,
(SELECT `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='alice')),
('345 Charles St.',null,'Chelms',null,'03455',1,
(SELECT `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='bob')),
('456 Down St.',null,'Dalton',null,'04566',0,
(SELECT `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='bob')),
('543 East St.',null,'Everett',null,'01112',0,
(SELECT `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='bob')),
('654 Frank St.',null,'Foulton',null,'04322',1,
(SELECT `person`.`id` FROM `hw2_jawed_farha_fall_2017`.`person`
WHERE `person`.`username`='charlie'));



