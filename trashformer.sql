-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 16, 2026 at 03:35 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `trashformer`
--

-- --------------------------------------------------------

--
-- Table structure for table `hasil_laporan`
--

CREATE TABLE `hasil_laporan` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `file_pdf_url` varchar(255) DEFAULT NULL,
  `keterangan_hasil` text NOT NULL,
  `tanggal_selesai` date NOT NULL,
  `laporan_sampah_id` bigint NOT NULL,
  `petugas_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `laporan_sampah`
--

CREATE TABLE `laporan_sampah` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `deskripsi` text NOT NULL,
  `foto_url` varchar(255) DEFAULT NULL,
  `judul_laporan` varchar(150) NOT NULL,
  `lokasi` varchar(255) NOT NULL,
  `status_laporan` enum('BARU','DIPROSES','DITOLAK','SELESAI') NOT NULL,
  `tanggal_laporan` date NOT NULL,
  `petugas_id` bigint DEFAULT NULL,
  `tipe_sampah_id` bigint DEFAULT NULL,
  `warga_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran_sampah`
--

CREATE TABLE `pembayaran_sampah` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `bukti_pembayaran_url` varchar(255) DEFAULT NULL,
  `catatan_verifikasi` varchar(500) DEFAULT NULL,
  `keterangan` varchar(500) DEFAULT NULL,
  `metode_pembayaran` varchar(50) NOT NULL,
  `nominal` decimal(12,2) NOT NULL,
  `periode_tagihan` varchar(100) NOT NULL,
  `status` enum('DITOLAK','LUNAS','MENUNGGU_KONFIRMASI') NOT NULL,
  `tanggal_pembayaran` date NOT NULL,
  `warga_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `petugas`
--

CREATE TABLE `petugas` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `no_hp` varchar(30) NOT NULL,
  `nomor_petugas` varchar(30) NOT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `petugas`
--

INSERT INTO `petugas` (`id`, `created_at`, `updated_at`, `alamat`, `no_hp`, `nomor_petugas`, `user_id`) VALUES
(1, '2026-05-16 01:22:54.976728', '2026-05-16 01:22:54.976728', 'Pos Kebersihan RW 05', '081298765432', 'PTG-001', 3);

-- --------------------------------------------------------

--
-- Table structure for table `setoran_sampah`
--

CREATE TABLE `setoran_sampah` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `berat_kg` decimal(10,2) NOT NULL,
  `catatan` varchar(500) DEFAULT NULL,
  `status` enum('DITERIMA','DITOLAK','MENUNGGU_VERIFIKASI','SELESAI') NOT NULL,
  `tanggal_setoran` date NOT NULL,
  `petugas_id` bigint NOT NULL,
  `tipe_sampah_id` bigint NOT NULL,
  `warga_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tipe_sampah`
--

