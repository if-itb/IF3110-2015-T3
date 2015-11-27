-- MySQL dump 10.13  Distrib 5.6.21, for Win32 (x86)
--
-- Host: localhost    Database: wbd
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
  `IDAns` int(11) NOT NULL AUTO_INCREMENT,
  `IDQ` int(11) NOT NULL,
  `IDUser` int(11) NOT NULL,
  `Answer` text NOT NULL,
  `Vote` int(11) NOT NULL,
  PRIMARY KEY (`IDAns`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (28,54,5,'Real Madrid Cupu',3),(29,54,5,'Visca El Barca!',2),(30,55,1,'APA',1);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `IDQ` int(11) NOT NULL AUTO_INCREMENT,
  `IDUser` int(11) NOT NULL,
  `QuestionTopic` varchar(30) NOT NULL,
  `Content` text NOT NULL,
  `Vote` int(6) NOT NULL,
  PRIMARY KEY (`IDQ`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (54,7,'El Classico','Real Madrid 0-4 Barcelona',2),(55,1,'guuuu','oiiiooo',5),(56,7,'Cliff','Cliff Homo dan Bau',-1),(57,7,'Tri','Tri Chaer CIeee',1),(59,9,'Cewe Cakep IF','Susanti, Catherine Priscilla MUACHHH :*',1),(60,1,'Cowo Ganteng IF','Luminto dong',0),(61,1,'Cowo Jelek IF','Cliff',0);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `access_token` varchar(50) NOT NULL,
  `IDUser` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`access_token`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES ('04f2ed50ac1e42847636bacc4c723c58',6,'2015-11-25 11:31:37'),('1f0d7533eaa9e0ce2d09452824756ab5',5,'2015-11-25 15:13:18'),('c3a217b79fe6e611ba9d4c13fdcb0742',9,'2015-11-25 16:06:03'),('e15f849d84745e80106b74097a501059',1,'2015-11-25 15:33:34'),('f0a1bf404691edaed59e19908f0e89ca',5,'2015-11-25 15:19:15');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `IDUser` int(11) NOT NULL AUTO_INCREMENT,
  `Nama` varchar(30) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(30) NOT NULL,
  PRIMARY KEY (`IDUser`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Cliff Jonathan','cliff@gmail.com','santoso'),(2,'Ginanjar','ginanjar@gmail.com','busiri'),(3,'Try Yulianto','try@if.itb.ac.id','yulianto'),(4,'Aodyra','aodyra@gmail.com','khaidir'),(5,'Bimo Aryo','bimo@gmail.com','Tyasono'),(6,'Albert Tri','liealbert@gmail.com','triadrian'),(7,'Chairuni Aulia','chaer@gmail.com','Nusapati'),(9,'Luminto Luhur','luminto_luhur@hotmail.com','susanti'),(17,'Raymond Kosasih','raymondkosasih@gmail.com','kosasih');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote_answer`
--

DROP TABLE IF EXISTS `vote_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vote_answer` (
  `IDVoteAns` int(11) NOT NULL AUTO_INCREMENT,
  `IDUser` int(11) NOT NULL,
  `IDAns` int(11) NOT NULL,
  `vote_direction` int(11) NOT NULL,
  PRIMARY KEY (`IDVoteAns`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote_answer`
--

LOCK TABLES `vote_answer` WRITE;
/*!40000 ALTER TABLE `vote_answer` DISABLE KEYS */;
INSERT INTO `vote_answer` VALUES (3,1,29,0),(4,1,28,0),(5,1,30,1);
/*!40000 ALTER TABLE `vote_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote_question`
--

DROP TABLE IF EXISTS `vote_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vote_question` (
  `IDVoteQ` int(11) NOT NULL AUTO_INCREMENT,
  `IDUser` int(11) NOT NULL,
  `IDQ` int(11) NOT NULL,
  `vote_direction` int(11) NOT NULL,
  PRIMARY KEY (`IDVoteQ`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote_question`
--

LOCK TABLES `vote_question` WRITE;
/*!40000 ALTER TABLE `vote_question` DISABLE KEYS */;
INSERT INTO `vote_question` VALUES (1,1,55,1),(4,5,55,1),(5,5,57,1),(6,5,58,1),(7,1,56,0),(8,5,54,1),(9,9,59,1);
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

-- Dump completed on 2015-11-25 23:08:18
