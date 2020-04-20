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

CREATE TABLE IF NOT EXISTS Bots (
	bot_id BIGINT AUTO_INCREMENT,
	company_id BIGINT,
	active_time INT,
	probability FLOAT, 
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
