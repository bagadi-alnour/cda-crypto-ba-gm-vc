use crypto_db;
CREATE TABLE `cryptoCurrency` (
    `idCrypto` INTEGER NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL DEFAULT 'N/D',
    `symbol` VARCHAR(6) NOT NULL,
    `currentPrice` double(30,9) NOT NULL,
    `imageUrl` TINYTEXT,
    `lastUpdated` DATETIME NULL default CURRENT_TIMESTAMP,
    PRIMARY KEY (`idCrypto`)
);

CREATE TABLE `cryptoWallet` (
    `idWallet` INTEGER not NULL AUTO_INCREMENT,
    `idCrypto` INTEGER NOT NULL,
    `purchasePrice` double(20,19) not NULL,
    `quantity` double(20,19) NOT NULL,
    `purchaseDate` DATETIME NOT NULL default CURRENT_TIMESTAMP,
    PRIMARY KEY (`idWallet`),
    FOREIGN KEY (idCrypto) REFERENCES `cryptoCurrency` (`idCrypto`)
);
