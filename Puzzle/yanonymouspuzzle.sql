CREATE DATABASE IF NOT EXISTS `yanonymouspuzzle` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `yanonymouspuzzle`;

DROP TABLE IF EXISTS `edge`;
CREATE TABLE IF NOT EXISTS `edge` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `from_node_id` int(10) unsigned NOT NULL,
  `label` varchar(255) DEFAULT NULL,
  `to_node_id` int(10) unsigned NOT NULL,
  `graph_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

DROP TABLE IF EXISTS `graph`;
CREATE TABLE IF NOT EXISTS `graph` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;

DROP TABLE IF EXISTS `node`;
CREATE TABLE IF NOT EXISTS `node` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_hungarian_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;
