/*PAKAI MYSQL!*/

CREATE TABLE IF NOT EXISTS UAccount (
	Email varchar(320) NOT NULL,
	AuthorName varchar(255) NOT NULL,
	AccountPassword char(128) NOT  NULL,
	PRIMARY KEY (Email)
);

CREATE TABLE IF NOT EXISTS UAccountAccessToken (
	Token BINARY(16) NOT NULL,
	Email varchar(320) NOT NULL,
	Expiration DATETIME NOT NULL,
	PRIMARY KEY (Token),
	CONSTRAINT FOREIGN KEY (Email) REFERENCES UAccount(Email) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Question (
     qid int(11) NOT NULL AUTO_INCREMENT,
     Email varchar(320) NOT NULL, /*owner*/
     QuestionTopic varchar(255) NOT NULL,
     Content varchar(64511),
     vote int(11) DEFAULT '0',
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY(qid),
     CONSTRAINT FOREIGN KEY (Email) REFERENCES UAccount(Email)
);

CREATE TABLE IF NOT EXISTS Answer (
     aid int(11) NOT NULL AUTO_INCREMENT,
     qid int(11) NOT NULL,
     Email varchar(320) NOT NULL, /*owner*/
     Content varchar(64511),
     vote int(11) DEFAULT '0',
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY(aid),
     CONSTRAINT FOREIGN KEY (qid) REFERENCES Question(qid) ON DELETE CASCADE,
     CONSTRAINT FOREIGN KEY (Email) REFERENCES UAccount(Email)
);

CREATE TABLE IF NOT EXISTS HasVotedQuestion(
	UEmail varchar(320) NOT NULL,
	qid int(11) NOT NULL,
	voted boolean DEFAULT '0',
	CONSTRAINT FOREIGN KEY (UEmail) REFERENCES UAccount(Email),
    CONSTRAINT FOREIGN KEY (qid) REFERENCES Question(qid),
);

CREATE TABLE IF NOT EXISTS HasVotedAnswer(
	UEmail varchar(320) NOT NULL,
	qid int(11) NOT NULL,
	aid int(11) NOT NULL,
	voted boolean DEFAULT '0',
	CONSTRAINT FOREIGN KEY (UEmail) REFERENCES UAccount(Email),
    CONSTRAINT FOREIGN KEY (qid) REFERENCES Question(qid),
	CONSTRAINT FOREIGN KEY (aid) REFERENCES Answer(aid),
);

GRANT SELECT, INSERT, DELETE, UPDATE ON TABLE UAccount TO idservices IDENTIFIED BY 'idservicespwd';
GRANT SELECT, INSERT, DELETE, UPDATE ON TABLE UAccountAccessToken TO idservices;
GRANT SELECT ON TABLE UAccount TO stackexchange IDENTIFIED BY 'stackexchangespwd';
GRANT SELECT, INSERT, DELETE, UPDATE ON TABLE Question TO stackexchange;
GRANT SELECT, INSERT, DELETE, UPDATE ON TABLE Answer TO stackexchange;
