-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: classroomslack
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `channelcomment`
--

DROP TABLE IF EXISTS `channelcomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channelcomment` (
  `timestamp` varchar(50) NOT NULL,
  `companyName` varchar(100) NOT NULL,
  `channelName` varchar(100) NOT NULL,
  `emailId` varchar(50) NOT NULL,
  `comment` varchar(2000) NOT NULL,
  PRIMARY KEY (`timestamp`,`companyName`,`emailId`,`channelName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channelcomment`
--

LOCK TABLES `channelcomment` WRITE;
/*!40000 ALTER TABLE `channelcomment` DISABLE KEYS */;
INSERT INTO `channelcomment` VALUES ('2017.07.31.17.31.59','microsoft','general','submiitr07@gmail.com','Hi @here, welcome to general channel'),('2017.07.31.17.41.59','microsoft','general','navin@gmail.com','Hello there'),('2017.07.31.18.01.59','microsoft','general','submiitr07@gmail.com','Good to see you back here'),('2017.07.31.18.10.59','microsoft','general','submiitr07@gmail.com','Lets take things forward from here'),('2017.07.31.20.23.59','microsoft','general','navin@gmail.com','Sure.'),('2017.07.31.20.28.02','microsoft','general','navin@gmail.com','How about your placements. All prepared.\n:) :)'),('2017.07.31.20.30.38','microsoft','java- Graphics driver','navin@gmail.com','Users are complaining that drivers Intellij drivers are not getting installed properly'),('2017.07.31.20.30.54','microsoft','java- Graphics driver','navin@gmail.com','@shubham May you complete this taks by EOD'),('2017.07.31.21.43.36','microsoft','speakout','harpreet@gmail.com','First message at speakout'),('2017.08.01.02.28.36','microsoft','java- Graphics driver','submiitr07@gmail.com','ok, I will look through it');
/*!40000 ALTER TABLE `channelcomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `companyName` varchar(500) NOT NULL,
  `userName` varchar(100) NOT NULL,
  `employeeEmailId` varchar(500) NOT NULL,
  `password` varchar(100) NOT NULL,
  `slackId` varchar(100) NOT NULL,
  PRIMARY KEY (`companyName`,`employeeEmailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES ('Appeti','Joshi','joshi@gmail.com','iitr','Appeti.8Q8MF32.CN762064CH1JMT.2017.08.01.00.04.34'),('facebook','maddy','subm@facebook.cco','iitr','Appeti.8Q8MF32.CN762064CH1JMT.2017.08.01.00.04.34'),('Google','shubham','shubham@gmail.com','iitr','Appeti.8Q8MF32.CN762064CH1JMT.2017.08.01.00.04.34'),('microsoft','Harpreet Singh','harpreet@gmail.com','iitr','microsoft.8Q8MF32.CN762064CH1JMT.2017.07.31.20.41.37'),('microsoft','Navin Gupta','navin@gmail.com','iitr','microsoft.8Q8MF32.CN762064CH1JMT.2017.07.31.17.31.59'),('microsoft','Nitesh prajapati','nitesh@gmail.com','iitr','microsoft.8Q8MF32.CN762064CH1JMT.2017.07.31.18.31.07'),('microsoft','madHEYsia','submiitr07@gmail.com','iitr','Appeti.8Q8MF32.CN762064CH1JMT.2017.08.01.00.04.34');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currentuser`
--

DROP TABLE IF EXISTS `currentuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currentuser` (
  `id` varchar(100) NOT NULL,
  `companyName` varchar(100) NOT NULL,
  `userName` varchar(400) NOT NULL,
  `employeeEmailId` varchar(100) NOT NULL,
  `slackId` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currentuser`
--

LOCK TABLES `currentuser` WRITE;
/*!40000 ALTER TABLE `currentuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `currentuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `directmessages`
--

DROP TABLE IF EXISTS `directmessages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `directmessages` (
  `timestamp` varchar(50) NOT NULL,
  `companyName` varchar(100) NOT NULL,
  `senderEmailId` varchar(100) NOT NULL,
  `receiverEmailId` varchar(100) NOT NULL,
  `comment` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`timestamp`,`companyName`,`senderEmailId`,`receiverEmailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='			';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directmessages`
--

LOCK TABLES `directmessages` WRITE;
/*!40000 ALTER TABLE `directmessages` DISABLE KEYS */;
INSERT INTO `directmessages` VALUES ('2017.07.31.17.31.59','microsoft','submiitr07@gmail.com','navin@gmail.com','Hi'),('2017.07.31.17.32.09','microsoft','navin@gmail.com','submiitr07@gmail.com','Hi there'),('2017.07.31.17.35.59','microsoft','submiitr07@gmail.com','navin@gmail.com','wassaup boy'),('2017.07.31.17.36.09','microsoft','navin@gmail.com','submiitr07@gmail.com','all cool. Testing for DBMS project'),('2017.08.01.01.36.57','microsoft','navin@gmail.com','submiitr07@gmail.com','Yes, I love testing my own app'),('2017.08.01.01.53.13','microsoft','submiitr07@gmail.com','navin@gmail.com','Yeah');
/*!40000 ALTER TABLE `directmessages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `threads`
--

DROP TABLE IF EXISTS `threads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `threads` (
  `companyName` varchar(100) NOT NULL,
  `threadName` varchar(100) NOT NULL,
  `emailId` varchar(100) DEFAULT NULL,
  `threadType` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`companyName`,`threadName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `threads`
--

LOCK TABLES `threads` WRITE;
/*!40000 ALTER TABLE `threads` DISABLE KEYS */;
INSERT INTO `threads` VALUES ('Appeti','Joshi','joshi@gmail.com','direct message'),('facebook','facebook-chatOn',NULL,'channel'),('facebook','maddy','subm@facebook.cco','direct message'),('facebook','marketing-campaigns',NULL,'channel'),('facebook','speakout',NULL,'channel'),('google','random',NULL,'channel'),('Google','shubham','shubham@gmail.com','direct message'),('microsoft','backend',NULL,'channel'),('microsoft','designs',NULL,'channel'),('microsoft','email-list',NULL,'channel'),('microsoft','first channel added',NULL,'channel'),('microsoft','frontend',NULL,'channel'),('microsoft','general',NULL,'channel'),('microsoft','Harpreet Singh','harpreet@gmail.com','direct message'),('microsoft','java- Graphics driver',NULL,'channel'),('microsoft','madHEYsia','submiitr07@gmail.com','direct message'),('microsoft','marketing',NULL,'channel'),('microsoft','marketing-campaigns',NULL,'channel'),('microsoft','Navin Gupta','navin@gmail.com','direct message'),('microsoft','newFestive issues',NULL,'channel'),('microsoft','Nitesh prajapati','nitesh@gmail.com','direct message'),('microsoft','random',NULL,'channel'),('microsoft','speakout',NULL,'channel'),('mozilla','general',NULL,'channel');
/*!40000 ALTER TABLE `threads` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-01  2:57:45