CREATE TABLE `tipe_sampah` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `deskripsi` varchar(255) DEFAULT NULL,
  `harga_per_kg` decimal(12,2) NOT NULL,
  `nama_tipe` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tipe_sampah`
--

INSERT INTO `tipe_sampah` (`id`, `created_at`, `updated_at`, `deskripsi`, `harga_per_kg`, `nama_tipe`) VALUES
(1, '2026-05-16 01:22:55.026350', '2026-05-16 01:22:55.026350', 'Kategori sampah Organik untuk proses pengelolaan lingkungan.', '1200.00', 'Organik'),
(2, '2026-05-16 01:22:55.043863', '2026-05-16 01:22:55.043863', 'Kategori sampah Anorganik untuk proses pengelolaan lingkungan.', '1800.00', 'Anorganik'),
(3, '2026-05-16 01:22:55.063381', '2026-05-16 01:22:55.063381', 'Kategori sampah B3 untuk proses pengelolaan lingkungan.', '2500.00', 'B3'),
(4, '2026-05-16 01:22:55.085698', '2026-05-16 01:22:55.085698', 'Kategori sampah Plastik untuk proses pengelolaan lingkungan.', '2200.00', 'Plastik'),
(5, '2026-05-16 01:22:55.108225', '2026-05-16 01:22:55.108225', 'Kategori sampah Kertas untuk proses pengelolaan lingkungan.', '1500.00', 'Kertas'),
(6, '2026-05-16 01:22:55.139545', '2026-05-16 01:22:55.139545', 'Kategori sampah Logam untuk proses pengelolaan lingkungan.', '4500.00', 'Logam'),
(7, '2026-05-16 01:22:55.158074', '2026-05-16 01:22:55.158074', 'Kategori sampah Kaca untuk proses pengelolaan lingkungan.', '1700.00', 'Kaca');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `email` varchar(120) NOT NULL,
  `nama` varchar(120) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','PETUGAS','WARGA') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `created_at`, `updated_at`, `email`, `nama`, `password`, `role`) VALUES
(1, '2026-05-16 01:22:54.299956', '2026-05-16 01:22:54.299956', 'admin@trashformer.local', 'Admin Utama TRASHFORMER', '$2a$10$nwt.GhNqXDfC07tVapts..HxWnRFdMVH8wgmP2VmioVnEE0Sr8UOS', 'ADMIN'),
(2, '2026-05-16 01:22:54.748868', '2026-05-16 01:22:54.748868', 'warga@trashformer.local', 'Warga Contoh', '$2a$10$bk1lSH3DaWnU1/grocIvVuf177MluT2ByAom9MJhXqYy4fcbGTV.K', 'WARGA'),
(3, '2026-05-16 01:22:54.963718', '2026-05-16 01:22:54.963718', 'petugas@trashformer.local', 'Petugas Contoh', '$2a$10$ZXQ/N.1c16F1yCkZ10GRcOODFAIXxx6z7lFuFCfqHUOuApLkD.NYa', 'PETUGAS'),
(4, '2026-05-16 02:03:24.182891', '2026-05-16 02:03:24.182891', 'a@com', 'a', '$2a$10$gL3X3IbmXbfkDLhlUELPXe3iDqMjMbxFyyzkRscwYGnGgDoJqYYNO', 'WARGA');

-- --------------------------------------------------------

--
-- Table structure for table `warga`
--

CREATE TABLE `warga` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `no_hp` varchar(30) NOT NULL,
  `nik` varchar(32) NOT NULL,
  `rt` varchar(10) NOT NULL,
  `rw` varchar(10) NOT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `warga`
--

INSERT INTO `warga` (`id`, `created_at`, `updated_at`, `alamat`, `no_hp`, `nik`, `rt`, `rw`, `user_id`) VALUES
(1, '2026-05-16 01:22:54.764381', '2026-05-16 01:22:54.764381', 'Jl. Hijau Bersih No. 1', '081234567890', '3201000000000001', '01', '05', 2),
(2, '2026-05-16 02:03:24.347310', '2026-05-16 02:03:24.347310', 'a', 'a', 'a', 'a', 'a', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hasil_laporan`
--
ALTER TABLE `hasil_laporan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK9sfic0j478lp69qmnspb1fbrd` (`laporan_sampah_id`),
  ADD KEY `FK5lej1u0pm65qfo84s0ighxcwf` (`petugas_id`);

--
-- Indexes for table `laporan_sampah`
--
ALTER TABLE `laporan_sampah`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKojjmt03cvw8obdm8jq896x2nv` (`petugas_id`),
  ADD KEY `FKe5lul6py5ql84w79734vmvh7i` (`tipe_sampah_id`),
  ADD KEY `FKbnso0c4p2k0jwn5w4cb7po2re` (`warga_id`);

--
-- Indexes for table `pembayaran_sampah`
--
ALTER TABLE `pembayaran_sampah`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs9mi3s4558yc4hnue9gynlqlf` (`warga_id`);

