CREATE DATABASE stock_c2;

CREATE TABLE IF NOT EXISTS User (
	user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	email VARCHAR(255) NOT NULL,
	nickname VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	total_balance FLOAT DEFAULT 0.0,
	UNIQUE(email, nickname)
);

CREATE TABLE IF NOT EXISTS Company (
	company_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL
);

INSERT INTO Company(name) VALUES ('Stockers');
INSERT INTO Company(name) VALUES ('Welsh Cakes');
INSERT INTO Company(name) VALUES ('Openlame');
INSERT INTO Company(name) VALUES ('Yearin');
INSERT INTO Company(name) VALUES ('Goodsilron');
INSERT INTO Company(name) VALUES ('Condax');
INSERT INTO Company(name) VALUES ('Opentech');
INSERT INTO Company(name) VALUES ('Golddex');
INSERT INTO Company(name) VALUES ('Isdom');
INSERT INTO Company(name) VALUES ('Y-corporation');
INSERT INTO Company(name) VALUES ('Zencorporation');
INSERT INTO Company(name) VALUES ('Statholdings');
INSERT INTO Company(name) VALUES ('Dalttechnology');
INSERT INTO Company(name) VALUES ('Kinnamplus');
INSERT INTO Company(name) VALUES ('Singletechno');
INSERT INTO Company(name) VALUES ('Xx-holding');
INSERT INTO Company(name) VALUES ('Hottechi');
INSERT INTO Company(name) VALUES ('Labdrill');
INSERT INTO Company(name) VALUES ('Domzoom');
INSERT INTO Company(name) VALUES ('Scottech');

CREATE TABLE IF NOT EXISTS Bots (
	bot_id BIGINT AUTO_INCREMENT,
	company_id BIGINT,
	active_time INT,
	probability FLOAT, 
	activity_status BOOLEAN,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
	modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (bot_id, company_id),
	FOREIGN KEY (company_id) REFERENCES Company(company_id)
);

CREATE TABLE IF NOT EXISTS Share (
	share_id BIGINT AUTO_INCREMENT,
	company_id BIGINT,
	price FLOAT DEFAULT 0.0,
	time DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (share_id, company_id),
	FOREIGN KEY (company_id) REFERENCES Company(company_id)
);

INSERT INTO Share(company_id, price) VALUES (1,300);
INSERT INTO Share(company_id, price) VALUES (2,450);
INSERT INTO Share(company_id, price) VALUES (3,500);
INSERT INTO Share(company_id, price) VALUES (4,550);
INSERT INTO Share(company_id, price) VALUES (5,600);
INSERT INTO Share(company_id, price) VALUES (6,20);
INSERT INTO Share(company_id, price) VALUES (7,30);
INSERT INTO Share(company_id, price) VALUES (8,40);
INSERT INTO Share(company_id, price) VALUES (9,50);
INSERT INTO Share(company_id, price) VALUES (10,60);
INSERT INTO Share(company_id, price) VALUES (11,100);
INSERT INTO Share(company_id, price) VALUES (12,700);
INSERT INTO Share(company_id, price) VALUES (13,70);
INSERT INTO Share(company_id, price) VALUES (14,280);
INSERT INTO Share(company_id, price) VALUES (15,80);
INSERT INTO Share(company_id, price) VALUES (16,90);
INSERT INTO Share(company_id, price) VALUES (17,230);
INSERT INTO Share(company_id, price) VALUES (18,200);
INSERT INTO Share(company_id, price) VALUES (19,170);
INSERT INTO Share(company_id, price) VALUES (20,10);

CREATE TABLE IF NOT EXISTS Purchase (
	user_id BIGINT,
	share_id BIGINT,
	company_id BIGINT,
	share_quantity FLOAT,
	PRIMARY KEY (user_id, share_id, company_id),
	FOREIGN KEY (user_id) REFERENCES User(user_id),
	FOREIGN KEY (share_id) REFERENCES Share(share_id),
	FOREIGN KEY (company_id) REFERENCES Company(company_id)
);
