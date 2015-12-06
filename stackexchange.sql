-- MySQL dump 10.13  Distrib 5.6.21, for Win32 (x86)
--
-- Host: localhost    Database: stackexchange
-- ------------------------------------------------------
-- Server version	5.6.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_question` int(10) NOT NULL,
  `id_user` int(10) NOT NULL,
  `content` text NOT NULL,
  `timepost` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `answer_ibfk_1` (`id_question`),
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,4,8,'AAAAA','2015-12-06 11:55:04');
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_question` int(10) DEFAULT NULL,
  `id_user` int(10) DEFAULT NULL,
  `content` text,
  `timepost` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id_question` (`id_question`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,4,8,'Tes Comment','2015-12-06 13:43:47'),(2,4,9,'Tes Comment 2','2015-12-06 13:43:56');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_user` int(10) NOT NULL,
  `topic` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `timepost` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `question_ibfk_1` (`id_user`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (4,7,'TEES','TEEESESTSTST','2015-12-06 10:09:49'),(5,8,'Basdoasjsaodkoaskdoaskdosa','ASFSAFAF','2015-12-06 15:39:12');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (7,'Raihan','raihan@gmail.com','1234'),(8,'Wawan','wawan@gmail.com','0098'),(9,'jacq','jacq@gmail.com','jacq'),(10,'jacq','','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_token`
--

DROP TABLE IF EXISTS `user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_token` (
  `id_user` int(11) NOT NULL,
  `token` varchar(255) NOT NULL,
  `time_expire` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `user_token_ibfk_1` (`id_user`),
  CONSTRAINT `user_token_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_token`
--

LOCK TABLES `user_token` WRITE;
/*!40000 ALTER TABLE `user_token` DISABLE KEYS */;
INSERT INTO `user_token` VALUES (7,'java.util.Random@7672c331Raihan','2015-11-30 04:18:12'),(8,'java.util.Random@7269b90Wawan','2015-11-30 04:40:19'),(7,'java.util.Random@35650a24Raihan','2015-11-30 04:49:11'),(7,'java.util.Random@668dfd22Raihan','2015-11-30 05:26:30'),(7,'java.util.Random@38869f4Raihan','2015-11-30 05:28:03'),(8,'java.util.Random@6b188b64Wawan','2015-11-30 05:28:52'),(7,'java.util.Random@64ea79b5Raihan','2015-11-30 05:40:06'),(8,'java.util.Random@1731b52cWawan','2015-11-30 05:40:29'),(8,'java.util.Random@7e106524Wawan','2015-11-30 05:44:45'),(8,'java.util.Random@19980a17Wawan','2015-11-30 05:48:54'),(8,'java.util.Random@596e3106Wawan','2015-11-30 05:51:52'),(7,'java.util.Random@76e4d76eRaihan','2015-11-30 06:00:09'),(7,'java.util.Random@12cf1f10Raihan','2015-11-30 06:07:06'),(7,'java.util.Random@1edc5a4bRaihan','2015-11-30 06:15:50'),(7,'java.util.Random@9e44d8aRaihan','2015-11-30 06:54:30'),(8,'java.util.Random@22d2f7c7Wawan','2015-11-30 06:59:47'),(9,'java.util.Random@74b02558jacq','2015-11-30 07:03:45'),(7,'java.util.Random@4e29e9abRaihan','2015-11-30 07:05:49'),(8,'java.util.Random@3a39956fWawan','2015-11-30 07:08:30'),(7,'899#Mozilla%2F5.0+%28Windows+NT+6.3%3B+WOW64%3B+rv%3A41.0%29+Gecko%2F20100101+Firefox%2F41.0#0:0:0:0:0:0:0:1','2015-12-06 10:14:38'),(7,'217#Mozilla%2F5.0+%28Windows+NT+6.3%3B+WOW64%3B+rv%3A41.0%29+Gecko%2F20100101+Firefox%2F41.0#0:0:0:0:0:0:0:1','2015-12-06 11:51:57'),(8,'810#Mozilla%2F5.0+%28Windows+NT+6.3%3B+WOW64%3B+rv%3A41.0%29+Gecko%2F20100101+Firefox%2F41.0#0:0:0:0:0:0:0:1','2015-12-06 11:52:52'),(8,'405#Mozilla%2F5.0+%28Windows+NT+6.3%3B+WOW64%3B+rv%3A41.0%29+Gecko%2F20100101+Firefox%2F41.0#0:0:0:0:0:0:0:1','2015-12-06 11:59:51'),(8,'507#Mozilla%2F5.0+%28Windows+NT+6.3%3B+WOW64%3B+rv%3A41.0%29+Gecko%2F20100101+Firefox%2F41.0#0:0:0:0:0:0:0:1','2015-12-06 12:03:08'),(8,'766#Mozilla%2F5.0+%28Windows+NT+6.3%3B+WOW64%3B+rv%3A41.0%29+Gecko%2F20100101+Firefox%2F41.0#0:0:0:0:0:0:0:1','2015-12-06 12:04:52'),(9,'595#Mozilla%2F5.0+%28Windows+NT+6.3%3B+WOW64%3B+rv%3A41.0%29+Gecko%2F20100101+Firefox%2F41.0#0:0:0:0:0:0:0:1','2015-12-06 12:06:53'),(8,'913#Mozilla%2F5.0+%28Windows+NT+6.3%3B+WOW64%3B+rv%3A41.0%29+Gecko%2F20100101+Firefox%2F41.0#0:0:0:0:0:0:0:1','2015-12-06 15:44:01'),(8,'292#Mozilla%2F5.0+%28Windows+NT+6.3%3B+WOW64%3B+rv%3A41.0%29+Gecko%2F20100101+Firefox%2F41.0#0:0:0:0:0:0:0:1','2015-12-06 16:12:29'),(8,'320#Mozilla%2F5.0+%28Windows+NT+6.3%3B+WOW64%3B+rv%3A41.0%29+Gecko%2F20100101+Firefox%2F41.0#0:0:0:0:0:0:0:1','2015-12-06 16:15:50');
/*!40000 ALTER TABLE `user_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote_answer`
--

DROP TABLE IF EXISTS `vote_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vote_answer` (
  `id_user` int(10) NOT NULL,
  `id_answer` int(10) NOT NULL,
  `type` int(1) NOT NULL,
  KEY `vote_answer_ibfk_1` (`id_answer`),
  KEY `vote_answer_ibfk_2` (`id_user`),
  CONSTRAINT `vote_answer_ibfk_1` FOREIGN KEY (`id_answer`) REFERENCES `answer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vote_answer_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote_answer`
--

LOCK TABLES `vote_answer` WRITE;
/*!40000 ALTER TABLE `vote_answer` DISABLE KEYS */;
INSERT INTO `vote_answer` VALUES (8,1,-1),(9,1,1);
/*!40000 ALTER TABLE `vote_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote_question`
--

DROP TABLE IF EXISTS `vote_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vote_question` (
  `id_user` int(10) NOT NULL,
  `id_question` int(10) NOT NULL,
  `type` int(1) NOT NULL,
  KEY `vote_question_ibfk_1` (`id_user`),
  KEY `vote_question_ibfk_2` (`id_question`),
  CONSTRAINT `vote_question_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vote_question_ibfk_2` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote_question`
--

LOCK TABLES `vote_question` WRITE;
/*!40000 ALTER TABLE `vote_question` DISABLE KEYS */;
INSERT INTO `vote_question` VALUES (7,4,1),(8,4,1);
/*!40000 ALTER TABLE `vote_question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-06 23:13:31
