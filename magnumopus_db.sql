-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 03-Fev-2022 às 18:29
-- Versão do servidor: 5.7.31
-- versão do PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `magnumopus_db`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `categorias`
--

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE IF NOT EXISTS `categorias` (
  `cod_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nome_categoria` varchar(50) NOT NULL,
  PRIMARY KEY (`cod_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `categorias`
--

INSERT INTO `categorias` (`cod_categoria`, `nome_categoria`) VALUES
(1, 'Guitarras'),
(3, 'Baterias'),
(4, 'Baixos'),
(5, 'Piano'),
(6, 'Reco-recos');

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `cod_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome_cliente` text NOT NULL,
  `morada` text NOT NULL,
  `cod_postal` varchar(8) NOT NULL,
  `localidade` varchar(50) NOT NULL,
  `cidade` text NOT NULL,
  `num_tel` int(9) NOT NULL,
  PRIMARY KEY (`cod_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`cod_cliente`, `nome_cliente`, `morada`, `cod_postal`, `localidade`, `cidade`, `num_tel`) VALUES
(3, 'Nuno Moura', 'Rua 1 , 123', '1234-567', 'Localidade', 'Cidade', 912345678);

-- --------------------------------------------------------

--
-- Estrutura da tabela `encomendas`
--

DROP TABLE IF EXISTS `encomendas`;
CREATE TABLE IF NOT EXISTS `encomendas` (
  `cod_encomenda` int(11) NOT NULL AUTO_INCREMENT,
  `cod_cliente` int(11) NOT NULL,
  `preco_total` float UNSIGNED NOT NULL DEFAULT '0',
  `data` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` varchar(25) DEFAULT 'Em Processamento',
  PRIMARY KEY (`cod_encomenda`),
  KEY `cod_cliente` (`cod_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `encomendas`
--

INSERT INTO `encomendas` (`cod_encomenda`, `cod_cliente`, `preco_total`, `data`, `estado`) VALUES
(1, 3, 1.13, '2022-02-01 15:39:53', 'Em Processamento');

-- --------------------------------------------------------

--
-- Estrutura da tabela `encomendas_produtos`
--

DROP TABLE IF EXISTS `encomendas_produtos`;
CREATE TABLE IF NOT EXISTS `encomendas_produtos` (
  `cod_encprod` int(11) NOT NULL AUTO_INCREMENT,
  `cod_encomenda` int(11) NOT NULL,
  `cod_produto` int(11) NOT NULL,
  `quant` int(11) UNSIGNED NOT NULL,
  `preco_prods` float UNSIGNED DEFAULT '0',
  PRIMARY KEY (`cod_encprod`),
  KEY `cod_encomenda` (`cod_encomenda`),
  KEY `cod_produto` (`cod_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Acionadores `encomendas_produtos`
--
DROP TRIGGER IF EXISTS `trigger_preco_prods_in`;
DELIMITER $$
CREATE TRIGGER `trigger_preco_prods_in` BEFORE INSERT ON `encomendas_produtos` FOR EACH ROW SET NEW.preco_prods = 
  (
    SELECT preco_civa * NEW.quant
      FROM produtos
     WHERE NEW.cod_produto = cod_produto
  )
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `trigger_preco_prods_up`;
DELIMITER $$
CREATE TRIGGER `trigger_preco_prods_up` BEFORE UPDATE ON `encomendas_produtos` FOR EACH ROW SET NEW.preco_prods = 
  (
    SELECT preco_civa * NEW.quant
      FROM produtos
     WHERE NEW.cod_produto = cod_produto
  )
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `trigger_preco_total_in`;
DELIMITER $$
CREATE TRIGGER `trigger_preco_total_in` AFTER INSERT ON `encomendas_produtos` FOR EACH ROW UPDATE encomendas SET preco_total =
(
	SELECT SUM(preco_prods)
    FROM encomendas_produtos
    WHERE encomendas_produtos.cod_encomenda = encomendas.cod_encomenda
)
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `trigger_preco_total_up`;
DELIMITER $$
CREATE TRIGGER `trigger_preco_total_up` AFTER UPDATE ON `encomendas_produtos` FOR EACH ROW UPDATE encomendas SET preco_total =
(
	SELECT SUM(preco_prods)
    FROM encomendas_produtos
    WHERE encomendas_produtos.cod_encomenda = encomendas.cod_encomenda
)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_func` text NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(35) NOT NULL,
  `tipo_user` varchar(10) NOT NULL DEFAULT 'user',
  PRIMARY KEY (`username`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `login`
--

INSERT INTO `login` (`id`, `nome_func`, `username`, `password`, `tipo_user`) VALUES
(1, 'Administrador Magnum Opus', 'admin', 'adminmopus', 'admin'),
(2, 'Test User', '', '', 'user');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

DROP TABLE IF EXISTS `produtos`;
CREATE TABLE IF NOT EXISTS `produtos` (
  `cod_produto` int(11) NOT NULL AUTO_INCREMENT,
  `nome_produto` varchar(50) NOT NULL,
  `cod_categoria` int(11) NOT NULL,
  `cod_subcategoria` int(11) NOT NULL,
  `quant_disp` int(11) UNSIGNED NOT NULL,
  `preco` float DEFAULT NULL,
  `valor_iva` float DEFAULT NULL,
  `preco_civa` float GENERATED ALWAYS AS ((`preco` + `valor_iva`)) VIRTUAL,
  PRIMARY KEY (`cod_produto`),
  KEY `cod_categoria` (`cod_categoria`),
  KEY `cod_subcategoria` (`cod_subcategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Acionadores `produtos`
--
DROP TRIGGER IF EXISTS `trigger_valor_iva_in`;
DELIMITER $$
CREATE TRIGGER `trigger_valor_iva_in` BEFORE INSERT ON `produtos` FOR EACH ROW SET NEW.valor_iva = 
  (
    SELECT NEW.preco * iva
      FROM sub_categorias
     WHERE NEW.cod_subcategoria = cod_subcategoria
  )
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `trigger_valor_iva_up`;
DELIMITER $$
CREATE TRIGGER `trigger_valor_iva_up` BEFORE UPDATE ON `produtos` FOR EACH ROW SET NEW.valor_iva = 
  (
    SELECT NEW.preco * iva
      FROM sub_categorias
     WHERE NEW.cod_subcategoria = cod_subcategoria
  )
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `sub_categorias`
--

DROP TABLE IF EXISTS `sub_categorias`;
CREATE TABLE IF NOT EXISTS `sub_categorias` (
  `cod_subcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `cod_categoria` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `iva` float NOT NULL DEFAULT '0.13',
  PRIMARY KEY (`cod_subcategoria`),
  KEY `cod_categoria` (`cod_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `encomendas`
--
ALTER TABLE `encomendas`
  ADD CONSTRAINT `encomendas_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `clientes` (`cod_cliente`);

--
-- Limitadores para a tabela `encomendas_produtos`
--
ALTER TABLE `encomendas_produtos`
  ADD CONSTRAINT `encomendas_produtos_ibfk_2` FOREIGN KEY (`cod_produto`) REFERENCES `produtos` (`cod_produto`),
  ADD CONSTRAINT `encomendas_produtos_ibfk_3` FOREIGN KEY (`cod_encomenda`) REFERENCES `encomendas` (`cod_encomenda`);

--
-- Limitadores para a tabela `produtos`
--
ALTER TABLE `produtos`
  ADD CONSTRAINT `produtos_ibfk_1` FOREIGN KEY (`cod_categoria`) REFERENCES `categorias` (`cod_categoria`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `produtos_ibfk_2` FOREIGN KEY (`cod_subcategoria`) REFERENCES `sub_categorias` (`cod_subcategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `sub_categorias`
--
ALTER TABLE `sub_categorias`
  ADD CONSTRAINT `sub_categorias_ibfk_1` FOREIGN KEY (`cod_categoria`) REFERENCES `categorias` (`cod_categoria`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
