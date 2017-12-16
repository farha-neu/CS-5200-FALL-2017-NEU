DELETE `address` FROM `address` JOIN `person`
	ON `address`.`person`=`person`.`id`
WHERE 
	`person`.`username`='alice'
AND `address`.`primary`=1;
	
DELETE `widget` FROM `widget` JOIN `page`
	ON `widget`.`page`=`page`.`id`
WHERE 
	`page`.`title`='Contact'
AND `widget`.`order`= (SELECT `x`.`value`
						FROM (SELECT max(`widget`.`order`) AS `value` 
							FROM `widget`) AS `x`);

DELETE `page` FROM `page` JOIN `website`
	ON `page`.`website`=`website`.`id`
WHERE 
	`website`.`name`='Wikipedia'
AND `page`.`updated`= (SELECT `x`.`maxdate`
						FROM (SELECT max(`page`.`updated`) AS `maxdate` 
							FROM `page`) AS `x`);
    
DELETE FROM `website` WHERE `website`.`name`='CNET';