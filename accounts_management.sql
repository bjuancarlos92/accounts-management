-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-08-2022 a las 04:29:27
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `accounts_management`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `client_id` varchar(6) DEFAULT NULL,
  `password` varchar(4) NOT NULL,
  `status` varchar(5) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`client_id`, `password`, `status`, `id`) VALUES
('jose', '1234', 'True', 1),
('mari', '5678', 'True', 2),
('juan', '1245', 'True', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `id_account` int(11) NOT NULL,
  `account_number` varchar(10) NOT NULL,
  `account_type` varchar(20) NOT NULL,
  `initial_balance` int(11) NOT NULL,
  `status` varchar(5) NOT NULL,
  `id_cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`id_account`, `account_number`, `account_type`, `initial_balance`, `status`, `id_cliente`) VALUES
(4, '478758', 'Ahorro', 2000, 'True', 1),
(6, '225487', 'Corriente', 100, 'True', 2),
(8, '495878', 'Ahorro', 0, 'True', 3),
(10, '585545', 'Corriente', 1000, 'True', 1),
(12, '496825', 'Ahorros', 540, 'True', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta_movimiento`
--

CREATE TABLE `cuenta_movimiento` (
  `id_movement` int(11) NOT NULL,
  `id_account` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cuenta_movimiento`
--

INSERT INTO `cuenta_movimiento` (`id_movement`, `id_account`) VALUES
(5, 4),
(7, 6),
(9, 8),
(11, 10),
(13, 12),
(14, 4),
(15, 6),
(16, 6),
(17, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(18);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimiento`
--

CREATE TABLE `movimiento` (
  `id_movement` int(11) NOT NULL,
  `balance` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `movement_type` varchar(20) NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `movimiento`
--

INSERT INTO `movimiento` (`id_movement`, `balance`, `date`, `movement_type`, `value`) VALUES
(5, 2000, '2022-08-07 18:19:36', 'Credito', 2000),
(7, 100, '2022-08-07 18:19:43', 'Credito', 100),
(9, 0, '2022-08-07 18:19:50', 'Credito', 0),
(11, 1000, '2022-08-07 18:19:58', 'Credito', 1000),
(13, 540, '2022-08-07 18:27:35', 'Credito', 540),
(14, 1425, '2022-08-07 13:19:21', 'Debito', -575),
(15, 700, '2022-08-07 13:25:15', 'Credito', 600),
(16, 1300, '2022-08-07 13:25:15', 'Credito', 600),
(17, 0, '2022-08-07 13:37:58', 'Debito', -540);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(11) NOT NULL,
  `address` varchar(50) NOT NULL,
  `age` varchar(2) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `identification` varchar(10) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `phone` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `address`, `age`, `gender`, `identification`, `name`, `phone`) VALUES
(1, 'Otavalo sn y principal', NULL, NULL, NULL, 'Jose Lema', '098254785'),
(2, 'Amazonas y  NNUU', NULL, NULL, NULL, 'Marianela Montalvo', '097548965'),
(3, '13 junio y Equinoccial', NULL, NULL, NULL, 'Juan Osorio', '098874587');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`id_account`),
  ADD KEY `FKmkmi3xf6wrp0y1mdn8nm4weim` (`id_cliente`);

--
-- Indices de la tabla `cuenta_movimiento`
--
ALTER TABLE `cuenta_movimiento`
  ADD PRIMARY KEY (`id_movement`,`id_account`),
  ADD KEY `FK1yqoo5xqd1a6ls84lbvailfrn` (`id_account`);

--
-- Indices de la tabla `movimiento`
--
ALTER TABLE `movimiento`
  ADD PRIMARY KEY (`id_movement`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_3lxckclx5dj676liq2e52nplf` (`identification`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `FKkpvkbjg32bso6riqge70hwcel` FOREIGN KEY (`id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `FKmkmi3xf6wrp0y1mdn8nm4weim` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `cuenta_movimiento`
--
ALTER TABLE `cuenta_movimiento`
  ADD CONSTRAINT `FK1yqoo5xqd1a6ls84lbvailfrn` FOREIGN KEY (`id_account`) REFERENCES `cuenta` (`id_account`),
  ADD CONSTRAINT `FK84px0i9afmg21rwpqkjwcddsn` FOREIGN KEY (`id_movement`) REFERENCES `movimiento` (`id_movement`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
