-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Апр 15 2019 г., 12:24
-- Версия сервера: 5.7.23-24
-- Версия PHP: 5.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `u0539414_efendiapp`
--

-- --------------------------------------------------------

--
-- Структура таблицы `tbl_admin`
--

CREATE TABLE IF NOT EXISTS `tbl_admin` (
  `id` int(11) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` text NOT NULL,
  `email` varchar(100) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `user_role` enum('100','101','102') NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tbl_admin`
--

INSERT INTO `tbl_admin` (`id`, `username`, `password`, `email`, `full_name`, `user_role`) VALUES
(1, 'admin', 'd82494f05d6917ba02f7aaa29689ccb444bb73f20380876cb05d1f37537b7892', 'erikdeliyev@gmail.com', 'Administrator', '100');

-- --------------------------------------------------------

--
-- Структура таблицы `tbl_category`
--

CREATE TABLE IF NOT EXISTS `tbl_category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `category_image` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tbl_category`
--

INSERT INTO `tbl_category` (`category_id`, `category_name`, `category_image`) VALUES
(1, 'Акция!!!', '2083-2019-03-27.png'),
(2, 'Посуда', '9738-2019-03-27.png'),
(3, 'Турки/Джезве', '3393-2019-03-27.png'),
(4, 'Сладости', '2415-2019-03-27.png'),
(5, 'Арома Кофе', '6719-2019-03-27.png'),
(8, 'Кофе по-турецки', '1485-2019-03-27.png');

-- --------------------------------------------------------

--
-- Структура таблицы `tbl_config`
--

CREATE TABLE IF NOT EXISTS `tbl_config` (
  `id` int(11) NOT NULL,
  `currency_symbol` varchar(10) NOT NULL,
  `currency_id` int(3) NOT NULL,
  `tax` varchar(45) NOT NULL DEFAULT '0',
  `app_fcm_key` varchar(1000) NOT NULL DEFAULT '0',
  `package_name` varchar(500) NOT NULL DEFAULT 'com.app.ecommerce',
  `onesignal_app_id` varchar(500) NOT NULL DEFAULT '0',
  `onesignal_rest_api_key` varchar(500) NOT NULL DEFAULT '0',
  `providers` varchar(45) NOT NULL DEFAULT 'onesignal',
  `protocol_type` varchar(10) NOT NULL DEFAULT 'http://'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tbl_config`
--

INSERT INTO `tbl_config` (`id`, `currency_symbol`, `currency_id`, `tax`, `app_fcm_key`, `package_name`, `onesignal_app_id`, `onesignal_rest_api_key`, `providers`, `protocol_type`) VALUES
(1, '$', 121, '0', 'AAAAZwVVV6A:APA91bGOPS0Pnpxjuh_8iuE94AN2WwhjYGK_VDEBzjwYw8N6CWf9PGsniAc3pTmMgWJDBAVMeoqvqu1NBEOYWhAGR1DGZsCBf5P-6nBh-NXsOypzNxrGT74IOuTcZbYSLq5xVf1dd1Fl', 'com.app.ecommerce', 'c2bf748e-90a9-435c-b46a-ed40403542c4', 'NjllNjA3OTgtYWFmYS00YWNmLWI3N2MtNDJlMzY5ZTQ0OThm', 'onesignal', 'http://');

-- --------------------------------------------------------

--
-- Структура таблицы `tbl_currency`
--

CREATE TABLE IF NOT EXISTS `tbl_currency` (
  `currency_id` bigint(20) NOT NULL,
  `currency_code` varchar(3) CHARACTER SET utf8mb4 NOT NULL,
  `currency_name` varchar(100) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tbl_currency`
--

INSERT INTO `tbl_currency` (`currency_id`, `currency_code`, `currency_name`) VALUES
(1, 'AFA', 'Afghanistan afghani'),
(2, 'ALL', 'Albanian lek'),
(3, 'DZD', 'Algerian dinar'),
(4, 'AOR', 'Angolan kwanza reajustado'),
(5, 'ARS', 'Argentine peso'),
(6, 'AMD', 'Armenian dram'),
(7, 'AWG', 'Aruban guilder'),
(8, 'AUD', 'Australian dollar'),
(9, 'AZN', 'Azerbaijanian new manat'),
(10, 'BSD', 'Bahamian dollar'),
(11, 'BHD', 'Bahraini dinar'),
(12, 'BDT', 'Bangladeshi taka'),
(13, 'BBD', 'Barbados dollar'),
(14, 'BYN', 'Belarusian ruble'),
(15, 'BZD', 'Belize dollar'),
(16, 'BMD', 'Bermudian dollar'),
(17, 'BTN', 'Bhutan ngultrum'),
(18, 'BOB', 'Bolivian boliviano'),
(19, 'BWP', 'Botswana pula'),
(20, 'BRL', 'Brazilian real'),
(21, 'GBP', 'British pound'),
(22, 'BND', 'Brunei dollar'),
(23, 'BGN', 'Bulgarian lev'),
(24, 'BIF', 'Burundi franc'),
(25, 'KHR', 'Cambodian riel'),
(26, 'CAD', 'Canadian dollar'),
(27, 'CVE', 'Cape Verde escudo'),
(28, 'KYD', 'Cayman Islands dollar'),
(29, 'XOF', 'CFA franc BCEAO'),
(30, 'XAF', 'CFA franc BEAC'),
(31, 'XPF', 'CFP franc'),
(32, 'CLP', 'Chilean peso'),
(33, 'CNY', 'Chinese yuan renminbi'),
(34, 'COP', 'Colombian peso'),
(35, 'KMF', 'Comoros franc'),
(36, 'CDF', 'Congolese franc'),
(37, 'CRC', 'Costa Rican colon'),
(38, 'HRK', 'Croatian kuna'),
(39, 'CUP', 'Cuban peso'),
(40, 'CZK', 'Czech koruna'),
(41, 'DKK', 'Danish krone'),
(42, 'DJF', 'Djibouti franc'),
(43, 'DOP', 'Dominican peso'),
(44, 'XCD', 'East Caribbean dollar'),
(45, 'EGP', 'Egyptian pound'),
(46, 'SVC', 'El Salvador colon'),
(47, 'ERN', 'Eritrean nakfa'),
(48, 'EEK', 'Estonian kroon'),
(49, 'ETB', 'Ethiopian birr'),
(50, 'EUR', 'EU euro'),
(51, 'FKP', 'Falkland Islands pound'),
(52, 'FJD', 'Fiji dollar'),
(53, 'GMD', 'Gambian dalasi'),
(54, 'GEL', 'Georgian lari'),
(55, 'GHS', 'Ghanaian new cedi'),
(56, 'GIP', 'Gibraltar pound'),
(57, 'XAU', 'Gold (ounce)'),
(58, 'XFO', 'Gold franc'),
(59, 'GTQ', 'Guatemalan quetzal'),
(60, 'GNF', 'Guinean franc'),
(61, 'GYD', 'Guyana dollar'),
(62, 'HTG', 'Haitian gourde'),
(63, 'HNL', 'Honduran lempira'),
(64, 'HKD', 'Hong Kong SAR dollar'),
(65, 'HUF', 'Hungarian forint'),
(66, 'ISK', 'Icelandic krona'),
(67, 'XDR', 'IMF special drawing right'),
(68, 'INR', 'Indian rupee'),
(69, 'IDR', 'Indonesian rupiah'),
(70, 'IRR', 'Iranian rial'),
(71, 'IQD', 'Iraqi dinar'),
(72, 'ILS', 'Israeli new shekel'),
(73, 'JMD', 'Jamaican dollar'),
(74, 'JPY', 'Japanese yen'),
(75, 'JOD', 'Jordanian dinar'),
(76, 'KZT', 'Kazakh tenge'),
(77, 'KES', 'Kenyan shilling'),
(78, 'KWD', 'Kuwaiti dinar'),
(79, 'KGS', 'Kyrgyz som'),
(80, 'LAK', 'Lao kip'),
(81, 'LVL', 'Latvian lats'),
(82, 'LBP', 'Lebanese pound'),
(83, 'LSL', 'Lesotho loti'),
(84, 'LRD', 'Liberian dollar'),
(85, 'LYD', 'Libyan dinar'),
(86, 'LTL', 'Lithuanian litas'),
(87, 'MOP', 'Macao SAR pataca'),
(88, 'MKD', 'Macedonian denar'),
(89, 'MGA', 'Malagasy ariary'),
(90, 'MWK', 'Malawi kwacha'),
(91, 'MYR', 'Malaysian ringgit'),
(92, 'MVR', 'Maldivian rufiyaa'),
(93, 'MRO', 'Mauritanian ouguiya'),
(94, 'MUR', 'Mauritius rupee'),
(95, 'MXN', 'Mexican peso'),
(96, 'MDL', 'Moldovan leu'),
(97, 'MNT', 'Mongolian tugrik'),
(98, 'MAD', 'Moroccan dirham'),
(99, 'MZN', 'Mozambique new metical'),
(100, 'MMK', 'Myanmar kyat'),
(101, 'NAD', 'Namibian dollar'),
(102, 'NPR', 'Nepalese rupee'),
(103, 'ANG', 'Netherlands Antillian guilder'),
(104, 'NZD', 'New Zealand dollar'),
(105, 'NIO', 'Nicaraguan cordoba oro'),
(106, 'NGN', 'Nigerian naira'),
(107, 'KPW', 'North Korean won'),
(108, 'NOK', 'Norwegian krone'),
(109, 'OMR', 'Omani rial'),
(110, 'PKR', 'Pakistani rupee'),
(111, 'XPD', 'Palladium (ounce)'),
(112, 'PAB', 'Panamanian balboa'),
(113, 'PGK', 'Papua New Guinea kina'),
(114, 'PYG', 'Paraguayan guarani'),
(115, 'PEN', 'Peruvian nuevo sol'),
(116, 'PHP', 'Philippine peso'),
(117, 'XPT', 'Platinum (ounce)'),
(118, 'PLN', 'Polish zloty'),
(119, 'QAR', 'Qatari rial'),
(120, 'RON', 'Romanian new leu'),
(121, 'руб', 'Russian ruble'),
(122, 'RWF', 'Rwandan franc'),
(123, 'SHP', 'Saint Helena pound'),
(124, 'WST', 'Samoan tala'),
(125, 'STD', 'Sao Tome and Principe dobra'),
(126, 'SAR', 'Saudi riyal'),
(127, 'RSD', 'Serbian dinar'),
(128, 'SCR', 'Seychelles rupee'),
(129, 'SLL', 'Sierra Leone leone'),
(130, 'XAG', 'Silver (ounce)'),
(131, 'SGD', 'Singapore dollar'),
(132, 'SBD', 'Solomon Islands dollar'),
(133, 'SOS', 'Somali shilling'),
(134, 'ZAR', 'South African rand'),
(135, 'KRW', 'South Korean won'),
(136, 'LKR', 'Sri Lanka rupee'),
(137, 'SDG', 'Sudanese pound'),
(138, 'SRD', 'Suriname dollar'),
(139, 'SZL', 'Swaziland lilangeni'),
(140, 'SEK', 'Swedish krona'),
(141, 'CHF', 'Swiss franc'),
(142, 'SYP', 'Syrian pound'),
(143, 'TWD', 'Taiwan New dollar'),
(144, 'TJS', 'Tajik somoni'),
(145, 'TZS', 'Tanzanian shilling'),
(146, 'THB', 'Thai baht'),
(147, 'TOP', 'Tongan paanga'),
(148, 'TTD', 'Trinidad and Tobago dollar'),
(149, 'TND', 'Tunisian dinar'),
(150, 'TRY', 'Turkish lira'),
(151, 'TMT', 'Turkmen new manat'),
(152, 'AED', 'UAE dirham'),
(153, 'UGX', 'Uganda new shilling'),
(154, 'XFU', 'UIC franc'),
(155, 'UAH', 'Ukrainian hryvnia'),
(156, 'UYU', 'Uruguayan peso uruguayo'),
(157, 'USD', 'US dollar'),
(158, 'UZS', 'Uzbekistani sum'),
(159, 'VUV', 'Vanuatu vatu'),
(160, 'VEF', 'Venezuelan bolivar fuerte'),
(161, 'VND', 'Vietnamese dong'),
(162, 'YER', 'Yemeni rial'),
(163, 'ZMK', 'Zambian kwacha'),
(164, 'ZWL', 'Zimbabwe dollar');

-- --------------------------------------------------------

--
-- Структура таблицы `tbl_fcm_template`
--

CREATE TABLE IF NOT EXISTS `tbl_fcm_template` (
  `id` int(11) NOT NULL,
  `message` text NOT NULL,
  `image` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL DEFAULT 'Notification',
  `link` varchar(500) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tbl_fcm_template`
--

INSERT INTO `tbl_fcm_template` (`id`, `message`, `image`, `title`, `link`) VALUES
(30, 'Поступил новый кофе Mehmet Efendi', '2088-2019-04-15.jpg', 'Кофе по-турецки', '');

-- --------------------------------------------------------

--
-- Структура таблицы `tbl_fcm_token`
--

CREATE TABLE IF NOT EXISTS `tbl_fcm_token` (
  `id` int(11) NOT NULL,
  `token` text NOT NULL,
  `user_unique_id` varchar(255) NOT NULL,
  `app_version` varchar(255) NOT NULL,
  `os_version` varchar(255) NOT NULL,
  `device_model` varchar(255) NOT NULL,
  `device_manufacturer` varchar(255) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tbl_fcm_token`
--

INSERT INTO `tbl_fcm_token` (`id`, `token`, `user_unique_id`, `app_version`, `os_version`, `device_model`, `device_manufacturer`, `date`) VALUES
(1, 'e7VLLnt7uQU:APA91bHjFHxF0aTvKIGm1rXU2RCfWOarsEkcEWBfUI9_8JnHmalgrAP0ECZutOaFlfVJBGdGuZ5j9y8p9F-21lcKSAOw3pf-sUtiBLQRYExW6lJxTNIyMILU233Pa-1qkTAy3Mt-3bUp', '87b66d0f1dc0b0ce', '6 (3.3.0)', 'Nougat 7.1.1', 'Android SDK built for x86', 'Google', '2019-01-31 09:54:28');

-- --------------------------------------------------------

--
-- Структура таблицы `tbl_help`
--

CREATE TABLE IF NOT EXISTS `tbl_help` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tbl_help`
--

INSERT INTO `tbl_help` (`id`, `title`, `content`) VALUES
(1, 'Контакты', '<p>Наши контакты:</p>\r\n\r\n<p>Россия, Москва<br />\r\nМетро Белорусская, Тверская Застава 3</p>\r\n\r\n<p>Email : erikdeliyev@gmail.com</p>\r\n'),
(2, 'Профиль', '<p>Чтобы каждый раз не вводить адрес доставки или адрес пункта самовывоза, заполните Профиль, укажите номер телефона и адрес и после каждого заказа они автоматически будут заполняться.<br />\r\nТакже в профиле хранится вся история ваших заказов.</p>\r\n'),
(3, 'Доставка', '<p>Мы доставляем во все регионы РФ, в Республику Беларусь, в Казахстан.<br />\r\nВ регионы РФ доставка осуществляется службами: Boxberry, СДЭК, Почта России<br />\r\nВ Республику Беларусь доставка осуществляется: СДЭК<br />\r\nВ Казахстан доставка осуществляется: СДЭК<br />\r\n<br />\r\nПосле заказа мы Вам выставим счёт где кроме ваша заказа будет также сумма доставки. Доставка в пункты самовывоза СДЭК и Boxberry в крупные города России 250 руб до 3кг. Доставка в Республику Беларусь - 310 руб.&nbsp;<br />\r\nВы можете самостоятельно вычислить сумму доставки на сайтах:<br />\r\n- Boxberry - www.boxberry.ru<br />\r\n- СДЭК - www.cdek.ru</p>\r\n\r\n<p>Самовывоз - бесплатный у метро Белорусская, Москва.</p>\r\n'),
(4, 'Как оплатить?', '<p>После отправки заказа, ожидайте от нас информацию на email. Мы проверим наличие и выставим вам счёт на оплату.&nbsp;</p>\r\n\r\n<p>Оплатить можно следующим способом:</p>\r\n\r\n<p><strong>Оплата онлайн на расчётный счёт</strong></p>\r\n\r\n<ul>\r\n	<li>Обычная оплата как во всех интернет-магазинах. Мы Вам выставим счёт и пришлём ссылку для оплаты.</li>\r\n</ul>\r\n\r\n<p><strong>Сбербанк Онлайн</strong></p>\r\n\r\n<ul>\r\n	<li>Можете оплатить напрямую на нашу карту через Сбербанк Онлайн.</li>\r\n	<li>Номер: 0101 0101 0101</li>\r\n</ul>\r\n\r\n<p><strong>Яндекс Деньги</strong></p>\r\n\r\n<ul>\r\n	<li>Номер кошелька: 41001925694264</li>\r\n</ul>\r\n\r\n<p><strong>Qiwi</strong></p>\r\n\r\n<ul>\r\n	<li>По номеру телефона: +7 3249739595</li>\r\n</ul>\r\n\r\n<p><strong>Webmoney</strong></p>\r\n\r\n<ul>\r\n	<li>WMR: R785987785095</li>\r\n	<li>WMZ: Z164694859622</li>\r\n</ul>\r\n'),
(5, 'Как заказать?', '<p><strong>Как сделать заказ в приложении:</strong></p>\r\n\r\n<ul>\r\n	<li>Выберите интересующиеся вас товары и нажмите на кнопку Купить.</li>\r\n	<li>Все выбранные товары хранятся в Корзине. Иконка сверху ввиде корзины</li>\r\n	<li>Затем перейдите к оформлению заказа. Для этого нажмите на иконку Корзина.</li>\r\n	<li>Нажмите на кнопку Оформить.</li>\r\n	<li>Заполните все поля и нажмите на кнопку Отправить Заказ</li>\r\n	<li>После получения заказа, мы проверим правильность оформления, адрес доставки, наличие на складе и выставим счёт на оплату.</li>\r\n</ul>\r\n\r\n<p><strong>Спасибо за заказ!</strong></p>\r\n');

-- --------------------------------------------------------

--
-- Структура таблицы `tbl_order`
--

CREATE TABLE IF NOT EXISTS `tbl_order` (
  `id` int(11) NOT NULL,
  `code` varchar(45) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `address` varchar(500) NOT NULL,
  `shipping` varchar(255) NOT NULL,
  `date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_list` text NOT NULL,
  `order_total` varchar(255) NOT NULL,
  `comment` text NOT NULL,
  `status` varchar(1) NOT NULL DEFAULT '0',
  `player_id` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tbl_order`
--

INSERT INTO `tbl_order` (`id`, `code`, `name`, `email`, `phone`, `address`, `shipping`, `date_time`, `order_list`, `order_total`, `comment`, `status`, `player_id`) VALUES
(1, 'CB8J1SJBG', 'Erik', 'your.email@gmail.com', '+628123456789', 'rrfhgs rs sr sr sr', 'Free Shipping', '2019-03-27 02:34:49', '1 Apple watch series 3 GPS 42mm Black 400 RUB,\n\nOrder : 400 RUB\nTax : 0 % : 0 RUB\nTotal : 400 RUB', '400 RUB', '04044b043a0430 ', '2', '2215328e-0a18-437d-a7cb-4eda820784f3'),
(2, 'RBAGFY37Y', 'Хрик', 'your.email@gmail.com', '+628123456789', 'YМосква, метро Белорусская', 'Free Shipping', '2019-03-27 17:03:56', '1 Кофе Mehmet Efendi 500гр. 880 RUB,\n\nOrder : 880 RUB\nTax : 0 % : 0 RUB\nTotal : 880 RUB', '880 RUB', 'Срочно', '1', 'd9c61d6f-1317-41ff-919f-f5ced8a1a191'),
(3, 'TP4KG9BD1', 'Ваше имя', 'вашemail@gmail.com', '+784768467846', 'Ваш Адрес', 'Boxberry в пункт самовывоза', '2019-03-31 20:48:43', '2 Пишмание Фисташки. 300гр 640 руб,\n\nЗаказ: 640 руб\nНалог: 0 % : 0 руб\nВсего: 640 руб', '640 руб', 'Срочно', '1', 'd9c61d6f-1317-41ff-919f-f5ced8a1a191'),
(4, '32PTKTB0P', 'Эрик', 'seoaz@yandex.ru', '+784768467846', 'Москва, ул. Верхняя 3', 'Самовывоз', '2019-04-04 15:57:39', '1 Кофе Mehmet Efendi 500гр. 880 руб,\n\nЗаказ: 880 руб\nНалог: 0 % : 0 руб\nВсего: 880 руб', '880 руб', 'Доставить Завтра', '1', 'd9c61d6f-1317-41ff-919f-f5ced8a1a191'),
(5, 'GXYF6WVK4', 'Ваше имя', 'seoaz@yandex.ru', '+784768467846', 'Москва, ул. Богатырская 38', 'Курьерская до двери', '2019-04-11 23:34:00', '1 Кофе Mehmet Efendi 250 гр. 480 руб,\n\nЗаказ: 480 руб\nНалог: 0 % : 0 руб\nВсего: 480 руб', '480 руб', 'Ермар', '0', 'd9c61d6f-1317-41ff-919f-f5ced8a1a191');

-- --------------------------------------------------------

--
-- Структура таблицы `tbl_product`
--

CREATE TABLE IF NOT EXISTS `tbl_product` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_price` double NOT NULL,
  `product_status` varchar(45) NOT NULL,
  `product_image` text NOT NULL,
  `product_description` text NOT NULL,
  `product_quantity` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tbl_product`
--

INSERT INTO `tbl_product` (`product_id`, `product_name`, `product_price`, `product_status`, `product_image`, `product_description`, `product_quantity`, `category_id`) VALUES
(55, 'первый пк', 880, 'Available', '1554803327_Instagram-Banners_500.jpg', '<p>Кофе от Мехмет Эфенди изготовлен согласно старым восточным традициям и обладает очень мелким помолом, который невозможно достичь в домашних условиях без изменения вкуса (пережигание кофе из-за многократного перемалывания в кофемолке).</p>\r\n\r\n<p>Турецкий&nbsp;молотый&nbsp;кофе Kurukahveci Mehmet Efendi&nbsp;(Курукахведжи Мехмет Эфенди).&nbsp;Турецкий кофе&nbsp;произведён из высококачественных&nbsp;кофейных&nbsp;зёрен сорта &laquo;Арабика&raquo;, который родом из Центральной Америки, Бразилии и Эфиопии.&nbsp;Кофейные зёрна&nbsp;тщательно сортируются, обжариваются, запекаются и перемалываются в тончайший порошок.&nbsp;Кофе&nbsp;подаётся в маленьких чашечках. После приготовления&nbsp;кофе&nbsp;должен некоторое время постоять, чтобы&nbsp;кофейная&nbsp;гуща осела на дно.</p>\r\n\r\n<p>Страна:&nbsp;Турция</p>\r\n', 100, 8),
(56, 'Второй пк', 320, 'Available', '1554051171_pism1.jpg', '<p>Легендарная турецкая сладость с историей зарождения и с невероятным вкусом. Это реклама? Нет, те кто пробовали не дадут соврать. Мы Вам гарантируем превосходный, сладкий и нежный вкус. Но будьте осторожны! Если вы подсядите на пишмание, обратной дороги не будет. Правда реабилитационного центра Пишманиезависимости пока нет, поэтому закупайте пока не поздно.</p>\r\n\r\n<p>Производитель: Gokkusagi<br />\r\nСтрана: Турция<br />\r\nСрок хранения: 12 месяцев</p>\r\n', 100, 4),
(57, 'Третий пк', 190, 'Available', '1554802700_100gr.jpg', '<p>Мягкая метализированная упаковка 100 г.<br />\r\nНатуральный, обжаренный, мелкий помол. Идеален для приготовления в турке.</p>\r\n\r\n<p>Специальная упаковка позволяет кофе сохранять свой вкус и аромат максимально долго. Кофе по-турецки Mehmet Efendi для турки 100 г. лучшее решение для тех кто предпочитает максимально свежий кофе.</p>\r\n\r\n<p>Кофе от Мехмет Эфенди изготовлен согласно старым восточным традициям и обладает очень мелким помолом, который невозможно достичь в домашних условиях без изменения вкуса (пережигание кофе из-за многократного перемалывания в кофемолке).</p>\r\n\r\n<p>Страна: Турция</p>\r\n', 100, 8),
(58, 'Кофе Mehmet Efendi 250 гр.', 480, 'Available', '1554803199_m250.jpg', '<p>Кофе от Мехмет Эфенди изготовлен согласно старым восточным традициям и обладает очень мелким помолом, который невозможно достичь в домашних условиях без изменения вкуса (пережигание кофе из-за многократного перемалывания в кофемолке).</p>\r\n\r\n<p>Турецкий&nbsp;молотый&nbsp;кофе&nbsp;Kurukahveci Mehmet Efendi&nbsp;(Курукахведжи Мехмет Эфенди).&nbsp;Турецкий кофе&nbsp;произведён из высококачественных&nbsp;кофейных&nbsp;зёрен сорта &laquo;Арабика&raquo;, родом из Центральной Америки, Бразилии и Эфиопии.&nbsp;Кофейные зёрна&nbsp;тщательно сортируются, обжариваются, запекаются и перемалываются в тончайший порошок.&nbsp;Кофе&nbsp;подаётся в маленьких чашечках. После приготовления&nbsp;кофе&nbsp;должен некоторое время постоять, чтобы&nbsp;кофейная&nbsp;гуща осела на дно.</p>\r\n\r\n<p>Страна:&nbsp;Турция</p>\r\n', 100, 8),
(59, 'Рахат лукум «Падишах» 250гр. С орехами', 160, 'Available', '1554803428_lokum_010.jpg', '<p>Лукум &laquo;Падишах&raquo; с орехами, ассорти в кокосовой стружке . Изысканное угощение к праздничному столу. Подарочные наборы лукума.<br />\r\nРахат лукум &mdash; это не только невероятно колоритный десерт, приготовленный в соответствии с древними традициями, но и необычный вкус, который понравится истинным гурманам. Попробуйте и Вы на себе магию рахат-лукума &hellip; В чем заключается польза лукума? Энергия и бодрость на весь день, отсутствие голода и прекрасное настроение Вам обеспечены! Орехи&nbsp; входящие в состав лукума, делают эту сладость полезной и обогащенной витаминами и микроэлементами.</p>\r\n\r\n<p>Сделано кондитерами из Турции</p>\r\n\r\n<p>Вес: 250гр.<br />\r\nУпаковка: Подарочная<br />\r\nТорговая марка: Анталия Лукум<br />\r\nПроизводитель: Акдениз-1<br />\r\nСтрана: Россия</p>\r\n\r\n<p>Сертификат качества: ISO 22000<br />\r\nДекларация о соответствии: EAC</p>\r\n\r\n<p>Срок годности: 12 месяцев</p>\r\n', 100, 4),
(60, 'Пишмание Классический 250гр.', 250, 'Available', '1554803576_pishmaniye_classic_250gr_correct.jpg', '<p>Пишмание Классический 250гр.<br />\r\nЛегендарная турецкая сладость с историей зарождения и с невероятным вкусом. Это реклама? Нет, те кто пробовали не дадут соврать. Мы Вам гарантируем превосходный, сладкий и нежный вкус. Но будьте осторожны! Если вы подсядите на пишмание, обратной дороги не будет. Правда реабилитационного центра Пишманиезависимости пока нет, поэтому закупайте пока не поздно.</p>\r\n\r\n<p>Производитель: Gokkusagi<br />\r\nСтрана: Турция<br />\r\nСрок хранения: 12 месяцев</p>\r\n', 100, 4);

-- --------------------------------------------------------

--
-- Структура таблицы `tbl_setting`
--

CREATE TABLE IF NOT EXISTS `tbl_setting` (
  `Variable` varchar(20) NOT NULL,
  `Value` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tbl_setting`
--

INSERT INTO `tbl_setting` (`Variable`, `Value`) VALUES
('Tax', '10'),
('Currency', 'USD');

-- --------------------------------------------------------

--
-- Структура таблицы `tbl_shipping`
--

CREATE TABLE IF NOT EXISTS `tbl_shipping` (
  `shipping_id` int(11) NOT NULL,
  `shipping_name` varchar(500) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tbl_shipping`
--

INSERT INTO `tbl_shipping` (`shipping_id`, `shipping_name`) VALUES
(3, 'Бесплатная доставка'),
(4, 'Самовывоз'),
(5, 'Курьерская до двери'),
(6, 'Boxberry в пункт самовывоза'),
(7, 'СДЭК в пункт самовывоза');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `tbl_admin`
--
ALTER TABLE `tbl_admin`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `tbl_category`
--
ALTER TABLE `tbl_category`
  ADD PRIMARY KEY (`category_id`);

--
-- Индексы таблицы `tbl_config`
--
ALTER TABLE `tbl_config`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `tbl_currency`
--
ALTER TABLE `tbl_currency`
  ADD PRIMARY KEY (`currency_id`);

--
-- Индексы таблицы `tbl_fcm_template`
--
ALTER TABLE `tbl_fcm_template`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `tbl_fcm_token`
--
ALTER TABLE `tbl_fcm_token`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `tbl_help`
--
ALTER TABLE `tbl_help`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `tbl_order`
--
ALTER TABLE `tbl_order`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `tbl_product`
--
ALTER TABLE `tbl_product`
  ADD PRIMARY KEY (`product_id`);

--
-- Индексы таблицы `tbl_shipping`
--
ALTER TABLE `tbl_shipping`
  ADD PRIMARY KEY (`shipping_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `tbl_admin`
--
ALTER TABLE `tbl_admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT для таблицы `tbl_category`
--
ALTER TABLE `tbl_category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT для таблицы `tbl_config`
--
ALTER TABLE `tbl_config`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT для таблицы `tbl_currency`
--
ALTER TABLE `tbl_currency`
  MODIFY `currency_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=165;
--
-- AUTO_INCREMENT для таблицы `tbl_fcm_template`
--
ALTER TABLE `tbl_fcm_template`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT для таблицы `tbl_fcm_token`
--
ALTER TABLE `tbl_fcm_token`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT для таблицы `tbl_help`
--
ALTER TABLE `tbl_help`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT для таблицы `tbl_order`
--
ALTER TABLE `tbl_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT для таблицы `tbl_product`
--
ALTER TABLE `tbl_product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=61;
--
-- AUTO_INCREMENT для таблицы `tbl_shipping`
--
ALTER TABLE `tbl_shipping`
  MODIFY `shipping_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
