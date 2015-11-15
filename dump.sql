-- MySQL dump 10.15  Distrib 10.0.22-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: re-asklyz
-- ------------------------------------------------------
-- Server version	10.0.22-MariaDB-log

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
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answers` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `qid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `content` text,
  `votes` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`aid`),
  KEY `qid` (`qid`),
  CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`qid`) REFERENCES `questions` (`qid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (12,56,'Agung Baptiso Sorlawan','jancuks@gmail.com','Leberkas pork rump meatloaf prosciutto t-bone. Ribeye ground round pork pig, kielbasa pork loin swine shoulder tongue beef sausage shankle turkey. Meatball frankfurter short ribs, turducken leberkas cow shankle pork belly ribeye meatloaf turkey picanha venison drumstick. Kielbasa jerky rump biltong tenderloin boudin tri-tip. Short loin shankle shoulder doner.\r\n\r\nShoulder alcatra jowl, pork loin short ribs rump sirloin. Ham tenderloin doner tongue. Strip steak swine t-bone picanha pork chop chicken. Spare ribs chicken jerky pig tongue ham hock, landjaeger cow meatball salami. Strip steak cupim shoulder salami. Filet mignon spare ribs alcatra sirloin. Pork chop turducken doner pig chicken, pancetta filet mignon ham hock sirloin biltong ham capicola.',0,'2015-11-14 04:10:54'),(13,57,'Shery Werning','portreeveship@cheechako.org','Turkey salami jerky, sausage jowl pig t-bone rump short ribs venison swine. Bresaola pastrami prosciutto short ribs beef tail, drumstick jowl hamburger pork meatloaf cupim strip steak. Sausage ham venison, cupim beef pork chop bresaola beef ribs frankfurter kielbasa andouille fatback strip steak sirloin drumstick. Bacon bresaola rump spare ribs strip steak pork chop kevin porchetta meatloaf meatball brisket corned beef.\r\n\r\nBiltong venison swine, corned beef prosciutto pork belly leberkas boudin kielbasa pastrami brisket pancetta turducken meatloaf picanha. Cow shoulder salami, ground round drumstick biltong tenderloin rump. Filet mignon boudin rump, drumstick kevin bresaola flank fatback pork chop. Strip steak pancetta meatloaf brisket short ribs pork belly.\r\n\r\nCapicola ground round shoulder, bacon chuck ham hock pig shankle short loin jerky. Capicola t-bone frankfurter spare ribs, sirloin chicken porchetta pastrami beef ribs tri-tip. Sausage tail picanha frankfurter short ribs drumstick. Kielbasa drumstick tenderloin rump strip steak beef ribs ball tip t-bone chicken spare ribs short loin tail kevin ham fatback.\r\n\r\nBacon meatloaf leberkas, tail tongue kevin short ribs flank. Hamburger filet mignon doner chuck fatback turducken jowl kielbasa alcatra. Chuck porchetta alcatra, doner ham hock strip steak bacon leberkas tenderloin sirloin fatback meatball. Salami jowl beef ribs shoulder turducken',0,'2015-11-14 04:14:14'),(14,58,'Agung Baptiso Sorlawan','agungbsorlawan@gmail.com','Bacon ipsum dolor amet sausage drumstick jowl beef ribs alcatra pancetta pastrami prosciutto tri-tip kevin pork loin corned beef flank landjaeger. Bresaola doner swine, pancetta pork belly turkey beef porchetta tail tenderloin bacon picanha. Shoulder hamburger tail, jowl sausage salami prosciutto ground round boudin. Meatball pig tail landjaeger ham sausage short loin spare ribs. Ball tip turducken venison prosciutto, shank porchetta frankfurter ham cow. Flank turducken short loin doner sirloin tri-tip cow tenderloin rump ball tip turkey pork chop. Shankle tongue short ribs kielbasa cupim, meatloaf bacon boudin.',-2,'2015-11-14 06:49:41'),(15,59,'Agung Baptiso Sorlawan','agungbsorlawan@gmail.com','var namevalue=encodeURIComponent(document.getElementById(\"name\").value)\r\nvar agevalue=encodeURIComponent(document.getElementById(\"age\").value)\r\nvar parameters=\"name=\"+namevalue+\"&age=\"+agevalue\r\nmypostrequest.open(\"POST\", \"basicform.php\", true)\r\nmypostrequest.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\")\r\nmypostrequest.send(parameters)',0,'2015-11-14 06:50:54');
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `qid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `qtopic` varchar(255) DEFAULT NULL,
  `qcontent` text,
  `votes` int(11) DEFAULT NULL,
  `answer_count` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`qid`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (55,'Agung Baptiso Sorlawan','agungbsorlawan@gmail.com','Spare ribs','Spare ribs short ribs jerky turducken, pork loin sirloin shoulder ham frankfurter pork belly ribeye rump doner venison. Cupim ham hock flank, beef ribs shoulder ham meatloaf. Tri-tip sausage pancetta, ribeye flank landjaeger doner. Hamburger frankfurter meatloaf pork chop, short ribs fatback bresaola tail meatball pig capicola.',0,0,'2015-11-14 04:10:10'),(56,'Jeffrey Gergely','mesophyte@familiarly.co.uk','Bacon','Turducken ham hock bacon frankfurter, meatball capicola cow tenderloin beef tail. Alcatra sausage kielbasa tri-tip, capicola spare ribs rump. Kevin bresaola alcatra ball tip boudin, picanha corned beef turducken shankle chuck ribeye pork chop pancetta porchetta. Brisket sausage andouille, beef ribs rump t-bone meatloaf. Beef venison short loin, picanha shankle meatball bresaola shank turducken ball tip pork belly spare ribs. Fatback meatball salami porchetta flank ham hock tenderloin ground round chicken sirloin tail pork chop picanha kevin. Spare ribs pork loin beef ribs cow bacon jowl kevin pancetta.\r\n\r\nTongue jerky t-bone jowl shoulder chicken, tri-tip pork loin. Pork chop pork belly meatball cow pork loin porchetta landjaeger short ribs. Pork belly spare ribs cupim beef ribs biltong, bacon ham meatloaf tri-tip pork chop boudin venison. Kevin pork andouille, spare ribs picanha pancetta beef ribs alcatra bresaola cupim biltong. Biltong capicola beef ribs shankle ribeye jowl short loin tongue turkey. Cupim biltong drumstick jerky.',0,1,'2015-11-14 04:11:33'),(57,'Rocky Holloway','jabbingly@aisteoir.edu','Chicken Tenderloinc','Chicken tenderloin tri-tip alcatra, bresaola prosciutto shankle. Rump salami venison, capicola flank pork bacon hamburger ham hock fatback alcatra beef spare ribs sirloin filet mignon. Frankfurter t-bone biltong cupim chuck filet mignon kielbasa pork loin prosciutto, meatball flank picanha. Beef ribs beef ham doner, turducken strip steak meatball chuck alcatra sirloin. Short loin cow brisket, pork hamburger swine alcatra jerky picanha beef ribs landjaeger chicken pork chop turkey. Turkey cupim biltong tongue swine alcatra, pork pastrami.\r\n\r\nHam hock porchetta jowl pork loin. Shank pork turkey meatball capicola tri-tip sausage doner. Pork belly pork chop ball tip strip steak cupim ground round meatball prosciutto spare ribs pork t-bone swine fatback bacon. Turkey prosciutto tenderloin cow. Leberkas chuck venison pork belly, pastrami jerky landjaeger meatloaf beef kevin porchetta. Filet mignon leberkas flank turkey tri-tip chicken shank, cupim ?',1,1,'2015-11-14 05:40:20'),(58,'Yajaira Carabajal','peristalith@tritheocracy.com','Beef Corned Cook 2','Beef corned beef kevin alcatra ground round pork chop spare ribs shankle. Swine landjaeger corned beef, bresaola jerky tenderloin chicken cupim hamburger. Beef ribs sirloin picanha beef landjaeger leberkas kielbasa frankfurter swine. Jerky meatloaf tenderloin frankfurter cow cupim porchetta rump landjaeger flank. Biltong porchetta jowl jerky pastrami shank.\r\n\r\nFatback short ribs tenderloin pork belly ground round hamburger. Picanha cupim shoulder pork chop, frankfurter salami porchetta. Bacon short ribs shankle pork belly filet mignon kielbasa. Cupim pork chop short loin hamburger andouille prosciutto meatball short ribs. Alcatra pork flank short ribs drumstick ribeye, shankle prosciutto pancetta pork loin hamburger ground round cow bacon ? ',4,1,'2015-11-14 06:49:42'),(59,'Agung Baptiso Sorlawan','agungbsorlawan@gmail.com','Topic na','Hahaha',3,1,'2015-11-14 06:51:08');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'aufar','aufar@gmail.com','yahu');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-15 16:01:08
