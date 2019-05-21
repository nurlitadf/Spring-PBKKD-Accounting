-- SET AUTOCOMMIT = 0;
-- START TRANSACTION;
-- SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;



CREATE TABLE customer (
  Customer_id int NOT NULL PRIMARY KEY,
  Customer_name varchar(30) DEFAULT NULL,
  Customer_address varchar(50) DEFAULT NULL,
  Customer_phone varchar(20) DEFAULT NULL,
  created_at datetime NOT NULL,
  updated_at datetime NOT NULL
) ;

SET IDENTITY_INSERT customer ON ;
INSERT INTO customer (Customer_id, Customer_name, Customer_address, Customer_phone, created_at, updated_at) VALUES
(1, 'lita', 'salatiga', '081221213333', '2019-05-12 07:12:53', '2019-05-12 07:12:53'),
(10, 'litaa', 'salatiga', '081221213333', '2019-05-12 07:13:03', '2019-05-12 07:13:03');

SET IDENTITY_INSERT customer OFF;

CREATE TABLE detail_pembayaran (
  Transaksi_id int DEFAULT NULL,
  Menu_id int DEFAULT NULL
) ;

SET IDENTITY_INSERT detail_pembayaran ON ;
INSERT INTO detail_pembayaran (Transaksi_id, Menu_id, id, created_at, updated_at) VALUES
(1, 1, 1, '2019-05-07 00:00:00', '2019-05-24 17:00:00');

SET IDENTITY_INSERT detail_pembayaran OFF;

CREATE TABLE menu (
  Restaurant_id int DEFAULT NULL PRIMARY KEY,
  Menu_id int NOT NULL,
  Menu_name varchar(20) DEFAULT NULL,
  Menu_price int DEFAULT NULL,
  created_at datetime NOT NULL,
  updated_at datetime NOT NULL
) ;

SET IDENTITY_INSERT menu ON ;
INSERT INTO menu (Restaurant_id, Menu_id, Menu_name, Menu_price, created_at, updated_at) VALUES
(1, 1, 'burger', 25000, '2019-05-12 07:08:37', '2019-05-12 07:08:37'),
(1, 2, 'nasi uduk', 15000, '2019-05-12 07:12:44', '2019-05-12 07:25:55'),
(1, 3, 'es teh', 25000, '2019-05-12 07:24:03', '2019-05-12 07:24:03'),
(1, 10, 'burger', 25000, '2019-05-12 07:13:58', '2019-05-12 07:13:58');

SET IDENTITY_INSERT menu OFF;

CREATE TABLE restaurant (
  Restaurant_id int NOT NULL,
  Restaurant_name varchar(20) DEFAULT NULL,
  Restaurant_address varchar(40) DEFAULT NULL,
  Restaurant_phone varchar(20) DEFAULT NULL,
  created_at datetime NOT NULL,
  updated_at datetime NOT NULL
) ;

ALTER TABLE restaurant
  ADD PRIMARY KEY (Restaurant_id);

SET IDENTITY_INSERT restaurant ON ;
INSERT INTO restaurant (Restaurant_id, Restaurant_name, Restaurant_address, Restaurant_phone, created_at, updated_at) VALUES
(1, 'mcd', 'mulyosari', '14045', '2019-05-03 00:00:00', '2019-05-03 00:00:00');

SET IDENTITY_INSERT restaurant OFF;

CREATE TABLE transaksi (
  Transaksi_id int NOT NULL
) ;

SET IDENTITY_INSERT transaksi ON ;
INSERT INTO transaksi (Transaksi_id, Time, Date, Transaksi_status, Restaurant_id, Customer_id, Jenis_pembayaran, created_at, updated_at) VALUES
(1, '05:27:29', '2019-05-02', 1, 1, 1, 'cash', '2019-05-07 00:00:00', '2019-05-24 17:00:00');

SET IDENTITY_INSERT transaksi OFF;





ALTER TABLE menu
  ADD CONSTRAINT FKblwdtxevpl4mrds8a12q0ohu6 FOREIGN KEY (Restaurant_id) REFERENCES restaurant (Restaurant_id) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
