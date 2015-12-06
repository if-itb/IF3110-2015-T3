-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2015 at 12:09 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

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
-- Table structure for table `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
`id` int(11) NOT NULL,
  `id_question` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `content` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `votes` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id`, `id_question`, `id_user`, `content`, `timestamp`, `votes`) VALUES
(1, 1, 3, 'kopo', '2015-11-24 06:54:03', 0),
(2, 7, 3, 'hihih', '2015-11-24 06:55:23', 1),
(3, 7, 3, 'hoho', '2015-11-24 06:55:29', 1);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
`id` int(11) NOT NULL,
  `id_question` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `content` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
`id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `topic` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `votes` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `id_user`, `topic`, `content`, `timestamp`, `votes`) VALUES
(1, 1, 'halo', 'visat azwar pipin', '2015-11-22 10:30:51', 0),
(7, 3, 'pipin K', 'lllllllllllllkkkkkkkkkkk', '2015-11-24 04:55:18', -1);

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE IF NOT EXISTS `token` (
  `user_id` int(11) NOT NULL,
  `access_token` varchar(255) NOT NULL,
  `expire_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `token`
--

INSERT INTO `token` (`user_id`, `access_token`, `expire_date`) VALUES
(3, '2ac4819afb034f978501171193aadf0e', '2015-11-26 10:05:14'),
(4, 'ec75d283ae31449d806b7875dbc49dfa', '2015-11-26 10:17:48');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL,
  `password` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `password`, `name`, `email`) VALUES
(1, 'pipipiiii', 'pipin', 'pipin_k@ymail.com'),
(2, 'e882b72bccfc2ad578c27b0d9b472a14', 'tifani', 'tifa@mobil.com'),
(3, '2d7acadf10224ffdabeab505970a8934', 'puput', 'puput@ymail.com'),
(4, 'fb6ab4c12a9baff730339f00f278bdac', 'Kei', 'kei@lovelyz.ga');

-- --------------------------------------------------------

--
-- Table structure for table `vote_answer`
--

CREATE TABLE IF NOT EXISTS `vote_answer` (
  `id_user` int(11) NOT NULL,
  `id_answer` int(11) NOT NULL,
  `vote_up` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vote_answer`
--

INSERT INTO `vote_answer` (`id_user`, `id_answer`, `vote_up`) VALUES
(3, 2, 1),
(3, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `vote_question`
--

CREATE TABLE IF NOT EXISTS `vote_question` (
  `id_user` int(11) NOT NULL,
  `id_question` int(11) NOT NULL,
  `vote_up` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vote_question`
--

INSERT INTO `vote_question` (`id_user`, `id_question`, `vote_up`) VALUES
(3, 7, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
 ADD PRIMARY KEY (`id`), ADD KEY `ques_id` (`id_question`), ADD KEY `user_id` (`id_user`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
 ADD PRIMARY KEY (`id`), ADD KEY `id_question` (`id_question`), ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
 ADD PRIMARY KEY (`id`), ADD KEY `user_id` (`id_user`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
 ADD UNIQUE KEY `user_id` (`user_id`), ADD UNIQUE KEY `access_token` (`access_token`), ADD KEY `user_id_2` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `vote_answer`
--
ALTER TABLE `vote_answer`
 ADD KEY `id_user` (`id_user`), ADD KEY `id_answer` (`id_answer`);

--
-- Indexes for table `vote_question`
--
ALTER TABLE `vote_question`
 ADD KEY `id_user` (`id_user`), ADD KEY `id_question` (`id_question`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
ADD CONSTRAINT `answer_question_fk` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `answer_user_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
ADD CONSTRAINT `comment_question_fk` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `comment_user_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `question`
--
ALTER TABLE `question`
ADD CONSTRAINT `question_user_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `token`
--
ALTER TABLE `token`
ADD CONSTRAINT `token_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vote_answer`
--
ALTER TABLE `vote_answer`
ADD CONSTRAINT `vote_answer_fk` FOREIGN KEY (`id_answer`) REFERENCES `answer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `vote_answer_user_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vote_question`
--
ALTER TABLE `vote_question`
ADD CONSTRAINT `vote_question_fk` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `vote_question_user_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
