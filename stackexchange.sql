-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2015 at 03:20 PM
-- Server version: 5.6.21
-- PHP Version: 5.5.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `stackexchange`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE IF NOT EXISTS `answers` (
`AnswerID` int(11) NOT NULL,
  `QuestionID` int(11) NOT NULL,
  `Votes` int(11) NOT NULL,
  `Answer` varchar(9999) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Datetime` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`AnswerID`, `QuestionID`, `Votes`, `Answer`, `Name`, `Email`, `Datetime`) VALUES
(6, 38, 0, 'you cant', 'Cliff', 'cliffsantoso@gmail.com', '2015-10-10 17:11:48'),
(7, 38, 1, 'get a boyfriend instead', 'Ben', 'benlemuel@gmail.com', '2015-10-10 17:15:21'),
(9, 38, 0, 'bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben bacot ben ', 'Cliff J', 'cliffsantoso@gmail.com', '2015-10-10 17:25:09'),
(10, 38, 0, 'aadfafaf', 'fsfsdfs', 'fdfsfsf', '2015-10-10 19:00:18'),
(11, 39, 7, '', 'sgsdgsdg', '', '2015-10-10 19:03:31'),
(12, 38, 0, '', 'asd', '', '2015-10-10 19:06:28'),
(13, 39, 3, 'fsfs', 'dsaad', 'sfsdfds@sggsg.asdfdf', '2015-10-10 21:09:42'),
(14, 37, 3, 'fsfsfsfsfsf', 'Cliff', 'fasdfasfasf@gkajf.sjfds', '2015-10-10 21:26:04'),
(15, 42, 5, 'YOU''RE FUCKING GAY', 'Cliff', '', '2015-10-15 15:22:29'),
(16, 43, 1, 'asasadsadsa', 'Yoga', 'yoga@gmail.com', '2015-11-27 10:53:43'),
(17, 38, -1, 'rwa', 'tio', 'tio', '2015-12-05 15:54:19');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
`CommentID` int(11) NOT NULL,
  `QuestionID` int(11) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Content` varchar(9999) NOT NULL,
  `Datetime` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`CommentID`, `QuestionID`, `Name`, `Content`, `Datetime`) VALUES
