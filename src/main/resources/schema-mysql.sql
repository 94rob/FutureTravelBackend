-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: future_travel
-- ------------------------------------------------------
-- Server version	8.0.29

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

-- Database
CREATE DATABASE IF NOT EXISTS future_travel;
USE future_travel;

CREATE USER 'futuretravel'@'localhost' IDENTIFIED BY 'futuretravel';
GRANT ALL ON future_travel.* TO 'futuretravel'@'localhost';

--
-- Table structure for table `contesto`
--

DROP TABLE IF EXISTS `contesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contesto` (
  `ID_CONTESTO` int NOT NULL AUTO_INCREMENT,
  `PROVINCIA` varchar(45) NOT NULL,
  `TIPO_ALLOGGIO` varchar(45) NOT NULL,
  `RESIDENZA_CLIENTI` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_CONTESTO`),
  KEY `RESIDENZA_CLIENTI` (`RESIDENZA_CLIENTI`),
  KEY `TIPO_ALLOGGIO` (`TIPO_ALLOGGIO`),
  KEY `contesto_ibfk_1` (`PROVINCIA`),
  CONSTRAINT `contesto_ibfk_1` FOREIGN KEY (`PROVINCIA`) REFERENCES `provincia` (`ID_PROVINCIA`),
  CONSTRAINT `contesto_ibfk_2` FOREIGN KEY (`RESIDENZA_CLIENTI`) REFERENCES `residenza_clienti` (`ID_RESIDENZA`),
  CONSTRAINT `contesto_ibfk_3` FOREIGN KEY (`TIPO_ALLOGGIO`) REFERENCES `tipo_alloggio` (`ID_ALLOGGIO`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contesto`
--

LOCK TABLES `contesto` WRITE;
/*!40000 ALTER TABLE `contesto` DISABLE KEYS */;
INSERT INTO `contesto` VALUES (21,'ITF31','HOTELLIKE','IT'),(22,'ITF31','HOTELLIKE','WRL_X_ITA'),(23,'ITF31','OTHER','IT'),(24,'ITF31','OTHER','WRL_X_ITA'),(25,'ITF32','HOTELLIKE','IT'),(26,'ITF32','HOTELLIKE','WRL_X_ITA'),(27,'ITF32','OTHER','IT'),(28,'ITF32','OTHER','WRL_X_ITA'),(29,'ITF33','HOTELLIKE','IT'),(30,'ITF33','HOTELLIKE','WRL_X_ITA'),(31,'ITF33','OTHER','IT'),(32,'ITF33','OTHER','WRL_X_ITA'),(33,'ITF34','HOTELLIKE','IT'),(34,'ITF34','HOTELLIKE','WRL_X_ITA'),(35,'ITF34','OTHER','IT'),(36,'ITF34','OTHER','WRL_X_ITA'),(37,'ITF35','HOTELLIKE','IT'),(38,'ITF35','HOTELLIKE','WRL_X_ITA'),(39,'ITF35','OTHER','IT'),(40,'ITF35','OTHER','WRL_X_ITA'),(41,'ITF3','HOTELLIKE','IT'),(42,'ITF3','HOTELLIKE','WRL_X_ITA'),(43,'ITF3','OTHER','IT'),(44,'ITF3','OTHER','WRL_X_ITA');
/*!40000 ALTER TABLE `contesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provincia` (
  `ID_PROVINCIA` varchar(45) NOT NULL,
  `NOME_PROVINCIA` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID_PROVINCIA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincia`
--

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
INSERT INTO `provincia` VALUES ('ITF3','Campania'),('ITF31','Caserta'),('ITF32','Benevento'),('ITF33','Napoli'),('ITF34','Avellino'),('ITF35','Salerno');
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `residenza_clienti`
--

DROP TABLE IF EXISTS `residenza_clienti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `residenza_clienti` (
  `ID_RESIDENZA` varchar(45) NOT NULL,
  `DESCRIZIONE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID_RESIDENZA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `residenza_clienti`
--

LOCK TABLES `residenza_clienti` WRITE;
/*!40000 ALTER TABLE `residenza_clienti` DISABLE KEYS */;
INSERT INTO `residenza_clienti` VALUES ('IT','Italia'),('WORLD','Mondo'),('WRL_X_ITA','Estero');
/*!40000 ALTER TABLE `residenza_clienti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storico`
--

DROP TABLE IF EXISTS `storico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storico` (
  `ID_STORICO` int NOT NULL AUTO_INCREMENT,
  `CONTESTO` int NOT NULL,
  `TIME` varchar(45) NOT NULL,
  `PRESENZE` int DEFAULT NULL,
  `ARRIVI` int DEFAULT NULL,
  `TIPODATO` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`ID_STORICO`),
  KEY `CONTESTO` (`CONTESTO`),
  CONSTRAINT `storico_ibfk_1` FOREIGN KEY (`CONTESTO`) REFERENCES `contesto` (`ID_CONTESTO`)
) ENGINE=InnoDB AUTO_INCREMENT=27497 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storico`
--

LOCK TABLES `storico` WRITE;
/*!40000 ALTER TABLE `storico` DISABLE KEYS */;
/*!40000 ALTER TABLE `storico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_alloggio`
--

DROP TABLE IF EXISTS `tipo_alloggio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_alloggio` (
  `ID_ALLOGGIO` varchar(45) NOT NULL,
  `DESCRIZIONE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID_ALLOGGIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_alloggio`
--

LOCK TABLES `tipo_alloggio` WRITE;
/*!40000 ALTER TABLE `tipo_alloggio` DISABLE KEYS */;
INSERT INTO `tipo_alloggio` VALUES ('ALL','Totale esercizi ricettivi'),('HOTELLIKE','Strutture alberghiere'),('OTHER','Strutture extra alberghiere');
/*!40000 ALTER TABLE `tipo_alloggio` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-22 20:49:42
