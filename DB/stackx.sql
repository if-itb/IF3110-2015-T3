-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 05, 2015 at 04:27 PM
-- Server version: 5.5.46-0ubuntu0.14.04.2
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `stackx`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
  `answer_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '1',
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `question_id` (`question_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=17 ;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`answer_id`, `question_id`, `user_id`, `content`, `create_date`) VALUES
(15, 20, 1, 'a', '2015-10-30 18:07:34'),
(16, 25, 1, 'Ini adalah jawaban', '2015-11-17 21:09:29');

-- --------------------------------------------------------

--
-- Table structure for table `comment_answer`
--

CREATE TABLE IF NOT EXISTS `comment_answer` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `answer_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` varchar(500) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `answer_id` (`answer_id`,`user_id`),
  KEY `user_Id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `comment_answer`
--

INSERT INTO `comment_answer` (`comment_id`, `answer_id`, `user_id`, `content`, `create_date`) VALUES
(2, 15, 1, 'Coba content', '2015-12-04 07:42:00'),
(3, 15, 1, 'aoi', '2015-12-04 11:40:43'),
(4, 15, 1, 'INI ELVAN NGAJAK GELOT', '2015-12-05 15:59:27');

-- --------------------------------------------------------

--
-- Table structure for table `comment_question`
--

CREATE TABLE IF NOT EXISTS `comment_question` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` varchar(500) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `question_id` (`question_id`,`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `comment_question`
--

INSERT INTO `comment_question` (`comment_id`, `question_id`, `user_id`, `content`, `create_date`) VALUES
(8, 25, 1, 'woi', '2015-12-04 12:22:43'),
(9, 25, 2, 'asdfwoi', '2015-12-04 12:22:57'),
(10, 25, 2, 'gamau', '2015-12-04 12:23:04'),
(11, 25, 2, 'gamau', '2015-12-05 15:06:48'),
(12, 25, 2, 'gamau', '2015-12-05 15:09:38'),
(13, 25, 2, 'gamau', '2015-12-05 15:09:47'),
(14, 25, 2, 'gamau', '2015-12-05 15:16:44'),
(15, 25, 2, 'gamau', '2015-12-05 15:16:49'),
(17, 25, 1, 'INI ELVAN NGAJAK GELOT', '2015-12-05 16:04:51');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '1',
  `title` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=30 ;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`question_id`, `user_id`, `title`, `content`, `create_date`) VALUES
(20, 1, 'Gelot oi', 'Ini mestinya isinya apa sih?', '2015-11-17 00:00:00'),
(22, 1, 'asd', 'sadadsadsa', '2015-10-30 18:11:32'),
(23, 1, 'Gelut', 'GelutOi', '2015-11-16 17:39:11'),
(24, 1, 'Ini percobaan prepared statement', 'Percobaan prepared statement berhasil', '2015-11-16 18:57:10'),
(25, 1, 'Coba', 'Coba', '2015-11-16 18:57:13'),
(26, 1, 'Ini coba tanya', 'ga bisa gitu dong', '2015-11-18 10:55:08'),
(27, 1, 'Ini coba tanya', 'ga bisa gitu dong', '2015-11-18 11:38:50'),
(28, 2, 'Elvan Ayo Cepat', 'Ayo cepat-cepat gelot di lapangan.', '2015-11-18 11:58:06'),
(29, 1, 'Saya gila', 'gila banget', '2015-11-23 15:46:06');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `name`, `email`, `password`, `create_date`) VALUES
(1, 'Natan Elia', 'natanelia7@gmail.com', 'natanelia', '2015-11-16 00:00:00'),
(2, 'Elvan', 'elvan@gelot.com', 'gelot', '2015-11-18 11:56:15'),
(3, 'natan', 'natan', 'natan', '2015-11-23 17:59:51');

-- --------------------------------------------------------

--
-- Table structure for table `user_token`
--

CREATE TABLE IF NOT EXISTS `user_token` (
  `user_id` int(11) NOT NULL DEFAULT '1',
  `access_token` varchar(256) NOT NULL,
  `create_time` varchar(50) NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `user_id_2` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_token`
--

INSERT INTO `user_token` (`user_id`, `access_token`, `create_time`) VALUES
(1, '5dbccc83-9b6b-40e3-9afd-d8fda1132e85', '12/05/2015 16:19:51'),
(2, '3e4678aa-8e83-4816-abae-c0b6a39bea84', '11/18/2015 11:57:26');

-- --------------------------------------------------------

--
-- Table structure for table `vote_answer`
--

CREATE TABLE IF NOT EXISTS `vote_answer` (
  `vote_Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `answer_id` int(11) NOT NULL,
  `value` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`vote_Id`),
  KEY `user_id` (`user_id`,`answer_id`),
  KEY `answer_id` (`answer_id`),
  KEY `user_id_2` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `vote_answer`
--

INSERT INTO `vote_answer` (`vote_Id`, `user_id`, `answer_id`, `value`) VALUES
(1, 1, 16, 0),
(2, 2, 16, -1),
(3, 1, 15, 1);

-- --------------------------------------------------------

--
-- Table structure for table `vote_comment_answer`
--

CREATE TABLE IF NOT EXISTS `vote_comment_answer` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `comment_id` int(11) NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`vote_id`),
  KEY `user_id` (`user_id`,`comment_id`),
  KEY `comment_id` (`comment_id`),
  KEY `user_id_2` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `vote_comment_answer`
--

INSERT INTO `vote_comment_answer` (`vote_id`, `user_id`, `comment_id`, `value`) VALUES
(1, 1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `vote_comment_question`
--

CREATE TABLE IF NOT EXISTS `vote_comment_question` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `comment_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`vote_id`),
  KEY `user_id` (`user_id`,`comment_id`,`question_id`,`value`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `vote_comment_question`
--

INSERT INTO `vote_comment_question` (`vote_id`, `user_id`, `comment_id`, `question_id`, `value`) VALUES
(2, 1, 2, 0, 1),
(1, 1, 8, 0, -1);

-- --------------------------------------------------------

--
-- Table structure for table `vote_question`
--

CREATE TABLE IF NOT EXISTS `vote_question` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `value` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`vote_id`),
  KEY `user_id` (`user_id`),
  KEY `question_id` (`question_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `vote_question`
--

INSERT INTO `vote_question` (`vote_id`, `user_id`, `question_id`, `value`) VALUES
(1, 1, 25, 1),
(2, 2, 25, 3);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `comment_answer`
--
ALTER TABLE `comment_answer`
  ADD CONSTRAINT `comment_answer_ibfk_1` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`answer_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `comment_answer_ibfk_2` FOREIGN KEY (`user_Id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `comment_question`
--
ALTER TABLE `comment_question`
  ADD CONSTRAINT `comment_question_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `comment_question_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `vote_answer`
--
ALTER TABLE `vote_answer`
  ADD CONSTRAINT `vote_answer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vote_answer_ibfk_2` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`answer_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vote_comment_answer`
--
ALTER TABLE `vote_comment_answer`
  ADD CONSTRAINT `vote_comment_answer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `vote_comment_answer_ibfk_2` FOREIGN KEY (`comment_id`) REFERENCES `comment_answer` (`comment_id`) ON DELETE CASCADE;

--
-- Constraints for table `vote_question`
--
ALTER TABLE `vote_question`
  ADD CONSTRAINT `vote_question_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vote_question_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
