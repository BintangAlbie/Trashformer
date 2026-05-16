-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 16, 2026 at 03:32 AM
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
-- Database: `db_pangkalan_gas`
--
CREATE DATABASE IF NOT EXISTS `db_pangkalan_gas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `db_pangkalan_gas`;

-- --------------------------------------------------------

--
-- Table structure for table `stoks`
--

CREATE TABLE `stoks` (
  `id` int NOT NULL,
  `jenis_gas` varchar(255) DEFAULT NULL,
  `jumlah` int DEFAULT NULL,
  `harga_satuan` decimal(10,2) DEFAULT NULL,
  `satuan` varchar(255) DEFAULT NULL,
  `tanggal_update` datetime DEFAULT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `stoks`
--

INSERT INTO `stoks` (`id`, `jenis_gas`, `jumlah`, `harga_satuan`, `satuan`, `tanggal_update`, `createdAt`, `updatedAt`) VALUES
(1, 'Elpiji 12 kg', 50, '100000.00', 'tabung', '2026-05-11 22:09:18', '2026-05-11 22:09:18', '2026-05-11 22:09:18'),
(2, 'Elpiji 3 kg', 100, '25000.00', 'tabung', '2026-05-11 22:09:18', '2026-05-11 22:09:18', '2026-05-11 22:09:18'),
(3, 'Elpiji 5 kg', 75, '40000.00', 'tabung', '2026-05-11 22:09:18', '2026-05-11 22:09:18', '2026-05-11 22:09:18');

-- --------------------------------------------------------

--
-- Table structure for table `transaksis`
--

CREATE TABLE `transaksis` (
  `id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `jumlah_beli` int DEFAULT NULL,
  `harga_total` decimal(12,2) DEFAULT NULL,
  `metode` enum('tunai','kredit','transfer') DEFAULT NULL,
  `status` enum('pending','ACC','selesai','batal') DEFAULT NULL,
  `tanggal_transaksi` datetime DEFAULT NULL,
  `tanda_tangan` longtext,
  `catatan` text,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transaksis`
--

INSERT INTO `transaksis` (`id`, `user_id`, `jumlah_beli`, `harga_total`, `metode`, `status`, `tanggal_transaksi`, `tanda_tangan`, `catatan`, `createdAt`, `updatedAt`) VALUES
(1, 1, 2, '200000.00', 'tunai', 'selesai', '2026-05-11 22:09:18', NULL, 'Pengiriman ke rumah', '2026-05-11 22:09:18', '2026-05-11 22:09:18'),
(2, 1, 1, '100000.00', 'transfer', 'selesai', '2026-05-10 22:09:18', NULL, 'Transfer BCA', '2026-05-11 22:09:18', '2026-05-11 22:09:18'),
(3, 1, 3, '120000.00', 'kredit', 'selesai', '2026-05-09 22:09:18', NULL, 'Bayar minggu depan', '2026-05-11 22:09:18', '2026-05-11 22:09:18');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `no_ktp` varchar(255) DEFAULT NULL,
  `role` enum('pangkalan','pembeli') DEFAULT NULL,
  `sub_role` enum('rumahtangga','usaha_mikro','none') DEFAULT NULL,
  `alamat` text,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `no_ktp`, `role`, `sub_role`, `alamat`, `createdAt`, `updatedAt`) VALUES
(1, 'pangkalan01', 'password123', '1234567890123456', 'pangkalan', 'none', 'Jl. Merdeka No. 123, Kota Bandung', '2026-05-11 22:09:18', '2026-05-11 22:09:18'),
(2, 'pembeli01', 'pass123', '1111111111111111', 'pembeli', 'rumahtangga', 'Jl. Sudirman No. 45, Bandung', '2026-05-11 22:09:18', '2026-05-11 22:09:18'),
(3, 'pembeli02', 'pass123', '2222222222222222', 'pembeli', 'usaha_mikro', 'Jl. Ahmad Yani No. 78, Bandung', '2026-05-11 22:09:18', '2026-05-11 22:09:18'),
(4, 'pembeli03', 'pass123', '3333333333333333', 'pembeli', 'rumahtangga', 'Jl. Gatot Subroto No. 90, Bandung', '2026-05-11 22:09:18', '2026-05-11 22:09:18');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `stoks`
--
ALTER TABLE `stoks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaksis`
--
ALTER TABLE `transaksis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `stoks`
--
ALTER TABLE `stoks`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transaksis`
--
ALTER TABLE `transaksis`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksis`
--
ALTER TABLE `transaksis`
  ADD CONSTRAINT `transaksis_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE;
--
-- Database: `demo_db`
--
CREATE DATABASE IF NOT EXISTS `demo_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `demo_db`;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `nama` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;
--
-- Database: `demo_db1`
--
CREATE DATABASE IF NOT EXISTS `demo_db1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `demo_db1`;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `nama` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nama`) VALUES
(1, 'aa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Database: `kasir_db`
--
CREATE DATABASE IF NOT EXISTS `kasir_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `kasir_db`;

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id` bigint UNSIGNED NOT NULL,
  `kode_barang` varchar(20) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `stok` int DEFAULT '0',
  `stok_minimum` int DEFAULT '5',
  `harga_beli` decimal(15,2) NOT NULL,
  `harga_jual` decimal(15,2) NOT NULL,
  `satuan` varchar(20) DEFAULT 'pcs',
  `deskripsi` text,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id`, `kode_barang`, `nama_barang`, `kategori`, `stok`, `stok_minimum`, `harga_beli`, `harga_jual`, `satuan`, `deskripsi`, `created_at`, `updated_at`) VALUES
(1, 'BRG0001', 'Laptop ASUS', 'Elektronik', 10, 2, '6000000.00', '7000000.00', 'unit', NULL, NULL, NULL),
(2, 'BRG0002', 'Mouse Logitech', 'Elektronik', 50, 10, '50000.00', '80000.00', 'pcs', NULL, NULL, NULL),
(3, 'BRG0003', 'Keyboard Gaming', 'Elektronik', 20, 5, '200000.00', '300000.00', 'pcs', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `detail_penjualan`
--

CREATE TABLE `detail_penjualan` (
  `id` bigint UNSIGNED NOT NULL,
  `penjualan_id` bigint UNSIGNED NOT NULL,
  `barang_id` bigint UNSIGNED NOT NULL,
  `jumlah` int NOT NULL,
  `harga_satuan` decimal(15,2) NOT NULL,
  `subtotal` decimal(15,2) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `id` bigint UNSIGNED NOT NULL,
  `no_transaksi` varchar(50) NOT NULL,
  `nama_pembeli` varchar(100) NOT NULL,
  `total_harga` decimal(15,2) DEFAULT '0.00',
  `bayar` decimal(15,2) DEFAULT '0.00',
  `kembalian` decimal(15,2) DEFAULT '0.00',
  `status` enum('lunas','belum_lunas') DEFAULT 'lunas',
  `catatan` text,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kode_barang` (`kode_barang`),
  ADD KEY `idx_barang_kode` (`kode_barang`);

--
-- Indexes for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_penjualan` (`penjualan_id`),
  ADD KEY `fk_barang` (`barang_id`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `no_transaksi` (`no_transaksi`),
  ADD KEY `idx_penjualan_no` (`no_transaksi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD CONSTRAINT `fk_barang` FOREIGN KEY (`barang_id`) REFERENCES `barang` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_penjualan` FOREIGN KEY (`penjualan_id`) REFERENCES `penjualan` (`id`) ON DELETE CASCADE;
--
-- Database: `pangkalan_gas`
--
CREATE DATABASE IF NOT EXISTS `pangkalan_gas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `pangkalan_gas`;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int NOT NULL,
  `orderNumber` varchar(255) NOT NULL,
  `userId` int NOT NULL,
  `totalAmount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `status` enum('pending','confirmed','completed','cancelled') DEFAULT 'pending',
  `paymentStatus` enum('unpaid','paid','partial') DEFAULT 'unpaid',
  `deliveryDate` datetime DEFAULT NULL,
  `notes` text,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` int NOT NULL,
  `orderId` int NOT NULL,
  `productId` int NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `price` decimal(10,2) NOT NULL,
  `stock` int NOT NULL DEFAULT '0',
  `unit` varchar(255) NOT NULL DEFAULT 'kg',
  `category` varchar(255) NOT NULL DEFAULT 'LPG',
  `image` varchar(255) DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT '1',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` int NOT NULL,
  `orderId` int NOT NULL,
  `userId` int NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `paymentMethod` enum('cash','bank_transfer','card') NOT NULL,
  `transactionDate` datetime DEFAULT NULL,
  `referenceNumber` varchar(255) DEFAULT NULL,
  `notes` text,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` text,
  `role` enum('customer','admin') DEFAULT 'customer',
  `isActive` tinyint(1) DEFAULT '1',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `phone`, `address`, `role`, `isActive`, `createdAt`, `updatedAt`) VALUES
(1, 'a', 'b@gamil.com', '$2b$10$rPeGRtfGDBiKM16lSHgbv./08p8d14h0wc8RFIwMM4h8/P0EUieiS', '2', '2', 'customer', 1, '2026-05-12 00:51:04', '2026-05-12 00:51:04'),
(2, '1', '1@com', '$2b$10$ZAk252RTQIfo8JGqtx4iie7HQTv91N1WRjKw07G6s8/aHK4KQzWIK', '1', '1', 'customer', 1, '2026-05-12 00:57:54', '2026-05-12 00:57:54'),
(4, '2', '2@com', '2', '2', '2', 'admin', 1, '2026-05-12 02:34:51', '2026-05-12 02:34:51');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `orderNumber` (`orderNumber`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `orderId` (`orderId`),
  ADD KEY `productId` (`productId`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `orderId` (`orderId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
--
-- Database: `trashformer`
--
CREATE DATABASE IF NOT EXISTS `trashformer` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `trashformer`;

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
--
-- Database: `trashformer_db`
--
CREATE DATABASE IF NOT EXISTS `trashformer_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `trashformer_db`;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
