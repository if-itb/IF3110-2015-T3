-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2015 at 04:14 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

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
`answer_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '1',
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `create_date` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
`comment_id` int(11) NOT NULL,
  `answer_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` varchar(500) NOT NULL,
  `create_date` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

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
`comment_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` varchar(500) NOT NULL,
  `create_date` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

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
`question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '1',
  `title` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `create_date` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
`user_id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `create_date` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

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
  `random_string` varchar(256) NOT NULL,
  `user_agent` varchar(256) NOT NULL,
  `ip_address` varchar(256) NOT NULL,
  `create_time` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_token`
--

INSERT INTO `user_token` (`user_id`, `random_string`, `user_agent`, `ip_address`, `create_time`) VALUES
(1, '0d07b925-bf9c-4640-9b70-8b532c859c64', 'Test Browser 1.0', '0:0:0:0:0:0:0:1', '12/05/2015 22:12:49');

-- --------------------------------------------------------

--
-- Table structure for table `vote_answer`
--

CREATE TABLE IF NOT EXISTS `vote_answer` (
`vote_Id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `answer_id` int(11) NOT NULL,
  `value` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

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
`vote_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `comment_id` int(11) NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

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
`vote_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `comment_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

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
`vote_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `value` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vote_question`
--

INSERT INTO `vote_question` (`vote_id`, `user_id`, `question_id`, `value`) VALUES
(1, 1, 25, 1),
(2, 2, 25, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
 ADD PRIMARY KEY (`answer_id`), ADD KEY `question_id` (`question_id`), ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `comment_answer`
--
ALTER TABLE `comment_answer`
 ADD PRIMARY KEY (`comment_id`), ADD KEY `answer_id` (`answer_id`,`user_id`), ADD KEY `user_Id` (`user_id`);

--
-- Indexes for table `comment_question`
--
ALTER TABLE `comment_question`
 ADD PRIMARY KEY (`comment_id`), ADD KEY `question_id` (`question_id`,`user_id`), ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
 ADD PRIMARY KEY (`question_id`), ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`user_id`), ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `user_token`
--
ALTER TABLE `user_token`
 ADD PRIMARY KEY (`user_id`,`user_agent`,`ip_address`), ADD UNIQUE KEY `random_string` (`random_string`);

--
-- Indexes for table `vote_answer`
--
ALTER TABLE `vote_answer`
 ADD PRIMARY KEY (`vote_Id`), ADD KEY `user_id` (`user_id`,`answer_id`), ADD KEY `answer_id` (`answer_id`), ADD KEY `user_id_2` (`user_id`);

--
-- Indexes for table `vote_comment_answer`
--
ALTER TABLE `vote_comment_answer`
 ADD PRIMARY KEY (`vote_id`), ADD KEY `user_id` (`user_id`,`comment_id`), ADD KEY `comment_id` (`comment_id`), ADD KEY `user_id_2` (`user_id`);

--
-- Indexes for table `vote_comment_question`
--
ALTER TABLE `vote_comment_question`
 ADD PRIMARY KEY (`vote_id`), ADD KEY `user_id` (`user_id`,`comment_id`,`question_id`,`value`);

--
-- Indexes for table `vote_question`
--
ALTER TABLE `vote_question`
 ADD PRIMARY KEY (`vote_id`), ADD KEY `user_id` (`user_id`), ADD KEY `question_id` (`question_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
MODIFY `answer_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `comment_answer`
--
ALTER TABLE `comment_answer`
MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `comment_question`
--
ALTER TABLE `comment_question`
MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
MODIFY `question_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `vote_answer`
--
ALTER TABLE `vote_answer`
MODIFY `vote_Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `vote_comment_answer`
--
ALTER TABLE `vote_comment_answer`
MODIFY `vote_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `vote_comment_question`
--
ALTER TABLE `vote_comment_question`
MODIFY `vote_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `vote_question`
--
ALTER TABLE `vote_question`
MODIFY `vote_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
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
ADD CONSTRAINT `comment_answer_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE;

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
