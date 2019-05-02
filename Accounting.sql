/*
SQLyog Ultimate v10.42 
MySQL - 5.5.5-10.1.38-MariaDB : Database - accounting
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`accounting` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `accounting`;

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `Customer_id` int(11) NOT NULL,
  `Customer_name` varchar(30) DEFAULT NULL,
  `Customer_address` varchar(50) DEFAULT NULL,
  `Customer_phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `customer` */

/*Table structure for table `detail_pembayaran` */

DROP TABLE IF EXISTS `detail_pembayaran`;

CREATE TABLE `detail_pembayaran` (
  `Transaksi_id` int(11) DEFAULT NULL,
  `Menu_id` int(11) DEFAULT NULL,
  KEY `Transaksi_id` (`Transaksi_id`),
  KEY `Menu_id` (`Menu_id`),
  CONSTRAINT `detail_pembayaran_ibfk_1` FOREIGN KEY (`Transaksi_id`) REFERENCES `transaksi` (`Transaksi_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `detail_pembayaran_ibfk_2` FOREIGN KEY (`Menu_id`) REFERENCES `menu` (`Menu_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `detail_pembayaran` */

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `Restaurant_id` int(11) DEFAULT NULL,
  `Menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `Menu_name` varchar(20) DEFAULT NULL,
  `Menu_price` int(11) DEFAULT NULL,
  PRIMARY KEY (`Menu_id`),
  KEY `Restaurant_id` (`Restaurant_id`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`Restaurant_id`) REFERENCES `restaurant` (`Restaurant_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `menu` */

/*Table structure for table `restaurant` */

DROP TABLE IF EXISTS `restaurant`;

CREATE TABLE `restaurant` (
  `Restaurant_id` int(11) NOT NULL AUTO_INCREMENT,
  `Restaurant_name` varchar(20) DEFAULT NULL,
  `Restaurant_address` varchar(40) DEFAULT NULL,
  `Restaurant_phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `restaurant` */

/*Table structure for table `transaksi` */

DROP TABLE IF EXISTS `transaksi`;

CREATE TABLE `transaksi` (
  `Transaksi_id` int(11) NOT NULL,
  `Time` time DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Transaksi_status` int(11) DEFAULT NULL,
  `Restaurant_id` int(11) DEFAULT NULL,
  `Customer_id` int(11) DEFAULT NULL,
  `Jenis_pembayaran` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Transaksi_id`),
  KEY `Restaurant_id` (`Restaurant_id`),
  KEY `Customer_id` (`Customer_id`),
  CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`Restaurant_id`) REFERENCES `restaurant` (`Restaurant_id`),
  CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`Customer_id`) REFERENCES `customer` (`Customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `transaksi` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
