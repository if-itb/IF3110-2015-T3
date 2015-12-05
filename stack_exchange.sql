-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2015 at 07:41 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `stack_exchange`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
  `answer_id` int(11) NOT NULL AUTO_INCREMENT,
  `answerer_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `answerer_email` varchar(100) NOT NULL,
  `answer_content` text NOT NULL,
  `answer_vote` int(11) NOT NULL DEFAULT '0',
  `question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `question_id_index` (`question_id`),
  KEY `answer_id` (`answer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`answer_id`, `answerer_name`, `answerer_email`, `answer_content`, `answer_vote`, `question_id`, `user_id`) VALUES
(13, 'william', 'william@gmail.com', 'Website ini untuk nanya-nanya tentang berbagai macam hal mas, kalo mau nanya monggo ya, siapa tau yang laen bisa bantuin mas.. hehe', 3, 9, 1),
(14, 'marcel', 'marcel@gmail.com', 'Untuk nanya-nanya doang kok', 2, 9, 2),
(16, 'kevin', 'kevin@gmail.com', 'That would be an alleged affair but no not why he stepped down. ', 0, 11, 3),
(17, 'marcel', 'marcel@gmail.com', 'From his own answer on Quora; \r\n"When I said that my leadership style is based on listening, I meant it. You may have trouble believing this – I truly didn’t seek public office to be someone, but to do something for our country. \r\n\r\nI know people are angry at Congress. I know people are frustrated by Washington’s seeming inability to do the work that you send people there to do. I know people are skeptical of anything an elected official has to say. To be completely candid, I understand why you feel that way, because I feel the same way. \r\n\r\nI truly believe that we are here as public servants; our job is to work for you – period. Your public servants should not be here to gain power or positions; we should be here to do what’s best for our country. \r\n\r\nHouse Republicans need a new leader that can unite them, and after many days of talking with my colleagues, it became clear that I would not be that person. The selection of a new Speaker can never be about a single person, it must be about doing what s right for the country. \r\n\r\nWe need a new start – a fresh face who can bring Congress together. I hope that my choice will help heal the wounds that are keeping well-meaning people from finding common cause for our country. \r\n"', 0, 11, 2),
(19, 'kevin', 'kevin@gmail.com', 'Halo salam kenal', 0, 9, 3),
(20, 'william', 'william@gmail.com', 'Oh, itu pake dynamic programming, jadi jangan pake rekursif soalnya bakalan gak efisien', 1, 12, 1),
(21, 'tes', 'masuk', 'ga?', 0, 11, 2);

-- --------------------------------------------------------

--
-- Table structure for table `answer_vote`
--

CREATE TABLE IF NOT EXISTS `answer_vote` (
  `user_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `answer_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`question_id`,`answer_id`),
  KEY `user_id` (`user_id`,`question_id`,`answer_id`),
  KEY `question_id` (`question_id`),
  KEY `answer_id` (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer_vote`
--

INSERT INTO `answer_vote` (`user_id`, `question_id`, `answer_id`) VALUES
(1, 9, 13),
(1, 9, 14),
(2, 9, 13),
(2, 9, 14),
(3, 9, 13),
(3, 12, 20);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_content` text NOT NULL,
  `user_name` int(11) NOT NULL,
  `answer_id` int(11) NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `asker_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `asker_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_topic` varchar(400) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_content` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_vote` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `question_id` (`question_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`question_id`, `asker_name`, `asker_email`, `question_topic`, `question_content`, `question_vote`, `user_id`) VALUES
(9, 'william', 'william@gmail.com', 'Apa sih ini ?', 'hahahahhahahahahah', 3, 1),
(11, 'marcel', 'marcel@gmail.com', 'Do we now know why McCarthy suddenly dropped out of the vote for House Speaker?', 'Is it because he was was carrying on a long running affair with a Congresswoman? \n\nMultiple sources within Bakersfield, North Carolina, & on Capitol Hill tell Gotnews.com that Majority Leader Kevin McCarthy (R-CA) and Renee Ellmers (R-NC) have been carrying on a long-running affair since 2011. ', 0, 2),
(12, 'kevin', 'kevin@gmail.com', 'Algoritma', 'Gimana cara membuat algoritma fibonaci dengan efisien ?', 0, 3),
(13, 'ken', 'ken@mail.com', 'lelel', 'lelelel', 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `question_vote`
--

CREATE TABLE IF NOT EXISTS `question_vote` (
  `user_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`question_id`),
  KEY `user_id` (`user_id`,`question_id`),
  KEY `question_vote_ibfk_2` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question_vote`
--

INSERT INTO `question_vote` (`user_id`, `question_id`) VALUES
(1, 9),
(2, 9),
(3, 9),
(2, 12),
(2, 13);

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE IF NOT EXISTS `token` (
  `uuid` varchar(100) NOT NULL,
  `user_id` int(11) NOT NULL,
  `expires` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_agent` varchar(100) NOT NULL,
  `ip_address` varchar(100) NOT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) NOT NULL,
  `nama` varchar(200) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email`, `nama`, `password`) VALUES
(1, 'william@gmail.com', 'william', 'william'),
(2, 'marcel@gmail.com', 'marcel', 'acelacel'),
(3, 'kevin@gmail.com', 'kevin', 'kevin'),
(4, 'admin@gmail.com', 'admin', 'admin'),
(5, 'ken@mail.com', 'ken', 'ken');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
