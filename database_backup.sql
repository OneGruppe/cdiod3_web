-- phpMyAdmin SQL Dump
-- version 4.4.7
-- http://www.phpmyadmin.net
--
-- Vært: localhost
-- Genereringstid: 30. 04 2018 kl. 20:49:09
-- Serverversion: 5.5.47-MariaDB
-- PHP-version: 5.5.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `CDIO3`
--

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` int(11) NOT NULL,
  `role` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Data dump for tabellen `roles`
--

INSERT INTO `roles` (`role_id`, `role`) VALUES
(1, 'admin'),
(4, 'laborant'),
(2, 'pharmacist'),
(3, 'produktion');

-- --------------------------------------------------------

--
-- Stand-in-struktur for visning `totalView`
--
CREATE TABLE IF NOT EXISTS `totalView` (
`user_id` int(11)
,`username` varchar(20)
,`password` varchar(20)
,`ini` varchar(3)
,`cpr` varchar(11)
,`role_id` int(11)
,`role` varchar(10)
);

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `ini` varchar(3) NOT NULL,
  `cpr` varchar(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

--
-- Data dump for tabellen `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `ini`, `cpr`) VALUES
(1, 'admin', '', '', ''),
(45, 'HaldorH', 'Heidi123', 'HH', '010190-1124'),
(46, 'RogerV', 'Vibeke123', 'RV', '010191-1125'),
(47, 'KajF', 'KajFelix123', 'KF', '010192-1127'),
(48, 'FilipC', 'Caj123', 'FC', '010192-1129'),
(49, 'NatalieV', 'Vibeke123', 'NV', '010192-1130'),
(51, 'HelgaA', 'Agnes123', 'HA', '010197-1132'),
(52, 'TheodorK', 'Kaj123', 'TK', '010180-1135'),
(53, 'BodilT', 'Torsk123', 'BT', '010293-2326');

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `users_roles`
--

CREATE TABLE IF NOT EXISTS `users_roles` (
  `users_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Data dump for tabellen `users_roles`
--

INSERT INTO `users_roles` (`users_id`, `roles_id`) VALUES
(1, 1),
(45, 1),
(45, 3),
(46, 1),
(46, 3),
(47, 2),
(47, 4),
(48, 3),
(48, 4),
(49, 2),
(51, 3),
(52, 3),
(53, 4);

-- --------------------------------------------------------

--
-- Struktur for visning `totalView`
--
DROP TABLE IF EXISTS `totalView`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `totalView` AS select `users`.`user_id` AS `user_id`,`users`.`username` AS `username`,`users`.`password` AS `password`,`users`.`ini` AS `ini`,`users`.`cpr` AS `cpr`,`roles`.`role_id` AS `role_id`,`roles`.`role` AS `role` from ((`users` join `users_roles` on((`users`.`user_id` = `users_roles`.`users_id`))) join `roles` on((`roles`.`role_id` = `users_roles`.`roles_id`)));

--
-- Begrænsninger for dumpede tabeller
--

--
-- Indeks for tabel `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `id` (`role_id`),
  ADD UNIQUE KEY `role` (`role`);

--
-- Indeks for tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `id` (`user_id`,`username`,`ini`,`cpr`),
  ADD UNIQUE KEY `user_id` (`user_id`,`username`,`ini`,`cpr`);

--
-- Indeks for tabel `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `roles_id` (`roles_id`),
  ADD KEY `users_id` (`users_id`);

--
-- Brug ikke AUTO_INCREMENT for slettede tabeller
--

--
-- Tilføj AUTO_INCREMENT i tabel `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=54;
--
-- Begrænsninger for dumpede tabeller
--

--
-- Begrænsninger for tabel `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`users_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
