-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2015 at 10:21 AM
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
-- Table structure for table `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
`id` int(10) NOT NULL,
  `id_question` int(10) NOT NULL,
  `id_user` int(10) NOT NULL,
  `content` text NOT NULL,
  `timepost` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id`, `id_question`, `id_user`, `content`, `timepost`) VALUES
(1, 1, 2, 'Halo salam kenal. Nama saya wawan', '2015-11-28 00:20:55'),
(2, 1, 3, 'Halo namaku raihan', '2015-11-28 00:21:16');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
`id` int(10) NOT NULL,
  `id_user` int(10) NOT NULL,
  `topic` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `timepost` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `id_user`, `topic`, `content`, `timepost`) VALUES
(1, 1, 'Hello, World!', 'Selamat datang di Web Kami...', '2015-11-28 00:20:18');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(10) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `email`, `password`) VALUES
(1, 'Admin', 'admin@selamatdaribadai.org', 'admin123'),
(2, 'adarwawan', 'adarwawan@gmail.com', 'adalah123'),
(3, 'raihannur', 'raihan@gmail.com', '280795');

-- --------------------------------------------------------

--
-- Table structure for table `user_token`
--

CREATE TABLE IF NOT EXISTS `user_token` (
  `id_user` int(11) NOT NULL,
  `token` varchar(255) NOT NULL,
  `time_expire` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_token`
--

INSERT INTO `user_token` (`id_user`, `token`, `time_expire`) VALUES
(2, '797#Java/1.8.0_31#127.0.0.1', '2015-12-05 07:30:19'),
(2, '350#Java/1.8.0_31#127.0.0.1', '2015-12-05 07:31:52'),
(2, '718#Java/1.8.0_31#127.0.0.1', '2015-12-05 07:33:16'),
(2, '497#Mozilla/5.0 (Windows NT 10.0; rv:42.0) Gecko/20100101 Firefox/42.0#0:0:0:0:0:0:0:1', '2015-12-05 07:35:59'),
(2, '738#Java/1.8.0_31#127.0.0.1', '2015-12-05 07:40:55'),
(2, '42#Mozilla/5.0 (Windows NT 10.0; rv:42.0) Gecko/20100101 Firefox/42.0#127.0.0.1', '2015-12-05 07:44:25'),
(2, '610#Mozilla%2F5.0+%28Windows+NT+10.0%3B+rv%3A42.0%29+Gecko%2F20100101+Firefox%2F42.0#0:0:0:0:0:0:0:1', '2015-12-05 09:15:23'),
(2, '530#Mozilla%2F5.0+%28Windows+NT+10.0%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F46.0.2490.86+Safari%2F537.36#0:0:0:0:0:0:0:1', '2015-12-05 09:20:14'),
(2, '592#Mozilla%2F5.0+%28Windows+NT+10.0%3B+rv%3A42.0%29+Gecko%2F20100101+Firefox%2F42.0#0:0:0:0:0:0:0:1', '2015-12-05 09:20:48');

-- --------------------------------------------------------

--
-- Table structure for table `vote_answer`
--

CREATE TABLE IF NOT EXISTS `vote_answer` (
  `id_user` int(10) NOT NULL,
  `id_answer` int(10) NOT NULL,
  `type` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vote_question`
--

CREATE TABLE IF NOT EXISTS `vote_question` (
  `id_user` int(10) NOT NULL,
  `id_question` int(10) NOT NULL,
  `type` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
 ADD PRIMARY KEY (`id`), ADD KEY `answer_ibfk_1` (`id_question`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
 ADD PRIMARY KEY (`id`), ADD KEY `question_ibfk_1` (`id_user`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `user_token`
--
ALTER TABLE `user_token`
 ADD KEY `user_token_ibfk_1` (`id_user`);

--
-- Indexes for table `vote_answer`
--
ALTER TABLE `vote_answer`
 ADD KEY `vote_answer_ibfk_1` (`id_answer`), ADD KEY `vote_answer_ibfk_2` (`id_user`);

--
-- Indexes for table `vote_question`
--
ALTER TABLE `vote_question`
 ADD KEY `vote_question_ibfk_1` (`id_user`), ADD KEY `vote_question_ibfk_2` (`id_question`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `question`
--
ALTER TABLE `question`
ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_token`
--
ALTER TABLE `user_token`
ADD CONSTRAINT `user_token_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vote_answer`
--
ALTER TABLE `vote_answer`
ADD CONSTRAINT `vote_answer_ibfk_1` FOREIGN KEY (`id_answer`) REFERENCES `answer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `vote_answer_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vote_question`
--
ALTER TABLE `vote_question`
ADD CONSTRAINT `vote_question_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `vote_question_ibfk_2` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
