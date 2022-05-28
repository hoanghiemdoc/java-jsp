-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 28, 2022 lúc 10:27 AM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `restaurant_products`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `name`, `createdAt`, `updatedAt`, `status`) VALUES
(1, 'Grill', '2022-05-27 23:45:49', '2022-05-27 23:45:49', 1),
(2, 'Boiled food', '2022-05-27 23:45:49', '2022-05-27 23:45:49', 1),
(3, 'Vegetarian dish', '2022-05-27 23:45:49', '2022-05-27 23:45:49', 1),
(4, 'Drinks\n', '2022-05-27 23:45:49', '2022-05-27 23:45:49', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `categoryId` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `description` varchar(250) NOT NULL,
  `price` double(15,4) NOT NULL,
  `detail` text NOT NULL,
  `image` text NOT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `categoryId`, `name`, `description`, `price`, `detail`, `image`, `createdAt`, `updatedAt`, `status`) VALUES
(1, 2, 'Pizza', 'Lorem ipsum', 100.0000, '<p>Lorem ipsum</p>\r\n', 'https://res.cloudinary.com/bac-ninh/image/upload/v1653706322/mfmwws7txcaytliecbd2.jpg', '2022-05-28 09:49:45', '2022-05-28 09:49:45', 1),
(2, 2, 'Noodles', 'Lorem ipsum', 100.0000, '<p>Lorem ipsum</p>\r\n', 'https://res.cloudinary.com/bac-ninh/image/upload/v1653706271/n15gswzryavol1bx4ksp.jpg', '2022-05-28 09:48:56', '2022-05-28 09:48:56', 1),
(9, 1, 'Burger', 'Good', 124.5600, '<p>Good</p>\r\n\r\n<p>&nbsp;</p>\r\n', 'https://res.cloudinary.com/bac-ninh/image/upload/v1653706221/jatfydh2ubkvxf46yaqc.jpg', '2022-05-28 09:48:15', '2022-05-28 09:48:15', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `foreignKeyProduct` (`categoryId`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `foreignKeyProduct` FOREIGN KEY (`categoryId`) REFERENCES `products` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
