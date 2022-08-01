-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 25, 2022 at 06:17 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `messagescheduler`
--

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `message_id` int(11) NOT NULL,
  `message_content` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `phone_number` varchar(32) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `scheduled_at` timestamp NULL DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `gupshup_message_id` varchar(255) DEFAULT NULL,
  `sent_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`message_id`, `message_content`, `user_id`, `phone_number`, `created_at`, `scheduled_at`, `status`, `gupshup_message_id`, `sent_at`) VALUES
(10, 'One more time checking', 12, '919943693401', '2022-07-21 01:58:46', '1970-01-20 04:39:03', 2, 'e2307ab2-84a4-481f-847c-09aeb61acad2', '2022-07-21 01:59:06'),
(11, 'One more time checking', 8, '919943693401', '2022-07-21 04:54:34', '2022-07-21 01:58:46', 2, 'abf8792e-6344-45ae-a9a6-a81943c1c0d4', '2022-07-21 04:54:54'),
(12, 'One more time checking', 8, '919943693401', '2022-07-21 04:58:34', '2022-07-21 01:58:46', 2, '6da15175-8fd5-4743-8b4d-792938c65c2f', '2022-07-21 04:58:45'),
(13, 'One more time checking', 8, '919943693401', '2022-07-21 04:59:36', '2022-07-21 01:58:46', 2, '608b3356-8cab-4ef4-bb4f-37c0fbe47828', '2022-07-21 04:59:59'),
(14, 'One more time checking', 8, '919952862652', '2022-07-21 05:00:56', '2022-07-21 01:58:46', 2, 'e52db6d2-39bc-42b1-b6d7-a41144cdbe68', '2022-07-21 05:00:58'),
(15, 'is the API is working?', 8, '919952862652', '2022-07-21 05:55:25', '2022-07-21 01:58:46', 2, '1bd6c8a2-0580-44f6-9fcb-1143ee57e35e', '2022-07-21 06:19:13'),
(16, 'Testing for Message Repository', 8, '919952862652', '2022-07-21 12:02:32', NULL, 0, NULL, NULL),
(17, 'Testing for Message Repository', 8, '919952862652', '2022-07-21 12:08:42', NULL, 0, NULL, NULL),
(18, 'Testing for Message Repository', 8, '919952862652', '2022-07-21 12:09:36', NULL, 0, NULL, NULL),
(19, 'API Testing', 13, '919952862652', '2022-07-22 04:21:54', '2022-07-21 01:58:46', 2, '25db7ccf-2169-4f2f-a5ca-1941d43ea5fb', '2022-07-22 04:22:08'),
(20, 'Testing for Message Repository', 8, '919952862652', '2022-07-22 08:04:59', NULL, 0, NULL, NULL),
(21, 'Testing for Message Repository', 8, '919952862652', '2022-07-22 08:11:20', NULL, 0, NULL, NULL),
(22, 'Testing for Message Repository', 8, '919952862652', '2022-07-22 08:14:48', '2022-07-22 08:14:48', 2, '6bb5d010-ac8e-434d-9841-cc59f6913f4a', '2022-07-22 08:15:00'),
(23, 'Testing for Message Repository', 8, '919952862652', '2022-07-22 08:16:43', '2022-07-22 08:16:43', 2, '40836397-3458-4a45-bf5d-5fe68450e5e4', '2022-07-22 08:17:00'),
(24, 'Testing for Message Repository', 8, '919952862652', '2022-07-22 09:18:02', '2022-07-22 09:18:02', 2, '9611db61-f695-4891-8d7d-9b4cae483a9e', '2022-07-22 09:18:30'),
(25, 'Testing API 1', 13, '919952862652', '2022-07-22 11:24:04', '2022-07-22 04:21:27', 2, 'f0a41983-88bb-4f5f-8576-ecc77ba817f6', '2022-07-22 11:24:21'),
(26, 'Testing API 1', 13, '919952862652', '2022-07-22 11:26:31', '2022-07-22 04:21:27', 2, 'dc65a52a-473c-458d-8a71-4f343a95f66b', '2022-07-22 11:26:51'),
(27, 'Testing API 1', 13, '919952862652', '2022-07-22 11:59:28', '2022-07-22 04:21:27', 2, 'a7e17325-0d26-4057-b812-03efb0eb8315', '2022-07-22 11:59:45');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(55) NOT NULL,
  `auth_token` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `auth_token`, `created_at`) VALUES
(3, 'Vishva', 'authenticationToken.generateAuthenticationToken();', '2022-07-18 12:06:52'),
(4, 'Vishva', 'authenticationToken.generateAuthenticationToken();', '2022-07-18 12:49:32'),
(5, 'Vishva', 'authenticationToken.generateAuthenticationToken();', '2022-07-18 12:52:31'),
(6, 'Vishva', 'authenticationToken.generateAuthenticationToken();', '2022-07-18 12:52:57'),
(7, 'Vishva', 'c61261e0-542a-4b48-bbec-06662766ea18', '2022-07-19 02:22:31'),
(8, 'Vishva', '707343b4-3559-437c-8f25-318ad0440a0d', '2022-07-19 02:23:42'),
(9, 'Vishva', 'e2af4362-90e2-436e-8879-b69434d10983', '2022-07-19 02:41:06'),
(10, 'Vishva', '94adb4e3-982e-4f29-a280-7a709777fe64', '2022-07-19 02:42:46'),
(11, 'Vishva', '175201f3-a5a3-42de-87e0-2c5129da8615', '2022-07-19 02:45:30'),
(12, 'Vishva', '82793852-48d6-4b47-8d02-226850dfa0cd', '2022-07-19 02:46:48'),
(13, 'azure', '9dd510e9-9d7c-4cc1-95b8-d6692fcdc0a8', '2022-07-22 04:21:27');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `used_fk` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `used_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

