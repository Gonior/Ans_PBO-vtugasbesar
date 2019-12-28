-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 28, 2019 at 06:53 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbtugasbesar`
--
CREATE DATABASE IF NOT EXISTS `dbtugasbesar` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `dbtugasbesar`;

-- --------------------------------------------------------

--
-- Table structure for table `anime`
--

DROP TABLE IF EXISTS `anime`;
CREATE TABLE IF NOT EXISTS `anime` (
  `id_anime` int(11) NOT NULL AUTO_INCREMENT,
  `judul_anime` varchar(100) NOT NULL,
  `jumlah_episode` int(11) NOT NULL,
  `rating_anime` double NOT NULL,
  `gambar_anime` varchar(225) NOT NULL,
  `sinopsis_anime` varchar(225) NOT NULL,
  `durasi_anime` varchar(20) NOT NULL,
  `status` varchar(15) NOT NULL,
  `genre_anime` varchar(255) NOT NULL,
  PRIMARY KEY (`id_anime`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `anime`
--

INSERT INTO `anime` (`id_anime`, `judul_anime`, `jumlah_episode`, `rating_anime`, `gambar_anime`, `sinopsis_anime`, `durasi_anime`, `status`, `genre_anime`) VALUES
(1, 'Kimi No Na Wa', 1, 9.12, 'Kimi No Na Wa.jpg', 'Menceritakan seorang gadis bernama Mitsuha Miyamizu yang tinggal di pedesaan dengan kehidupan rutinitas sebagai seorang pelajar, ia bisa menenun dan juga membuat sake untuk dipersembahkan kepada dewa.', '1 jam 46 menit', 'Completed', 'Romance, Supernatural, School, Drama'),
(2, 'Kaguya-sama wa Kokurasetai Tensai-tachi no Renai Zunousen', 12, 8.42, 'Kaguya-sama wa Kokurasetai Tensai-tachi no Renai Zunousen.jpg', 'Sang ketua OSIS Miyuki Shirogane dan wakil ketua OSIS Kaguya Shinomiya yang terlihat sempurna dimata para siswa, namun mereka berdua memiliki harga diri yang tinggi. Sehingga mereka sangat sulit untuk menyatakan perasaan', '24 menit', 'completed', 'Comedy, Psychological, Romance, School, Seinen');

-- --------------------------------------------------------

--
-- Table structure for table `streaming`
--

DROP TABLE IF EXISTS `streaming`;
CREATE TABLE IF NOT EXISTS `streaming` (
  `id_streaming` int(11) NOT NULL AUTO_INCREMENT,
  `id_anime` int(11) NOT NULL,
  `episode_streaming` int(11) NOT NULL,
  `url_streaming` text NOT NULL,
  PRIMARY KEY (`id_anime`),
  KEY `id_streaming` (`id_streaming`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `streaming`
--

INSERT INTO `streaming` (`id_streaming`, `id_anime`, `episode_streaming`, `url_streaming`) VALUES
(1, 1, 1, 'https://r5---sn-4g5e6nze.googlevideo.com/videoplayback?expire=1577454562&ei=YpsFXrbbGofEuAXQoKPQAw&ip=2a04:3543:1000:2310:30da:13ff:fead:798f&id=3843bfe85ee1494e&itag=18&source=blogger&mm=31&mn=sn-4g5e6nze&ms=au&mv=m&mvi=4&pl=64&susc=bl&mime=video/mp4&dur=1420.178&lmt=1577316292669729&mt=1577425660&sparams=expire,ei,ip,id,itag,source,susc,mime,dur,lmt&sig=ALgxI2wwRAIgD2MaYfvENBGVEVHZ_avYASD9uCHIq6SNFq3wmeuWeUMCIGdGCjg3fXVX6Z-FxLrfoqIBrYTGKFgLj2cmvopE0pBh&lsparams=mm,mn,ms,mv,mvi,pl&lsig=AHylml4wRQIgJT2dYd5fuDMf8GULVMDEkLw3p7ed1luI-45hL1wTMQICIQC7JBwxIUj7NSk6kSgrVczO5PHU9tIxNAJwNE4nazUY_w%3D%3D'),
(2, 2, 1, 'https://www.youtube.com/');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `nama_user` varchar(255) NOT NULL,
  `username_user` varchar(255) NOT NULL,
  `password_user` varchar(255) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nama_user`, `username_user`, `password_user`) VALUES
(6, 'admin', 'admin', 'admin');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `streaming`
--
ALTER TABLE `streaming`
  ADD CONSTRAINT `streaming_ibfk_1` FOREIGN KEY (`id_anime`) REFERENCES `anime` (`id_anime`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
