-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 14 jan. 2024 à 19:07
-- Version du serveur : 8.2.0
-- Version de PHP : 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `kassos`
--
CREATE DATABASE IF NOT EXISTS `kassos` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `kassos`;

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `texte` longtext NOT NULL,
  `theme` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'aucun',
  `image` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `score` int DEFAULT NULL,
  `id_utilisateur` int NOT NULL,
  `titre` varchar(200) NOT NULL DEFAULT '"pas de titre ici"',
  PRIMARY KEY (`id`),
  KEY `fk_utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`id`, `texte`, `theme`, `image`, `date`, `score`, `id_utilisateur`, `titre`) VALUES
(23, 'JE SUIS CONETENT QUE MON APPLICATION COMMENCE A ETRE FONCTIONELLE', '', '1703182868356_photo_2023-11-04_09-37-17.jpg', '2023-12-21 19:21:08', 3, 8, 'JE SUIS NOUVEAU'),
(24, 'VOUS NE ME TROUVEZ PAS BEAU?', 'Monde', '1703182986939_photo_2023-09-30_19-05-54.jpg', '2023-12-21 19:23:07', 7, 8, 'CLASSE'),
(25, 'rien juste kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm llllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllo oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo', '', '1703229620513_geek-wallpapers-stugon.com-2.jpg', '2023-12-22 08:20:21', 7, 8, ' test de longeur'),
(26, 'JV , lire ok', 'divertissement', '1703237958170_gojo-nawpic-16-e1627160527336.jpg', '2023-12-22 10:43:16', 1, 9, 'Loisirs '),
(27, 'llllllllllllllllllllllllllllllloooooooooooooooooooooooooooooooooooooooooooooooooooooooooollllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll', '', NULL, '2023-12-22 23:25:57', 5, 9, 'le test sans images'),
(28, 'gffffffffffffff', '', '1703607636934_profile.png', '2023-12-26 17:20:37', 0, 8, 'pour verifier'),
(29, 'hj', '', NULL, '2023-12-26 17:38:02', 1, 8, 'ok'),
(30, 'putain c\'est chaud', '', '1703610722575_bb-16.png', '2023-12-26 18:12:03', 0, 8, 'le test de validation aujourdhui'),
(31, '**Dimitri Tapamo, le soldat homosexuel et alcoolique**\r\n\r\nDimitri Tapamo est un soldat camerounais qui a fait les gros titres ces derniers jours pour son orientation sexuelle et son addiction à l\'alcool.\r\n\r\nTapamo est né dans une petite ville du Cameroun. Il a toujours su qu\'il était différent des autres garçons, mais il a caché son homosexualité pendant de nombreuses années. Il a finalement décidé de sortir du placard lorsqu\'il a rejoint l\'armée.\r\n\r\nTapamo a dit qu\'il a été accueilli à bras ouverts par ses camarades de l\'armée. Il a dit qu\'ils l\'ont accepté pour ce qu\'il était.\r\n\r\nCependant, Tapamo a également dit qu\'il a été victime de discrimination et de harcèlement. Il a dit qu\'il a été insulté et menacé par certains de ses supérieurs.\r\n\r\nTapamo a également déclaré qu\'il est alcoolique. Il a dit qu\'il boit pour faire face au stress et à la discrimination qu\'il subit.\r\n\r\nL\'histoire de Dimitri Tapamo est un rappel que l\'homosexualité est encore un sujet tabou dans de nombreux pays, y compris le Cameroun. Elle montre également que les personnes LGBTQ+ peuvent être victimes de discrimination et de violence, même dans les forces armées.\r\n\r\n**L\'homosexualité dans l\'armée camerounaise**\r\n\r\nL\'homosexualité est illégale au Cameroun. Les personnes LGBTQ+ qui sont prises en flagrant délit d\'homosexualité peuvent être condamnées à une peine de prison pouvant aller jusqu\'à cinq ans.\r\n\r\nL\'armée camerounaise n\'a pas de politique officielle en matière d\'orientation sexuelle. Cependant, il est largement admis que les personnes LGBTQ+ ne sont pas les bienvenues dans l\'armée.\r\n\r\n**Les défis auxquels sont confrontés les soldats LGBTQ+**\r\n\r\nLes soldats LGBTQ+ qui servent dans l\'armée camerounaise sont confrontés à de nombreux défis. Ils peuvent être victimes de discrimination, de harcèlement et de violence.\r\n\r\nIls peuvent également avoir du mal à trouver un soutien de leurs collègues et de leurs supérieurs.\r\n\r\n**La nécessité d\'un changement**\r\n\r\nL\'histoire de Dimitri Tapamo montre qu\'il est urgent de changer les choses pour les soldats LGBTQ+ au Cameroun. Ils méritent d\'être traités avec respect et dignité, quel que soit leur orientation sexuelle.\r\n\r\nLe gouvernement camerounais devrait abroger les lois qui criminalisent l\'homosexualité. L\'armée camerounaise devrait également adopter une politique officielle qui protège les droits des soldats LGBTQ+.', '', '1703882365600_profile3.jpg', '2023-12-29 21:39:26', 1, 10, 'JE SUIS UN SOLDAT '),
(32, '**Être une femme**\r\n\r\nÊtre une femme, c\'est être une personne. C\'est avoir un corps, une âme et un esprit. C\'est avoir des expériences, des émotions et des opinions. C\'est être unique et spéciale.\r\n\r\nÊtre une femme, c\'est aussi être confrontée à des défis. Les femmes sont souvent victimes de discrimination, de violence et d\'inégalités. Elles doivent souvent lutter pour leurs droits et leur place dans la société.\r\n\r\nCependant, les femmes sont également fortes et résilientes. Elles ont surmonté de nombreux obstacles au fil de l\'histoire et continuent de se battre pour un monde meilleur.\r\n\r\n**Les défis auxquels sont confrontées les femmes**\r\n\r\nLes femmes sont confrontées à de nombreux défis dans le monde entier. Ces défis peuvent être liés à leur sexe, à leur race, à leur religion ou à leur classe sociale.\r\n\r\nVoici quelques-uns des défis auxquels sont confrontées les femmes :\r\n\r\n* **La discrimination** : Les femmes sont souvent victimes de discrimination dans le monde du travail, dans l\'éducation et dans la vie publique. Elles sont moins payées que les hommes pour le même travail, elles ont moins de chances d\'être promues et elles sont sous-représentées dans les postes de pouvoir.\r\n[Image of Discrimination contre les femmes]\r\n* **La violence** : Les femmes sont souvent victimes de violence, notamment de violence domestique, de viol et d\'agression sexuelle. La violence contre les femmes est un problème mondial qui a des conséquences dévastatrices sur les femmes et leurs familles.\r\n[Image of Violence contre les femmes]\r\n* **L\'inégalité** : Les femmes sont souvent victimes d\'inégalités, notamment en matière d\'accès à l\'éducation, aux soins de santé et aux ressources économiques. Ces inégalités limitent les possibilités des femmes et entravent leur développement.\r\n[Image of Inégalités entre les femmes]\r\n\r\n**Les réalisations des femmes**\r\n\r\nMalgré les défis auxquels elles sont confrontées, les femmes ont accompli de grandes choses. Elles ont été des chefs d\'État, des scientifiques, des artistes, des écrivaines et des athlètes de renommée mondiale.\r\n\r\nVoici quelques-unes des réalisations des femmes :\r\n\r\n* **Les femmes ont obtenu le droit de vote dans de nombreux pays.**\r\n[Image of Les femmes ont obtenu le droit de vote]\r\n* **Les femmes ont joué un rôle important dans la lutte pour l\'égalité des droits.**\r\n[Image of Les femmes ont joué un rôle important dans la lutte pour l\'égalité des droits]\r\n* **Les femmes ont contribué de manière significative à la science, à la technologie, aux arts et à la littérature.**\r\n\r\n\r\n**L\'avenir des femmes**\r\n\r\nL\'avenir des femmes est prometteur. Les femmes sont de plus en plus éduquées, engagées et influentes. Elles continuent de se battre pour leurs droits et leur place dans la société, et elles auront un impact significatif sur le monde dans les années à venir.\r\n\r\nLes femmes sont des leaders, des innovateurs et des changeurs de monde. Elles sont la force motrice du progrès et elles façonnent l\'avenir de notre planète.', 'Monde', '1703882607374_profile1.jpg', '2023-12-29 21:43:27', 0, 11, 'JE SUIS UNE FEMME'),
(33, '**Être un renard malicieux**\r\n\r\nLes renards sont souvent considérés comme des animaux malicieux. Ils sont connus pour leur intelligence, leur ruse et leur capacité à tromper les autres.\r\n\r\n**Les traits de caractère d\'un renard malicieux**\r\n\r\nLes renards malicieux sont généralement intelligents et rusés. Ils sont capables de planifier et de mettre en œuvre des stratagèmes complexes. Ils sont également très bons à lire les gens et à identifier leurs faiblesses.\r\n\r\nLes renards malicieux sont également souvent audacieux et aventureux. Ils ne craignent pas de prendre des risques et ils sont toujours à la recherche de nouvelles opportunités. Ils peuvent également être très manipulateurs et ils n\'hésiteront pas à utiliser les autres pour atteindre leurs objectifs.\r\n\r\n**Les avantages d\'être un renard malicieux**\r\n\r\nÊtre un renard malicieux peut avoir de nombreux avantages. Les renards malicieux sont souvent très réussis dans la vie. Ils peuvent réussir dans les affaires, la politique ou toute autre carrière qui exige de l\'intelligence, de la ruse et de la capacité à manipuler les gens.\r\n\r\nLes renards malicieux sont également souvent très amusants à côtoyer. Ils sont pleins d\'esprit et ils aiment plaisanter et jouer. Ils peuvent également être très charmants et ils savent comment gagner les gens à leur cause.\r\n\r\n**Les inconvénients d\'être un renard malicieux**\r\n\r\nÊtre un renard malicieux peut également avoir des inconvénients. Les renards malicieux peuvent être considérés comme des manipulateurs et des égoïstes. Ils peuvent également être cruels et ils n\'hésiteront pas à blesser les autres pour atteindre leurs objectifs.\r\n\r\nLes renards malicieux peuvent également être très solitaires. Ils ont du mal à faire confiance aux autres et ils ont souvent peur d\'être trahis.\r\n\r\n**Conclusion**\r\n\r\nÊtre un renard malicieux peut être une chose positive ou négative. Cela dépend de la façon dont on utilise ses compétences. Si on utilise ses compétences pour le bien, on peut être un leader efficace et un ami fidèle. Cependant, si on utilise ses compétences pour le mal, on peut être un manipulateur et un tyran.', '', NULL, '2023-12-29 21:45:47', 1, 12, 'JE SUIS UN RENARD'),
(35, '**Être fort**\r\n\r\nÊtre fort, c\'est être capable de surmonter les obstacles. C\'est être capable de faire face aux défis. C\'est être capable de se battre pour ce en quoi on croit.\r\n\r\n**Les différentes formes de force**\r\n\r\nLa force peut prendre de nombreuses formes. Elle peut être physique, mentale, émotionnelle ou spirituelle.\r\n\r\n**La force physique**\r\n\r\nLa force physique est la capacité de soulever des objets lourds, de courir vite ou de se défendre contre une attaque.\r\n\r\n**La force mentale**\r\n\r\nLa force mentale est la capacité de rester calme sous pression, de surmonter les obstacles et de se fixer des objectifs.\r\n\r\n**La force émotionnelle**\r\n\r\nLa force émotionnelle est la capacité de gérer ses émotions, de faire face au stress et de surmonter les traumatismes.\r\n\r\n**La force spirituelle**\r\n\r\nLa force spirituelle est la capacité de trouver un sens à la vie, de se connecter à quelque chose de plus grand que soi et de trouver la paix intérieure.\r\n\r\n**Les avantages d\'être fort**\r\n\r\nÊtre fort présente de nombreux avantages. Cela peut nous permettre d\'atteindre nos objectifs, de surmonter les obstacles et de vivre une vie plus épanouie.\r\n\r\n**Comment devenir fort**\r\n\r\nIl n\'y a pas de recette miracle pour devenir fort. Cependant, il existe quelques choses que vous pouvez faire pour développer votre force :\r\n\r\n* **Fixez-vous des objectifs**\r\n\r\nLes objectifs vous donneront un but et une motivation pour vous améliorer.\r\n\r\n* **Faites de l\'exercice régulièrement**\r\n\r\nL\'exercice est le meilleur moyen de développer votre force physique.\r\n\r\n* **Apprenez à gérer votre stress**\r\n\r\nLe stress peut affaiblir votre force mentale et émotionnelle.\r\n\r\n* **Trouvez quelque chose qui vous donne un sens**\r\n\r\nAvoir un but dans la vie peut vous donner la force de surmonter les obstacles.\r\n\r\n**Conclusion**\r\n\r\nÊtre fort, c\'est une qualité précieuse. Cela peut nous aider à vivre une vie plus épanouie et à avoir un impact positif sur le monde.', '', '1703883428639_gojo-nawpic-16-e1627160527336.jpg', '2024-01-01 21:35:12', 3, 15, 'ETRE FORT ok'),
(36, 'je suis hereux pour cette nouvelle annee', 'divertissement', '1704028434913__0b9c4198-8bc1-468d-ad8b-401e4677a27a.jpg', '2023-12-31 14:13:55', 16, 15, 'MON PREMIER ARTICLE'),
(38, 'oooooiiiiiiooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo', '', '1704287736742__2932a9fc-c3c7-42f0-927c-874365292227-removebg-preview.png', '2024-01-03 14:15:37', 8, 16, 'okkkkkkkkkkkkkkkkk'),
(39, 'On va faire un test', 'aucun', '1704707635737_front-medium-3480613795.jpg', '2024-01-08 10:53:56', 0, 18, 'On va faire un test');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `texte` varchar(255) NOT NULL,
  `id_utilisateur` int DEFAULT NULL,
  `id_article` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_utilisateur` (`id_utilisateur`),
  KEY `fk_article` (`id_article`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id`, `date`, `texte`, `id_utilisateur`, `id_article`) VALUES
(1, '2023-12-22 09:39:47', 'le plus fort ', 9, 26),
(2, '2023-12-22 09:45:45', 'non', 9, 24),
(4, '2023-12-23 23:33:45', 'tu ne mets pas de tof', 9, 27),
(5, '2023-12-24 14:39:43', 'ok', 8, 24),
(6, '2023-12-25 10:08:50', 'ok', 9, 25),
(7, '2023-12-29 21:06:15', 'naaaadaaa\r\n', 14, 24),
(8, '2023-12-31 13:16:03', 'j\'aime cet article', 15, 35),
(9, '2023-12-31 13:33:48', 'j\'aime beaucoup', 9, 36),
(10, '2023-12-31 13:39:05', 'ok', 15, 36),
(11, '2024-01-03 13:26:03', 'oki', 16, 38),
(13, '2024-01-08 09:58:07', 'oui', 18, 25);

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `utilisateur_id` int NOT NULL,
  `article_id` int NOT NULL,
  `message` varchar(255) NOT NULL,
  `acteur_id` int NOT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `lu` tinyint(1) NOT NULL DEFAULT '0',
  `date_lec` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `utilisateur_id` (`utilisateur_id`),
  KEY `article_id` (`article_id`),
  KEY `acteur_id` (`acteur_id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `notification`
--

INSERT INTO `notification` (`id`, `utilisateur_id`, `article_id`, `message`, `acteur_id`, `date`, `lu`, `date_lec`) VALUES
(1, 9, 27, ' A COMMENTE VOTRE PUBLICATION ', 9, '2023-12-24 00:33:45', 1, '2024-01-04 17:50:18'),
(2, 8, 24, ' A COMMENTE VOTRE PUBLICATION ', 8, '2023-12-24 15:39:43', 0, NULL),
(3, 8, 25, ' A COMMENTE VOTRE PUBLICATION ', 9, '2023-12-25 11:08:50', 0, NULL),
(4, 8, 24, ' A COMMENTE VOTRE PUBLICATION ', 14, '2023-12-29 22:06:15', 0, NULL),
(5, 14, 35, ' A COMMENTE VOTRE PUBLICATION ', 15, '2023-12-31 14:16:03', 0, NULL),
(6, 15, 36, ' A COMMENTE VOTRE PUBLICATION ', 9, '2023-12-31 14:33:48', 0, NULL),
(7, 15, 36, ' A COMMENTE VOTRE PUBLICATION ', 15, '2023-12-31 14:39:05', 0, NULL),
(8, 16, 38, ' A COMMENTE VOTRE PUBLICATION ', 16, '2024-01-03 14:26:03', 1, '2024-01-03 14:27:09'),
(9, 16, 37, ' A SUPPRIME VOTRE PUBLICATION ', 14, '2024-01-04 17:06:41', 0, NULL),
(10, 16, 37, ' A SUPPRIME VOTRE PUBLICATION ', 14, '2024-01-04 17:06:58', 0, NULL),
(11, 16, 37, ' A SUPPRIME VOTRE PUBLICATION ', 9, '2024-01-04 17:08:52', 0, NULL),
(12, 15, 36, ' A SUPPRIME VOTRE PUBLICATION ', 9, '2024-01-04 17:14:39', 0, NULL),
(13, 13, 34, ' A SUPPRIME VOTRE PUBLICATION ', 9, '2024-01-04 17:16:51', 0, NULL),
(14, 16, 37, ' A COMMENTE VOTRE PUBLICATION ', 9, '2024-01-04 17:17:19', 0, NULL),
(15, 16, 37, ' A SUPPRIME VOTRE PUBLICATION ', 9, '2024-01-04 17:17:24', 0, NULL),
(16, 8, 29, ' A SUPPRIME VOTRE PUBLICATION ', 9, '2024-01-04 17:18:33', 0, NULL),
(17, 16, 37, ' A SUPPRIME VOTRE PUBLICATION ', 9, '2024-01-04 17:48:35', 0, NULL),
(18, 8, 25, ' A COMMENTE VOTRE PUBLICATION ', 18, '2024-01-08 10:58:07', 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `libelle` smallint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `libelle`) VALUES
(11, 0),
(13, 0),
(3, 0),
(4, 0),
(5, 0),
(6, 0),
(7, 1),
(10, 1),
(9, 1),
(12, 0),
(14, 0),
(15, 0),
(17, 1),
(18, 0),
(19, 1),
(20, 1),
(23, 0),
(22, 1);

-- --------------------------------------------------------

--
-- Structure de la table `story`
--

DROP TABLE IF EXISTS `story`;
CREATE TABLE IF NOT EXISTS `story` (
  `id` int NOT NULL AUTO_INCREMENT,
  `utilisateur_id` int NOT NULL,
  `image` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `utilisateur_id` (`utilisateur_id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `theme`
--

DROP TABLE IF EXISTS `theme`;
CREATE TABLE IF NOT EXISTS `theme` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `libelle` varchar(250) NOT NULL DEFAULT '''aucun''',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `theme`
--

INSERT INTO `theme` (`ID`, `libelle`) VALUES
(1, 'Aucun'),
(2, 'Monde'),
(3, 'Ecologie');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(100) NOT NULL,
  `pseudo` varchar(100) DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `photo` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `ville` varchar(100) DEFAULT NULL,
  `Actif` tinyint(1) NOT NULL DEFAULT '1',
  `role_id` int DEFAULT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT 'mot_de_passe',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `email`, `nom`, `pseudo`, `date`, `photo`, `description`, `telephone`, `ville`, `Actif`, `role_id`, `password`) VALUES
(6, 'donfackarthur650@gmail.com', 'DONFACK', NULL, '2023-12-18 00:00:00', '1702939988229_gojo-nawpic-16-e1627160527336.jpg', NULL, NULL, NULL, 1, 6, '$2a$10$t.cKX8tbTrgibBydBT/f7OMoh/7RbTb6HnRX2Er8m17OZAW7vKwM.'),
(8, 'donfackarthur750@gmail.com', 'TIGER FOX ', 'THE BEST', '2023-12-21 00:00:00', '1703182808545_photo_2023-09-30_19-05-54.jpg', 'JE SUIS LE SOLDAT LE PLUS PUISSANT DU BATAILLON', '658866639', 'yaoundé', 1, 10, '$2a$10$dFuk5iu5HZW1bix0ge0vt.6I/RL4.bJBJskQnPQG7Z109B3A7tU7S'),
(9, 'adafrancine2004@gmail.com', 'Ada', 'Coupi', '2023-12-22 00:00:00', '1703237751658_R.jfif', 'Ah', '693169206', 'Yaounde', 1, 12, '$2a$10$JQH4ihb0V7JNMRs.WgXe9Opodjw4caOFYLvMA/dd/mKdqNP.nob2K'),
(10, 'donfackarthur350@gmail.com', 'DIMITRI TAPAMO', NULL, '2023-12-29 00:00:00', '1703882095597_profile4.jpg', NULL, NULL, NULL, 1, 13, '$2a$10$dIsYaViQq4De9g6S/ojE5.lIC/vYtHjRNG8VygBOT42XWX3w9ZwPu'),
(11, 'donfackarthur150@gmail.com', 'mefoungue Nancy', NULL, '2023-12-29 00:00:00', '1703882509206_profile1.jpg', NULL, NULL, NULL, 1, 14, '$2a$10$9d9rMSvA8e0Vy3mS0Yqge.eXrUG48qrLA.mCami8MdKFkX/deWHSq'),
(12, 'donfackarthur130@gmail.com', 'FOX ARTHUR', NULL, '2023-12-29 00:00:00', '1703882683066_profile5.jpg', NULL, NULL, NULL, 1, 15, '$2a$10$NwiLa6tcybKD8VTyc2KVuuHpSFhMoRMN99Yv724fQOzx2Om6iclWu'),
(14, 'donfackarthur170@gmail.com', 'DEMENOU ', NULL, '2023-12-29 00:00:00', '1703883314035_profile3.jpg', NULL, NULL, NULL, 1, 17, '$2a$10$F2SzHeQssxrOXPSqRNnGCeyUpMTj2jj2KgFJIUMnkXfIzgds8kfHe'),
(15, 'Tiwa@gmail.com', 'TIWA', 'THE BEST', '2023-12-31 00:00:00', '1704028170262__43e38c81-f2df-49dc-bb33-ed63ef6cd2c6.jpg', 'JE SUIS UN ETUDIANT DE LSI 3 INFO', '699999999', 'YAOUNDE', 1, 19, '$2a$10$.7iFMrxHyNixzmQj7AS/gOw12dD6XKgWSdRkrETc9hzSUWF72552.'),
(16, 'fox@gmail.com', 'TIGER ARTHUR', 'THE BEST', '2024-01-03 00:00:00', '1704285813374__2932a9fc-c3c7-42f0-927c-874365292227-modified.jpg', '', '', '', 1, 22, '$2a$10$6qN1eO8DpWsNCGrbbU2gvON/S719u0pVxRiWFLgcRBtTV01F8VVSa'),
(18, 'noutchataudrey@gmail.com', 'noutchat', NULL, '2024-01-08 00:00:00', '1704707335298_818PdDviTuL.jpg', NULL, NULL, NULL, 1, 23, '$2a$10$ie6vCO4QxIkrJ.41UnUFD.NeZkSmOYZPpn4tGcEjRL.7/gUO7Ryx6');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `fk_id_utilisateur` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_utilisateur` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `fk_article` FOREIGN KEY (`id_article`) REFERENCES `article` (`id`),
  ADD CONSTRAINT `fk_commentaire_utilisateur` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`);

DELIMITER $$
--
-- Évènements
--
DROP EVENT IF EXISTS `Supprimer_Notifications`$$
CREATE DEFINER=`root`@`localhost` EVENT `Supprimer_Notifications` ON SCHEDULE EVERY 1 HOUR STARTS '2023-12-23 18:01:06' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM notification WHERE lu = TRUE AND date_lecture < NOW() - INTERVAL 24 HOUR$$

DROP EVENT IF EXISTS `Supprimer_Stories`$$
CREATE DEFINER=`root`@`localhost` EVENT `Supprimer_Stories` ON SCHEDULE EVERY 1 HOUR STARTS '2023-12-24 12:52:48' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM story WHERE date < NOW() - INTERVAL 24 HOUR$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
