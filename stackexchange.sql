-- MySQL dump 10.13  Distrib 5.6.20, for Win32 (x86)
--
-- Host: localhost    Database: stackexchange
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `access_token`
--

DROP TABLE IF EXISTS `access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `token` varchar(255) NOT NULL,
  `expire_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`),
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `access_token_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_token`
--

LOCK TABLES `access_token` WRITE;
/*!40000 ALTER TABLE `access_token` DISABLE KEYS */;
INSERT INTO `access_token` VALUES (1,2,'admin@se.com209','2015-12-04 17:00:00'),(4,2,'admin@se.com207','2015-11-17 07:03:36'),(5,2,'admin@se.com80','2015-11-17 07:03:37'),(6,2,'admin@se.com513','2015-11-17 07:03:38'),(7,2,'admin@se.com181','2015-11-17 07:03:38'),(8,2,'admin@se.com977','2015-11-17 07:03:38'),(9,2,'admin@se.com612','2015-11-17 07:03:38'),(10,2,'admin@se.com851','2015-11-17 07:03:38'),(11,2,'admin@se.com992','2015-11-17 07:03:38'),(12,2,'admin@se.com32','2015-11-17 07:03:38'),(13,2,'admin@se.com11','2015-11-17 07:04:04'),(14,2,'admin@se.com541','2015-11-17 07:10:54'),(15,2,'admin@se.com30','2015-11-17 07:31:13'),(16,2,'admin@se.com166','2015-11-17 07:43:49'),(17,2,'admin@se.com367','2015-11-17 07:46:02'),(18,3,'fery@dra.ma60','2015-11-18 08:06:05'),(19,3,'fery@dra.ma995','2015-11-18 09:00:23'),(20,3,'fery@dra.ma208','2015-11-18 09:39:38'),(21,3,'fery@dra.ma447','2015-11-18 09:45:50'),(22,3,'fery@dra.ma127','2015-11-18 09:52:35'),(23,3,'fery@dra.ma815','2015-11-23 04:16:02'),(24,3,'fery@dra.ma844','2015-11-23 04:21:28'),(25,3,'fery@dra.ma425','2015-11-25 08:37:58'),(26,6,'demo@itb.ac.id307','2015-11-30 04:26:47'),(27,6,'demo@itb.ac.id766','2015-11-30 04:32:02'),(28,3,'fery@dra.ma9960:0:0:0:0:0:0:1Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36','2015-12-05 12:31:06'),(29,3,'fery@dra.ma8810:0:0:0:0:0:0:1Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36','2015-12-05 12:31:26'),(30,3,'fery@dra.ma1640:0:0:0:0:0:0:1Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36','2015-12-05 12:35:27'),(31,3,'fery@dra.ma1410:0:0:0:0:0:0:1Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36','2015-12-05 12:36:32'),(32,3,'fery@dra.ma3420:0:0:0:0:0:0:1Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36','2015-12-05 12:37:43'),(33,3,'fery@dra.ma7940:0:0:0:0:0:0:1Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36','2015-12-05 12:40:14'),(34,3,'fery@dra.ma9530:0:0:0:0:0:0:1Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36','2015-12-05 13:01:15'),(35,3,'fery@dra.ma472','2015-12-05 13:06:52'),(36,3,'fery@dra.ma466Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36','2015-12-05 13:11:05'),(37,3,'fery%40dra.ma362null','2015-12-05 13:20:11'),(38,3,'fery%40dra.ma424Mozilla%2F5.0+%28Windows+NT+6.1%3B+WOW64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F45.0.2454.101+Safari%2F537.36','2015-12-05 13:20:32'),(39,3,'fery%40dra.ma114nullnull','2015-12-05 13:21:22'),(40,3,'fery%40dra.ma6780%3A0%3A0%3A0%3A0%3A0%3A0%3A1Mozilla%2F5.0+%28Windows+NT+6.1%3B+WOW64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F45.0.2454.101+Safari%2F537.36','2015-12-05 13:21:43'),(41,3,'fery%40dra.ma7290%3A0%3A0%3A0%3A0%3A0%3A0%3A1Mozilla%2F5.0+%28Windows+NT+6.1%3B+WOW64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F45.0.2454.101+Safari%2F537.36','2015-12-05 13:27:42'),(42,3,'fery%40dra.ma5910%3A0%3A0%3A0%3A0%3A0%3A0%3A1mozilla%2F5.0+%28windows+nt+6.1%3B+wow64%29+applewebkit%2F537.36+%28khtml%2C+like+gecko%29+chrome%2F45.0.2454.101+safari%2F537.36','2015-12-05 13:29:24'),(43,3,'fery%40dra.ma570%3A0%3A0%3A0%3A0%3A0%3A0%3A1Mozilla%2F5.0+%28Windows+NT+6.1%3B+WOW64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F45.0.2454.101+Safari%2F537.36','2015-12-05 13:30:42'),(44,3,'fery%40dra.ma9390%3A0%3A0%3A0%3A0%3A0%3A0%3A1Mozilla%2F5.0+%28Windows+NT+6.1%3B+WOW64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F45.0.2454.101+Safari%2F537.36','2015-12-05 13:31:28'),(45,3,'fery%40dra.ma1290%3A0%3A0%3A0%3A0%3A0%3A0%3A1Mozilla%2F5.0+%28Windows+NT+6.1%3B+WOW64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F45.0.2454.101+Safari%2F537.36','2015-12-05 13:32:32'),(46,3,'fery%40dra.ma5340%3A0%3A0%3A0%3A0%3A0%3A0%3A1Mozilla%2F5.0+%28Windows+NT+6.1%3B+WOW64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F45.0.2454.101+Safari%2F537.36','2015-12-05 13:33:34'),(47,3,'fery%40dra.ma7840%3A0%3A0%3A0%3A0%3A0%3A0%3A1Mozilla%2F5.0+%28Windows+NT+6.1%3B+WOW64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F45.0.2454.101+Safari%2F537.36','2015-12-05 13:37:57'),(48,3,'fery%40dra.ma3660%3A0%3A0%3A0%3A0%3A0%3A0%3A1Mozilla%2F5.0+%28Windows+NT+6.1%3B+WOW64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F45.0.2454.101+Safari%2F537.36','2015-12-05 17:31:46'),(49,3,'fery%40dra.ma5830%3A0%3A0%3A0%3A0%3A0%3A0%3A1Mozilla%2F5.0+%28Windows+NT+6.1%3B+WOW64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F45.0.2454.101+Safari%2F537.36','2015-12-05 18:25:18'),(50,3,'fery%40dra.ma3370%3A0%3A0%3A0%3A0%3A0%3A0%3A1Mozilla%2F5.0+%28Windows+NT+6.1%3B+WOW64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F45.0.2454.101+Safari%2F537.36','2015-12-05 18:32:56'),(51,3,'fery%40dra.ma4490%3A0%3A0%3A0%3A0%3A0%3A0%3A1Mozilla%2F5.0+%28Windows+NT+6.1%3B+WOW64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F45.0.2454.101+Safari%2F537.36','2015-12-05 18:38:43'),(52,3,'fery%40dra.ma4720%3A0%3A0%3A0%3A0%3A0%3A0%3A1Mozilla%2F5.0+%28Windows+NT+6.1%3B+WOW64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F45.0.2454.101+Safari%2F537.36','2015-12-05 18:45:00');
/*!40000 ALTER TABLE `access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_question` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `content` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id_question_idx` (`id_question`),
  CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`id_question`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (1,5,3,'cot min','2015-11-18 08:55:36'),(2,6,3,'LElah','2015-11-25 08:33:17'),(3,6,5,'wow\r\n','2015-11-25 08:40:32');
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_question`
--

DROP TABLE IF EXISTS `comment_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_question` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_question` (`id_question`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `comment_question_ibfk_3` FOREIGN KEY (`id_question`) REFERENCES `questions` (`id`),
  CONSTRAINT `comment_question_ibfk_4` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_question`
--

LOCK TABLES `comment_question` WRITE;
/*!40000 ALTER TABLE `comment_question` DISABLE KEYS */;
INSERT INTO `comment_question` VALUES (2,3,6,'asd');
/*!40000 ALTER TABLE `comment_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `topic` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `id_user_idx` (`id_user`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (2,2,'test manual','lelah','2015-11-17 07:22:35'),(3,2,'test','ettt','2015-11-17 07:41:11'),(4,2,'cukupkan','aku sudah lelah','2015-11-17 07:42:48'),(5,2,'LELAH','AYO UDAHAN','2015-11-17 07:44:45'),(6,3,'TIDAK LELAH','SEMANGAT\r\nwfqq\r\ngeqeqggqe\r\nrhrherh\r\ngqqeg','2015-11-18 09:34:56');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'admin@se.com','aaaa','admin'),(3,'fery@dra.ma','aaaa','fery'),(4,'bayu@cuk.com','qwert','bayu'),(5,'byourp@yahoo.com','qwerty','bayu'),(6,'demo@itb.ac.id','demo','demo');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote_answer`
--

DROP TABLE IF EXISTS `vote_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vote_answer` (
  `id_user` int(11) NOT NULL,
  `id_answer` int(11) NOT NULL,
  `value` int(1) NOT NULL,
  PRIMARY KEY (`id_user`,`id_answer`),
  KEY `id_answer` (`id_answer`),
  CONSTRAINT `vote_answer_ibfk_1` FOREIGN KEY (`id_answer`) REFERENCES `answers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vote_answer_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote_answer`
--

LOCK TABLES `vote_answer` WRITE;
/*!40000 ALTER TABLE `vote_answer` DISABLE KEYS */;
INSERT INTO `vote_answer` VALUES (2,1,-1),(3,1,-1),(6,2,-1);
/*!40000 ALTER TABLE `vote_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote_question`
--

DROP TABLE IF EXISTS `vote_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vote_question` (
  `id_user` int(11) NOT NULL,
  `id_question` int(11) NOT NULL,
  `value` int(1) NOT NULL,
  PRIMARY KEY (`id_user`,`id_question`),
  KEY `id_question` (`id_question`),
  CONSTRAINT `vote_question_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vote_question_ibfk_2` FOREIGN KEY (`id_question`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote_question`
--

LOCK TABLES `vote_question` WRITE;
/*!40000 ALTER TABLE `vote_question` DISABLE KEYS */;
INSERT INTO `vote_question` VALUES (2,2,1),(2,5,1),(2,6,-1),(5,6,1),(6,6,1);
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

-- Dump completed on 2015-12-06  1:43:35
