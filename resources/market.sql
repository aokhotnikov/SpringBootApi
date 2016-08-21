-- Хост: localhost
-- Время создания: Авг 16 2016 г., 17:15
-- Версия сервера: 5.5.50-0ubuntu0.14.04.1
-- Версия PHP: 5.5.9-1ubuntu4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `market`
--

-- --------------------------------------------------------

--
-- Структура таблицы `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `parent_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Дамп данных таблицы `category`
--

INSERT INTO `category` (`id`, `name`, `parent_id`) VALUES
(1, 'Телефоны', 0),
(2, 'Бытовая техника', 0),
(3, 'Компьютеры', 0),
(4, 'Инструменты', 0),
(5, 'Смартфоны', 1),
(6, 'Мобильные', 1),
(7, 'Офисные', 1),
(8, 'Холодильники', 2),
(9, 'Пылесосы', 2),
(10, 'Утюги', 2),
(11, 'Ноутбуки', 3),
(12, 'Мониторы', 3),
(13, 'Планшеты', 3),
(14, 'Комплектующие', 3),
(15, 'Дрели', 4),
(16, 'Перфораторы', 4);

-- --------------------------------------------------------

--
-- Структура таблицы `category_characters`
--

CREATE TABLE IF NOT EXISTS `category_characters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_id` int(11) DEFAULT NULL,
  `char_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_characters_1_idx` (`cat_id`),
  KEY `fk_category_characters_2_idx` (`char_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=46 ;

--
-- Дамп данных таблицы `category_characters`
--

INSERT INTO `category_characters` (`id`, `cat_id`, `char_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 5, 1),
(4, 5, 2),
(5, 6, 1),
(6, 6, 2),
(7, 2, 6),
(8, 2, 9),
(9, 8, 6),
(10, 8, 9),
(11, 9, 9),
(12, 5, 3),
(13, 5, 10),
(14, 6, 3),
(15, 6, 10),
(16, 3, 1),
(17, 3, 2),
(18, 3, 3),
(19, 3, 4),
(20, 3, 5),
(21, 3, 7),
(22, 3, 8),
(23, 3, 10),
(24, 11, 1),
(25, 11, 2),
(26, 11, 3),
(27, 11, 4),
(28, 11, 5),
(29, 11, 7),
(30, 11, 8),
(31, 11, 10),
(32, 3, 12),
(33, 11, 12),
(34, 12, 1),
(35, 12, 7),
(36, 12, 9),
(37, 13, 2),
(38, 13, 3),
(39, 13, 4),
(40, 13, 7),
(41, 13, 10),
(42, 13, 12),
(43, 14, 12),
(44, 15, 11),
(45, 16, 11);

-- --------------------------------------------------------

--
-- Структура таблицы `category_tovar`
--

