-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 08, 2016 at 02:25 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gamebox`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(15) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `highscore`
--

CREATE TABLE `highscore` (
  `INPUTID` int(11) NOT NULL,
  `USERNAME` varchar(30) NOT NULL,
  `SCORE` int(11) NOT NULL,
  `GAME` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `highscore`
--

INSERT INTO `highscore` (`INPUTID`, `USERNAME`, `SCORE`, `GAME`) VALUES
(1, 'Test', 150, 'Test'),
(2, 'Domaredd', 45, 'Tetris'),
(4, 'Domared', 45, 'Tetris'),
(5, 'doom', 24, 'Break Breaker'),
(6, 'doom', 25, 'Break Breaker'),
(7, 'jote', 24, 'Break Breaker'),
(8, 'jote', 24, 'Pacman'),
(9, 'jote', 4, 'Snake'),
(10, 'jote', 1, 'Snake'),
(11, 'jote', 1, 'Snake'),
(12, 'jote', 1, 'Snake'),
(13, 'jote', 41, 'Pacman'),
(14, 'jote', 0, 'Snake'),
(15, 'deren', 0, 'Snake'),
(16, 'reinhart', 24, 'Break Breaker'),
(17, 'reinhart', 0, 'Angka Berapa?'),
(18, 'reinhart', 110, 'Angka Berapa?'),
(19, 'reinhart', 29, 'Break Breaker'),
(20, 'reinhart', 24, 'Break Breaker'),
(21, 'reinhart', 31, 'Pacman'),
(22, 'reinhart', 2, 'Snake'),
(23, 'reinhart', 25, 'Break Breaker'),
(24, 'reinhart', 0, 'Angka Berapa?'),
(25, 'reinhart', 100, 'Angka Berapa?');

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE `player` (
  `username` varchar(15) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (`username`, `password`) VALUES
('deren', 'deren'),
('doom', 'ganteng'),
('ewe', ''),
('jote', 'jonesss'),
('jotecool', 'cool'),
('joted', 'jonesss'),
('re', 're'),
('reinhart', 'bagus');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `highscore`
--
ALTER TABLE `highscore`
  ADD PRIMARY KEY (`INPUTID`);

--
-- Indexes for table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `highscore`
--
ALTER TABLE `highscore`
  MODIFY `INPUTID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
