CREATE DATABASE  IF NOT EXISTS `manageuser_buivanphuoc` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `manageuser_buivanphuoc`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: manageuser_buivanphuoc
-- ------------------------------------------------------
-- Server version	5.1.38-community

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
-- Table structure for table `mst_group`
--

DROP TABLE IF EXISTS `mst_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_group`
--

LOCK TABLES `mst_group` WRITE;
/*!40000 ALTER TABLE `mst_group` DISABLE KEYS */;
INSERT INTO `mst_group` VALUES (1,'Phòng phát triển số 1'),(2,'Phòng phát triển số 2'),(3,'Phòng phát triển số 3'),(4,'Phòng phát triển số 4');
/*!40000 ALTER TABLE `mst_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mst_japan`
--

DROP TABLE IF EXISTS `mst_japan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_japan` (
  `code_level` varchar(15) NOT NULL,
  `name_level` varchar(255) NOT NULL,
  PRIMARY KEY (`code_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_japan`
--

LOCK TABLES `mst_japan` WRITE;
/*!40000 ALTER TABLE `mst_japan` DISABLE KEYS */;
INSERT INTO `mst_japan` VALUES ('N1','Trình độ tiếng nhật cấp 1'),('N2','Trình độ tiếng nhật cấp 2'),('N3','Trình độ tiếng nhật cấp 3'),('N4','Trình độ tiếng nhật cấp 4'),('N5','Trình độ tiếng nhật cấp 5');
/*!40000 ALTER TABLE `mst_japan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_detail_user_japan`
--

DROP TABLE IF EXISTS `tbl_detail_user_japan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_detail_user_japan` (
  `detail_user_japan_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `code_level` varchar(15) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `total` int(11) NOT NULL,
  PRIMARY KEY (`detail_user_japan_id`),
  KEY `user_id` (`user_id`),
  KEY `code_level` (`code_level`),
  CONSTRAINT `tbl_detail_user_japan_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`),
  CONSTRAINT `tbl_detail_user_japan_ibfk_2` FOREIGN KEY (`code_level`) REFERENCES `mst_japan` (`code_level`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_detail_user_japan`
--

LOCK TABLES `tbl_detail_user_japan` WRITE;
/*!40000 ALTER TABLE `tbl_detail_user_japan` DISABLE KEYS */;
INSERT INTO `tbl_detail_user_japan` VALUES (1,1,'N5','2005-07-08','2006-07-08',90),(2,4,'N3','2005-05-20','2006-05-20',100),(3,2,'N5','2005-07-08','2006-07-08',90),(4,3,'N4','2005-05-20','2006-05-20',100),(5,5,'N5','2005-07-08','2006-07-08',90),(6,2,'N2','2005-05-20','2006-05-20',100);
/*!40000 ALTER TABLE `tbl_detail_user_japan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `login_name` varchar(15) NOT NULL,
  `password` varchar(50) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `full_name_kana` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `tel` varchar(15) NOT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `tbl_user_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `mst_group` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (1,1,'ntmhuong','123456','SJHFSUIGJGSDBUTFGIU','Nguyễn Thị Mai Hương','Hương Kana','huong@gmail.com','123456789','1983-07-07'),(2,1,'hieudt','123456','SJHFSUIGJGSDBUTFGIU','Đoàn Trọng Hiếu','Hiếu Kana','hieu@gmali.com','987456123','1983-08-08'),(3,2,'longth','123456','SJHFSUIGJGSDBUTFGIU','Trần Hoàng Long','Long Kana','long@gmail.com','654123789','1983-09-08'),(4,2,'dungdv','123456','SJHFSUIGJGSDBUTFGIU','Đỗ Văn Dũng','Dũng Kana','dung@gmail.com','789123456','1983-10-08'),(5,3,'phuongnv','123456','SJHFSUIGJGSDBUTFGIU','Nguyễn Việt Phương','Phương Kana','phuong@gmail.com','456987213','1983-11-08');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-26  9:10:46