(1, 37, 'Cliff', 'asdf', '2015-12-05 22:03:03'),
(2, 37, 'Cliff', 'asdf', '2015-12-05 22:03:37'),
(3, 37, 'Cliff', 'asdf', '2015-12-05 22:04:24'),
(4, 37, 'Cliff', 'dssfsf', '2015-12-05 22:05:32'),
(5, 37, 'Cliff', 'asdf', '2015-12-05 22:08:03'),
(6, 37, 'Cliff', 'asdf', '2015-12-05 22:08:23'),
(7, 37, 'Cliff', 'asdf', '2015-12-05 22:18:42'),
(8, 37, 'Cliff', 'ggg', '2015-12-05 22:18:48'),
(9, 37, 'Cliff', 'gggggg my friend', '2015-12-05 22:18:52');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE IF NOT EXISTS `questions` (
`QuestionID` int(11) NOT NULL,
  `Votes` int(11) NOT NULL,
  `Answers` int(11) NOT NULL,
  `Topic` varchar(25) NOT NULL,
  `Question` varchar(9999) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Datetime` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`QuestionID`, `Votes`, `Answers`, `Topic`, `Question`, `Name`, `Email`, `Datetime`) VALUES
(37, 4, 1, 'asdf', 'fsfsfsfsf', 'fsdfsfsf', 'afasfaf@asdf.cg', '2015-10-10 17:19:54'),
(38, 8, 7, 'How to get a gf?', 'im such a loser', 'fdsa', 'cliffsantoso@gmail.com', '2015-10-10 17:08:03'),
(39, 2, 2, 'asdasd', 'asdsdsd', 'adadasdsad', 'sfsfsf@sgsgsgs.sdf', '2015-10-10 19:03:24'),
(42, -2, 1, 'ben???''', 'is ben gay?" i''m not!@#%^*()`~', 'Cliff', 'cliffsantoso@gmail.com', '2015-10-15 15:25:49'),
(43, 1, 1, 'asdad', 'sadasd', 'Yoga', 'yoga@gmail.com', '2015-11-27 10:53:16'),
(44, 1, 0, 'asdad', 'sadasd', 'Yoga', 'yoga@gmail.com', '2015-11-27 10:53:16');

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

CREATE TABLE IF NOT EXISTS `sessions` (
`SessionID` int(11) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `AccessToken` varchar(500) NOT NULL,
  `ExpiredDate` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sessions`
--

INSERT INTO `sessions` (`SessionID`, `Email`, `AccessToken`, `ExpiredDate`) VALUES
(2, 'tio', 'dfcc5e81-8c60-4d90-9b6e-7c6fba3eee55', '0000-00-00 00:00:00'),
(3, 'tio', '26aa4171-c3d7-4f7e-bc2d-678cfb60b062', '0000-00-00 00:00:00'),
(4, 'yoga@gmail.com', 'f50c365e-af19-4c64-b7e1-b43a40344ff0', '2015-11-27 07:36:59'),
(5, 'yoga@gmail.com', '1a5dc46e-4f34-4f27-adc6-01480f071ef4', '2015-11-27 07:42:07'),
(6, 'yoga@gmail.com', 'bd8354fa-b75a-450b-9423-e8098b2d7909', '2015-11-27 07:50:18'),
(7, 'yoga@gmail.com', 'd6ab7a55-ee2a-496e-9103-7c884d2648cf', '2015-11-27 10:57:16'),
(8, 'yoga@gmail.com', '17c44935-d893-4fcf-87ac-1ab991208590', '2015-11-27 10:59:39'),
(9, 'yoga@gmail.com', '96b86eb8-3dcd-4740-8bc5-52c588628b63', '2015-12-05 10:45:56'),
(10, 'yoga@gmail.com', 'eaf5fdcc-92f4-4f79-a1d4-bfb952c615f1', '2015-12-05 11:37:20'),
(12, 'yoga@gmail.com', '70c12f3c-82a4-47c3-99a8-2e7ea6edfaed', '2015-12-05 11:37:45'),
(13, 'yoga@gmail.com', '8b6d33d9-ac05-4618-b824-b4147208a28b', '2015-12-05 11:40:17'),
(15, 'yoga@gmail.com', 'f9f0845b-4d5a-4b6e-ae18-05376440f355', '2015-12-05 11:44:25'),
(16, 'yoga@gmail.com', '9ebe481b-39fc-498f-b7fa-dcbe959dcc97', '2015-12-05 11:45:36'),
(17, 'yoga@gmail.com', '3fb7912f-3b5b-4719-83e4-ea725b543404', '2015-12-05 12:31:35'),
(18, 'yoga@gmail.com', 'de551792-5034-483a-83c9-ae69ba1a7ff4', '2015-12-05 12:34:05'),
(19, 'yoga@gmail.com', '225c258c-827d-4cb4-b0d0-b5aac5918893', '2015-12-05 12:34:59'),
(20, 'yoga@gmail.com', '44ff4bf4-46df-4ec0-bc66-42683116ead0', '2015-12-05 12:36:34'),
(21, 'yoga@gmail.com', 'be7dafac-e41d-41f0-b7e2-e43c8fc3211b', '2015-12-05 12:42:17'),
(22, 'yoga@gmail.com', '42c591c9-0399-490f-9a7b-2bd9befaa97b', '2015-12-05 12:50:51'),
(23, 'yoga@gmail.com', '6cdd66f7-c25d-4a19-9687-7b04bb31c961', '2015-12-05 12:52:32'),
(24, 'yoga@gmail.com', 'df0d500c-f6a5-44b2-971b-42a18a4fc727', '2015-12-05 12:55:15'),
(25, 'tio', '77f0c746-b9e1-41c8-a62d-a7e7f99d9e6a', '2015-12-05 13:00:18'),
(26, 'tio', '25b7fd3a-ec5f-487b-a55c-2ae1019904a1', '2015-12-05 13:09:15'),
(27, 'tio', '3d53267c-4b8e-4b58-82e0-9f933cb060f4', '2015-12-05 13:11:42'),
(28, 'tio', '5f575fa3-d370-4eaf-a685-4255a7350364', '2015-12-05 13:18:56'),
(29, 'tio', '31bfb677-39bc-477b-af30-0ec8c074c7d8', '2015-12-05 13:24:02'),
(30, 'yoga@gmail.com', '56bfe1b4-227c-4e08-8e94-68808dcb7f8e', '2015-12-05 13:43:59'),
(31, 'yoga@gmail.com', '41bb758c-828b-4f4e-be39-cd7ab1973b6a', '2015-12-05 13:49:52'),
(32, 'yoga@gmail.com', '520e8f24-dbcf-4158-9cdb-c6aabb30e2d6', '2015-12-05 13:54:49'),
(33, 'yoga@gmail.com', 'a371b895-4ba7-4562-8bc8-1ec6ba06492f', '2015-12-05 14:00:13'),
(34, 'yoga@gmail.com', '56c210b7-894c-4ee9-becf-188445f29c72', '2015-12-05 14:05:30'),
(35, 'yoga@gmail.com', '38ee5c1a-7ac0-46ea-8783-73a9ac3a240c', '2015-12-05 14:07:04'),
(36, 'yoga@gmail.com', 'e9224071-6481-4322-a65c-74cae47f3f73', '2015-12-05 14:35:38'),
(37, 'tio', 'a8e9dc9d-145e-4be8-b1e3-b19181174931', '2015-12-05 15:31:54'),
(41, 'tio', '845a7e5e-1a94-488f-9470-f6821ed1b99e', '2015-12-05 15:51:55'),
(42, 'tio', '7d08fd10-5c6d-4662-8b0a-42cf85e41ae6', '2015-12-05 15:55:41'),
(43, 'tio', 'cbd1d4a7-24c1-4338-82a3-1eadbf41496f', '2015-12-05 15:58:01'),
(46, 'cliffsantoso@gmail.com', '7ef676df-d27f-4cd2-91c1-e1d0919c6522', '2015-12-05 20:12:05');

-- --------------------------------------------------------

--
-- Table structure for table `upanswer`
--

CREATE TABLE IF NOT EXISTS `upanswer` (
  `email` varchar(50) DEFAULT NULL,
  `IDAns` int(11) DEFAULT NULL,
  `totalVote` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `upanswer`
--

INSERT INTO `upanswer` (`email`, `IDAns`, `totalVote`) VALUES
('tio', 6, 0),
('yoga@gmail.com', 6, 0),
('yoga@gmail.com', 7, 1),
('yoga@gmail.com', 16, 1),
('tio', 9, 0),
('tio', 14, 1),
('tio', 17, -1);

-- --------------------------------------------------------

--
-- Table structure for table `upquestion`
--

CREATE TABLE IF NOT EXISTS `upquestion` (
  `email` varchar(50) NOT NULL,
  `IDQuestion` int(11) DEFAULT NULL,
  `totalVote` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `upquestion`
--

INSERT INTO `upquestion` (`email`, `IDQuestion`, `totalVote`) VALUES
('tio', 38, 0),
('tio', 37, 1),
('yoga@gmail.com', 38, -1),
('yoga@gmail.com', 43, 1),
('yoga@gmail.com', 37, 1),
('yoga@gmail.com', 39, -1),
('yoga@gmail.com', 44, 1),
('yoga@gmail.com', 42, 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`UserID` int(11) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `Name`, `Email`, `Password`) VALUES
(1, 'Yoga', 'yoga@gmail.com', 'yoga'),
(2, 'Cliff', 'cliffsantoso@gmail.com', 'cliff'),
(3, 'asdf', 'asdfasdf', 'asdfasfd'),
(5, 'tio', 'tio', 'tiotio');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
 ADD PRIMARY KEY (`AnswerID`), ADD UNIQUE KEY `AnswerID` (`AnswerID`), ADD KEY `QuestionID` (`QuestionID`), ADD KEY `AnswerID_2` (`AnswerID`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
 ADD PRIMARY KEY (`CommentID`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
 ADD PRIMARY KEY (`QuestionID`), ADD UNIQUE KEY `QuestionID` (`QuestionID`);

--
-- Indexes for table `sessions`
--
ALTER TABLE `sessions`
 ADD PRIMARY KEY (`SessionID`), ADD UNIQUE KEY `SessionID` (`SessionID`);

--
-- Indexes for table `upanswer`
--
ALTER TABLE `upanswer`
 ADD KEY `IDAns` (`IDAns`), ADD KEY `email` (`email`);

--
-- Indexes for table `upquestion`
--
ALTER TABLE `upquestion`
 ADD KEY `IDQuestion` (`IDQuestion`), ADD KEY `email` (`email`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`UserID`), ADD UNIQUE KEY `UserID` (`UserID`), ADD KEY `UserID_2` (`UserID`), ADD KEY `Email` (`Email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
MODIFY `AnswerID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
MODIFY `CommentID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
MODIFY `QuestionID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=45;
--
-- AUTO_INCREMENT for table `sessions`
--
ALTER TABLE `sessions`
MODIFY `SessionID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=49;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
ADD CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`QuestionID`) REFERENCES `questions` (`QuestionID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `upanswer`
--
ALTER TABLE `upanswer`
ADD CONSTRAINT `upanswer_ibfk_1` FOREIGN KEY (`IDAns`) REFERENCES `answers` (`AnswerID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `upanswer_ibfk_2` FOREIGN KEY (`email`) REFERENCES `users` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `upquestion`
--
ALTER TABLE `upquestion`
ADD CONSTRAINT `upquestion_ibfk_1` FOREIGN KEY (`IDQuestion`) REFERENCES `questions` (`QuestionID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `upquestion_ibfk_2` FOREIGN KEY (`email`) REFERENCES `users` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
