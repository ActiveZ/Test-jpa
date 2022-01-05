-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: infra_db_1
-- Generation Time: Jan 05, 2022 at 11:03 AM
-- Server version: 10.7.1-MariaDB-1:10.7.1+maria~focal
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pizza_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `credit_card`
--

CREATE TABLE `credit_card` (
  `id` int(11) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL,
  `expiration` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customer_credit_card`
--

CREATE TABLE `customer_credit_card` (
  `customer_id` int(11) DEFAULT NULL,
  `credit_card_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customer_drink`
--

CREATE TABLE `customer_drink` (
  `customer_order_id` int(11) NOT NULL,
  `drink` varchar(20) NOT NULL,
  `qty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customer_order`
--

CREATE TABLE `customer_order` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `credit_card_id` int(11) DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customer_pizza`
--

CREATE TABLE `customer_pizza` (
  `customer_order_id` int(11) NOT NULL,
  `pizza_id` int(11) NOT NULL,
  `qty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `drink`
--

CREATE TABLE `drink` (
  `name` varchar(20) NOT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `drink`
--

INSERT INTO `drink` (`name`, `price`) VALUES
('coke', 2),
('diet-coke', 2),
('liquid-schwartz', 20),
('red-balls', 4);

-- --------------------------------------------------------

--
-- Table structure for table `pizza`
--

CREATE TABLE `pizza` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `size` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pizza`
--

INSERT INTO `pizza` (`id`, `name`, `size`) VALUES
(1, 'Margherita Small', 'S'),
(2, 'Margherita Max', 'XL'),
(3, 'Carbonara L', 'L'),
(4, 'Carbonara XL', 'XL'),
(5, 'Gorgonzola', 'XL'),
(6, 'Mediterranea', 'XL'),
(7, 'Ortolana', 'XL');

-- --------------------------------------------------------

--
-- Table structure for table `size`
--

CREATE TABLE `size` (
  `name` varchar(2) NOT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `size`
--

INSERT INTO `size` (`name`, `price`) VALUES
('L', 14),
('M', 12),
('S', 10),
('XL', 18);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `credit_card`
--
ALTER TABLE `credit_card`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`,`number`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_credit_card`
--
ALTER TABLE `customer_credit_card`
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `credit_card_id` (`credit_card_id`);

--
-- Indexes for table `customer_drink`
--
ALTER TABLE `customer_drink`
  ADD PRIMARY KEY (`customer_order_id`,`drink`),
  ADD KEY `drink` (`drink`);

--
-- Indexes for table `customer_order`
--
ALTER TABLE `customer_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `credit_card_id` (`credit_card_id`);

--
-- Indexes for table `customer_pizza`
--
ALTER TABLE `customer_pizza`
  ADD PRIMARY KEY (`customer_order_id`,`pizza_id`),
  ADD KEY `pizza_id` (`pizza_id`);

--
-- Indexes for table `drink`
--
ALTER TABLE `drink`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `pizza`
--
ALTER TABLE `pizza`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `size` (`size`);

--
-- Indexes for table `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `credit_card`
--
ALTER TABLE `credit_card`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customer_order`
--
ALTER TABLE `customer_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pizza`
--
ALTER TABLE `pizza`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer_credit_card`
--
ALTER TABLE `customer_credit_card`
  ADD CONSTRAINT `customer_credit_card_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `customer_credit_card_ibfk_2` FOREIGN KEY (`credit_card_id`) REFERENCES `credit_card` (`id`);

--
-- Constraints for table `customer_drink`
--
ALTER TABLE `customer_drink`
  ADD CONSTRAINT `customer_drink_ibfk_1` FOREIGN KEY (`customer_order_id`) REFERENCES `customer_order` (`id`),
  ADD CONSTRAINT `customer_drink_ibfk_2` FOREIGN KEY (`drink`) REFERENCES `drink` (`name`);

--
-- Constraints for table `customer_order`
--
ALTER TABLE `customer_order`
  ADD CONSTRAINT `customer_order_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `customer_order_ibfk_2` FOREIGN KEY (`credit_card_id`) REFERENCES `credit_card` (`id`);

--
-- Constraints for table `customer_pizza`
--
ALTER TABLE `customer_pizza`
  ADD CONSTRAINT `customer_pizza_ibfk_1` FOREIGN KEY (`customer_order_id`) REFERENCES `customer_order` (`id`),
  ADD CONSTRAINT `customer_pizza_ibfk_2` FOREIGN KEY (`pizza_id`) REFERENCES `pizza` (`id`);

--
-- Constraints for table `pizza`
--
ALTER TABLE `pizza`
  ADD CONSTRAINT `pizza_ibfk_1` FOREIGN KEY (`size`) REFERENCES `size` (`name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
