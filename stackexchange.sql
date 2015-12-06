-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2015 at 09:57 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

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
-- Table structure for table `answers`
--

CREATE TABLE IF NOT EXISTS `answers` (
`id` int(10) NOT NULL,
  `uid` int(10) NOT NULL,
  `qid` int(10) NOT NULL,
  `content` text NOT NULL,
  `timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
`id` int(10) NOT NULL,
  `uid` int(10) NOT NULL,
  `qid` int(10) NOT NULL,
  `content` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE IF NOT EXISTS `questions` (
`id` int(10) NOT NULL,
  `uid` int(10) NOT NULL,
  `topic` varchar(150) NOT NULL,
  `content` text NOT NULL,
  `timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=42 ;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `uid`, `topic`, `content`, `timestamp`) VALUES
(1, 20, 'semper sapien a libero nam dui proin leo odio', 'In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.', '2015-11-23 11:04:45'),
(2, 14, 'turpis sed ante vivamus tortor duis mattis', 'Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.\n\nVestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.', '2015-11-23 11:04:46'),
(3, 20, 'mattis nibh ligula nec sem duis aliquam convallis nunc', 'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.\n\nIn hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.', '2015-11-23 11:04:46'),
(4, 5, 'tellus semper interdum mauris ullamcorper purus sit amet', 'Fusce consequat. Nulla nisl. Nunc nisl.\n\nDuis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.\n\nIn hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.', '2015-11-23 11:04:46'),
(5, 10, 'donec posuere metus vitae ipsum aliquam non mauris morbi', 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.\n\nCurabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', '2015-11-23 11:04:46'),
(6, 2, 'molestie nibh in lectus pellentesque at nulla suspendisse potenti cras', 'Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', '2015-11-23 11:04:46'),
(7, 11, 'venenatis turpis enim blandit mi in porttitor pede', 'Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.\n\nMorbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.\n\nFusce consequat. Nulla nisl. Nunc nisl.', '2015-11-23 11:04:47'),
(8, 3, 'et ultrices posuere cubilia curae duis faucibus accumsan odio', 'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.', '2015-11-23 11:04:47'),
(9, 17, 'quis lectus suspendisse potenti in eleifend quam', 'Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.', '2015-11-23 11:04:47'),
(10, 6, 'sagittis dui vel nisl duis ac', 'Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.', '2015-11-23 11:04:47'),
(11, 4, 'tristique fusce congue diam id ornare imperdiet sapien urna', 'Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.\n\nQuisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.\n\nPhasellus in felis. Donec semper sapien a libero. Nam dui.', '2015-11-23 11:04:47'),
(12, 5, 'a libero nam dui proin leo odio porttitor', 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.', '2015-11-23 11:04:47'),
(13, 13, 'sit amet turpis elementum ligula vehicula consequat', 'Phasellus in felis. Donec semper sapien a libero. Nam dui.\n\nProin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius.', '2015-11-23 11:04:48'),
(14, 12, 'sapien in sapien iaculis congue', 'Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis.', '2015-11-23 11:04:48'),
(15, 13, 'ac nibh fusce lacus purus aliquet', 'Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.', '2015-11-23 11:04:48'),
(16, 12, 'viverra eget congue eget semper rutrum nulla nunc', 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.', '2015-11-23 11:04:48'),
(17, 2, 'erat volutpat in congue etiam justo etiam pretium iaculis', 'Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.\n\nCum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', '2015-11-23 11:04:48'),
(18, 14, 'porttitor lorem id ligula suspendisse ornare consequat lectus', 'Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.', '2015-11-23 11:04:48'),
(19, 20, 'etiam faucibus cursus urna ut tellus nulla ut erat id', 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque.', '2015-11-23 11:04:48'),
(20, 11, 'vel augue vestibulum ante ipsum', 'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.', '2015-11-23 11:04:48'),
(21, 3, 'cras non velit nec nisi vulputate', 'Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.\n\nProin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.', '2015-11-23 11:04:49'),
(22, 20, 'elementum ligula vehicula consequat morbi a', 'Fusce consequat. Nulla nisl. Nunc nisl.', '2015-11-23 11:04:49'),
(23, 15, 'sapien quis libero nullam sit amet turpis', 'Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.', '2015-11-23 11:04:49'),
(24, 9, 'ut odio cras mi pede malesuada in imperdiet et', 'Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio.\n\nCras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '2015-11-23 11:04:49'),
(25, 16, 'neque sapien placerat ante nulla justo aliquam', 'Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.', '2015-11-23 11:04:49'),
(26, 1, 'pede posuere nonummy integer non velit donec diam ', ' Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque.\r\n\r\nDuis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus. ', '2015-11-23 11:04:49'),
(27, 9, 'condimentum neque sapien placerat ante nulla justo aliquam quis turpis', 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque.', '2015-11-23 11:04:49'),
(28, 3, 'praesent blandit nam nulla integer', 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.\n\nMorbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', '2015-11-23 11:04:50'),
(29, 15, 'nulla pede ullamcorper augue a suscipit nulla elit ac nulla', 'Phasellus in felis. Donec semper sapien a libero. Nam dui.', '2015-11-23 11:04:50'),
(30, 20, 'curabitur gravida nisi at nibh in hac habitasse platea', 'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.\n\nDuis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.\n\nMauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.', '2015-11-23 11:04:50'),
(31, 21, 'lalal  ', '  lakaoxsaink  \r\nOMG OMG OMG \r\nyeayy\r\n', '2015-11-27 06:22:40'),
(33, 23, 'lala', 'lalalala ', '2015-11-27 10:13:38'),
(37, 1, 'Marvin Gaye  asdfafds  fdsafsa', '    You got the feeling that i want  Cause Marvin Gaye had it all\r\n dsafdsafdaf afdsafdsafdsa ', '2015-11-27 19:26:05'),
(40, 1, 'HMIF Merdu', 'Siapa anggota HMIF yang suaranya paling merdu ? ', '2015-11-27 22:12:43'),
(41, 1, 'dfafd', ' afdafdsafdsaf fdafda', '2015-11-27 23:02:24');

-- --------------------------------------------------------

--
-- Table structure for table `tokens`
--

CREATE TABLE IF NOT EXISTS `tokens` (
  `uid` int(10) NOT NULL,
  `token_str` varchar(100) NOT NULL,
  `lifetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`uid` int(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`uid`, `name`, `email`, `password`) VALUES
(1, 'Jonathan', 'jonathan@gmail.com', 'donorkasinor'),
(2, 'Virginia', 'vmartinez1@va.gov', 'a'),
(3, 'Jonathan', 'jmorgan2@yahoo.com', 'pede'),
(4, 'Christine', 'cpatterson3@devhub.com', 'suscipit'),
(5, 'Sara', 'smoore4@ed.gov', 'vel'),
(6, 'Andrea', 'agonzalez5@usda.gov', 'morbi'),
(7, 'Jessica', 'jdean6@simplemachines.org', 'ut'),
(8, 'Eric', 'eberry7@nhs.uk', 'nunc'),
(9, 'Dennis', 'dlynch8@adobe.com', 'vel'),
(10, 'Heather', 'hwilliamson9@bloglovin.com', 'id'),
(11, 'Roger', 'rmeyera@wordpress.com', 'lacinia'),
(12, 'Antonio', 'awoodsb@simplemachines.org', 'cum'),
(13, 'Paula', 'prosec@vk.com', 'morbi'),
(14, 'Richard', 'rjacksond@myspace.com', 'quam'),
(15, 'Matthew', 'mrichardsone@mapy.cz', 'morbi'),
(16, 'Beverly', 'bsanchezf@google.co.jp', 'mi'),
(17, 'Theresa', 'tstanleyg@blog.com', 'etiam'),
(18, 'Christina', 'cwhiteh@sfgate.com', 'sapien'),
(19, 'Ronald', 'rthompsoni@howstuffworks.com', 'sit'),
(20, 'Kevin', 'kfisherj@howstuffworks.com', 'gravida'),
(21, 'Pamel', 'set@an.com', 'setancom'),
(22, 'Ahmad', 'ahmadnf@gmail.com', 'ahmadnf'),
(23, 'Bimo', 'bimo@yahoo.com', 'bimo'),
(26, 'gungun', 'apiunggun@gmail.com', 'apiunggun'),
(27, 'Koala', 'koala@yahoo.com', 'koala');

-- --------------------------------------------------------

--
-- Table structure for table `vote_answers`
--

CREATE TABLE IF NOT EXISTS `vote_answers` (
`id` int(10) NOT NULL,
  `uid` int(10) NOT NULL,
  `aid` int(10) NOT NULL,
  `value` int(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Table structure for table `vote_questions`
--

CREATE TABLE IF NOT EXISTS `vote_questions` (
`id` int(10) NOT NULL,
  `uid` int(10) NOT NULL,
  `qid` int(10) NOT NULL,
  `value` int(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
 ADD PRIMARY KEY (`id`), ADD KEY `uid` (`uid`), ADD KEY `qid` (`qid`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
 ADD PRIMARY KEY (`id`), ADD KEY `uid` (`uid`), ADD KEY `qid` (`qid`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
 ADD PRIMARY KEY (`id`), ADD KEY `uid` (`uid`);

--
-- Indexes for table `tokens`
--
ALTER TABLE `tokens`
 ADD UNIQUE KEY `token_str` (`token_str`), ADD KEY `uid` (`uid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`uid`);

--
-- Indexes for table `vote_answers`
--
ALTER TABLE `vote_answers`
 ADD PRIMARY KEY (`id`), ADD KEY `uid` (`uid`), ADD KEY `aid` (`aid`);

--
-- Indexes for table `vote_questions`
--
ALTER TABLE `vote_questions`
 ADD PRIMARY KEY (`id`), ADD KEY `qid` (`qid`), ADD KEY `uid` (`uid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=42;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
MODIFY `uid` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `vote_answers`
--
ALTER TABLE `vote_answers`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `vote_questions`
--
ALTER TABLE `vote_questions`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
ADD CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE,
ADD CONSTRAINT `answers_ibfk_2` FOREIGN KEY (`qid`) REFERENCES `questions` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE,
ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`qid`) REFERENCES `questions` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
ADD CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE;

--
-- Constraints for table `tokens`
--
ALTER TABLE `tokens`
ADD CONSTRAINT `tokens_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE;

--
-- Constraints for table `vote_answers`
--
ALTER TABLE `vote_answers`
ADD CONSTRAINT `vote_answers_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE,
ADD CONSTRAINT `vote_answers_ibfk_2` FOREIGN KEY (`aid`) REFERENCES `answers` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `vote_questions`
--
ALTER TABLE `vote_questions`
ADD CONSTRAINT `vote_questions_ibfk_1` FOREIGN KEY (`qid`) REFERENCES `questions` (`id`) ON DELETE CASCADE,
ADD CONSTRAINT `vote_questions_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
