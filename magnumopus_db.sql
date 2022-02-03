-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 01-Fev-2022 às 17:08
-- Versão do servidor: 5.7.11
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `magnumopus_db`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `categorias`
--

CREATE TABLE `categorias` (
  `cod_categoria` int(11) NOT NULL,
  `nome_categoria` varchar(50) NOT NULL,
  PRIMARY KEY (`cod_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

CREATE TABLE `clientes` (
  `cod_cliente` int(11) NOT NULL,
  `nome_cliente` text NOT NULL,
  `morada` text NOT NULL,
  `cod_postal` varchar(8) NOT NULL,
  `localidade` varchar(50) NOT NULL,
  `cidade` text NOT NULL,
  `num_tel` int(9) NOT NULL,
  PRIMARY KEY (`cod_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`cod_cliente`, `nome_cliente`, `morada`, `cod_postal`, `localidade`, `cidade`, `num_tel`) VALUES
(3, 'Nuno Moura', 'Rua 1 , 123', '1234-567', 'Localidade', 'Cidade', 912345678);

-- --------------------------------------------------------

--
-- Estrutura da tabela `encomendas`
--

CREATE TABLE `encomendas` (
  `cod_encomenda` int(11) NOT NULL,
  `cod_cliente` int(11) NOT NULL,
  `preco_total` float UNSIGNED NOT NULL DEFAULT '0',
  `data` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` varchar(25) DEFAULT 'Em Processamento',
  PRIMARY KEY (`cod_encomenda`),
  KEY `cod_cliente` (`cod_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `encomendas`
--

INSERT INTO `encomendas` (`cod_encomenda`, `cod_cliente`, `preco_total`, `data`, `estado`) VALUES
(1, 3, 1.13, '2022-02-01 15:39:53', 'Em Processamento');

-- --------------------------------------------------------

--
-- Estrutura da tabela `encomendas_produtos`
--

CREATE TABLE `encomendas_produtos` (
  `cod_encprod` int(11) NOT NULL,
  `cod_encomenda` int(11) NOT NULL,
  `cod_produto` int(11) NOT NULL,
  `quant` int(11) UNSIGNED NOT NULL,
  `preco_prods` float DEFAULT '0'
  PRIMARY KEY (`cod_encprod`),
  KEY `cod_produto` (`cod_produto`),
  KEY `cod_encomenda` (`cod_encomenda`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `encomendas_produtos`
--

INSERT INTO `encomendas_produtos` (`cod_encprod`, `cod_encomenda`, `cod_produto`, `quant`, `preco_prods`) VALUES
(1, 1, 3, 1, 1.13);

--
-- Acionadores `encomendas_produtos`
--
DELIMITER $$
CREATE TRIGGER `trigger_preco_prods_in` BEFORE INSERT ON `encomendas_produtos` FOR EACH ROW SET NEW.preco_prods = 
  (
    SELECT preco_civa * NEW.quant
      FROM produtos
     WHERE NEW.cod_produto = cod_produto
  )
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trigger_preco_prods_up` BEFORE UPDATE ON `encomendas_produtos` FOR EACH ROW SET NEW.preco_prods = 
  (
    SELECT preco_civa * NEW.quant
      FROM produtos
     WHERE NEW.cod_produto = cod_produto
  )
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trigger_preco_total_in` AFTER INSERT ON `encomendas_produtos` FOR EACH ROW UPDATE encomendas SET preco_total =
(
	SELECT SUM(preco_prods)
    FROM encomendas_produtos
    WHERE encomendas_produtos.cod_encomenda = encomendas.cod_encomenda
)
$$
DELIMITER ;
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

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `nome_func` text NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(35) NOT NULL,
  `tipo_user` varchar(10) NOT NULL DEFAULT 'user',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_2` (`username`),
  KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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

CREATE TABLE `produtos` (
  `cod_produto` int(11) NOT NULL,
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
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`cod_produto`, `nome_produto`, `cod_categoria`, `cod_subcategoria`, `quant_disp`, `preco`, `valor_iva`, `preco_civa`) VALUES
(3, 'a', 1, 1, 0, 1, 0.13, 1.13);

--
-- Acionadores `produtos`
--
DELIMITER $$
CREATE TRIGGER `trigger_valor_iva_in` BEFORE INSERT ON `produtos` FOR EACH ROW SET NEW.valor_iva = 
  (
    SELECT NEW.preco * iva
      FROM sub_categorias
     WHERE NEW.cod_subcategoria = cod_subcategoria
  )
$$
DELIMITER ;
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


  CREATE TABLE `sub_categorias` (
  `cod_subcategoria` int(11) NOT NULL,
  `nome_subcategoria` varchar(50) NOT NULL,
  `cod_categoria` int(11) NOT NULL,
  `iva` float NOT NULL DEFAULT '0.13',
  PRIMARY KEY (`cod_subcategoria`),
  KEY `cod_categoria` (`cod_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


--
-- Extraindo dados da tabela `sub_categorias`
--

INSERT INTO `sub_categorias` (`cod_subcategoria`, `nome_subcategoria`, `cod_categoria`, `iva`) VALUES
(1, 'Guitarras Elétricas', 1, 0.13),
(3, 'Bateria Acustica', 3, 0.13),
(4, 'Baixo Elétrico', 4, 0.13);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categorias`
--
ALTER TABLE `categorias`
  MODIFY `cod_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `clientes`
--
ALTER TABLE `clientes`
  MODIFY `cod_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `encomendas`
--
ALTER TABLE `encomendas`
  MODIFY `cod_encomenda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `encomendas_produtos`
--
ALTER TABLE `encomendas_produtos`
  MODIFY `cod_encprod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
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
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
