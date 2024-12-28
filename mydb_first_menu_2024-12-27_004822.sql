-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb_first
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `username` varchar(10) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone` varchar(15) NOT NULL COMMENT '手机号',
  `email` varchar(30) DEFAULT 'NULL' COMMENT '邮箱',
  `picture` text COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'yzlmhzz','198251','17720881775','NULL',NULL),(9,'张三','$2a$10$R0Whr3BXRgUM5rG3NgAhfegc.SF3Ef41EybyCoSMZybFRqLEFLvuW','12345678901','NULL',NULL),(10,'yzlmhzz11','$2a$10$ab/ah3/2cbVz.uziMClXWOXeuAngspyNhoNt3U1BhisdPPc8Woy2.','14354326432','NULL',NULL),(11,'yzlmhzz12','$2a$10$kS5QZp/QT9B1pHv6rLgY5.iPMEJtvhBUs.C4M85Ebpd2/rSdC.NPy','14653728192','NULL',NULL),(12,'李四','$2a$10$xSlhEK6mrMV0/tbKJsRLTeRYxAWuVTiFPQRmMcV7v2fDF0pvWqfGq','14892019282','NULL',NULL),(13,'王五','$2a$10$.zaTP1kyi4Jjyi1.sPcQDekhCBNxglpvUw7GMs.S1HVZ4JupBpH1y','15637237291','NULL',NULL),(14,'王五一','$2a$10$4wuVhDEkWwULL7UaWav8ROdMIZaJhdkjHmKM5UqD4qRMl1LNlpsPS','13823728271','NULL',NULL),(15,'zhangsan','$2a$10$HRt7PCrIfTWsycQ3z4b7uOtIQxrXteHHh6Yp/SRvC8dS5gPktJtHy','13432454245','NULL',NULL),(16,'lisi','$2a$10$zi1iYT5RzNoDnNscOgj82uaX0vyTuSZvs32WuDc.ZIEo2csliWXKm','13543234323','NULL',NULL),(17,'刘六','$2a$10$D4Ws4BRPwMmHyUcpNxL8cO5BZLVJ3TEf7ogfkJ1GCM306.J7W6mAe','13634563456','NULL',NULL),(18,'曾昕昕','$2a$10$h2fTqNNk3C.1gD9PKizIneq5IfFejEbzuy5/RD4p164hsZx2Ojvm2','13445654563','NULL',NULL),(19,'吴骋昊','$2a$10$GkYwx.ox3N9GKc6gCP0YmO7XJS4aQBEkJr8/6w0OF8g7AcCCiuYSC','13545643342','NULL',NULL),(20,'czh','$2a$10$f5lwjpypxfCTcTsQUaswPOYDWNAMQfj7qsMYppxU4ukcURCxDjkW6','13634242345','NULL',NULL),(21,'yzlmhzz1','198251','17720882775','NULL',NULL),(22,'yzlmhzz2','198251','17720882775','NULL',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `menu` varchar(30) DEFAULT NULL COMMENT '菜名',
  `belong` varchar(20) DEFAULT NULL COMMENT '餐厅名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='食堂菜品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'荔枝肉','丰庭'),(2,'番茄炒蛋','南光'),(3,'红烧肉','南光');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-27  0:48:32
