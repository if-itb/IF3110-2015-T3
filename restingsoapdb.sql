-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2015 at 01:40 PM
-- Server version: 5.6.26-log
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restingsoapdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
  `A_ID` int(3) NOT NULL,
  `Q_ID` int(3) NOT NULL,
  `Content` varchar(12000) NOT NULL,
  `Vote` int(3) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `User_ID` int(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`A_ID`, `Q_ID`, `Content`, `Vote`, `Time`, `User_ID`) VALUES
(1, 4, 'asdasdasd', 0, '2015-12-06 08:17:44', 1);

-- --------------------------------------------------------

--
-- Table structure for table `answervote`
--

CREATE TABLE IF NOT EXISTS `answervote` (
  `User_ID` int(3) NOT NULL,
  `A_ID` int(3) NOT NULL,
  `VoteType` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `C_ID` int(3) NOT NULL,
  `Q_ID` int(3) NOT NULL,
  `Content` varchar(12000) NOT NULL,
  `User_ID` int(20) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `Q_ID` int(3) NOT NULL,
  `Vote` int(3) NOT NULL,
  `Answer_Count` int(11) NOT NULL DEFAULT '0',
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Title` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Content` varchar(12000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `User_ID` int(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`Q_ID`, `Vote`, `Answer_Count`, `Time`, `Title`, `Content`, `User_ID`) VALUES
(3, 0, 0, '2015-11-26 05:06:11', 'Test 2 Edited', 'Reintegration test 2 edited', 1),
(4, 0, 1, '2015-11-26 06:31:24', 'Baru', 'Dari frontend pasca reintegrasi', 1);

-- --------------------------------------------------------

--
-- Table structure for table `questionvote`
--

CREATE TABLE IF NOT EXISTS `questionvote` (
  `User_ID` int(3) NOT NULL,
  `Q_ID` int(3) NOT NULL,
  `VoteType` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE IF NOT EXISTS `token` (
  `Token_ID` int(100) NOT NULL,
  `value` varchar(2000) NOT NULL,
  `user_Email` varchar(3000) NOT NULL,
  `expiration_date` varchar(3000) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `token`
--

INSERT INTO `token` (`Token_ID`, `value`, `user_Email`, `expiration_date`) VALUES
(7, '94c652cb-f393-48fb-857e-54b083d0a757|Chrome_Windows_Other|0:0:0:0:0:0:0:1', 'benbong@gmail.com', '2015-12-06 15:28:21');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `User_ID` int(20) NOT NULL,
  `Name` varchar(15000) NOT NULL,
  `Email` varchar(2000) NOT NULL,
  `Password` varchar(3000) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`User_ID`, `Name`, `Email`, `Password`) VALUES
(1, 'Benjamin Bong', 'benbong@gmail.com', 'bongtillwedrop'),
(2, 'Hans Christian Gunawan', 'hcguyz@gmail.com', '250895'),
(3, 'Juan Anton', 'juan_anton@gmail.com', 'juananton');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`A_ID`),
  ADD KEY `Q_ID` (`Q_ID`),
  ADD KEY `User_ID` (`User_ID`),
  ADD KEY `User_ID_2` (`User_ID`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`C_ID`),
  ADD KEY `User_ID` (`User_ID`),
  ADD KEY `Q_ID` (`Q_ID`) USING BTREE;

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`Q_ID`),
  ADD UNIQUE KEY `Q_ID` (`Q_ID`),
  ADD KEY `User_ID` (`User_ID`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`Token_ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`User_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `A_ID` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `C_ID` int(3) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `Q_ID` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `token`
--
ALTER TABLE `token`
  MODIFY `Token_ID` int(100) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `User_ID` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`Q_ID`) REFERENCES `question` (`Q_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `Q_ID` FOREIGN KEY (`Q_ID`) REFERENCES `question` (`Q_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `User` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
