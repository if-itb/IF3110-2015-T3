CREATE TABLE user(
	userId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	email VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL
);

CREATE TABLE question(
	questionId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	askerId INT NOT NULL,
	topic VARCHAR(255) NOT NULL,
	content TEXT NOT NULL,
	time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (askerId) REFERENCES user(userId)
);

CREATE TABLE answer(
	answerId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	questionId INT NOT NULL,
	content TEXT NOT NULL,
	time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	answererId INT NOT NULL,
	FOREIGN KEY (answererId) REFERENCES user(userId),
	FOREIGN KEY (questionId) REFERENCES question(questionId)
);

CREATE TABLE votes_question(
	questionId INT NOT NULL,
	voter INT NOT NULL,
	value INT DEFAULT 0,
	FOREIGN KEY (questionId) REFERENCES question(questionId),
	FOREIGN KEY (voter) REFERENCES user(userId)
);

CREATE TABLE votes_answer(
	answerId INT NOT NULL,
	voter INT UNIQUE NOT NULL,
	value INT DEFAULT 0,
	FOREIGN KEY (answerId) REFERENCES answer(answerId),
	FOREIGN KEY (voter) REFERENCES user(userId)
);

CREATE TABLE tokenlist ( 
	userId int(11), 
	token varchar(255), 
	expdate timestamp 
);

CREATE TABLE comment (
	commentId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	questionId INT NOT NULL,
	commenterId INT NOT NULL,
	content TEXT NOT NULL,
	FOREIGN KEY (questionId) REFERENCES question(questionId),
	FOREIGN KEY (commenterId) REFERENCES user(userId)
);
