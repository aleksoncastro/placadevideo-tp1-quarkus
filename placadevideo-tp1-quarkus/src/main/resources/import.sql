-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- Inserindo dados na tabela Cartao
INSERT INTO Cartao (dataValidade, numero, titular, cvv) 
VALUES 
('2025-12-31', '1234-5678-9012-3456', 'João Silva', '123'),
('2026-11-30', '9876-5432-1098-7654', 'Maria Oliveira', '456');

-- Inserindo dados na tabela Cliente
INSERT INTO Cliente (dataNascimento, cpf, nome, email) 
VALUES 
('1990-05-21', '111.222.333-44', 'João Silva', 'joao.silva@example.com'),
('1985-08-14', '555.666.777-88', 'Maria Oliveira', 'maria.oliveira@example.com');


-- Inserindo dados na tabela Endereco
INSERT INTO Endereco (cliente_id, bairro, cep, cidade, estado, numero) 
VALUES 
(1, 'Centro', '12345-678', 'São Paulo', 'SP', '100'),
(2, 'Jardins', '98765-432', 'Rio de Janeiro', 'RJ', '200');

-- Inserindo dados na tabela Estado
INSERT INTO Estado (sigla, nome) 
VALUES 
('SP', 'São Paulo'),
('RJ', 'Rio de Janeiro');

-- Inserindo dados na tabela Fornecedor
INSERT INTO Fornecedor (cnpj, nome, email) 
VALUES 
('12.345.678/0001-99', 'Tech Supplies', 'contato@techsupplies.com'),
('98.765.432/0001-11', 'Gadget Store', 'contato@gadgetstore.com');

-- Inserindo dados na tabela Lote
INSERT INTO Lote (dataFabricacao, quantidade, codigo) 
VALUES 
('2024-01-15', 100, 'L1001'),
('2024-02-20', 150, 'L1002');

-- Inserindo dados na tabela Municipio
INSERT INTO Municipio (estado_id, nome) 
VALUES 
(1, 'São Paulo'),
(2, 'Rio de Janeiro');

-- Inserindo dados na tabela Pessoa
INSERT INTO Pessoa (nome) 
VALUES 
('Carlos Lima'),
('Ana Costa');

-- Inserindo dados na tabela PessoaFisica
INSERT INTO PessoaFisica (id, sexo, cpf) 
VALUES 
(1, 1, '333.222.111-00'),
(2, 2, '777.888.999-00');

-- Inserindo dados na tabela PlacaDeVideo
INSERT INTO PlacaDeVideo (compatibilidade, energia, preco, vram, lote_id, categoria, descricao, modelo, resolucao) 
VALUES 
(1, 200, 2500.00, 8, 1, 'Gamer', 'RTX 3070', 'NVIDIA', '4K'),
(1, 180, 1500.00, 6, 2, 'Gamer', 'GTX 1660', 'NVIDIA', 'Full HD');

-- Inserindo dados na tabela TelefoneCliente
INSERT INTO TelefoneCliente (cliente_id, codigoArea, numero) 
VALUES 
(1, '11', '99999-8888'),
(2, '21', '98888-7777');

-- Inserindo dados na tabela TelefoneFornecedor
INSERT INTO TelefoneFornecedor (fornecedor_id, codigoArea, numero) 
VALUES 
(1, '11', '3222-1234'),
(2, '21', '3344-5678');
