-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 15, 2019 at 03:29 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `accounting`
--

-- --------------------------------------------------------

--
-- Table structure for table `cashflow`
--

CREATE TABLE `cashflow` (
  `id` int(11) NOT NULL,
  `source` varchar(20) NOT NULL,
  `source_id` int(11) DEFAULT NULL,
  `destination` varchar(20) NOT NULL,
  `destination_id` int(11) NOT NULL,
  `jumlah_uang` int(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `Customer_id` int(11) NOT NULL,
  `Customer_name` varchar(30) DEFAULT NULL,
  `Customer_address` varchar(50) DEFAULT NULL,
  `Customer_phone` varchar(20) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`Customer_id`, `Customer_name`, `Customer_address`, `Customer_phone`, `created_at`, `updated_at`) VALUES
(1, 'lita', 'salatiga', '081221213333', '2019-05-12 07:12:53', '2019-05-12 07:12:53'),
(10, 'litaa', 'salatiga', '081221213333', '2019-05-12 07:13:03', '2019-05-12 07:13:03');

-- --------------------------------------------------------

--
-- Table structure for table `detail_pembayaran`
--

CREATE TABLE `detail_pembayaran` (
  `Transaksi_id` int(11) DEFAULT NULL,
  `Menu_id` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail_pembayaran`
--

INSERT INTO `detail_pembayaran` (`Transaksi_id`, `Menu_id`, `id`, `created_at`, `updated_at`) VALUES
(1, 1, 1, '2019-05-07 00:00:00', '2019-05-24 17:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `Restaurant_id` int(11) DEFAULT NULL,
  `Menu_id` int(11) NOT NULL,
  `Menu_name` varchar(20) DEFAULT NULL,
  `Menu_price` int(11) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`Restaurant_id`, `Menu_id`, `Menu_name`, `Menu_price`, `created_at`, `updated_at`) VALUES
(1, 1, 'burger', 25000, '2019-05-12 07:08:37', '2019-05-12 07:08:37'),
(1, 2, 'nasi uduk', 15000, '2019-05-12 07:12:44', '2019-05-12 07:25:55'),
(1, 3, 'es teh', 25000, '2019-05-12 07:24:03', '2019-05-12 07:24:03'),
(1, 10, 'burger', 25000, '2019-05-12 07:13:58', '2019-05-12 07:13:58');

-- --------------------------------------------------------

--
-- Table structure for table `restaurant`
--

CREATE TABLE `restaurant` (
  `Restaurant_id` int(11) NOT NULL,
  `Restaurant_name` varchar(20) DEFAULT NULL,
  `Restaurant_address` varchar(40) DEFAULT NULL,
  `Restaurant_phone` varchar(20) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`Restaurant_id`, `Restaurant_name`, `Restaurant_address`, `Restaurant_phone`, `created_at`, `updated_at`) VALUES
(1, 'mcd', 'mulyosari', '14045', '2019-05-03 00:00:00', '2019-05-03 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `Transaksi_id` int(11) NOT NULL,
  `Time` time DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Transaksi_status` int(11) DEFAULT NULL,
  `Restaurant_id` int(11) DEFAULT NULL,
  `Customer_id` int(11) DEFAULT NULL,
  `Jenis_pembayaran` varchar(20) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`Transaksi_id`, `Time`, `Date`, `Transaksi_status`, `Restaurant_id`, `Customer_id`, `Jenis_pembayaran`, `created_at`, `updated_at`) VALUES
(1, '05:27:29', '2019-05-02', 1, 1, 1, 'cash', '2019-05-07 00:00:00', '2019-05-24 17:00:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cashflow`
--
ALTER TABLE `cashflow`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`Customer_id`);

--
-- Indexes for table `detail_pembayaran`
--
ALTER TABLE `detail_pembayaran`
  ADD KEY `Transaksi_id` (`Transaksi_id`),
  ADD KEY `Menu_id` (`Menu_id`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`Menu_id`),
  ADD KEY `Restaurant_id` (`Restaurant_id`);

--
-- Indexes for table `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`Restaurant_id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`Transaksi_id`),
  ADD KEY `Restaurant_id` (`Restaurant_id`),
  ADD KEY `Customer_id` (`Customer_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `Menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `restaurant`
--
ALTER TABLE `restaurant`
  MODIFY `Restaurant_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_pembayaran`
--
ALTER TABLE `detail_pembayaran`
  ADD CONSTRAINT `detail_pembayaran_ibfk_1` FOREIGN KEY (`Transaksi_id`) REFERENCES `transaksi` (`Transaksi_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_pembayaran_ibfk_2` FOREIGN KEY (`Menu_id`) REFERENCES `menu` (`Menu_id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `FKblwdtxevpl4mrds8a12q0ohu6` FOREIGN KEY (`Restaurant_id`) REFERENCES `restaurant` (`Restaurant_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`Restaurant_id`) REFERENCES `restaurant` (`Restaurant_id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `FKb2xmawe0ig7qpl3rwq5ut1f08` FOREIGN KEY (`Customer_id`) REFERENCES `customer` (`Customer_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKk3by3npoi4cvxtubb0sxh277j` FOREIGN KEY (`Restaurant_id`) REFERENCES `restaurant` (`Restaurant_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`Restaurant_id`) REFERENCES `restaurant` (`Restaurant_id`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`Customer_id`) REFERENCES `customer` (`Customer_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
