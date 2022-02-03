-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 28-Jan-2022 às 13:05
-- Versão do servidor: 5.7.11
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


	@@ -18,7 +17,7 @@ SET time_zone = "+00:00";
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `magnumopus_db`
--

-- --------------------------------------------------------
	@@ -27,12 +26,10 @@ SET time_zone = "+00:00";
-- Estrutura da tabela `categorias`
--

CREATE TABLE `categorias` (
  `cod_categoria` int(11) NOT NULL,
  `nome_categoria` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `categorias`
	@@ -50,17 +47,15 @@ INSERT INTO `categorias` (`cod_categoria`, `nome_categoria`) VALUES
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `cod_cliente` int(11) NOT NULL,
  `nome_cliente` text NOT NULL,
  `morada` text NOT NULL,
  `cod_postal` varchar(8) NOT NULL,
  `localidade` varchar(50) NOT NULL,
  `cidade` text NOT NULL,
  `num_tel` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `clientes`
	@@ -75,54 +70,31 @@ INSERT INTO `clientes` (`cod_cliente`, `nome_cliente`, `morada`, `cod_postal`, `
-- Estrutura da tabela `encomendas`
--

CREATE TABLE `encomendas` (
  `cod_encomenda` int(11) NOT NULL,
  `cod_cliente` int(11) NOT NULL,
  `preco_total` float UNSIGNED NOT NULL DEFAULT '0',
  `data` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` varchar(25) DEFAULT 'Em Processamento'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `encomendas_produtos`
--

CREATE TABLE `encomendas_produtos` (
  `cod_encprod` int(11) NOT NULL,
  `cod_encomenda` int(11) NOT NULL,
  `cod_produto` int(11) NOT NULL,
  `quant` int(11) UNSIGNED NOT NULL,
  `preco_prods` float UNSIGNED DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Acionadores `encomendas_produtos`
--
DELIMITER $$
CREATE TRIGGER `trigger_preco_prods_in` BEFORE INSERT ON `encomendas_produtos` FOR EACH ROW SET NEW.preco_prods = 
  (
	@@ -132,7 +104,6 @@ CREATE TRIGGER `trigger_preco_prods_in` BEFORE INSERT ON `encomendas_produtos` F
  )
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trigger_preco_prods_up` BEFORE UPDATE ON `encomendas_produtos` FOR EACH ROW SET NEW.preco_prods = 
  (
	@@ -142,7 +113,6 @@ CREATE TRIGGER `trigger_preco_prods_up` BEFORE UPDATE ON `encomendas_produtos` F
  )
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trigger_preco_total_in` AFTER INSERT ON `encomendas_produtos` FOR EACH ROW UPDATE encomendas SET preco_total =
(
	@@ -152,7 +122,6 @@ CREATE TRIGGER `trigger_preco_total_in` AFTER INSERT ON `encomendas_produtos` FO
)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trigger_preco_total_up` AFTER UPDATE ON `encomendas_produtos` FOR EACH ROW UPDATE encomendas SET preco_total =
(
	@@ -169,62 +138,50 @@ DELIMITER ;
-- Estrutura da tabela `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `nome_func` text NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(35) NOT NULL,
  `tipo_user` varchar(10) NOT NULL DEFAULT 'user'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `login`
--

INSERT INTO `login` (`id`, `nome_func`, `username`, `password`, `tipo_user`) VALUES
(1, 'Administrador Magnum Opus', 'admin', 'adminmopus', 'admin'),
(2, 'TEST USER', '', '', 'user');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `cod_produto` int(11) NOT NULL,
  `nome_produto` varchar(50) NOT NULL,
  `cod_categoria` int(11) NOT NULL,
  `cod_subcategoria` int(11) NOT NULL,
  `quant_disp` int(11) UNSIGNED NOT NULL,
  `preco` float UNSIGNED DEFAULT NULL,
  `valor_iva` float UNSIGNED DEFAULT NULL,
  `preco_civa` float GENERATED ALWAYS AS ((`preco` + `valor_iva`)) VIRTUAL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`cod_produto`, `nome_produto`, `cod_categoria`, `cod_subcategoria`, `quant_disp`, `preco`, `valor_iva`, `preco_civa`) VALUES
(2, 'Millenium Focus Junior Drum Set Black', 3, 3, 4, 15, 1.95, 16.95),
(3, 'Harley Benton JB-75MN Black Vintage Series', 4, 4, 5, 20, 2.6, 22.6);

--
-- Acionadores `produtos`
--
DELIMITER $$
CREATE TRIGGER `trigger_valor_iva_in` BEFORE INSERT ON `produtos` FOR EACH ROW SET NEW.valor_iva = 
  (
	@@ -234,7 +191,6 @@ CREATE TRIGGER `trigger_valor_iva_in` BEFORE INSERT ON `produtos` FOR EACH ROW S
  )
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trigger_valor_iva_up` BEFORE UPDATE ON `produtos` FOR EACH ROW SET NEW.valor_iva = 
  (
	@@ -251,15 +207,12 @@ DELIMITER ;
-- Estrutura da tabela `sub_categorias`
--

CREATE TABLE `sub_categorias` (
  `cod_subcategoria` int(11) NOT NULL,
  `nome_subcategoria` varchar(50) NOT NULL,
  `cod_categoria` int(11) NOT NULL,
  `iva` float UNSIGNED NOT NULL DEFAULT '0.13'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `sub_categorias`
	@@ -273,14 +226,106 @@ INSERT INTO `sub_categorias` (`cod_subcategoria`, `nome_subcategoria`, `cod_cate
--
-- Acionadores `sub_categorias`
--
DELIMITER $$
CREATE TRIGGER `trigger_iva_up` AFTER UPDATE ON `sub_categorias` FOR EACH ROW UPDATE produtos SET valor_iva = preco * NEW.iva
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`cod_categoria`);

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cod_cliente`);

--
-- Indexes for table `encomendas`
--
ALTER TABLE `encomendas`
  ADD PRIMARY KEY (`cod_encomenda`),
  ADD KEY `cod_cliente` (`cod_cliente`);

--
-- Indexes for table `encomendas_produtos`
--
ALTER TABLE `encomendas_produtos`
  ADD PRIMARY KEY (`cod_encprod`),
  ADD KEY `cod_produto` (`cod_produto`),
  ADD KEY `cod_encomenda` (`cod_encomenda`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username_2` (`username`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`cod_produto`),
  ADD KEY `cod_categoria` (`cod_categoria`),
  ADD KEY `cod_subcategoria` (`cod_subcategoria`);

--
-- Indexes for table `sub_categorias`
--
ALTER TABLE `sub_categorias`
  ADD PRIMARY KEY (`cod_subcategoria`),
  ADD KEY `cod_categoria` (`cod_categoria`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categorias`
--
ALTER TABLE `categorias`
  MODIFY `cod_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `clientes`
--
ALTER TABLE `clientes`
  MODIFY `cod_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `encomendas`
--
ALTER TABLE `encomendas`
  MODIFY `cod_encomenda` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `encomendas_produtos`
--
ALTER TABLE `encomendas_produtos`
  MODIFY `cod_encprod` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `produtos`
--
ALTER TABLE `produtos`
  MODIFY `cod_produto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `sub_categorias`
--
ALTER TABLE `sub_categorias`
  MODIFY `cod_subcategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
	@@ -308,7 +353,6 @@ ALTER TABLE `produtos`
--
ALTER TABLE `sub_categorias`
  ADD CONSTRAINT `sub_categorias_ibfk_1` FOREIGN KEY (`cod_categoria`) REFERENCES `categorias` (`cod_categoria`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;