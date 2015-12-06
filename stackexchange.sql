-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2015 at 09:08 AM
-- Server version: 5.6.21
-- PHP Version: 5.5.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `stackexchane2`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `username` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`username`, `nama`, `password`, `email`) VALUES
('aka', 'aka aka', 'aka', 'aka@gmail.com'),
('fiqie', 'fiqie', 'fiqie', 'fiqi9e@gmail.com'),
('ica', 'dininta annisa', 'ica', 'ica@gmail.com'),
('oji', 'oji', 'oji', 'oji'),
('rahman', 'rahman adianto', 'rahman', 'rahman@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
`id_answer` int(11) NOT NULL,
  `id_question` int(11) NOT NULL,
  `vote` int(11) NOT NULL,
  `content` text NOT NULL,
  `date` datetime NOT NULL,
  `username` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id_answer`, `id_question`, `vote`, `content`, `date`, `username`) VALUES
(3, 3, 5, 'huu gendut', '2015-11-18 00:00:00', 'aka');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id_question` int(11) NOT NULL,
  `id_comment` int(11) NOT NULL,
  `content` text NOT NULL,
  `date` datetime NOT NULL,
  `username` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
`id_question` int(11) NOT NULL,
  `topic` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `content` text NOT NULL,
  `vote` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id_question`, `topic`, `username`, `content`, `vote`, `date`) VALUES
(3, 'Salon', 'ica', 'lagi pengen dandan cantik', -99, '2015-11-25 00:00:00'),
(5, 'topik baru', 'fiqie', 'mulai lagi saja', 0, '2015-11-18 17:15:48'),
(6, 'topik yang paling baru', 'fiqie', 'content baru di edit', 1, '2015-11-25 11:20:10'),
(7, 'topik yang baru', 'fiqie', 'isi yang baru', 0, '2015-11-25 13:06:17'),
(8, 'sgerhgrgrshgbragbarrg', 'fiqie', 'gaghhdhfdahfgfsgaf', 0, '2015-11-25 13:58:13');

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE IF NOT EXISTS `token` (
  `token_string` text NOT NULL,
  `date_create` datetime NOT NULL,
  `valid_hour` int(11) NOT NULL DEFAULT '6',
  `username` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `token`
--

INSERT INTO `token` (`token_string`, `date_create`, `valid_hour`, `username`) VALUES
('key', '2015-11-24 13:24:00', 6, 'fiqie'),
('111e5d8d-bec1-44c0-9839-55e2cb8b49cd', '2015-11-24 17:38:25', 6, 'rahman'),
('5bf96d70-514e-4e69-8c0e-a71f2a05308a', '2015-11-25 11:03:03', 6, 'fiqie');

-- --------------------------------------------------------

--
-- Table structure for table `vote_answer`
--

CREATE TABLE IF NOT EXISTS `vote_answer` (
  `id_answer` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vote_answer`
--

INSERT INTO `vote_answer` (`id_answer`, `username`, `value`) VALUES
(3, 'fiqie', -1);

-- --------------------------------------------------------

--
-- Table structure for table `vote_question`
--

CREATE TABLE IF NOT EXISTS `vote_question` (
  `id_question` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vote_question`
--

INSERT INTO `vote_question` (`id_question`, `username`, `value`) VALUES
(6, 'rahman', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
 ADD PRIMARY KEY (`id_answer`), ADD KEY `id_question` (`id_question`,`username`), ADD KEY `username` (`username`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
 ADD PRIMARY KEY (`id_comment`), ADD KEY `id_question` (`id_question`,`username`), ADD KEY `username` (`username`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
 ADD PRIMARY KEY (`id_question`), ADD KEY `username` (`username`), ADD KEY `username_2` (`username`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
 ADD KEY `username` (`username`);

--
-- Indexes for table `vote_answer`
--
ALTER TABLE `vote_answer`
 ADD KEY `id_answer` (`id_answer`), ADD KEY `username` (`username`);

--
-- Indexes for table `vote_question`
--
ALTER TABLE `vote_question`
 ADD KEY `id_question` (`id_question`), ADD KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
MODIFY `id_answer` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
MODIFY `id_question` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
ADD CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`username`) REFERENCES `account` (`username`),
ADD CONSTRAINT `answer_ibfk_3` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`) ON DELETE CASCADE;

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `question`
--
ALTER TABLE `question`
ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`username`) REFERENCES `account` (`username`);

--
-- Constraints for table `token`
--
ALTER TABLE `token`
ADD CONSTRAINT `token_ibfk_1` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vote_answer`
--
ALTER TABLE `vote_answer`
ADD CONSTRAINT `fk_id_answer` FOREIGN KEY (`id_answer`) REFERENCES `answer` (`id_answer`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `vote_answer_ibfk_1` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vote_question`
--
ALTER TABLE `vote_question`
ADD CONSTRAINT `vote_question_ibfk_1` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `vote_question_ibfk_2` FOREIGN KEY (`username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
