-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 14, 2015 at 08:39 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `question`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
  `Question_ID` int(11) NOT NULL,
  `Answer_ID` int(11) NOT NULL,
  `Content` varchar(100) NOT NULL,
  `Vote` int(11) NOT NULL,
  `Answered_by` varchar(15) NOT NULL,
  `Email` varchar(25) NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`Question_ID`, `Answer_ID`, `Content`, `Vote`, `Answered_by`, `Email`, `datetime`) VALUES
(8, 8, 'Content\r\n            ', 1, 'Name', 'Email@example.com', '2015-10-16 12:15:07'),
(8, 9, 'Content\r\n            ', 0, 'Name', 'Email@example.com', '2015-10-16 12:18:26'),
(10, 10, 'Content\r\n            ', 4, 'Name', 'Email@example.com', '2015-10-16 13:26:37');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `member_id` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `email` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE IF NOT EXISTS `questions` (
  `Question_ID` int(11) NOT NULL,
  `Content` varchar(100) NOT NULL,
  `Asked_by` varchar(15) NOT NULL,
  `Email` varchar(25) NOT NULL,
  `Vote_Point` int(11) NOT NULL DEFAULT '0',
  `Answers` int(11) NOT NULL DEFAULT '0',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `QuestionTopic` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`Question_ID`, `Content`, `Asked_by`, `Email`, `Vote_Point`, `Answers`, `datetime`, `QuestionTopic`) VALUES
(8, 'Content\r\n                ', 'gua edit yang i', 'Email@example.com', 3, 2, '2015-10-16 09:21:03', 'Question Topic'),
(9, 'Content\r\n        ', 'Name', 'Email@example.com', 2, 0, '2015-10-16 13:17:25', 'Question Topic'),
(10, 'Content\r\n        ', 'Name', 'Email@example.com', 2, 1, '2015-10-16 13:19:10', 'Question Topic');

-- --------------------------------------------------------

--
-- Table structure for table `vote`
--

CREATE TABLE IF NOT EXISTS `vote` (
  `id_member` varchar(25) NOT NULL,
  `id_question` varchar(25) NOT NULL,
  `id_answer` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
 ADD PRIMARY KEY (`Answer_ID`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
 ADD PRIMARY KEY (`Question_ID`), ADD UNIQUE KEY `Question_ID` (`Question_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
