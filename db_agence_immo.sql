-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 02 déc. 2025 à 22:23
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db_agence_immo`
--

-- --------------------------------------------------------

--
-- Structure de la table `bien`
--

CREATE TABLE `bien` (
  `id_bien` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `ville` varchar(50) NOT NULL,
  `surface` decimal(10,2) DEFAULT NULL,
  `prixMensuel` decimal(10,2) NOT NULL,
  `id_prop` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `bien`
--

INSERT INTO `bien` (`id_bien`, `type`, `ville`, `surface`, `prixMensuel`, `id_prop`) VALUES
(6, 'appartement', 'ifrane', 200.00, 500.00, 17),
(8, 'Villa', 'Maknasse', 500.00, 20600.00, NULL),
(9, 'villa', 'casa', 400.00, 1200.00, NULL),
(11, 'appatement', 'Taja', 100.00, 500.00, NULL),
(12, 'villa', 'safi', 567.00, 577.00, NULL),
(13, 'Villa', 'Rabat', 300.00, 15000.00, NULL),
(14, 'appatement', 'ifrane', 300.00, 800.00, 17),
(15, 'villa', 'Marrakech', 300.00, 2000.00, 17);

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

CREATE TABLE `location` (
  `id_location` int(11) NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date DEFAULT NULL,
  `loyer` decimal(10,2) NOT NULL,
  `id_bien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `location`
--

INSERT INTO `location` (`id_location`, `dateDebut`, `dateFin`, `loyer`, `id_bien`) VALUES
(22, '2025-10-01', '2025-11-30', 16000.00, 8),
(23, '2025-08-01', '2025-11-30', 16000.00, 6),
(24, '2025-08-01', '2025-11-30', 22000.00, 11),
(25, '2025-10-01', '2025-12-30', 2000.00, 11),
(26, '2025-07-01', '2025-11-30', 16000.00, 14);

-- --------------------------------------------------------

--
-- Structure de la table `proprietaire`
--

CREATE TABLE `proprietaire` (
  `id_prop` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `contact` varchar(50) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `proprietaire`
--

INSERT INTO `proprietaire` (`id_prop`, `nom`, `contact`, `adresse`) VALUES
(8, 'omar alami', '0666775533', 'Taja'),
(16, 'Hayat alami', '0788556677', 'Safi'),
(17, 'jad lakhal', '0654810009', 'Marrakech'),
(18, 'samira imal', '0865472322', 'taja'),
(19, 'samir jad', '076543757688', 'sale'),
(20, 'salma alami', '0765432456', 'casa'),
(21, 'Salma salami', 'salmasal@gmail.com', 'Casa Maarif'),
(23, 'hiba alami', '0765432456', 'benimalal'),
(24, 'Salma salami jadi', 'salmasal@gmail.com', 'Casa Maarif'),
(25, 'salma lakhal', '076543234567', 'Safi'),
(26, 'JIhan nadin', '07556677898', 'Taja');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `email`) VALUES
(1, 'admin', '1234', 'lakhalsalma18@gmail.com');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bien`
--
ALTER TABLE `bien`
  ADD PRIMARY KEY (`id_bien`),
  ADD KEY `id_prop` (`id_prop`);

--
-- Index pour la table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id_location`),
  ADD KEY `id_bien` (`id_bien`);

--
-- Index pour la table `proprietaire`
--
ALTER TABLE `proprietaire`
  ADD PRIMARY KEY (`id_prop`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bien`
--
ALTER TABLE `bien`
  MODIFY `id_bien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `location`
--
ALTER TABLE `location`
  MODIFY `id_location` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT pour la table `proprietaire`
--
ALTER TABLE `proprietaire`
  MODIFY `id_prop` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bien`
--
ALTER TABLE `bien`
  ADD CONSTRAINT `bien_ibfk_1` FOREIGN KEY (`id_prop`) REFERENCES `proprietaire` (`id_prop`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `location_ibfk_1` FOREIGN KEY (`id_bien`) REFERENCES `bien` (`id_bien`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
