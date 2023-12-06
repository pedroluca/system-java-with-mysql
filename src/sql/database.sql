-- Criação do banco de dados
CREATE DATABASE sistema_produtos;
USE sistema_produtos;

-- Criação da tabela
CREATE TABLE Produto (
  codigo INT PRIMARY KEY,
  nome VARCHAR(50),
  preco DECIMAL(10,2),
  quantidade INT,
  material VARCHAR(100),
  data_vencimento VARCHAR(15),
  isPerecivel BOOLEAN
);

-- Opcional: adição de produtos pré-definidos para que o banco não inicialize vazio
INSERT INTO `Produto` (`codigo`, `nome`, `preco`, `quantidade`, `material`, `data_vencimento`, `isPerecivel`) VALUES
(1, 'Feijão', 3.75, 26, NULL, '20/12/2024', 1),
(2, 'Arroz', 3.49, 15, NULL, '20/07/2024', 1),
(3, 'Estojo', 10.00, 15, 'pano e zíper', NULL, 0),
(214, 'Banana', 12.00, 24, NULL, '24/12/2024', 1),
(512, 'Abóbora', 10.99, 12, NULL, '20/12/2024', 1),
(621, 'Escada', 99.59, 10, 'ferro', NULL, 0),
(51527, 'Martelo', 49.99, 20, 'madeira e ferro', NULL, 0);