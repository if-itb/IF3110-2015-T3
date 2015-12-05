-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3307
-- Generation Time: Nov 27, 2015 at 05:30 PM
-- Server version: 10.1.8-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dazzlesquad`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `content` longtext NOT NULL,
  `vote` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `answer`:
--

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id`, `user_id`, `question_id`, `content`, `vote`, `date`) VALUES
(4, 0, 14, 'hdhnfdhgfmhfmhg', 1, '2015-11-27 18:35:30'),
(5, 0, 14, 'dfkafjdfjdlf', -1, '2015-11-27 22:11:35'),
(6, 0, 15, 'djsfsdfjs', 0, '2015-11-27 22:12:10'),
(7, 0, 12, 'bgvjgvjhvh', 1, '2015-11-27 22:16:17'),
(8, 0, 14, 'hhgsjdsaf', -1, '2015-11-27 22:31:40');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `topic` text NOT NULL,
  `content` longtext NOT NULL,
  `vote` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `question`:
--

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `user_id`, `topic`, `content`, `vote`, `date`) VALUES
(12, 2, 'opopop', 'oopopop[o[pofspdofp[dspWWasjdnasjkdkasndkjasnkjdnaskjdnasjkdnas', 6, '2015-11-26 00:00:00'),
(14, 1, 'aa', 'sadasdasda', 8, '2015-11-27 18:25:17'),
(15, 1, 'hehehehehhe', 'jhbjchbqkjhcbqwhj', 0, '2015-11-27 21:46:43'),
(16, 1, 'fgdgf', 'hhefvhwehv', 0, '2015-11-27 21:47:08'),
(17, 2, 'arfqwrqwrqs', 'DSKNFK;ASDHF;ASJF;LASHLFJASL;KF', -1, '2015-11-27 23:23:47');

-- --------------------------------------------------------

--
-- Table structure for table `tokenlist`
--

CREATE TABLE `tokenlist` (
  `id` int(11) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `exp_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `tokenlist`:
--

--
-- Dumping data for table `tokenlist`
--

INSERT INTO `tokenlist` (`id`, `user_id`, `token`, `exp_date`) VALUES
(1, '2', 'pipin@gmail.comFri Nov 27 19:13:26 ICT 2015', '2015-11-27 19:13:26'),
(2, '1', 'zfachrina@hotmail.comFri Nov 27 19:14:08 ICT 2015', '2015-11-27 19:14:08'),
(3, '2', 'pipin@gmail.comFri Nov 27 19:14:19 ICT 2015', '2015-11-27 19:14:19'),
(4, '2', 'pipin@gmail.comFri Nov 27 19:52:58 ICT 2015', '2015-11-27 19:52:58'),
(5, '2', 'pipin@gmail.comFri Nov 27 19:53:53 ICT 2015', '2015-11-27 19:53:53'),
(6, '1', 'zfachrina@hotmail.comFri Nov 27 20:01:19 ICT 2015', '2015-11-27 20:01:19'),
(7, '2', 'pipin@gmail.comFri Nov 27 20:04:18 ICT 2015', '2015-11-27 20:04:18'),
(8, '3', 'nithoalif@yahoo.comFri Nov 27 20:10:03 ICT 2015', '2015-11-27 20:10:03'),
(9, '2', 'pipin@gmail.comFri Nov 27 20:15:42 ICT 2015', '2015-11-27 20:15:42'),
(10, '2', 'pipin@gmail.comFri Nov 27 20:21:22 ICT 2015', '2015-11-27 20:21:22'),
(11, '2', 'pipin@gmail.comFri Nov 27 20:26:40 ICT 2015', '2015-11-27 20:26:40'),
(12, '2', 'pipin@gmail.comFri Nov 27 21:32:09 ICT 2015', '2015-11-27 21:32:09'),
(13, '1', 'zfachrina@hotmail.comFri Nov 27 21:33:28 ICT 2015', '2015-11-27 21:33:28'),
(14, '2', 'pipin@gmail.comFri Nov 27 21:41:16 ICT 2015', '2015-11-27 21:41:16'),
(15, '2', 'pipin@gmail.comFri Nov 27 21:48:12 ICT 2015', '2015-11-27 21:48:12'),
(16, '1', 'zfachrina@hotmail.comFri Nov 27 21:51:13 ICT 2015', '2015-11-27 21:51:13'),
(17, '2', 'pipin@gmail.comFri Nov 27 22:14:56 ICT 2015', '2015-11-27 22:14:56'),
(18, '2', 'pipin@gmail.comFri Nov 27 22:23:26 ICT 2015', '2015-11-27 22:23:26'),
(19, '2', 'pipin@gmail.comFri Nov 27 22:32:47 ICT 2015', '2015-11-27 22:32:47'),
(20, '2', 'pipin@gmail.comFri Nov 27 22:46:42 ICT 2015', '2015-11-27 22:46:42'),
(21, '2', 'pipin@gmail.comFri Nov 27 23:13:23 ICT 2015', '2015-11-27 23:13:23'),
(22, '2', 'pipin@gmail.comFri Nov 27 23:17:22 ICT 2015', '2015-11-27 23:17:22'),
(23, '2', 'pipin@gmail.comFri Nov 27 23:26:39 ICT 2015', '2015-11-27 23:26:39'),
(24, '2', 'pipin@gmail.comFri Nov 27 23:32:08 ICT 2015', '2015-11-27 23:32:08'),
(25, '2', 'pipin@gmail.comFri Nov 27 23:32:12 ICT 2015', '2015-11-27 23:32:12');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `user`:
--

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`) VALUES
(1, 'zulva', 'zfachrina@hotmail.com', 'zulva'),
(2, 'pipin', 'pipin@gmail.com', 'loveaodyra'),
(3, 'nithoalif', 'nithoalif@yahoo.com', '951badur');

-- --------------------------------------------------------

--
-- Table structure for table `vote_answer`
--

CREATE TABLE `vote_answer` (
  `id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `answer_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `vote_answer`:
--

--
-- Dumping data for table `vote_answer`
--

INSERT INTO `vote_answer` (`id`, `question_id`, `answer_id`, `user_id`) VALUES
(4, 0, 6, 2),
(5, 0, 4, 2),
(6, 0, 5, 2),
(7, 0, 7, 2),
(8, 0, 8, 2);

-- --------------------------------------------------------

--
-- Table structure for table `vote_question`
--

CREATE TABLE `vote_question` (
  `id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `vote_question`:
--

--
-- Dumping data for table `vote_question`
--

INSERT INTO `vote_question` (`id`, `question_id`, `user_id`) VALUES
(1, 14, 0),
(2, 14, 0),
(3, 14, 0),
(4, 14, 0),
(5, 14, 0),
(6, 14, 0),
(8, 12, 2),
(9, 12, 2),
(10, 12, 2),
(11, 14, 2),
(12, 15, 2),
(13, 16, 2),
(14, 15, 1),
(15, 16, 1),
(16, 14, 1),
(17, 17, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tokenlist`
--
ALTER TABLE `tokenlist`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `token` (`token`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vote_answer`
--
ALTER TABLE `vote_answer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vote_question`
--
ALTER TABLE `vote_question`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `tokenlist`
--
ALTER TABLE `tokenlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `vote_answer`
--
ALTER TABLE `vote_answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `vote_question`
--
ALTER TABLE `vote_question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
