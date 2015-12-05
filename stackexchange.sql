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
  `num_answer` int(11) NOT NULL AUTO_INCREMENT,
  `id_question` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `content` longtext NOT NULL,
  `answer_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `num_vote` int(11) DEFAULT '0',
  PRIMARY KEY (`num_answer`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,49,1,'JKT48','2015-11-17 22:01:27',0),(2,50,1,' kok bisa?','2015-11-22 21:28:47',1),(3,58,1,' Bukan ivan... Wilhelm','2015-11-23 21:12:45',0);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_vote`
--

DROP TABLE IF EXISTS `answer_vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_vote` (
  `id_answer` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `status` int(1) DEFAULT '0',
  KEY `id_answer` (`id_answer`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `answer_vote_ibfk_1` FOREIGN KEY (`id_answer`) REFERENCES `answer` (`num_answer`),
  CONSTRAINT `answer_vote_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_vote`
--

LOCK TABLES `answer_vote` WRITE;
/*!40000 ALTER TABLE `answer_vote` DISABLE KEYS */;
INSERT INTO `answer_vote` VALUES (2,1,1);
/*!40000 ALTER TABLE `answer_vote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id_question` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `content` longtext NOT NULL,
  `question_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `topic` varchar(100) NOT NULL,
  `num_vote` int(11) NOT NULL DEFAULT '0',
  `num_answer` int(11) DEFAULT '0',
  PRIMARY KEY (`id_question`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (47,1,'Apakah CherryBelle itu unyu? Kalo iya kenapa?','2015-11-12 19:31:24','Keunyuan CherryBelle',1,0),(49,2,'Pilih CherryBelle atau JKT48?','2015-11-12 20:08:05','JKT48 vs CherryBelle',1,0),(50,1,'Gila','2015-11-16 04:42:44','Topik1',-1,1),(52,1,'halo apa kabar?','2015-11-16 12:38:44','kabar',1,0),(53,1,' test test','2015-11-22 11:14:52','Test',0,0),(56,1,'Apakah CherryBelle itu unyu? Kalo iya kenapa?','2015-11-22 20:41:07','',0,0),(57,10,' Apa itu Tusiri?','2015-11-23 11:42:18','Tusiri',0,0),(58,1,' Ivan','2015-11-23 21:12:16','Siapakah dia?',1,1);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_comment`
--

DROP TABLE IF EXISTS `question_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_comment` (
  `id_comment` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_question` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `comment_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_comment`),
  KEY `id_user` (`id_user`),
  KEY `id_question` (`id_question`),
  CONSTRAINT `question_comment_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `question_comment_ibfk_2` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_comment`
--

LOCK TABLES `question_comment` WRITE;
/*!40000 ALTER TABLE `question_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `question_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_vote`
--

DROP TABLE IF EXISTS `question_vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_vote` (
  `id_question` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `status` int(1) DEFAULT '0',
  KEY `id_question` (`id_question`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `question_vote_ibfk_1` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`),
  CONSTRAINT `question_vote_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_vote`
--

LOCK TABLES `question_vote` WRITE;
/*!40000 ALTER TABLE `question_vote` DISABLE KEYS */;
INSERT INTO `question_vote` VALUES (50,1,-1),(47,1,1),(49,1,1),(52,1,1),(58,1,1);
/*!40000 ALTER TABLE `question_vote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `access_token` varchar(50) NOT NULL,
  `id_user` int(11) NOT NULL,
  `timestamp` datetime NOT NULL,
  `IP_Address` varchar(20) DEFAULT NULL,
  `user_agent` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`access_token`),
  UNIQUE KEY `access_token` (`access_token`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `token_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES ('JdgmsAK8oE6UA6o3TDxc6ZnCsjP6TKQD7FNiuf1cRFOYnFad4o',1,'2015-11-24 13:58:11',NULL,NULL),('pOaTojbgCnl19Id7Eyvx1zM6XA0ikLJpfieEbJIdRLpS60wEZq',11,'2015-11-24 11:32:54',NULL,NULL);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `fullname` text NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ivanchibi','81dc9bdb52d04dc20036dbd8313ed055','andrianto.ivan@yahoo.co.id','Ivan Andrianto'),(2,'ivanjkt48','81dc9bdb52d04dc20036dbd8313ed055','','Ivan Andrianto'),(3,'Gila','81dc9bdb52d04dc20036dbd8313ed055','','Gilazzzz'),(4,'Gilaaaa','81dc9bdb52d04dc20036dbd8313ed055','','Gilaaaaaaaaaaa'),(5,'a','81dc9bdb52d04dc20036dbd8313ed055','','b'),(6,'Wilhelmus','81dc9bdb52d04dc20036dbd8313ed055','','Wilhelmus Andrian Tusiri'),(7,'candyolivia','e10adc3949ba59abbe56e057f20f883e','candy_maths@yahoo.com','Candy Olivia Mawalim'),(8,'candylim','96e79218965eb72c92a549dd5a330112','candy@gmail.com','Candy Olivia '),(9,'candycandy','81dc9bdb52d04dc20036dbd8313ed055','candyolivia@s.itb.ac.id','Candy Olivia Mawalim'),(10,'wilhelm','81dc9bdb52d04dc20036dbd8313ed055','wilhelmus@yahoo.co.id','Wilhelmus'),(11,'cancan','81dc9bdb52d04dc20036dbd8313ed055','candycandy@gmail.com','candy lim');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-05 10:50:30
