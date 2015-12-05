/*PAKAI MYSQL!*/

CREATE TABLE IF NOT EXISTS UAccount (
	Email varchar(320) NOT NULL,
	AuthorName varchar(255) NOT NULL,
	AccountPassword char(128) NOT  NULL,
	PRIMARY KEY (Email)
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
     CONSTRAINT FOREIGN KEY (Email) REFERENCES UAccount(Email) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS HasVotedQuestion(
	Email varchar(320) NOT NULL,
	qid int(11) NOT NULL,
	up boolean DEFAULT '0',
	CONSTRAINT FOREIGN KEY (Email) REFERENCES UAccount(Email) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (qid) REFERENCES Question(qid) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS HasVotedAnswer(
	Email varchar(320) NOT NULL,
	qid int(11) NOT NULL,
	aid int(11) NOT NULL,
	up boolean DEFAULT '0',
	CONSTRAINT FOREIGN KEY (Email) REFERENCES UAccount(Email) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (qid) REFERENCES Question(qid) ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (aid) REFERENCES Answer(aid) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS QuestionComments(
	cid int(11) NOT NULL AUTO_INCREMENT,
	qid int(11) NOT NULL,
	Email varchar(320) NOT NULL,
	qcomment varchar(64511),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (cid),
	CONSTRAINT FOREIGN KEY (Email) REFERENCES UAccount(Email) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (qid) REFERENCES Question(qid) ON DELETE CASCADE
);

GRANT SELECT, INSERT, DELETE, UPDATE ON TABLE UAccount TO idservices IDENTIFIED BY 'idservicespwd';
GRANT SELECT, INSERT ON TABLE UAccount TO stackexchange IDENTIFIED BY 'stackexchangespwd';
GRANT SELECT, INSERT, DELETE, UPDATE ON TABLE Question TO stackexchange;
GRANT SELECT, INSERT, DELETE, UPDATE ON TABLE Answer TO stackexchange;
GRANT SELECT, INSERT, DELETE, UPDATE ON TABLE HasVotedQuestion TO stackexchange;
GRANT SELECT, INSERT, DELETE, UPDATE ON TABLE HasVotedAnswer TO stackexchange;
GRANT SELECT, INSERT, DELETE, UPDATE ON TABLE QuestionComments TO stackexchange;
