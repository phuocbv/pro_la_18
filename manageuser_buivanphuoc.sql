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
INSERT INTO `mst_group` VALUES (1,'Group 1'),(2,'Group 2'),(3,'Group 3'),(4,'Group 4');
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
INSERT INTO `mst_japan` VALUES ('N1','Level 1'),('N2','Level 2'),('N3','Level 3'),('N4','Level 4'),('N5','Level 5');
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_detail_user_japan`
--

LOCK TABLES `tbl_detail_user_japan` WRITE;
/*!40000 ALTER TABLE `tbl_detail_user_japan` DISABLE KEYS */;
INSERT INTO `tbl_detail_user_japan` VALUES (8,23,'N3','2017-11-02','2018-11-20',200),(9,24,'N2','2017-11-05','2018-11-07',250),(10,25,'N2','2017-11-08','2018-11-08',332),(17,32,'N1','2014-05-16','2017-10-29',342),(20,36,'N1','2017-02-14','2018-02-24',160),(21,28,'N3','2017-11-30','2018-11-23',180),(23,40,'N2','2017-11-23','2018-11-23',123),(24,37,'N2','2017-11-23','2018-11-23',123),(25,41,'N2','2017-11-24','2018-11-24',321),(26,42,'N1','2017-11-24','2018-11-24',180),(27,43,'N1','2017-11-28','2018-11-28',234),(28,22,'N3','2017-11-30','2018-11-30',323),(29,45,'N2','2017-11-30','2018-11-30',323);
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
  `full_name` varchar(255) NOT NULL,
  `full_name_kana` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `tel` varchar(15) NOT NULL,
  `birthday` date NOT NULL,
  `salt` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `tbl_user_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `mst_group` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (22,2,'dacuoi','B39708458F9B0A68E9BE9DFC5C200638','da cuoi','','dacuoTESTTi@gmail.com','3213-1323-3123','2017-11-05','5966B4262ED9A42880FE531BAB23C4FD'),(23,1,'foryou','8ECE0F049027BE246248F6E4095F9D18','da cuoi','','foryou@gmail.com','1313-3123-3213','2017-11-03','2AD782B688EEB28F0D40433CB3774DCB'),(24,1,'asdad','FC907C1A6C8EE8BAB7C72E2E02C89C11','nguyen cong minh','','nguyencongminh@gmail.com','242-2344-2344','1992-05-07','CAAAB14714C0C79C50E02E7129C4DA24'),(25,2,'dsfdsgsgf','A02DA4D14E5BAD52FC90A65F9FAEDE8C','sdvs sgfdg njg','','asddda@sdfds.sfdsf','3233-3213-3214','2000-11-08','25C384472885A9310E1A2F07E9797646'),(27,1,'dacuoi12345678','BBA983A23757B3A0D602B4BDDD2B239E','đinh công tráng','','dinhcongtrang@gmail.com','4324-5434-4324','1980-05-01','387408C49CFF62AD9756B012C23038EE'),(28,4,'dacuoi12345678','39DC16D828616775DD06A9ADEB14FAB','Nguyen Thi Thuy','','nguyenthithuy@gmail.com','1234-5433-2233','1980-10-01','D47FC49A76E098970EAA7034E1D5642'),(29,1,'dacuoi12345678','60DF3F12A0C458B51E365109C483B6E8','sdadsa','dasds','dasdsaas','43245','1970-01-01','55742E23CD28094BBB0CFB146C2E140F'),(32,2,'dacuoi12345678','464BC76558C356D4F8E4797F0E7E63CE','Pham Minh Hai','','phamminhhai@gmail.com','4324-5-323','1980-01-01','2BB23EE5442C2A8EE3C20F6E347AD4B0'),(36,1,'nguyenminhanh','6B985DA3C3BC1CF2C5C9BEA4AA0CA419','nguyen minh anh','','nguyenminhanh@gmail.com','1-1-1','2017-11-04','B35C75D2BAF48FF7248EA553A8F0667B'),(37,3,'nguyenthebinh','41509C3EA3B8444B914D67D992FD04A5','nguyen the binh','','nguyenthebinh@gmail.com','1234-123-233','2002-11-23','8A6F5B8FC0BE95C2D2E39218EC878E00'),(38,3,'nguyenthebinh','77EB4581EBBB446381D0C35CCA769EB4','binhnguyen the binhnguyen the binhnguyen the binhnguyen the binhnguyen the binhnguyen the binhnguyen the binhnguyen the binhnguyen the binhnguyen the binhnguyen the binhnguyen the binhnguyen the binhnguyen the binhnguyen the','','nguyenthebao@gmail.com','1234-1-1','1990-11-23','E89D944C00118A5504BD2030BF185937'),(39,1,'adsadad','4484784505983C0159547801F216093FBDF65304','nguew czsd','','asdad@ddasd.asdsd','1-1-1','2017-11-23','DB5FF23F2FDAD5A8DB5C5D390FBF70A7'),(40,2,'tranminhba','5D9694085866B8FC12E1C071E1649271155A648A','tran minh ba','','tranminhba@gmail.com','1-2-2','1999-11-23','CA55DC027D1118981B90DDA45F1D9FB1A64D17E9'),(41,1,'dadsa','CF31CCDE4F1259272C750E7F059449E9BA1BB94A','sadasd adsdsad','','adasds@dgs.dsad','1-1-1','1999-11-24','7E75C07C45B0E28EDDF8A6CA5D7BB23ED733FA5'),(42,2,'asasddad1','4DAE3909FF5F7F0903A2B2E5E6E2BD5CF92C320C','ASASS ADSDS','','nguyenvanaasa@gfgtre.com','1-1-1234','2017-11-24','EE132677B4CE734E9AD6D05B08CC1E570E20B9DE'),(43,1,'dsadsadddadd','F6DF1B3BCCE7FCB9A822DD3AE0E10B6A88995738','adasd dasdasd','','daasddsaadasd@gmail.com','1-1-1','2017-11-28','6F3F9ADDB5F275374AB8FB6567A0CC8581187E5'),(44,1,'ffsfsaffasdf','1C295125DE4E98C5E9A6788CBBD8B2D43686418A','dasda dasdasd\\','','asdasddasd@dadas.com','1-1-1','2017-11-28','3AFF1D9C89C0B5DCDBF63F46F01F864281FB662F'),(45,1,'cszczczcxz','852FE920C75516C0442EA9CEABD659CF1F93822D','zcxzczx sdfds','','sfsdfds@fsdf.ci','12-2-2','2017-11-30','57319FDFAB546E537707A1E593D5DB65512D4CE9');
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

-- Dump completed on 2017-11-30 10:39:51