CREATE TABLE IF NOT EXISTS `category_tovar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_id` int(11) NOT NULL,
  `tovar_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_tovar_1_idx` (`cat_id`),
  KEY `fk_category_tovar_2_idx` (`tovar_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Дамп данных таблицы `category_tovar`
--

INSERT INTO `category_tovar` (`id`, `cat_id`, `tovar_id`) VALUES
(1, 3, 1),
(2, 11, 1),
(3, 3, 2),
(4, 11, 2),
(5, 3, 3),
(6, 11, 3),
(7, 2, 4),
(8, 8, 4),
(9, 2, 5),
(10, 8, 5),
(11, 1, 6),
(12, 5, 6),
(13, 3, 7),
(14, 13, 7);

-- --------------------------------------------------------

--
-- Структура таблицы `characters`
--

CREATE TABLE IF NOT EXISTS `characters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Дамп данных таблицы `characters`
--

INSERT INTO `characters` (`id`, `name`) VALUES
(1, 'Экран'),
(2, 'Wi-Fi'),
(3, 'Bluetooth 4.0'),
(4, 'Windows 10'),
(5, 'Multi-Touch'),
(6, 'No Frost'),
(7, 'Время отклика'),
(8, 'DOS'),
(9, 'Энергопотребление'),
(10, 'Bluetooth 3.0'),
(11, 'Реверс'),
(12, 'RAM');

-- --------------------------------------------------------

--
-- Структура таблицы `owner`
--

DROP TABLE IF EXISTS `owner`;
CREATE TABLE IF NOT EXISTS `owner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `owner`
--

INSERT INTO `owner` (`id`, `name`) VALUES
(1, 'Alex'),
(3, 'Andrey'),
(2, 'Ivan');

-- --------------------------------------------------------

--
-- Структура таблицы `tovar`
--

DROP TABLE IF EXISTS `tovar`;
CREATE TABLE IF NOT EXISTS `tovar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `available` tinyint(1) NOT NULL DEFAULT '1',
  `price` decimal(8,2) NOT NULL,
  `garanty` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk1_idx` (`owner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tovar`
--

INSERT INTO `tovar` (`id`, `name`, `available`, `price`, `garanty`, `owner_id`) VALUES
(1, 'HP Stream 11-r001ur (N8J56EA) Purple', 1, '5499.00', 2, 1),
(2, 'Asus EeeBook E202SA (E202SA-FD0016D) White', 1, '7699.00', 2, 3),
(3, 'Asus Transformer Book T100HA 64GB Rouge Pink (T100HA-FU011T)', 0, '9500.00', 1, 2),
(4, 'CANDY CCTOS 502WH ', 1, '3499.00', 3, 2),
(5, 'ELECTROLUX 1601 AOW3', 1, '4599.00', 3, 1),
(6, 'Samsung Galaxy J7 J700H/DS Gold', 1, '2999.00', 2, 3),
(7, 'Lenovo Vibe K5 Plus (A6020a46) Champagne Gold', 1, '6959.00', 0, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `tovar_characters`
--

CREATE TABLE IF NOT EXISTS `tovar_characters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tovar_id` int(11) DEFAULT NULL,
  `val_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tovar_characters_1_idx` (`tovar_id`),
  KEY `fk_tovar_characters_2_idx` (`val_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

--
-- Дамп данных таблицы `tovar_characters`
--

INSERT INTO `tovar_characters` (`id`, `tovar_id`, `val_id`) VALUES
(1, 1, 1),
(2, 1, 3),
(3, 1, 5),
(4, 1, 7),
(5, 1, 9),
(6, 2, 2),
(7, 2, 3),
(8, 2, 5),
(9, 2, 9),
(10, 3, 2),
(11, 3, 16),
(12, 4, 11),
(13, 4, 18),
(14, 5, 12),
(15, 5, 19),
(16, 6, 26),
(17, 7, 2),
(18, 7, 28),
(19, 6, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `tovar_characters_denormal`
--

CREATE TABLE IF NOT EXISTS `tovar_characters_denormal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tovar_id` int(11) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `char_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tovar_characters_1_idx` (`tovar_id`),
  KEY `fk_tovar_characters_denormal_2_idx` (`char_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=19 ;

--
-- Дамп данных таблицы `tovar_characters_denormal`
--

INSERT INTO `tovar_characters_denormal` (`id`, `tovar_id`, `name`, `char_id`) VALUES
(1, 1, 'TFT', 1),
(2, 1, 'Да', 2),
(3, 1, 'Да', 3),
(4, 1, 'Да', 4),
(5, 1, 'Да', 5),
(6, 2, 'IPS', 1),
(7, 2, 'Да', 2),
(8, 2, 'Да', 3),
(9, 2, 'Да', 5),
(10, 3, 'IPS', 1),
(11, 3, 'Да', 8),
(12, 4, 'Да', 6),
(13, 4, 'А', 9),
(14, 5, 'Нет', 6),
(15, 5, 'А+', 9),
(16, 6, '1Гб', 12),
(17, 7, 'IPS', 1),
(18, 7, '4Гб', 12);

-- --------------------------------------------------------

--
-- Структура таблицы `values_characters`
--

CREATE TABLE IF NOT EXISTS `values_characters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `char_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_values_characters_1_idx` (`char_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=30 ;

--
-- Дамп данных таблицы `values_characters`
--

INSERT INTO `values_characters` (`id`, `name`, `char_id`) VALUES
(1, 'TFT', 1),
(2, 'IPS', 1),
(3, 'Да', 2),
(4, 'Нет', 2),
(5, 'Да', 3),
(6, 'Нет', 3),
(7, 'Да', 4),
(8, 'Нет', 4),
(9, 'Да', 5),
(10, 'Нет', 5),
(11, 'Да', 6),
(12, 'Нет', 6),
(13, '2с', 7),
(14, '4с', 7),
(15, '8с', 7),
(16, 'Да', 8),
(17, 'Нет', 8),
(18, 'А', 9),
(19, 'А+', 9),
(20, 'В', 9),
(21, 'В+', 9),
(22, 'Да', 10),
(23, 'Нет', 10),
(24, 'Да', 11),
(25, 'Нет', 11),
(26, '1Гб', 12),
(27, '2Гб', 12),
(28, '4Гб', 12),
(29, '8Гб', 12);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `category_characters`
--
ALTER TABLE `category_characters`
  ADD CONSTRAINT `fk_category_characters_1` FOREIGN KEY (`cat_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_category_characters_2` FOREIGN KEY (`char_id`) REFERENCES `characters` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `category_tovar`
--
ALTER TABLE `category_tovar`
  ADD CONSTRAINT `fk_category_tovar_1` FOREIGN KEY (`cat_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_category_tovar_2` FOREIGN KEY (`tovar_id`) REFERENCES `tovar` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `tovar_characters`
--
ALTER TABLE `tovar_characters`
  ADD CONSTRAINT `fk_tovar_characters_1` FOREIGN KEY (`tovar_id`) REFERENCES `tovar` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tovar_characters_2` FOREIGN KEY (`val_id`) REFERENCES `values_characters` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `tovar_characters_denormal`
--
ALTER TABLE `tovar_characters_denormal`
  ADD CONSTRAINT `fk_tovar_characters_denormal_1` FOREIGN KEY (`tovar_id`) REFERENCES `tovar` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tovar_characters_denormal_2` FOREIGN KEY (`char_id`) REFERENCES `characters` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `values_characters`
--
ALTER TABLE `values_characters`
  ADD CONSTRAINT `fk_values_characters_1` FOREIGN KEY (`char_id`) REFERENCES `characters` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
