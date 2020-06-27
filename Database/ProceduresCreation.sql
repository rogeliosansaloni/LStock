
USE stock_c2;

DELIMITER $$

DROP PROCEDURE IF EXISTS getCompaniesChange$$
CREATE PROCEDURE getCompaniesChange()
BEGIN
	SELECT DISTINCT c.company_id as companyId, c.name as name, MAX(s1.price) as current_share, IFNULL((MAX(s1.price) - MAX(s2.price)),0) as share_change, IFNULL(((MAX(s1.price) - MAX(s2.price))/MAX(s1.price))*100,0) as change_per
	FROM Company as c LEFT JOIN Share as s1 ON c.company_id = s1.company_id RIGHT JOIN Share as s2 ON s2.company_id = c.company_id
	WHERE s1.time = (SELECT MAX(s3.time) FROM Share as s3 WHERE s3.company_id = s1.company_id)
	AND s2.time = (SELECT MAX(s4.time) FROM Share as s4 WHERE s4.company_id = s2.company_id AND s4.time <= ADDDATE(NOW(), INTERVAL -5 MINUTE))
    GROUP BY c.company_id
    ORDER BY c.company_id ASC;
END$$

DROP PROCEDURE IF EXISTS getNumUserShares$$
CREATE PROCEDURE getNumUserShares(IN id_user INT, IN id_company INT)
BEGIN
	SELECT SUM(p.share_quantity) as numUserShares
    FROM Purchase AS p
    WHERE p.user_id = id_user AND p.company_id = id_company
    GROUP BY p.user_id, p.company_id;
END$$

DROP PROCEDURE IF EXISTS getMaxMinValues$$
CREATE PROCEDURE getMaxMinValues(IN i INT, IN id_company INT)
BEGIN
	SELECT MAX(s1.price) as maximumValue, MIN(s1.price) as minimumValue
	FROM Share as s1 
    WHERE s1.company_id = id_company AND
	s1.time <= ((SELECT MAX(s5.time) - INTERVAL i MINUTE FROM Share as s5 WHERE s5.company_id = s1.company_id)) AND 
	s1.time > ((SELECT MAX(s6.time) - INTERVAL i+1 MINUTE FROM Share as s6 WHERE s6.company_id = s1.company_id));
END$$

DROP PROCEDURE IF EXISTS getCompanyDetails$$
CREATE PROCEDURE getCompanyDetails(IN i INT, IN id_company INT)
BEGIN
	SELECT c.company_id as companyId, c.name as name, s1.share_id as shareIdOpen, MAX(s1.price) as valueOpen, s1.time as timeOpen,  
    s2.share_id as shareIdClose, s2.price as valueClose, s2.time as timeClose, i as minutesBefore
	FROM Company as c JOIN Share as s1 ON c.company_id = s1.company_id JOIN Share as s2 ON s2.company_id = c.company_id
	WHERE c.company_id = id_company AND
	s2.time <= ((SELECT MAX(s5.time) - INTERVAL i MINUTE FROM Share as s5 WHERE s5.company_id = s1.company_id)) AND 
	s2.time > ((SELECT MAX(s6.time) - INTERVAL i+1 MINUTE FROM Share as s6 WHERE s6.company_id = s1.company_id)) AND 
	s1.time <= ((SELECT MAX(s8.time) - INTERVAL i+1 MINUTE FROM Share as s8 WHERE s8.company_id = s2.company_id))
    GROUP BY s1.share_id, s2.share_id
    ORDER BY s1.time DESC, s2.time DESC LIMIT 1;
END$$

DROP PROCEDURE IF EXISTS getSharesSell$$
CREATE PROCEDURE getSharesSell(IN userId INT, IN companyId INT)
BEGIN
	SELECT userId, companyId, s.share_id as shareId, c.name AS companyName, s.price AS shareValue, p.share_quantity AS shareQuantity
    FROM Share as s JOIN Company as c ON c.company_id = s.company_id JOIN Purchase as p ON p.share_id = s.share_id AND p.company_id = s.company_id
    WHERE p.user_id = userId AND c.company_id = companyID
    ORDER BY s.time DESC;
END$$

DROP PROCEDURE IF EXISTS getSharesCompany$$
CREATE PROCEDURE getSharesCompany(IN id_user INT, IN id_company INT)
BEGIN
	SELECT c.company_id as companyId, c.name as name, s1.price as current_price, p.share_quantity as numShares, (s1.price - s2.price)/p.share_quantity as gainValue;
END$$

