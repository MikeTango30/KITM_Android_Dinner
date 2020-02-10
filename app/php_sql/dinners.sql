SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = @;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS `id7924278_pokemon` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode ci;
USE `id7924278_pokemon`;


CREATE TABLE `dinners` (
`id` int(11) NOT NULL,
`created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
`type` text COLLATE utf8_unicode_ci NOT NULL,
`delivery` text COLLATE utf8_unicode_ci NOT NULL,
`price` decimal NOT NULL,
`payment` text COLLATE utf8_unicode_ci NOT NULL
})
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_unicode_ci;

ALTER TABLE `dinners`
ADD PRIMARY KEY(`id`);

ALTER TABLE `dinners`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;