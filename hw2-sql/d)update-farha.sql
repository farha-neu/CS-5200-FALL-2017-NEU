UPDATE `phone` 
SET `phone` = '333-444-5555'
WHERE `phone`.`primary` = 1
AND `phone`.`person`=
(SELECT `person`.`id` FROM `person`
WHERE `person`.`username`='charlie');



SELECT `widget`.`page` FROM `widget` WHERE `widget`.`name`='head345' INTO @store;
SELECT `widget`.`order` FROM `widget` WHERE `widget`.`name`='head345' INTO @pre;
set @post:=3;

UPDATE `widget`
	SET `widget`.`order`=
    CASE 
         WHEN `widget`.`name`= 'head345' THEN @post
         WHEN (@pre < @post AND `widget`.`name` <>'head345' AND
         `widget`.`order`> @pre AND `widget`.`order`<= @post)
		 THEN `widget`.`order`- 1
         WHEN (@pre > @post AND `widget`.`name` <>'head345' AND `widget`.`order`<= @pre)
		 THEN `widget`.`order`+ 1
     ELSE `widget`.`order`
    END
WHERE `widget`.`page`= @store;

UPDATE `page`
SET `page`.`title`=CONCAT('CNET',`page`.`title`)
WHERE `page`.`website`=(SELECT `website`.`id` FROM `website` WHERE `website`.`name`='CNET');

SELECT `pagerole`.`role` FROM `pagerole`,`developer`,`page`,`website`,`person`
	WHERE `pagerole`.`developer`=`developer`.`id`
    AND `pagerole`.`page`=`page`.`id`
    AND `page`.`website`=`website`.`id`
    AND `developer`.`id`=`person`.`id`
    AND `person`.`username`='bob'
    AND `website`.`name`='CNET'
    AND `page`.`title`='Home' INTO @bobrole;

SELECT `pagerole`.`id` FROM `pagerole`,`developer`,`page`,`website`,`person`
	WHERE `pagerole`.`developer`=`developer`.`id`
    AND `pagerole`.`page`=`page`.`id`
    AND `page`.`website`=`website`.`id`
    AND `developer`.`id`=`person`.`id`
    AND `person`.`username`='bob'
    AND `website`.`name`='CNET'
    AND `page`.`title`='Home' INTO @bobid;
    
SELECT `pagerole`.`id` FROM `pagerole`,`developer`,`page`,`website`,`person`
	WHERE `pagerole`.`developer`=`developer`.`id`
    AND `pagerole`.`page`=`page`.`id`
    AND `page`.`website`=`website`.`id`
    AND `developer`.`id`=`person`.`id`
    AND `person`.`username`='charlie'
    AND `website`.`name`='CNET'
    AND `page`.`title`='Home' INTO @charlieid;

UPDATE `pagerole` AS `p1`, `pagerole` AS `p2`
SET `p1`.`role` = `p2`.`role`,
	`p2`.`role`= @bobrole
WHERE (`p1`.`id`,`p2`.`id`)=(@bobid,@charlieid);

