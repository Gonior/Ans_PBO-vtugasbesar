-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 03, 2020 at 07:00 PM
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

CREATE TABLE `anime` (
  `id_anime` int(11) NOT NULL,
  `judul_anime` varchar(100) NOT NULL,
  `jumlah_episode` int(11) NOT NULL,
  `rating_anime` double NOT NULL,
  `gambar_anime` varchar(225) NOT NULL,
  `sinopsis_anime` varchar(225) NOT NULL,
  `durasi_anime` varchar(20) NOT NULL,
  `status` varchar(15) NOT NULL,
  `genre_anime` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `anime`
--

INSERT INTO `anime` (`id_anime`, `judul_anime`, `jumlah_episode`, `rating_anime`, `gambar_anime`, `sinopsis_anime`, `durasi_anime`, `status`, `genre_anime`) VALUES
(1, 'Kimi No Na Wa', 1, 9.12, 'Kimi No Na Wa.jpg', 'Menceritakan seorang gadis bernama Mitsuha Miyamizu yang tinggal di pedesaan dengan kehidupan rutinitas sebagai seorang pelajar, ia bisa menenun dan juga membuat sake untuk dipersembahkan kepada dewa.', '1 jam 46 menit', 'Completed', 'Romance, Supernatural, School, Drama'),
(2, 'Kaguya-sama: Love Is War', 12, 8.42, 'Kaguya-sama wa Kokurasetai Tensai-tachi no Renai Zunousen.jpg', 'Sang ketua OSIS Miyuki Shirogane dan wakil ketua OSIS Kaguya Shinomiya yang terlihat sempurna dimata para siswa, namun mereka berdua memiliki harga diri yang tinggi. Sehingga mereka sangat sulit untuk menyatakan perasaan', '24 menit', 'completed', 'Comedy, Psychological, Romance, School, Seinen'),
(3, 'Charlotte', 13, 7.91, 'Charlotte.jpg', 'Dunia dimana beberapa perempuan dan laki-laki memiliki kemampuan khusus, mereka mendapatkan kekuatan dari suatu kejadian yang pernah terjadi.', '24 menit', 'Completed', 'Drama, School, Super Power');

-- --------------------------------------------------------

--
-- Table structure for table `streaming`
--

CREATE TABLE `streaming` (
  `id_streaming` int(11) NOT NULL,
  `id_anime` int(11) NOT NULL,
  `episode_streaming` int(11) NOT NULL,
  `url_streaming` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `streaming`
--

INSERT INTO `streaming` (`id_streaming`, `id_anime`, `episode_streaming`, `url_streaming`) VALUES
(1, 1, 1, 'https://drive.google.com/file/d/0ByWK-tMARRCGYWdiSnRpNHR5YWs/preview'),
(2, 2, 2, 'https://drive.google.com/file/d/1P9zLK-V3G_o3t1shcvub9FQ_7y6D7MJn/preview'),
(3, 2, 3, 'https://drive.google.com/file/d/1X4zpubk3uwR5TVOvnAaJb5mNNXTUbPKO/preview'),
(4, 2, 4, 'https://drive.google.com/file/d/1FGNVzTx9uyQiNjlQPodPbEtX6FBG9My1/preview'),
(5, 2, 5, 'https://drive.google.com/file/d/1bWKDtJTaO_Ofb1mODSMD_rY1bjx24Xyc/preview'),
(6, 2, 6, 'https://drive.google.com/file/d/1llvYKYeNYnJmNrgzl9Ds3XJ9HXK8upSG/preview'),
(7, 2, 7, 'https://drive.google.com/file/d/1b1uX-mX3QsCthjhM4WwPM7nRImgy9By0/preview'),
(8, 2, 8, 'https://drive.google.com/file/d/1y3WlSOaVsiUIe6LWKakW9ITU_SB_ZuVy/preview'),
(9, 2, 9, 'https://drive.google.com/file/d/1FJpQXgBuHV8iGrOxfcZ8IKxaTE0qlL41/preview'),
(10, 2, 10, 'https://drive.google.com/file/d/1AZcNkaFkjkb3HCn5uK5ihhVTvRDP6P3I/preview'),
(11, 3, 1, 'https://drive.google.com/file/d/112WLixC0qVMwHflteeWZrv04_oht8FUb/preview'),
(12, 3, 2, 'https://drive.google.com/file/d/1x-T8lAV1yRig9zp4f6fOZjEGgniKITkC/preview'),
(13, 3, 3, 'https://drive.google.com/file/d/1-YyTx1jXA5S2j7srisjhbEdp8f5Nwy8A/preview'),
(14, 3, 4, 'https://drive.google.com/file/d/1_t_adPD2tO9bmnREbOhHQQG7qfL17rVv/preview'),
(15, 3, 5, 'https://drive.google.com/file/d/1TcQBJuGTY0V56ajcX91iJxQAU9nvE-69/preview'),
(16, 3, 6, 'https://drive.google.com/file/d/1zx5KOQUo5vWhqg6DmNpE97W6xquWMfzg/preview'),
(17, 3, 7, 'https://drive.google.com/file/d/17zMdmOr8N0ynpkY1ucn1U-8RvHt1EmD4/preview'),
(18, 3, 8, 'https://drive.google.com/file/d/1ChsN8KAUNhNGAL95PZCl_gSWuIp1yxGs/preview'),
(19, 3, 9, 'https://drive.google.com/file/d/1-vRpreCNXFCkRabCPOuXw6EniMtysdoa/preview'),
(20, 3, 10, 'https://drive.google.com/file/d/1H6fnibrO36iHO8UYCFivCYFttk50BLNQ/preview'),
(21, 3, 11, 'https://drive.google.com/file/d/15UuXwqpe9p7zXKgwJSkaCqC1zDsk9HuD/preview'),
(22, 3, 12, 'https://drive.google.com/file/d/1Rq9VrLmybwkMEgKyY9-Z4LZuGRXZH_vn/preview'),
(23, 3, 13, 'https://drive.google.com/file/d/13QZnOLaPr2dH3GLPfj5G6RX_46id_hwm/preview'),
(24, 2, 11, 'https://drive.google.com/file/d/1DhLudoJ63X1ae0VIkzBxAUvtYVX7SZqZ/preview'),
(25, 2, 12, 'https://drive.google.com/file/d/1JFswj2bVFtV2teY37ex8cGnDE19u6lhz/preview'),
(0, 2, 1, 'https://drive.google.com/open?id=13wHwlscfTp64drisAgwzdy78uAXpqR4a');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE 'user' (
  `id_user` int(11) NOT NULL,
  `nama_user` varchar(255) NOT NULL,
  `username_user` varchar(255) NOT NULL,
  `password_user` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nama_user`, `username_user`, `password_user`) VALUES
(6, 'admin', 'admin', 'admin'),
(11, 'Dedi cahya', 'gonior', 'bebaslah123'),
(12, 'Luchter', 'omaewa', '111111'),
(13, 'Fitriani', 'fitri', 'fitri'),
(14, 'asda', 'sndkjanskj', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anime`
--
ALTER TABLE `anime`
  ADD PRIMARY KEY (`id_anime`);

--
-- Indexes for table `streaming`
--
ALTER TABLE `streaming`
  ADD KEY `id_streaming` (`id_streaming`),
  ADD KEY `id_anime` (`id_anime`) USING BTREE;

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anime`
--
ALTER TABLE `anime`
  MODIFY `id_anime` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `streaming`
--
ALTER TABLE `streaming`
  MODIFY `id_streaming` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

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
