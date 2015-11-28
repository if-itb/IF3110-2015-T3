-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2015 at 08:00 AM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `stackexchange`
--

-- --------------------------------------------------------

--
-- Table structure for table `access_token`
--

CREATE TABLE IF NOT EXISTS `access_token` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `token` varchar(255) NOT NULL,
  `expire_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `access_token`
--

INSERT INTO `access_token` (`id`, `user_id`, `token`, `expire_date`) VALUES
(1, 2, 'admin@se.com209', '2015-11-17 07:01:09'),
(4, 2, 'admin@se.com207', '2015-11-17 07:03:36'),
(5, 2, 'admin@se.com80', '2015-11-17 07:03:37'),
(6, 2, 'admin@se.com513', '2015-11-17 07:03:38'),
(7, 2, 'admin@se.com181', '2015-11-17 07:03:38'),
(8, 2, 'admin@se.com977', '2015-11-17 07:03:38'),
(9, 2, 'admin@se.com612', '2015-11-17 07:03:38'),
(10, 2, 'admin@se.com851', '2015-11-17 07:03:38'),
(11, 2, 'admin@se.com992', '2015-11-17 07:03:38'),
(12, 2, 'admin@se.com32', '2015-11-17 07:03:38'),
(13, 2, 'admin@se.com11', '2015-11-17 07:04:04'),
(14, 2, 'admin@se.com541', '2015-11-17 07:10:54'),
(15, 2, 'admin@se.com30', '2015-11-17 07:31:13'),
(16, 2, 'admin@se.com166', '2015-11-17 07:43:49'),
(17, 2, 'admin@se.com367', '2015-11-17 07:46:02'),
(18, 3, 'fery@dra.ma60', '2015-11-18 08:06:05'),
(19, 3, 'fery@dra.ma995', '2015-11-18 09:00:23'),
(20, 3, 'fery@dra.ma208', '2015-11-18 09:39:38'),
(21, 3, 'fery@dra.ma447', '2015-11-18 09:45:50'),
(22, 3, 'fery@dra.ma127', '2015-11-18 09:52:35'),
(23, 3, 'fery@dra.ma815', '2015-11-23 04:16:02'),
(24, 3, 'fery@dra.ma844', '2015-11-23 04:21:28'),
(25, 3, 'fery@dra.ma425', '2015-11-25 08:37:58');

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE IF NOT EXISTS `answers` (
  `id` int(11) NOT NULL,
  `id_question` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `content` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`id`, `id_question`, `id_user`, `content`, `timestamp`) VALUES
(1, 5, 3, 'cot min', '2015-11-18 08:55:36'),
(2, 6, 3, 'LElah', '2015-11-25 08:33:17'),
(3, 6, 5, 'wow\r\n', '2015-11-25 08:40:32');

-- --------------------------------------------------------

--
-- Table structure for table `comment_question`
--

CREATE TABLE IF NOT EXISTS `comment_question` (
  `id` int(11) NOT NULL,
  `id_question` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE IF NOT EXISTS `questions` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `topic` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `id_user`, `topic`, `content`, `timestamp`) VALUES
(2, 2, 'test manual', 'lelah', '2015-11-17 07:22:35'),
(3, 2, 'test', 'ettt', '2015-11-17 07:41:11'),
(4, 2, 'cukupkan', 'aku sudah lelah', '2015-11-17 07:42:48'),
(5, 2, 'LELAH', 'AYO UDAHAN', '2015-11-17 07:44:45'),
(6, 3, 'TIDAK LELAH', 'SEMANGAT\r\nwfqq\r\ngeqeqggqe\r\nrhrherh\r\ngqqeg', '2015-11-18 09:34:56');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `name`) VALUES
(2, 'admin@se.com', 'aaaa', 'admin'),
(3, 'fery@dra.ma', 'aaaa', 'fery'),
(4, 'bayu@cuk.com', 'qwert', 'bayu'),
(5, 'byourp@yahoo.com', 'qwerty', 'bayu');

-- --------------------------------------------------------

--
-- Table structure for table `vote_answer`
--

CREATE TABLE IF NOT EXISTS `vote_answer` (
  `id_user` int(11) NOT NULL,
  `id_answer` int(11) NOT NULL,
  `value` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vote_answer`
--

INSERT INTO `vote_answer` (`id_user`, `id_answer`, `value`) VALUES
(3, 1, -1);

-- --------------------------------------------------------

--
-- Table structure for table `vote_question`
--

CREATE TABLE IF NOT EXISTS `vote_question` (
  `id_user` int(11) NOT NULL,
  `id_question` int(11) NOT NULL,
  `value` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vote_question`
--

INSERT INTO `vote_question` (`id_user`, `id_question`, `value`) VALUES
(2, 2, 1),
(2, 5, 1),
(5, 6, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `access_token`
--
ALTER TABLE `access_token`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `token` (`token`),
  ADD KEY `user_id` (`user_id`) USING BTREE;

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_question_idx` (`id_question`);

--
-- Indexes for table `comment_question`
--
ALTER TABLE `comment_question`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user_idx` (`id_user`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `vote_answer`
--
ALTER TABLE `vote_answer`
  ADD PRIMARY KEY (`id_user`,`id_answer`),
  ADD KEY `id_answer` (`id_answer`);

--
-- Indexes for table `vote_question`
--
ALTER TABLE `vote_question`
  ADD PRIMARY KEY (`id_user`,`id_question`),
  ADD KEY `id_question` (`id_question`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `access_token`
--
ALTER TABLE `access_token`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `comment_question`
--
ALTER TABLE `comment_question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `access_token`
--
ALTER TABLE `access_token`
  ADD CONSTRAINT `access_token_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`id_question`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `comment_question`
--
ALTER TABLE `comment_question`
  ADD CONSTRAINT `comment_question_ibfk_1` FOREIGN KEY (`id`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `comment_question_ibfk_2` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vote_answer`
--
ALTER TABLE `vote_answer`
  ADD CONSTRAINT `vote_answer_ibfk_1` FOREIGN KEY (`id_answer`) REFERENCES `answers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vote_answer_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vote_question`
--
ALTER TABLE `vote_question`
  ADD CONSTRAINT `vote_question_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vote_question_ibfk_2` FOREIGN KEY (`id_question`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
