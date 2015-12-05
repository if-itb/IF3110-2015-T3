-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2015 at 05:57 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `stack_exchange`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
  `id_answer` int(4) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `vote_num` int(11) NOT NULL DEFAULT '0',
  `datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_user` int(4) NOT NULL,
  `id_question` int(11) NOT NULL,
  PRIMARY KEY (`id_answer`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=44 ;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id_answer`, `content`, `vote_num`, `datetime`, `id_user`, `id_question`) VALUES
(43, 'Barkside''s answer works with FragmentPagerAdapter but doesn''t work with FragmentStatePagerAdapter, because it doesn''t set tags on fragments it passes to FragmentManager.\r\n<br>\r\n<br>With FragmentStatePagerAdapter it seems we can get by, using its instantiateItem(ViewGroup container, int position) call. It returns reference to fragment at position position. If FragmentStatePagerAdapter already holds reference to fragment in question, instantiateItem just returns reference to that fragment, and doesn''t call getItem() to instantiate it again.\r\n<br>\r\n<br>So, suppose, I''m currently looking at fragment #50, and want to access fragment #49. Since they are close, there''s a good chance the #49 will be already instantiated.', -1, '2015-10-28 10:43:30', 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `id_question` int(4) NOT NULL AUTO_INCREMENT,
  `topic` varchar(535) NOT NULL,
  `content` text NOT NULL,
  `vote_num` int(11) NOT NULL DEFAULT '0',
  `datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_user` int(4) NOT NULL,
  PRIMARY KEY (`id_question`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id_question`, `topic`, `content`, `vote_num`, `datetime`, `id_user`) VALUES
(2, 'Update data in ListFragment as part of ViewPager', 'I''m using the v4 compatibility ViewPager in Android. My FragmentActivity has a bunch of data which is to be displayed in different ways on different pages in my ViewPager. So far I just have 3 instances of the same ListFragment, but in the future I will have 3 instances of different ListFragments. The ViewPager is on a vertical phone screen, the lists are not side-by-side.\r<br>\r<br>Now a button on the ListFragment starts an separate full-page activity (via the FragmentActivty), which returns and FragentActivity modifies the data, saves it, then attempts to update all views in its ViewPager. It is here I am stuck.', 2, '2015-10-28 10:41:32', 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(535) NOT NULL,
  `email` varchar(535) NOT NULL,
  `password` varchar(128) NOT NULL,
  `token` varchar(36) DEFAULT NULL,
  `lifetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `name`, `email`, `password`, `token`, `lifetime`) VALUES
(2, 'Irene Wiliudarsan', 'irene@gmail.com', 'rahasia1', '', '2015-11-23 04:37:15'),
(3, 'Jen Teda', 'jenteda@hotmail.com', 'jenteda11', '', '2015-11-23 04:50:15'),
(4, 'Abraham Lincoln', 'abraham.lincoln@live.com', 'lincolnkece', '', '2015-11-21 04:37:15'),
(5, 'Lingga', 'lingga@yahoo.com', 'okelahkalobegitu9002', '', '2015-11-17 08:37:15'),
(6, 'Jen Teda', 'jenteda@gmail.com', 'localhost8080', '', '2015-11-19 04:30:15'),
(7, 'Angela Lynn', 'angela@gmail.com', 'test123', NULL, '2015-11-23 09:13:32'),
(8, 'Devina Ekawati', 'dvina.deph@gmail.com', 'devina', NULL, '2015-11-23 09:53:35'),
(9, 'Harry Potter', 'harry@gmail.com', 'harry', NULL, '2015-11-23 09:56:16'),
(11, 'Ron Weasley', 'ron@gmail.com', 'ron', NULL, '2015-11-23 10:28:51'),
(12, 'William Sentosa', 'william@gmail.com', 'test', NULL, '2015-11-23 10:41:34'),
(13, 'Candy Olivia', 'candy@gmail.com', 'candy', NULL, '2015-11-23 10:55:34'),
(15, 'Hermonie Granger', 'hermonie@gmail.com', 'hermonie', NULL, '2015-11-23 11:02:34'),
(16, 'Test', 'test@gmail.com', 'test', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `vote`
--

CREATE TABLE IF NOT EXISTS `vote` (
  `id_user` int(4) NOT NULL,
  `id_question` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
