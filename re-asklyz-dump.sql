-- MySQL dump 10.16  Distrib 10.1.9-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: re-asklyz
-- ------------------------------------------------------
-- Server version	10.1.9-MariaDB-log

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
  `access_token` varchar(256) NOT NULL DEFAULT '',
  `expiration_date` mediumtext NOT NULL,
  `user_id` int(11) unsigned DEFAULT NULL,
  `user_agent` varchar(256) NOT NULL,
  `ip` varchar(24) NOT NULL,
  PRIMARY KEY (`access_token`),
  KEY `fk_access_token_user_id_idx` (`user_id`),
  CONSTRAINT `fk_access_token_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access_token`
--

LOCK TABLES `access_token` WRITE;
/*!40000 ALTER TABLE `access_token` DISABLE KEYS */;
INSERT INTO `access_token` VALUES ('agungbsorlawan@gmail.com1449332804','1449347804',49,'test','192.168.1.103');
/*!40000 ALTER TABLE `access_token` ENABLE KEYS */;
UNLOCK TABLES;

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
  `user_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`aid`),
  KEY `qid` (`qid`),
  KEY `fk_answers_user_id_idx` (`user_id`),
  CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`qid`) REFERENCES `questions` (`qid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_answers_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (37,109,'Aufar Gilbran','aufargilbran@gmail.com','Pastrami fatback tongue sirloin salami. Hamburger porchetta biltong frankfurter picanha, turducken swine ham pancetta. Venison leberkas fatback pastrami ball tip, turkey andouille. Drumstick beef swine biltong alcatra rump sausage tri-tip strip steak jerky jowl kevin tenderloin tail. Filet mignon ball tip leberkas, shoulder kielbasa pork belly tri-tip chuck short ribs short loin.\r\n\r\nSwine kevin spare ribs, meatball tongue porchetta shoulder picanha jerky t-bone sirloin drumstick pig. Tri-tip meatball ball tip, pancetta frankfurter cupim hamburger. Ribeye ground round shoulder, pork belly chicken brisket turkey beef t-bone. Shank shoulder bacon salami leberkas. Beef doner biltong ham pork chop cow shank sausage chicken turkey.\r\n\r\n',1,'2015-12-01 01:26:55',NULL),(38,109,'Ginanjar Busisri','ginanjarbusiri@gmail.com','Drumstick filet mignon tri-tip ham hock. Venison andouille cow, frankfurter meatloaf brisket ground round. Fatback rump ham hock biltong porchetta filet mignon jerky turkey pork chop flank ribeye meatloaf. Swine sirloin tail, flank pork belly filet mignon frankfurter venison ball tip.\r\n\r\nCow chuck flank beef swine pig. Salami boudin flank, strip steak hamburger chuck prosciutto pork loin sausage picanha tri-tip beef venison capicola. Kevin pork belly pork loin corned beef salami picanha pork chop, pig shankle shoulder hamburger bresaola brisket capicola. Turkey ham hock andouille shankle meatball, hamburger brisket. Short ribs picanha biltong ground round shankle pork.',-1,'2015-12-01 01:26:49',NULL),(39,110,'Agung Baptiso S','agungbsorlawan@gmail.com','Spare ribs cupim chuck, leberkas bresaola ground round flank. Cupim alcatra corned beef tongue bresaola, cow sirloin drumstick boudin. Prosciutto doner meatloaf hamburger andouille. Meatball turducken meatloaf rump, hamburger boudin ham hock landjaeger tri-tip fatback doner corned beef sirloin picanha. Sirloin picanha venison sausage.\r\n\r\nFilet mignon tongue frankfurter pork loin. Capicola short ribs drumstick pastrami, short loin pork fatback tenderloin chicken shank brisket spare ribs. Capicola pork belly bresaola shank picanha hamburger pastrami. Ham hock meatloaf cow, bacon andouille t-bone short ribs swine pig landjaeger tenderloin.\r\n\r\nSwine tenderloin hamburger shoulder. Ham pastrami t-bone hamburger venison, swine tongue pork loin sausage chicken tail beef. Ribeye salami t-bone ham hock. Chicken venison pork belly andouille swine, capicola chuck flank. Filet mignon biltong salami shankle tail.\r\n\r\nFrankfurter sirloin jowl, tri-tip swine pork chuck spare ribs beef ribs. Drumstick jowl swine turkey filet mignon. Kevin rump pastrami, t-bone tri-tip swine fatback porchetta andouille pork chop meatball jowl. Tri-tip cupim tenderloin salami picanha sausage ball tip shoulder short ribs boudin sirloin drumstick leberkas.',0,'2015-12-01 01:27:16',NULL),(40,113,'userbaru','userbaru@gmail.com','xxx',1,'2015-12-01 04:28:43',NULL);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  `content` text,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
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
  `user_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`qid`),
  KEY `fk_questions_user_id_idx` (`user_id`),
  CONSTRAINT `fk_questions_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (109,'Agung Baptiso S','agungbsorlawan@gmail.com','AAAAAAAA','Corned beef doner hamburger short ribs, tongue tri-tip swine cow boudin. Shoulder tongue beef ribs jowl venison pork chop flank ham rump ball tip spare ribs bacon. Venison pork chop drumstick bacon bresaola leberkas biltong ground round corned beef sirloin flank strip steak alcatra pig. Boudin doner meatball, cow brisket chuck flank beef corned beef. Sausage swine fatback pork belly, pork loin short loin chuck alcatra bresaola ham pork chop pig.\r\n\r\nTurducken ball tip cupim jowl kevin ribeye. Alcatra brisket meatloaf tongue capicola turkey strip steak salami cupim beef chicken jerky. Tenderloin jerky cow ground round prosciutto, meatball ball tip. Turducken flank chuck spare ribs chicken andouille pastrami.',1,2,'2015-12-01 01:25:23',NULL),(110,'Ginanjar Busisri','ginanjarbusiri@gmail.com','BBBBBB','Prosciuttooo jowl filet mignon doner cow. Bacon tenderloin salami kielbasa picanha corned beef shankle andouille. Kielbasa venison strip steak fatback, meatloaf ham capicola. Turkey ball tip tenderloin, flank ground round pork chop drumstick pork belly cupim pork loin. Tri-tip tenderloin fatback, andouille alcatra picanha sausage.\r\n\r\nGround round beef venison landjaeger pancetta drumstick doner sausage bresaola leberkas prosciutto sirloin shoulder alcatra pastrami. Salami landjaeger kevin pork chop. Jowl doner bacon, pastrami tail beef pork filet mignon shankle shank rump frankfurter picanha corned beef. Corned beef picanha pork loin sausage tenderloin landjaeger bresaola strip steak tri-tip boudin beef ribs porchetta shankle sirloin cupim. Kevin flank cupim salami, strip steak swine filet mignon t-bone ribeye beef. Pork loin pastrami beef cow ham flank pork chop ham hock shankle jerky tri-tip. Beef sausage sirloin cow hamburger, shank pork loin.\r\n\r\nPorchetta bresaola frankfurter boudin, jerky hamburger ground round t-bone meatloaf venison drumstick. Pastrami capicola beef, pork chop pancetta porchetta shank. Salami ham hock frankfurter, tail landjaeger pastrami beef leberkas turducken turkey jerky ham. Spare ribs beef ribs brisket corned beef ground round ball tip short ribs landjaeger filet mignon tri-tip jerky swine. T-bone meatball ham hock tri-tip cupim. Sirloin boudin swine capicola fatback pork belly t-bone. Ham hock chicken boudin jerky.',1,1,'2015-12-01 01:27:24',NULL),(112,'Aufar Gilbran','aufargilbran@gmail.com','CCCCCCCC','Fatback pork loin salami, capicola porchetta leberkas beef ribs ham drumstick meatloaf t-bone alcatra picanha tongue. Pig jowl tenderloin ground round beef venison, frankfurter turkey porchetta turducken prosciutto pancetta. Pork belly picanha sirloin brisket corned beef. Filet mignon shank brisket jerky. Ham strip steak chuck, pork loin kielbasa spare ribs tongue.\r\n\r\nFilet mignon ham pork chop turducken. Picanha swine brisket chuck kielbasa beef ribs. Capicola pork loin filet mignon boudin beef chuck pastrami meatloaf. Sirloin leberkas meatloaf, filet mignon porchetta bacon spare ribs kevin tenderloin tri-tip shankle t-bone. Leberkas capicola ground round beef ribs flank tenderloin short loin chicken spare ribs cupim short ribs pork loin bacon ham hock.',1,0,'2015-12-01 01:33:48',NULL),(113,'Agung Baptiso S','agungbsorlawan@gmail.com','X','zzzzxxx',1,1,'2015-12-01 04:35:31',NULL),(115,'aufargilbran','aufargilbran@gmail.com','Start','Start',0,0,'2015-12-05 09:47:09',54),(116,'aufargilbran','aufargilbran@gmail.com','test','test',0,0,'2015-12-05 16:35:40',54);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_vote_answer`
--

DROP TABLE IF EXISTS `user_vote_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_vote_answer` (
  `id` int(11) DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  `value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_vote_answer`
--

LOCK TABLES `user_vote_answer` WRITE;
/*!40000 ALTER TABLE `user_vote_answer` DISABLE KEYS */;
INSERT INTO `user_vote_answer` VALUES (49,38,-1),(49,37,1),(52,40,1);
/*!40000 ALTER TABLE `user_vote_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_vote_question`
--

DROP TABLE IF EXISTS `user_vote_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_vote_question` (
  `id` int(11) DEFAULT NULL,
  `qid` int(11) DEFAULT NULL,
  `value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_vote_question`
--

LOCK TABLES `user_vote_question` WRITE;
/*!40000 ALTER TABLE `user_vote_question` DISABLE KEYS */;
INSERT INTO `user_vote_question` VALUES (50,109,1),(50,109,-1),(51,109,1),(49,110,1),(49,112,1),(52,113,1),(49,116,1),(49,116,-1);
/*!40000 ALTER TABLE `user_vote_question` ENABLE KEYS */;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (49,'Agung Baptiso S','agungbsorlawan@gmail.com','mxUcu14i4Tx6lg73DUefnw=='),(51,'Ginanjar Busisri','ginanjarbusiri@gmail.com','jRwaZY4KM0vXTnO37JzMPQ=='),(52,'userbaru','userbaru@gmail.com','vevufu6RWoAsiUw6yqBLow=='),(54,'aufargilbran','aufargilbran@gmail.com','75EzytgoAXwq0RIybfhQOw=='),(55,'kampret','kampret@gmail.com','dnCpYfJiGCCoYagC9B+88g=='),(56,'test','test@gmail.com','xKotAkNKS7ccGOjOUZrZjQ==');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 're-asklyz'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-05 23:41:10
