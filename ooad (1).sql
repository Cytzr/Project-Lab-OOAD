-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2024 at 02:01 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ooad`
--

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `item_id` int(11) UNSIGNED NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `item_size` varchar(255) NOT NULL,
  `item_price` int(11) NOT NULL,
  `item_category` varchar(255) NOT NULL,
  `item_status` tinyint(1) DEFAULT NULL,
  `item_wishlist` varchar(255) DEFAULT NULL,
  `item_offer_status` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `user_id` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`item_id`, `item_name`, `item_size`, `item_price`, `item_category`, `item_status`, `item_wishlist`, `item_offer_status`, `reason`, `user_id`) VALUES
(4, 'Item 2', '28', 23000, 'Item Makanan', 0, NULL, NULL, 'gk bagus', 1),
(7, 'ddd', '80inci', 230000, 'ddd', 1, NULL, NULL, NULL, 1),
(8, 'item', 'L', 60000, 'cat 1', 1, NULL, NULL, NULL, 3),
(9, 'Item 4', 'XXL', 500000, 'Pakaian', 1, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `offer`
--

CREATE TABLE `offer` (
  `offer_id` int(11) UNSIGNED NOT NULL,
  `user_id` int(11) UNSIGNED NOT NULL,
  `item_id` int(11) UNSIGNED NOT NULL,
  `offered_price` int(11) NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `offer`
--

INSERT INTO `offer` (`offer_id`, `user_id`, `item_id`, `offered_price`, `status`, `reason`) VALUES
(9, 2, 7, 240000, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` int(11) UNSIGNED NOT NULL,
  `user_id` int(11) UNSIGNED NOT NULL,
  `item_id` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `user_id`, `item_id`) VALUES
(5, 2, 7),
(6, 2, 7);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) UNSIGNED NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `phone_number`, `address`, `role`) VALUES
(1, 'Gerry', 'gerger@@', '+628127660739', 'Jalan U', 'Seller'),
(2, 'Kelvin', 'kelvin@@', '+627827272727272', 'yes', 'Buyer'),
(3, 'Kuning', 'ddddddd@', '+62727272727272', 'asasasaas', 'Seller');

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

CREATE TABLE `wishlist` (
  `wishlist_id` int(11) UNSIGNED NOT NULL,
  `item_id` int(11) UNSIGNED NOT NULL,
  `user_id` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `wishlist`
--

INSERT INTO `wishlist` (`wishlist_id`, `item_id`, `user_id`) VALUES
(7, 7, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `fk_user_id` (`user_id`);

--
-- Indexes for table `offer`
--
ALTER TABLE `offer`
  ADD PRIMARY KEY (`offer_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `fk_offer_item` (`item_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `fk_item_id` (`item_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD PRIMARY KEY (`wishlist_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `fk_wishlist_item` (`item_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `item_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `offer`
--
ALTER TABLE `offer`
  MODIFY `offer_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `wishlist`
--
ALTER TABLE `wishlist`
  MODIFY `wishlist_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `offer`
--
ALTER TABLE `offer`
  ADD CONSTRAINT `fk_offer_item` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `offer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `offer_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`);

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `fk_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`);

--
-- Constraints for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD CONSTRAINT `fk_wishlist_item` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  ADD CONSTRAINT `wishlist_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
