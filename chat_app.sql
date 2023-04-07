-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for chat_app
CREATE DATABASE IF NOT EXISTS `chat_app` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `chat_app`;

-- Dumping structure for table chat_app.messages
CREATE TABLE IF NOT EXISTS `messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `date_created` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table chat_app.messages: ~15 rows (approximately)
INSERT INTO `messages` (`id`, `username`, `content`, `key`, `date_created`) VALUES
	(123, 'Server', 'CD7FCC900897FD44033C601B56FAD9595CC5A356A2A81DB5E4684D5DE81918A0', '132', '27-03-2023 00:11:50'),
	(124, 'Server', 'C5A8BF6A031F84EAF643C334695B3EF72F43A216D5D7EF7E92F44DB6E9EA353A92DF3F5A7D6C145AC010893C2123654D6A2AB37D99047E18EA528F669EACBE3E', '132', '27-03-2023 00:12:06'),
	(125, 'Server', '698FB142374A6B46855480235D5B47A5AB96CA65F4DF1741A865B5E0AE2E0A6D', '', '27-03-2023 00:12:18'),
	(126, 'Server', '70FCCCF838530C0538B0CF690E057E9B0074EFC4E9244545A67BE25158DA4F83', '', '27-03-2023 00:12:21'),
	(127, 'Server', '27B18C967E571119867550B02ABF33919323142494701CDB11E20B61EAA26774', '', '27-03-2023 00:13:26'),
	(128, 'end', '8DB55FB1AB687B8E2258EE2B521894F679DB59DDC89A7C8A32F8458253F508DC', '', '27-03-2023 00:13:54'),
	(129, 'end', '27EC32F173D5310A861CBA4DB37C76DEBEB936DFD61EE2BB9908AFD1C075A459', '', '27-03-2023 00:13:56'),
	(130, 'end', '7F08E31FE7E67D93213EA532ADA8F1A25C3BB953869CAAEA17D33A818BDD8731C7E9D09714C82D002BB618C9FCF0189A', '', '27-03-2023 00:14:04'),
	(131, 'end', '0A4B412988AEC1C276CBE7F0B9AA0720B5829246DE1CC900839B6B7C5F945F45', '', '27-03-2023 00:14:05'),
	(132, 'Server', '1804E28CBF759FA964D34E27C4394AF6B236BB61D9F7324F4712A850AC931C98', '', '28-03-2023 06:51:29'),
	(133, 'Server', '08966719FA52B228C5375C95FF8C4BCE739F392F7DDE47D2F8C6F6CB9032433F', '', '28-03-2023 06:51:40'),
	(134, 'user', 'A0ACA7E868D2C6DC3B7C77EE4E5F6A2FD64EB8F27DA1481D7C2637C20EC527FA', '', '28-03-2023 06:51:44'),
	(135, 'user', '872D83B3B1EF58451A483E1C6C7F78CA6A67CD81AC0F586A4DF7833E24844D0A', '', '28-03-2023 06:51:47'),
	(136, 'Server', '8136ECCDC36E37FE0E10EF2D669B1D56C706F75E4127B70CC2FDF6941B2DAFD3', '', '28-03-2023 07:14:51'),
	(137, 'user', '7EC10C8DE02C5CC28179B7A989B63F95BA63CBB8995B849DCDBD057DC561F4689401C764A79843C2229C51D57452AF2EE3FDE68A237BBC463C28469F807A9D2D', '123', '28-03-2023 07:20:40');

-- Dumping structure for table chat_app.users
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `role` bit(1) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `date_created` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table chat_app.users: ~3 rows (approximately)
INSERT INTO `users` (`username`, `password`, `role`, `name`, `email`, `phone_number`, `date_created`) VALUES
	('end', 'end', b'0', 'I am normal User', '...', 'end', '23-03-2023 01:05:08'),
	('Server', 'Server', b'1', 'I am Server Admin', 'server@email.com', '13572468', '26-03-2023 20:08:24'),
	('user', 'user', b'1', 'I am User with Admin Role', 'user@email.com', '24681357', '22-03-2023 0:0:0');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
