-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 02, 2015 at 03:55 AM
-- Server version: 10.1.8-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wbd`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `IDAns` int(11) NOT NULL,
  `IDQ` int(11) NOT NULL,
  `IDUser` int(11) NOT NULL,
  `Answer` text NOT NULL,
  `Vote` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`IDAns`, `IDQ`, `IDUser`, `Answer`, `Vote`) VALUES
(28, 54, 5, 'Real Madrid Cupu', 3),
(29, 54, 5, 'Visca El Barca!', 2),
(30, 55, 1, 'APA', 1);

-- --------------------------------------------------------

--
-- Table structure for table `comment_answer`
--

CREATE TABLE `comment_answer` (
  `IDAns` int(11) NOT NULL,
  `IDUser` int(11) NOT NULL,
  `Comment` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `comment_question`
--

CREATE TABLE `comment_question` (
  `IDQ` int(11) NOT NULL,
  `IDUser` int(11) NOT NULL,
  `Comment` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `IDQ` int(11) NOT NULL,
  `IDUser` int(11) NOT NULL,
  `QuestionTopic` varchar(30) NOT NULL,
  `Content` text NOT NULL,
  `Vote` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`IDQ`, `IDUser`, `QuestionTopic`, `Content`, `Vote`) VALUES
(54, 7, 'El Classico', 'Real Madrid 0-4 Barcelona', 2),
(55, 1, 'guuuu', 'oiiiooo', 5),
(56, 7, 'Cliff', 'Cliff Homo dan Bau', -1),
(57, 7, 'Tri', 'Tri Chaer CIeee', 1),
(59, 9, 'Cewe Cakep IF', 'Susanti, Catherine Priscilla MUACHHH :*', 1),
(60, 1, 'Cowo Ganteng IF', 'Luminto dong', 0),
(61, 1, 'Cowo Jelek IF', 'Cliff', 0),
(65, 7, 'Cinta', 'Apakah kau mencintaiku?', 0),
(66, 7, 'Luminto', 'Luminto suka SNSD', 0);

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE `token` (
  `access_token` varchar(50) NOT NULL,
  `IDUser` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `token`
--

INSERT INTO `token` (`access_token`, `IDUser`, `created_at`) VALUES
('04f2ed50ac1e42847636bacc4c723c58', 6, '2015-11-25 11:31:37'),
('1f0d7533eaa9e0ce2d09452824756ab5', 5, '2015-11-25 15:13:18'),
('ac86a7346d7fd351a105b86aefde2d61', 7, '2015-11-26 11:23:52'),
('c3a217b79fe6e611ba9d4c13fdcb0742', 9, '2015-11-25 16:06:03'),
('e15f849d84745e80106b74097a501059', 1, '2015-11-25 15:33:34'),
('f0a1bf404691edaed59e19908f0e89ca', 5, '2015-11-25 15:19:15');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `IDUser` int(11) NOT NULL,
  `Nama` varchar(30) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`IDUser`, `Nama`, `Email`, `Password`) VALUES
(1, 'Cliff Jonathan', 'cliff@gmail.com', 'santoso'),
(2, 'Ginanjar', 'ginanjar@gmail.com', 'busiri'),
(3, 'Try Yulianto', 'try@if.itb.ac.id', 'yulianto'),
(4, 'Aodyra', 'aodyra@gmail.com', 'khaidir'),
(5, 'Bimo Aryo', 'bimo@gmail.com', 'Tyasono'),
(6, 'Albert Tri', 'liealbert@gmail.com', 'triadrian'),
(7, 'Chairuni Aulia', 'chaer@gmail.com', 'Nusapati'),
(9, 'Luminto Luhur', 'luminto_luhur@hotmail.com', 'susanti'),
(17, 'Raymond Kosasih', 'raymondkosasih@gmail.com', 'kosasih');

-- --------------------------------------------------------

--
-- Table structure for table `vote_answer`
--

CREATE TABLE `vote_answer` (
  `IDVoteAns` int(11) NOT NULL,
  `IDUser` int(11) NOT NULL,
  `IDAns` int(11) NOT NULL,
  `vote_direction` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vote_answer`
--

INSERT INTO `vote_answer` (`IDVoteAns`, `IDUser`, `IDAns`, `vote_direction`) VALUES
(3, 1, 29, 0),
(4, 1, 28, 0),
(5, 1, 30, 1);

-- --------------------------------------------------------

--
-- Table structure for table `vote_question`
--

CREATE TABLE `vote_question` (
  `IDVoteQ` int(11) NOT NULL,
  `IDUser` int(11) NOT NULL,
  `IDQ` int(11) NOT NULL,
  `vote_direction` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vote_question`
--

INSERT INTO `vote_question` (`IDVoteQ`, `IDUser`, `IDQ`, `vote_direction`) VALUES
(1, 1, 55, 1),
(4, 5, 55, 1),
(5, 5, 57, 1),
(6, 5, 58, 1),
(7, 1, 56, 0),
(8, 5, 54, 1),
(9, 9, 59, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`IDAns`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`IDQ`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`access_token`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`IDUser`);

--
-- Indexes for table `vote_answer`
--
ALTER TABLE `vote_answer`
  ADD PRIMARY KEY (`IDVoteAns`);

--
-- Indexes for table `vote_question`
--
ALTER TABLE `vote_question`
  ADD PRIMARY KEY (`IDVoteQ`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `IDAns` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `IDQ` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `IDUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `vote_answer`
--
ALTER TABLE `vote_answer`
  MODIFY `IDVoteAns` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `vote_question`
--
ALTER TABLE `vote_question`
  MODIFY `IDVoteQ` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
