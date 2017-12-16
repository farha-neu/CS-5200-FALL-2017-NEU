SELECT `person`.`username`,`person`.`password`,
`person`.`firstname`,`person`.`lastname`,
`person`.`email`,`developer`.`developerKey`
FROM `person` JOIN `developer`
ON `person`.`id`= `developer`.`id`;

SELECT `person`.`username`,`person`.`password`,
`person`.`firstname`,`person`.`lastname`,
`person`.`email`,`developer`.`developerKey`
FROM `person` JOIN `developer`
ON `person`.`id`= `developer`.`id`
WHERE `developer`.`id`=34;

SELECT `person`.`username`,`person`.`password`,
`person`.`firstname`,`person`.`lastname`,
`person`.`email`,`developer`.`developerKey`
FROM `websiterole`,`website`,`developer`,`role`,`person`
WHERE `websiterole`.`website`=`website`.`id`
AND `websiterole`.`developer`=`developer`.`id`
AND `websiterole`.`role`=`role`.`id`
AND `developer`.`id`=`person`.`id`
AND `website`.`name`= 'Twitter'
AND `role`.`name`<>'owner';

SELECT `person`.`username`,`person`.`password`,
`person`.`firstname`,`person`.`lastname`,
`person`.`email`,`developer`.`developerKey`,
`page`.`visits`
FROM `pagerole`,`page`,`developer`,`role`,`person`
WHERE `pagerole`.`page`=`page`.`id`
AND `pagerole`.`developer`=`developer`.`id`
AND `pagerole`.`role`=`role`.`id`
AND `developer`.`id`=`person`.`id`
AND `page`.`visits`<300000
AND `role`.`name`='reviewer';

SELECT `person`.`username`,`person`.`password`,
`person`.`firstname`,`person`.`lastname`,
`person`.`email`,`developer`.`developerKey`
FROM `pagerole`,`website`,`page`,`widget`,`developer`,`role`,`person`
WHERE `pagerole`.`page`=`page`.`id`
AND `pagerole`.`developer`=`developer`.`id`
AND `pagerole`.`role`=`role`.`id`
AND `page`.`website`=`website`.`id`
AND `widget`.`page`=`page`.`id`
AND `developer`.`id`=`person`.`id`
AND `role`.`name`='writer'
AND `widget`.`type`='heading'
AND `page`.`title` ='Home' 
AND `website`.`name`='CNET';

SELECT `website`.`name` FROM `website`
WHERE `website`.`visits`=
	(SELECT min(`website`.`visits`) FROM `website`);

SELECT `website`.`name` FROM `website`
WHERE `website`.`id`=678;

SELECT `website`.`name` AS 'website'
FROM `pagerole`,`website`,`page`,`widget`,`developer`,`role`,`person`
WHERE `pagerole`.`page`=`page`.`id`
AND `pagerole`.`developer`=`developer`.`id`
AND `pagerole`.`role`=`role`.`id`
AND `page`.`website`=`website`.`id`
AND `widget`.`page`=`page`.`id`
AND `developer`.`id`=`person`.`id`
AND `person`.`username`='bob'
AND `role`.`name`='reviewer'
AND `widget`.`type`='youtube';

SELECT `website`.`name` AS 'website'
FROM `websiterole`,`website`,`developer`,`role`,`person`
WHERE `websiterole`.`website`=`website`.`id`
AND `websiterole`.`developer`=`developer`.`id`
AND `websiterole`.`role`=`role`.`id`
AND `developer`.`id`=`person`.`id`
AND `person`.`username`= 'Alice'
AND `role`.`name`='owner';

SELECT `website`.`name` AS 'website'
FROM `websiterole`,`website`,`developer`,`role`,`person`
WHERE `websiterole`.`website`=`website`.`id`
AND `websiterole`.`developer`=`developer`.`id`
AND `websiterole`.`role`=`role`.`id`
AND `developer`.`id`=`person`.`id`
AND `person`.`username`= 'charlie'
AND `role`.`name`='admin'
AND `website`.`visits`> 6000000;

SELECT * FROM `page`
WHERE `page`.`visits`=
	(SELECT max(`page`.`visits`) FROM `page`);

SELECT `page`.`title` FROM `page`
WHERE `page`.`id`= 234;
	
SELECT `page`.`title`
FROM `pagerole`,`page`,`developer`,`role`,`person`
WHERE `pagerole`.`page`=`page`.`id`
AND `pagerole`.`developer`=`developer`.`id`
AND `pagerole`.`role`=`role`.`id`
AND `developer`.`id`=`person`.`id`
AND `person`.`username`='alice'
AND `role`.`name`='editor';

SELECT `website`.`name`,SUM(`page`.`visits`) AS  'page views'
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
WHERE `website`.`name`='CNET';

SELECT `website`.`name`,AVG(`page`.`visits`) AS  'avg page views'
FROM `page` JOIN `website`
ON `page`.`website`=`website`.`id`
where `website`.`name`='Wikipedia';

SELECT `widget`.`name` AS 'widget',
`widget`.`type`, `widget`.`text`,
`widget`.`order`,`widget`.`height`,  
`widget`.`width`, `widget`.`size`, 
`widget`.`url`
FROM  `widget` JOIN `page` JOIN `website`
ON `widget`.`page`=`page`.`id`
AND `page`.`website`=`website`.`id`
WHERE `website`.`name`='CNET'
AND `page`.`title`='Home';

SELECT `widget`.`name` AS 'widget',
`widget`.`type`, `widget`.`text`,
`widget`.`order`,`widget`.`height`,  
`widget`.`width`, `widget`.`size`, 
`widget`.`url`
FROM  `widget` JOIN `page` JOIN `website`
ON `widget`.`page`=`page`.`id`
AND `page`.`website`=`website`.`id`
WHERE `website`.`name`='CNN'
AND `widget`.`type`='youtube';

SELECT `widget`.`name` AS 'widget',
`widget`.`type`, `widget`.`text`,
`widget`.`order`,`widget`.`height`,  
`widget`.`width`, `widget`.`size`, 
`widget`.`url`,`page`.`title` AS 'page'
FROM `pagerole`,`page`,`widget`,`developer`,`role`,`person`
WHERE `pagerole`.`page`=`page`.`id`
AND `pagerole`.`developer`=`developer`.`id`
AND `pagerole`.`role`=`role`.`id`
AND `widget`.`page`=`page`.`id`
AND `developer`.`id`=`person`.`id`
AND `person`.`username`='alice'
AND `role`.`name`='reviewer'
AND `widget`.`type`='image';


SELECT `website`.`name` AS 'website',COUNT(`widget`.`id`) AS 'widget count'
FROM `website`,`page`,`widget`
WHERE `widget`.`page`=`page`.`id`
AND `page`.`website`=`website`.`id`
AND `website`.`name`='Wikipedia';





    
    
