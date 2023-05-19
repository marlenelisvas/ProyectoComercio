-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-05-2023 a las 09:09:43
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `aeropuerto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajeros`
--

CREATE TABLE `pasajeros` (
  `DNI` varchar(10) NOT NULL,
  `idVuelo` int(10) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Apellido1` varchar(20) NOT NULL,
  `Apellido2` varchar(20) DEFAULT NULL,
  `Nacionalidad` varchar(10) NOT NULL,
  `Edad` int(3) NOT NULL,
  `Sexo` enum('H','M') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vuelos`
--

CREATE TABLE `vuelos` (
  `idVuelo` int(10) NOT NULL,
  `CiudadO` varchar(20) NOT NULL,
  `CiudadD` varchar(20) NOT NULL,
  `Company` varchar(20) NOT NULL,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `Capacidad` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `vuelos`
--

INSERT INTO `vuelos` (`idVuelo`, `CiudadO`, `CiudadD`, `Company`, `Fecha`, `Hora`, `Capacidad`) VALUES
(1, 'Munich', 'Praga', 'Lufthansa', '2017-06-06', '01:33:00', 55),
(2, 'Munich', 'Praga', 'Ryanair', '2017-06-06', '04:45:00', 55),
(3, 'Málaga', 'Stuttgart', 'Norwegian', '2018-03-31', '10:10:00', 100),
(4, 'Stuttgart', 'Málaga', 'Norwegian', '2018-05-15', '09:45:00', 100),
(5, 'Bruselas', 'Paris', 'Ryanair', '2018-03-02', '23:15:00', 83);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pasajeros`
--
ALTER TABLE `pasajeros`
  ADD PRIMARY KEY (`DNI`,`idVuelo`),
  ADD KEY `idVuelo` (`idVuelo`);

--
-- Indices de la tabla `vuelos`
--
ALTER TABLE `vuelos`
  ADD PRIMARY KEY (`idVuelo`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pasajeros`
--
ALTER TABLE `pasajeros`
  ADD CONSTRAINT `pasajeros_ibfk_1` FOREIGN KEY (`idVuelo`) REFERENCES `vuelos` (`idVuelo`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
