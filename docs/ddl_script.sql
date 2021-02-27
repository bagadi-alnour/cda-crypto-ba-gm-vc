-- ---
-- Globals
-- ---
-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;
-- ---
-- Table 'crypto_currency'
-- 
-- ---
DROP TABLE IF EXISTS `crypto_currency`;
CREATE TABLE `crypto_currency` (
  `id_crypto` INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  `name` VARCHAR NOT NULL DEFAULT 'NULL',
  `symbol` VARCHAR(3) NOT NULL,
  `current_price` DECIMAL NOT NULL DEFAULT NULL,
  `image_url` VARCHAR NULL DEFAULT NULL,
  `last_updated` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id_crypto`)
);
-- ---
-- Table 'crypto_wallet'
-- 
-- ---
DROP TABLE IF EXISTS `crypto_wallet`;
CREATE TABLE `crypto_wallet` (
  `id_wallet` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `id_crypto` INTEGER NOT NULL DEFAULT NULL,
  `purchase_price` DECIMAL NOT NULL DEFAULT NULL,
  `purchase_date` DATETIME NOT NULL DEFAULT 'NULL',
  `quantity` DECIMAL NOT NULL DEFAULT NULL,
  PRIMARY KEY (`id_wallet`)
);
-- ---
-- Foreign Keys 
-- ---
ALTER TABLE `crypto_wallet` ADD FOREIGN KEY (id_crypto) REFERENCES `crypto_currency` (`id_crypto`);
-- ---
-- Table Properties
-- ---
-- ALTER TABLE `crypto_currency` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `crypto_wallet` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ---
-- Test Data
-- ---
-- INSERT INTO `crypto_currency` (`id_crypto`,`name`,`symbol`,`current_price`,`image_url`,`last_updated`) VALUES
-- ('','','','','','');
-- INSERT INTO `crypto_wallet` (`id_wallet`,`id_crypto`,`purchase_price`,`purchase_date`,`quantity`) VALUES
-- ('','','','','');