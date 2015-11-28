-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 27, 2015 at 01:46 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
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
  `AnswerID` int(11) NOT NULL AUTO_INCREMENT,
  `QuestionID` int(11) NOT NULL,
  `Votes` int(11) NOT NULL,
  `Answer` varchar(9999) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Datetime` datetime NOT NULL,
  PRIMARY KEY (`AnswerID`),
  UNIQUE KEY `AnswerID` (`AnswerID`),
  KEY `QuestionID` (`QuestionID`),
  KEY `AnswerID_2` (`AnswerID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

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
(14, 37, 2, 'fsfsfsfsfsf', 'Cliff', 'fasdfasfasf@gkajf.sjfds', '2015-10-10 21:26:04'),
(15, 42, 5, 'YOU''RE FUCKING GAY', 'Cliff', '', '2015-10-15 15:22:29');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE IF NOT EXISTS `questions` (
  `QuestionID` int(11) NOT NULL AUTO_INCREMENT,
  `Votes` int(11) NOT NULL,
  `Answers` int(11) NOT NULL,
  `Topic` varchar(25) NOT NULL,
  `Question` varchar(9999) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Datetime` datetime NOT NULL,
  PRIMARY KEY (`QuestionID`),
  UNIQUE KEY `QuestionID` (`QuestionID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=44 ;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`QuestionID`, `Votes`, `Answers`, `Topic`, `Question`, `Name`, `Email`, `Datetime`) VALUES
(37, 2, 1, 'asdf', 'fsfsfsfsf', 'fsdfsfsf', 'afasfaf@asdf.cg', '2015-10-10 17:19:54'),
(38, 9, 6, 'How to get a gf?', 'im such a loser', 'fdsa', 'cliffsantoso@gmail.com', '2015-10-10 17:08:03'),
(39, 3, 2, 'asdasd', 'asdsdsd', 'adadasdsad', 'sfsfsf@sgsgsgs.sdf', '2015-10-10 19:03:24'),
(42, -2, 1, 'ben???''', 'is ben gay?" i''m not!@#%^*()`~', 'Cliff', 'cliffsantoso@gmail.com', '2015-10-15 15:25:49'),
(43, -1, 0, 'halo', 'haloo', 'Yoga', 'yoga@gmail.com', '2015-11-27 07:32:10');

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

CREATE TABLE IF NOT EXISTS `sessions` (
  `SessionID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(50) NOT NULL,
  `AccessToken` varchar(50) NOT NULL,
  `ExpiredDate` datetime NOT NULL,
  PRIMARY KEY (`SessionID`),
  UNIQUE KEY `SessionID` (`SessionID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `sessions`
--

INSERT INTO `sessions` (`SessionID`, `Email`, `AccessToken`, `ExpiredDate`) VALUES
(2, 'tio', 'dfcc5e81-8c60-4d90-9b6e-7c6fba3eee55', '0000-00-00 00:00:00'),
(3, 'tio', '26aa4171-c3d7-4f7e-bc2d-678cfb60b062', '0000-00-00 00:00:00'),
(4, 'yoga@gmail.com', 'f50c365e-af19-4c64-b7e1-b43a40344ff0', '2015-11-27 07:36:59'),
(5, 'yoga@gmail.com', '1a5dc46e-4f34-4f27-adc6-01480f071ef4', '2015-11-27 07:42:07'),
(6, 'yoga@gmail.com', 'bd8354fa-b75a-450b-9423-e8098b2d7909', '2015-11-27 07:50:18');

-- --------------------------------------------------------

--
-- Table structure for table `upanswer`
--

CREATE TABLE IF NOT EXISTS `upanswer` (
  `email` varchar(50) DEFAULT NULL,
  `IDAns` int(11) DEFAULT NULL,
  `totalVote` int(11) DEFAULT NULL,
  KEY `IDAns` (`IDAns`),
  KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `upanswer`
--

INSERT INTO `upanswer` (`email`, `IDAns`, `totalVote`) VALUES
('tio', 6, 0),
('yoga@gmail.com', 6, 0),
('yoga@gmail.com', 7, 1);

-- --------------------------------------------------------

--
-- Table structure for table `upquestion`
--

CREATE TABLE IF NOT EXISTS `upquestion` (
  `email` varchar(50) NOT NULL,
  `IDQuestion` int(11) DEFAULT NULL,
  `totalVote` int(11) DEFAULT NULL,
  KEY `IDQuestion` (`IDQuestion`),
  KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `upquestion`
--

INSERT INTO `upquestion` (`email`, `IDQuestion`, `totalVote`) VALUES
('tio', 38, 1),
('tio', 37, 0),
('yoga@gmail.com', 38, -1),
('yoga@gmail.com', 43, -1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(20) NOT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `UserID` (`UserID`),
  KEY `UserID_2` (`UserID`),
  KEY `Email` (`Email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `Name`, `Email`, `Password`) VALUES
(1, 'Yoga', 'yoga@gmail.com', 'yoga'),
(2, 'Cliff', 'cliffsantoso@gmail.com', 'cliff'),
(3, 'asdf', 'asdfasdf', 'asdfasfd'),
(5, 'tio', 'tio', 'tiotio');

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