--
-- Indexes for table `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK1738ljw7a8cgcx46pspcbtv9g` (`nomor_petugas`),
  ADD UNIQUE KEY `UK3lgm9lroc06y6ox5go6h79eew` (`user_id`);

--
-- Indexes for table `setoran_sampah`
--
ALTER TABLE `setoran_sampah`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKd8c1gj4oo8s41vl8vt16lmq98` (`petugas_id`),
  ADD KEY `FKkyyshuav3ila0et94h7tyxswb` (`tipe_sampah_id`),
  ADD KEY `FKrwrx7t3c4kxf4d68ucbq1ow29` (`warga_id`);

--
-- Indexes for table `tipe_sampah`
--
ALTER TABLE `tipe_sampah`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKsuux2vqw347vntm7v5kmbs3ev` (`nama_tipe`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Indexes for table `warga`
--
ALTER TABLE `warga`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKsi81mj5eal0rg3fjtt9iyscm` (`nik`),
  ADD UNIQUE KEY `UKo50ihcksv8m7xskacv373v0ns` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `hasil_laporan`
--
ALTER TABLE `hasil_laporan`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `laporan_sampah`
--
ALTER TABLE `laporan_sampah`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `pembayaran_sampah`
--
ALTER TABLE `pembayaran_sampah`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `petugas`
--
ALTER TABLE `petugas`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `setoran_sampah`
--
ALTER TABLE `setoran_sampah`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tipe_sampah`
--
ALTER TABLE `tipe_sampah`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `warga`
--
ALTER TABLE `warga`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hasil_laporan`
--
ALTER TABLE `hasil_laporan`
  ADD CONSTRAINT `FK5lej1u0pm65qfo84s0ighxcwf` FOREIGN KEY (`petugas_id`) REFERENCES `petugas` (`id`),
  ADD CONSTRAINT `FKabixeifxna9a0n2jukrfuvrtc` FOREIGN KEY (`laporan_sampah_id`) REFERENCES `laporan_sampah` (`id`);

--
-- Constraints for table `laporan_sampah`
--
ALTER TABLE `laporan_sampah`
  ADD CONSTRAINT `FKbnso0c4p2k0jwn5w4cb7po2re` FOREIGN KEY (`warga_id`) REFERENCES `warga` (`id`),
  ADD CONSTRAINT `FKe5lul6py5ql84w79734vmvh7i` FOREIGN KEY (`tipe_sampah_id`) REFERENCES `tipe_sampah` (`id`),
  ADD CONSTRAINT `FKojjmt03cvw8obdm8jq896x2nv` FOREIGN KEY (`petugas_id`) REFERENCES `petugas` (`id`);

--
-- Constraints for table `pembayaran_sampah`
--
ALTER TABLE `pembayaran_sampah`
  ADD CONSTRAINT `FKs9mi3s4558yc4hnue9gynlqlf` FOREIGN KEY (`warga_id`) REFERENCES `warga` (`id`);

--
-- Constraints for table `petugas`
--
ALTER TABLE `petugas`
  ADD CONSTRAINT `FKli31h2kq6pvwoqqcj1d7ywkha` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `setoran_sampah`
--
ALTER TABLE `setoran_sampah`
  ADD CONSTRAINT `FKd8c1gj4oo8s41vl8vt16lmq98` FOREIGN KEY (`petugas_id`) REFERENCES `petugas` (`id`),
  ADD CONSTRAINT `FKkyyshuav3ila0et94h7tyxswb` FOREIGN KEY (`tipe_sampah_id`) REFERENCES `tipe_sampah` (`id`),
  ADD CONSTRAINT `FKrwrx7t3c4kxf4d68ucbq1ow29` FOREIGN KEY (`warga_id`) REFERENCES `warga` (`id`);

--
-- Constraints for table `warga`
--
ALTER TABLE `warga`
  ADD CONSTRAINT `FK16rlwj9us5ssap9allljmg2o1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
