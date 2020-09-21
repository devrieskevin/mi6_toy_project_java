-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Gegenereerd op: 03 jun 2020 om 22:40
-- Serverversie: 8.0.18
-- PHP-versie: 7.3.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mi6`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `agents`
--

CREATE TABLE `agents` (
  `agent_id` int(11) UNSIGNED ZEROFILL NOT NULL,
  `service_number` smallint(3) UNSIGNED ZEROFILL NOT NULL,
  `code_phrase` varchar(250) COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `license_to_kill` tinyint(1) NOT NULL DEFAULT '0',
  `license_expiration_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Gegevens worden geëxporteerd voor tabel `agents`
--

INSERT INTO `agents` (`agent_id`, `service_number`, `code_phrase`, `active`, `license_to_kill`, `license_expiration_date`) VALUES
(00000000001, 002, 'GoD SaVE THE QUeen', 1, 1, '2020-12-31 23:59:59'),
(00000000002, 005, 'GrEaT BRItAiN', 1, 1, '2020-01-01 00:00:00'),
(00000000003, 007, 'SHAKEN not StIRReD', 1, 1, '2020-12-31 23:59:59'),
(00000000004, 030, 'I hAve NO liCENce', 1, 0, NULL),
(00000000005, 102, 'i AlSO havE no LICence', 1, 0, NULL),
(00000000006, 777, 'OLd TimEr', 0, 1, '2020-12-31 23:59:59');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `login_attempts`
--

CREATE TABLE `login_attempts` (
  `login_attempt_id` int(11) UNSIGNED ZEROFILL NOT NULL,
  `service_number` smallint(3) UNSIGNED ZEROFILL NOT NULL,
  `login_attempt_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `success` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Gegevens worden geëxporteerd voor tabel `login_attempts`
--

INSERT INTO `login_attempts` (`login_attempt_id`, `service_number`, `login_attempt_date`, `success`) VALUES
(00000000001, 002, '2020-05-26 16:02:53', 1),
(00000000002, 002, '2020-05-26 16:03:17', 1),
(00000000008, 002, '2020-05-29 15:16:34', 0),
(00000000009, 002, '2020-05-29 15:17:38', 1),
(00000000010, 002, '2020-05-29 15:20:04', 1),
(00000000011, 002, '2020-05-29 15:20:12', 1),
(00000000013, 002, '2020-05-31 21:53:53', 1),
(00000000014, 002, '2020-05-31 21:54:02', 1),
(00000000015, 002, '2020-05-31 22:34:15', 0),
(00000000016, 002, '2020-05-31 22:38:37', 1),
(00000000017, 002, '2020-05-31 23:07:41', 1),
(00000000018, 002, '2020-05-31 23:10:53', 1),
(00000000019, 002, '2020-05-31 23:12:02', 1),
(00000000020, 002, '2020-06-01 22:25:35', 1),
(00000000021, 002, '2020-06-01 22:27:46', 1),
(00000000022, 002, '2020-06-01 22:30:02', 1),
(00000000023, 002, '2020-06-01 22:30:24', 0),
(00000000024, 002, '2020-06-01 22:31:24', 1),
(00000000025, 002, '2020-06-01 22:31:47', 1),
(00000000026, 002, '2020-06-01 22:32:42', 1),
(00000000027, 002, '2020-06-01 22:33:25', 0),
(00000000028, 002, '2020-06-01 22:34:56', 0),
(00000000029, 002, '2020-06-01 22:37:11', 1),
(00000000030, 002, '2020-06-01 22:37:50', 1),
(00000000031, 002, '2020-06-01 22:39:36', 1),
(00000000032, 002, '2020-06-02 00:29:41', 0),
(00000000033, 002, '2020-06-02 00:30:46', 1),
(00000000034, 002, '2020-06-02 00:37:18', 1),
(00000000035, 002, '2020-06-02 00:45:41', 1),
(00000000036, 002, '2020-06-02 00:49:24', 1),
(00000000037, 002, '2020-06-02 00:51:27', 1),
(00000000038, 002, '2020-06-02 00:52:58', 1),
(00000000039, 002, '2020-06-02 00:57:52', 1),
(00000000040, 002, '2020-06-02 00:58:20', 0),
(00000000041, 002, '2020-06-02 00:59:21', 0),
(00000000042, 002, '2020-06-02 01:05:57', 1),
(00000000043, 002, '2020-06-02 01:08:19', 0),
(00000000044, 002, '2020-06-02 01:09:27', 1),
(00000000045, 002, '2020-06-02 09:00:27', 1),
(00000000046, 002, '2020-06-02 09:01:07', 1),
(00000000047, 002, '2020-06-02 09:01:35', 1),
(00000000048, 002, '2020-06-02 09:01:59', 0),
(00000000049, 002, '2020-06-02 09:03:03', 0),
(00000000050, 002, '2020-06-02 09:05:06', 1),
(00000000051, 002, '2020-06-02 09:09:29', 1),
(00000000052, 002, '2020-06-02 09:10:31', 1),
(00000000053, 002, '2020-06-02 09:11:19', 0),
(00000000054, 002, '2020-06-02 09:12:22', 1),
(00000000055, 002, '2020-06-02 09:14:48', 1),
(00000000056, 002, '2020-06-02 09:15:12', 1),
(00000000057, 002, '2020-06-02 09:20:57', 1),
(00000000058, 002, '2020-06-02 09:21:19', 0),
(00000000059, 002, '2020-06-02 09:22:21', 0),
(00000000060, 002, '2020-06-02 09:25:01', 1),
(00000000061, 002, '2020-06-02 09:27:16', 1),
(00000000062, 002, '2020-06-02 09:27:56', 1),
(00000000063, 002, '2020-06-02 09:28:25', 1),
(00000000064, 002, '2020-06-02 09:39:17', 1),
(00000000065, 030, '2020-06-02 13:21:51', 1),
(00000000066, 005, '2020-06-02 14:05:52', 1),
(00000000067, 007, '2020-06-04 00:24:31', 1),
(00000000068, 007, '2020-06-04 00:31:59', 1);

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `agents`
--
ALTER TABLE `agents`
  ADD PRIMARY KEY (`agent_id`),
  ADD UNIQUE KEY `service_number` (`service_number`);

--
-- Indexen voor tabel `login_attempts`
--
ALTER TABLE `login_attempts`
  ADD PRIMARY KEY (`login_attempt_id`),
  ADD KEY `service_number` (`service_number`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `agents`
--
ALTER TABLE `agents`
  MODIFY `agent_id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT voor een tabel `login_attempts`
--
ALTER TABLE `login_attempts`
  MODIFY `login_attempt_id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `login_attempts`
--
ALTER TABLE `login_attempts`
  ADD CONSTRAINT `login_attempts_ibfk_1` FOREIGN KEY (`service_number`) REFERENCES `agents` (`service_number`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
