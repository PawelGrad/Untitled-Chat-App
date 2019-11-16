CREATE DATABASE  IF NOT EXISTS `chatapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `chatapp`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: chatapp
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES ('123','ROLE_USER'),('admin','ROLE_USER'),('NowyUser','ROLE_USER'),('Pawcik','ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chatrooms`
--

DROP TABLE IF EXISTS `chatrooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chatrooms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roomName` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_quxhveyh641u5x79u9vivgamm` (`roomName`),
  KEY `FK5tvlx4wk0u97n7ayjp8k01y9e` (`user_id`),
  CONSTRAINT `FK5tvlx4wk0u97n7ayjp8k01y9e` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chatrooms`
--

LOCK TABLES `chatrooms` WRITE;
/*!40000 ALTER TABLE `chatrooms` DISABLE KEYS */;
INSERT INTO `chatrooms` VALUES (1,'Public',1),(2,'Nowyroom',239),(3,'aaaasda',227),(4,'TESTOWY',1),(5,'lul',1),(6,'newroom',239),(7,'Room 1',227),(8,'Room 333',1);
/*!40000 ALTER TABLE `chatrooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (4);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitations`
--

DROP TABLE IF EXISTS `invitations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invitations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accepted` bit(1) NOT NULL,
  `invitee_id` bigint(20) NOT NULL,
  `inviter_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK88f0ea4w8fhwtsy1tkc0sl1xp` (`invitee_id`),
  KEY `FKc93ihvftpd11j547qgc9fobmc` (`inviter_id`),
  KEY `FK85dyeulm73h1xhoa2rfwubna` (`room_id`),
  CONSTRAINT `FK85dyeulm73h1xhoa2rfwubna` FOREIGN KEY (`room_id`) REFERENCES `chatrooms` (`id`),
  CONSTRAINT `FK88f0ea4w8fhwtsy1tkc0sl1xp` FOREIGN KEY (`invitee_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKc93ihvftpd11j547qgc9fobmc` FOREIGN KEY (`inviter_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitations`
--

LOCK TABLES `invitations` WRITE;
/*!40000 ALTER TABLE `invitations` DISABLE KEYS */;
INSERT INTO `invitations` VALUES (3,_binary '\0',239,1,1),(4,_binary '\0',239,1,1),(7,_binary '\0',239,1,1),(13,_binary '\0',239,239,6),(14,_binary '\0',239,239,6),(18,_binary '\0',1,227,1),(22,_binary '\0',1,227,1),(23,_binary '\0',1,1,1),(25,_binary '\0',1,227,1);
/*!40000 ALTER TABLE `invitations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `sender` varchar(255) DEFAULT NULL,
  `chatroom_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKin6xtvytfj99rq4s4g2rif13w` (`chatroom_id`),
  CONSTRAINT `FKin6xtvytfj99rq4s4g2rif13w` FOREIGN KEY (`chatroom_id`) REFERENCES `chatrooms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'11123','admin',1),(2,'123213','123',1),(3,'12321321','admin',1),(4,'123213213','123',1),(5,'aaaaa','admin',1),(6,'1231321','123',1),(7,'212313','admin',2),(8,'122312321','123',2),(9,'dasdasda','admin',1),(10,'asdadasdas','admin',1),(11,'asdadasdas','admin',1),(12,'asdasdasd','admin',1),(13,'asdasd','admin',1),(14,'das','admin',1),(15,'dad','admin',1),(16,'ada','admin',1),(17,'da','admin',1),(18,'sda','admin',1),(19,'dsfdsfdsfs','admin',1),(20,'1','admin',1),(21,'h','admin',1),(22,'213','admin',1),(23,'asdas','admin',1),(24,'asda','admin',1),(25,'123121','admin',1),(26,'Pawcik','admin',1),(27,'aaa','admin',1),(28,'Pawcik','admin',1),(29,'123','admin',1),(30,'123','admin',1),(31,'123','admin',1),(32,'123','admin',1),(33,'ghfghgfhgfhf','admin',1),(34,'Pawcik','admin',1),(35,'2','admin',1),(36,'Pawcik','admin',1),(37,'123','admin',1),(38,'sddfsdsdfsdfdsfsdfsdfds','admin',1),(39,'sdfds','admin',1),(40,'21321','admin',1),(41,'asdadasdasd','123',1),(42,'32132132131','admin',1),(43,'asda','admin',1),(44,'Pawcik','admin',1),(45,'asdasdas','Pawcik',1),(46,'dfdsfds','Pawcik',1),(47,'aad','Pawcik',1),(48,'asd','Pawcik',1),(49,'ad','Pawcik',1),(50,'asdadasd','Pawcik',1),(51,'admin','Pawcik',1),(52,'asdasdasdadas','admin',1),(53,'asdasdasdas','Pawcik',1),(54,'123213213','Pawcik',2),(55,'132321321','admin',2),(56,'Pawcik','admin',4),(57,'21321321','Pawcik',4),(58,'asdasdas','admin',4),(59,'Pawcik','admin',5),(60,'asdasdasd','admin',5),(61,'asdasdas','Pawcik',5),(62,'Pawcik','123',6),(63,'123','123',6),(64,'123','123',6),(65,'Pawcik','123',6),(66,'admin','Pawcik',6),(67,'Pawcik','admin',1),(68,'admin','Pawcik',1),(69,'Pawcik','admin',1),(70,'sdasda','Pawcik',1),(71,'admin','Pawcik',1),(72,'admin','Pawcik',1),(73,'admin','Pawcik',1),(74,'Pawcik','admin',1),(75,'Pawcik','admin',2),(76,'Pawcik','admin',2),(77,'Pawcik','admin',2),(78,'admin','Pawcik',1),(79,'Pawcik','admin',1),(80,'Pawcik','admin',1),(81,'Pawcik','admin',1),(82,'admin','Pawcik',1),(83,'Pawcik','admin',6),(84,'Pawcik','admin',6),(85,'Pawcik','admin',6),(86,'Pawcik','admin',6),(87,'Pawcik','Pawcik',1),(88,'admin','Pawcik',1),(89,'admin','Pawcik',2),(90,'admin','Pawcik',4),(91,'admin','Pawcik',5),(92,'admin','Pawcik',5),(93,'admin','Pawcik',6),(94,'admin','Pawcik',6),(95,'admin','Pawcik',6),(96,'admin','Pawcik',1),(97,'admin','Pawcik',6),(98,'admin','Pawcik',4),(99,'admin','Pawcik',4),(100,'admin','Pawcik',6),(101,'admin','Pawcik',6),(102,'admin','Pawcik',2),(103,'admin','Pawcik',1),(104,'Pawcik','Pawcik',3),(105,'Pawcik','Pawcik',5),(106,'Pawcik','admin',1),(107,'Pawcik','Pawcik',1),(108,'Pawcik','Pawcik',1),(109,'Pawcik','Pawcik',2),(110,'Pawcik','Pawcik',1),(111,'Pawcik','Pawcik',4),(112,'Pawcik','Pawcik',6),(113,'123','Pawcik',1),(114,'asdasdas','Pawcik',1),(115,'adadasdasda','Pawcik',1),(116,'123213213','Pawcik',1),(117,'213213','Pawcik',1),(118,'12321321','Pawcik',1),(119,'Pawcik','Pawcik',1),(120,'fdsfds','Pawcik',1),(121,'dsfsdf','Pawcik',1),(122,'sdfsf','Pawcik',1),(123,'sdfsfdsfdsf','Pawcik',1),(124,'gcdfgdfgdg','Pawcik',1),(125,'Pawcik','Pawcik',1),(126,'asdasdasd','Pawcik',1),(127,'Pawcik','Pawcik',1),(128,'admin','Pawcik',1),(129,'1213213213','Pawcik',1),(130,'Pawcik','Pawcik',1),(131,'asdasdas','Pawcik',1),(132,'12321321321','Pawcik',1),(133,'asdasdasd','Pawcik',8),(134,'adasdasdsad','admin',8);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_chatroom`
--

DROP TABLE IF EXISTS `user_chatroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_chatroom` (
  `user_id` bigint(20) NOT NULL,
  `chatroom_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`chatroom_id`),
  KEY `FKju42endp1t6upg9yap37guvpr` (`chatroom_id`),
  CONSTRAINT `FKju42endp1t6upg9yap37guvpr` FOREIGN KEY (`chatroom_id`) REFERENCES `chatrooms` (`id`),
  CONSTRAINT `FKtq7yihcc6gvfua2w3pj46b19o` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_chatroom`
--

LOCK TABLES `user_chatroom` WRITE;
/*!40000 ALTER TABLE `user_chatroom` DISABLE KEYS */;
INSERT INTO `user_chatroom` VALUES (1,1),(227,1),(240,1),(239,2),(239,6),(227,7),(1,8);
/*!40000 ALTER TABLE `user_chatroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `users_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'gradpawel91@gmail.com',_binary '','$2a$10$9I/w14e.aMWkQPRSYjWchuZGgvVKNOwuaDFPO8t5aRh8Y/E6BxVxm','admin'),(227,'pawcik1991@gmail.com',_binary '','$2a$10$DvS5g45jlc24thlkthYgjuJpjZnVzKBx1gYxSQ3gIlK4ppSLf8c8G','Pawcik'),(239,'123@gmail.com',_binary '','$2a$10$bsbQINIYcmk4re.zlvYIM.IUVYq/v4BnZyviswqPTFZhwDjrZNiMS','123'),(240,'nowy@gmail.com',_binary '','$2a$10$uZp8eVlq1VrutVUtGGlWW.QimEa1Gaw7Uf2D8gaB0jh3X7ik5GxYe','NowyUser');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `USERROLE` AFTER INSERT ON `users` FOR EACH ROW begin

    insert into authorities(username,authority) values (new.username,'ROLE_USER');

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `JoinPublic` AFTER INSERT ON `users` FOR EACH ROW insert into user_chatroom(user_id,chatroom_id) values (new.id,1) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-16  6:50:42
