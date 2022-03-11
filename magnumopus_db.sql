-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 11-Mar-2022 às 17:56
-- Versão do servidor: 10.4.17-MariaDB
-- versão do PHP: 8.0.0

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

CREATE TABLE `categorias` (
  `cod_categoria` int(11) NOT NULL,
  `nome_categoria` varchar(50) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `categorias`
--

INSERT INTO `categorias` (`cod_categoria`, `nome_categoria`, `status`) VALUES
(1, 'Guitarras', 1),
(3, 'Baterias', 1),
(4, 'Baixos', 1),
(5, 'Piano', 1);

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
  `contribuinte` int(9) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`cod_cliente`, `nome_cliente`, `morada`, `cod_postal`, `localidade`, `cidade`, `num_tel`, `contribuinte`, `status`) VALUES
(3, 'Nuno Moura', 'Rua 1 , 123', '1234-567', 'Localidade', 'Cidade', 912345679, 123456789, 1),
(5, 'Marta Pereira', 'Rua 2, 456', '1234-890', 'Localidade', 'Cidade', 937778898, 987654312, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `encomendas`
--

CREATE TABLE `encomendas` (
  `cod_encomenda` int(11) NOT NULL,
  `cod_cliente` int(11) NOT NULL,
  `preco_total` decimal(10,0) UNSIGNED NOT NULL DEFAULT 0,
  `data` datetime NOT NULL DEFAULT current_timestamp(),
  `cod_estado` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `encomendas`
--

INSERT INTO `encomendas` (`cod_encomenda`, `cod_cliente`, `preco_total`, `data`, `cod_estado`) VALUES
(1, 5, '200', '2022-02-16 20:21:35', 1),
(2, 3, '111', '2022-02-17 20:18:44', 1),
(3, 3, '200', '2022-02-17 20:18:59', 1),
(4, 3, '111', '2022-02-21 19:54:22', 1),
(5, 5, '111', '2022-02-21 19:56:01', 1),
(6, 5, '111', '2022-02-21 19:58:24', 1),
(8, 3, '1773', '2022-03-07 11:28:42', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `encomendas_estados`
--

CREATE TABLE `encomendas_estados` (
  `cod_estado` int(11) NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `encomendas_estados`
--

INSERT INTO `encomendas_estados` (`cod_estado`, `estado`) VALUES
(1, 'Em Processamento'),
(2, 'Pronta para Levantamento'),
(3, 'Levantada em Loja'),
(4, 'Enviada'),
(5, 'Entregue'),
(6, 'Cancelada'),
(7, 'Venda em Loja');

-- --------------------------------------------------------

--
-- Estrutura da tabela `encomendas_produtos`
--

CREATE TABLE `encomendas_produtos` (
  `cod_encprod` int(11) NOT NULL,
  `cod_encomenda` int(11) NOT NULL,
  `cod_produto` int(11) NOT NULL,
  `quant` int(11) UNSIGNED NOT NULL,
  `preco_prods` decimal(10,0) UNSIGNED DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `encomendas_produtos`
--

INSERT INTO `encomendas_produtos` (`cod_encprod`, `cod_encomenda`, `cod_produto`, `quant`, `preco_prods`) VALUES
(1, 1, 1, 1, '111'),
(2, 1, 3, 1, '89'),
(3, 2, 1, 1, '111'),
(4, 3, 1, 1, '111'),
(5, 3, 3, 1, '89'),
(6, 4, 1, 1, '111'),
(7, 5, 1, 1, '111'),
(8, 6, 1, 1, '111'),
(11, 8, 4, 4, '1356');

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
  `tipo_user` varchar(10) NOT NULL DEFAULT 'user'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `login`
--

INSERT INTO `login` (`id`, `nome_func`, `username`, `password`, `tipo_user`) VALUES
(1, 'Administrador Magnum Opus', 'admin1', '4ed4fce110784b8ed60b4ecd445c88b5', 'admin'),
(13, 'nuno', 'nunomi12', '250cf8b51c773f3f8dc8b4be867a9a02', 'user'),
(12, 'User 2', 'user2', '202cb962ac59075b964b07152d234b70', 'user'),
(11, 'User 1', 'user1', '250cf8b51c773f3f8dc8b4be867a9a02', 'user'),
(14, 'Nuno Moura', 'spitzadmin', '4ed4fce110784b8ed60b4ecd445c88b5', 'admin');

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
  `preco` decimal(10,0) DEFAULT NULL,
  `valor_iva` decimal(10,0) DEFAULT NULL,
  `preco_civa` decimal(10,0) GENERATED ALWAYS AS (`preco` + `valor_iva`) VIRTUAL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`cod_produto`, `nome_produto`, `cod_categoria`, `cod_subcategoria`, `quant_disp`, `preco`, `valor_iva`, `status`) VALUES
(1, 'Harley Benton PB-50 SB Vintage Series ', 4, 2, 109, '98', '13', 1),
(3, 'Harley Benton TE-20HH SBK Standard Series ', 1, 1, 88, '79', '10', 1),
(4, 'Guild A-20 Bob Marley ', 1, 13, 1337, '300', '39', 1);

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
  `cod_categoria` int(11) NOT NULL,
  `nome_subcategoria` varchar(50) NOT NULL,
  `iva` float NOT NULL DEFAULT 0.13,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `sub_categorias`
--

INSERT INTO `sub_categorias` (`cod_subcategoria`, `cod_categoria`, `nome_subcategoria`, `iva`, `status`) VALUES
(1, 1, 'Guitarras Elétricas', 0.13, 1),
(2, 4, 'Baixos Elétricos', 0.13, 1),
(4, 3, 'Baterias Elétricas', 0.13, 1),
(5, 5, 'Teclados', 0.13, 1),
(6, 5, 'Sintetizadores', 0.13, 1),
(7, 5, 'Teclados controladores MIDI', 0.13, 1),
(8, 5, 'Pianos verticais', 0.13, 1),
(9, 5, 'Pianos de cauda', 0.13, 1),
(10, 5, 'Amplificadores para teclados', 0.23, 1),
(11, 1, 'Cordas', 0.23, 1),
(12, 1, 'Guitarras Clássicas', 0.13, 1),
(13, 1, 'Guitarras Acústicas', 0.13, 1),
(14, 1, 'Amplificadores Guitarra Elétrica', 0.23, 1),
(15, 4, 'Baixos Acústicos/Semi-Acústicos', 0.13, 1),
(16, 4, 'Cordas', 0.23, 1),
(17, 4, 'Pedais Baixo', 0.23, 1),
(18, 3, 'Baterias Acústicas', 0.13, 1),
(19, 3, 'Baquetas', 0.23, 1);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`cod_categoria`);

--
-- Índices para tabela `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cod_cliente`);

--
-- Índices para tabela `encomendas`
--
ALTER TABLE `encomendas`
  ADD PRIMARY KEY (`cod_encomenda`),
  ADD KEY `cod_cliente` (`cod_cliente`),
  ADD KEY `cod_estado` (`cod_estado`);

--
-- Índices para tabela `encomendas_estados`
--
ALTER TABLE `encomendas_estados`
  ADD PRIMARY KEY (`cod_estado`);

--
-- Índices para tabela `encomendas_produtos`
--
ALTER TABLE `encomendas_produtos`
  ADD PRIMARY KEY (`cod_encprod`),
  ADD KEY `cod_encomenda` (`cod_encomenda`),
  ADD KEY `cod_produto` (`cod_produto`);

--
-- Índices para tabela `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Índices para tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`cod_produto`),
  ADD KEY `cod_categoria` (`cod_categoria`),
  ADD KEY `cod_subcategoria` (`cod_subcategoria`);

--
-- Índices para tabela `sub_categorias`
--
ALTER TABLE `sub_categorias`
  ADD PRIMARY KEY (`cod_subcategoria`),
  ADD KEY `cod_categoria` (`cod_categoria`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `categorias`
--
ALTER TABLE `categorias`
  MODIFY `cod_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de tabela `clientes`
--
ALTER TABLE `clientes`
  MODIFY `cod_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `encomendas`
--
ALTER TABLE `encomendas`
  MODIFY `cod_encomenda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `encomendas_estados`
--
ALTER TABLE `encomendas_estados`
  MODIFY `cod_estado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `encomendas_produtos`
--
ALTER TABLE `encomendas_produtos`
  MODIFY `cod_encprod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de tabela `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `cod_produto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de tabela `sub_categorias`
--
ALTER TABLE `sub_categorias`
  MODIFY `cod_subcategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `encomendas`
--
ALTER TABLE `encomendas`
  ADD CONSTRAINT `encomendas_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `clientes` (`cod_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `encomendas_ibfk_2` FOREIGN KEY (`cod_estado`) REFERENCES `encomendas_estados` (`cod_estado`) ON DELETE CASCADE ON UPDATE CASCADE;

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