DROP PROCEDURE IF EXISTS getSharesChange$$
CREATE PROCEDURE getSharesChange(IN userId INT)
BEGIN
	SELECT userId, c.company_id as companyId, s1.share_id as shareId, c.name AS companyName, s1.price AS shareOriginalValue, s2.price as shareCurrentValue, 
    p.share_quantity AS sharesQuantity, (s2.price - s1.price)*(p.share_quantity) as profitLoss
    FROM Share as s1 JOIN Company as c ON c.company_id = s1.company_id JOIN Purchase as p ON p.share_id = s1.share_id AND p.company_id = s1.company_id
    JOIN Share as s2 ON s2.company_id = s1.company_id
    WHERE p.user_id = userId
    AND s2.share_id = (SELECT s3.share_id FROM Share as s3 WHERE s3.company_id = s2.company_id ORDER BY s3.time DESC LIMIT 1)
    ORDER BY s1.time DESC;
END$$

DROP PROCEDURE IF EXISTS checkIfPurchaseExists$$
CREATE PROCEDURE checkIfPurchaseExists(IN id_user INT, IN id_company INT, IN id_share INT)
BEGIN
	SELECT p.share_quantity
    FROM Purchase as p
    WHERE p.user_id = id_user AND p.company_id = id_company AND p.share_id = id_share;
END$$

DROP PROCEDURE IF EXISTS insertPurchase$$
CREATE PROCEDURE insertPurchase(IN id_user INT, IN id_company INT, IN id_share INT, IN numShares INT)
BEGIN
	INSERT INTO Purchase (user_id, share_id, company_id, share_quantity)
    VALUES (id_user, id_share, id_company, numShares);
END$$

DROP PROCEDURE IF EXISTS updatePurchase$$
CREATE PROCEDURE updatePurchase(IN userId INT, IN companyId INT, IN shareId INT, IN numShares INT)
BEGIN
    
    SET @currentNumShares = 0;
    
    SELECT p.share_quantity
    INTO @currentNumShares
    FROM Purchase as p 
	WHERE p.user_id = userId AND p.company_id = companyId AND p.share_id = shareId;
	
    IF (@currentNumShares + numShares) <= 0 THEN
		DELETE FROM Purchase
        WHERE user_id = userId AND company_id = companyId AND share_id = shareId;
	ELSE
		UPDATE Purchase 
		SET share_quantity = @currentNumShares + numShares
		WHERE user_id = userId AND company_id = companyId AND share_id = shareId;
    END IF;
END$$

DROP PROCEDURE IF EXISTS updateUserBalance$$
CREATE PROCEDURE updateUserBalance(IN id_user INT, IN newBalance FLOAT)
BEGIN
	UPDATE User 
    SET total_balance = newBalance
    WHERE user_id = id_user;
END$$

DROP PROCEDURE IF EXISTS getUserProfileInfo$$
CREATE PROCEDURE getUserProfileInfo(IN id_user INT)
BEGIN
	SELECT email, nickname, description
    FROM User
    WHERE user_id = id_user;
END$$

DROP PROCEDURE IF EXISTS getCompanyCurrentValue$$
CREATE PROCEDURE getCompanyCurrentValue(IN id_company INT)
BEGIN
	SELECT price as currentValue
    FROM Share
    WHERE company_id = id_company
    ORDER BY time DESC LIMIT 1;
END$$

DROP PROCEDURE IF EXISTS getTop10Companies$$
CREATE PROCEDURE getTop10Companies()
BEGIN
	SELECT DISTINCT c.name, s1.price
    FROM Company as c
	JOIN Share as s1 ON s1.company_id = c.company_id
    WHERE s1.time = (SELECT MAX(s2.time) FROM Share as s2 WHERE s2.company_id = s1.company_id)
	ORDER BY s1.price DESC LIMIT 10;
END$$

DROP PROCEDURE IF EXISTS updateCompanyNewValue$$
CREATE PROCEDURE updateCompanyNewValue(IN id_company INT, IN newValueShare FLOAT)
BEGIN
	UPDATE User
    SET total_balance = newBalance
    WHERE user_id = id_user;
END$$

DROP PROCEDURE IF EXISTS getMostCurrentShareId$$
CREATE PROCEDURE getMostCurrentShareId(IN id_company INT)
BEGIN
	SELECT share_id
    FROM Share WHERE company_id = id_company
    ORDER BY time DESC LIMIT 1;
END$$

-- Query for Top10
SELECT DISTINCT c.name as name, MAX(s1.price) as price
FROM Share as s1 JOIN Company as c ON s1.company_id = c.company_id
WHERE s1.time = (SELECT MAX(s2.time) FROM Share as s2 WHERE s2.company_id = s1.company_id)
GROUP BY s1.company_id
ORDER BY MAX(s1.price) DESC LIMIT 10;

DELIMITER ;