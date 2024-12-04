-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- 1. Inserindo dados na tabela Usuario
INSERT INTO Usuario (perfil, senha, username) VALUES (1, 'senha123', 'usuario1');
INSERT INTO Usuario (perfil, senha, username) VALUES (2, 'senha456', 'usuario2');

-- 2. Inserindo dados na tabela Estado
INSERT INTO Estado (sigla, nome) VALUES ('SP', 'São Paulo');
INSERT INTO Estado (sigla, nome) VALUES ('RJ', 'Rio de Janeiro');

-- 3. Inserindo dados na tabela Municipio
INSERT INTO Municipio (estado_id, nome) VALUES (1, 'São Paulo');
INSERT INTO Municipio (estado_id, nome) VALUES (2, 'Rio de Janeiro');

-- 4. Inserindo dados na tabela Fornecedor
INSERT INTO Fornecedor (cnpj, email, nome) VALUES ('12345678000100', 'fornecedor1@example.com', 'Fornecedor 1');
INSERT INTO Fornecedor (cnpj, email, nome) VALUES ('98765432000199', 'fornecedor2@example.com', 'Fornecedor 2');

-- 5. Inserindo dados na tabela Funcionario
INSERT INTO Funcionario (dataNascimento, salario, statusFuncionario, id_usuario, cpf, nome, email) 
VALUES ('1990-01-01', 3500.50, 1, 1, '12345678901', 'Funcionario 1', 'funcionario1@example.com');
INSERT INTO Funcionario (dataNascimento, salario, statusFuncionario, id_usuario, cpf, nome, email) 
VALUES ('1985-02-02', 4200.75, 2, 2, '98765432109', 'Funcionario 2', 'funcionario2@example.com');

-- 6. Inserindo dados na tabela Cliente
INSERT INTO Cliente (dataNascimento, id_usuario, cpf, nome)
VALUES ('1992-03-03', 1, '11122233344', 'Cliente 1');
INSERT INTO Cliente (dataNascimento, id_usuario, cpf, nome)
VALUES ('1994-04-04', 2, '55566677788', 'Cliente 2');

-- 7. Inserindo dados na tabela Endereco
INSERT INTO Endereco (bairro, cep, cidade, estado, numero) VALUES ('Centro', '12345-678', 'São Paulo', 'SP', '100');
INSERT INTO Endereco (bairro, cep, cidade, estado, numero) VALUES ('Jardins', '87654-321', 'Rio de Janeiro', 'RJ', '200');

-- 8. Inserindo dados na tabela Cliente_Endereco
INSERT INTO Cliente_Endereco (Cliente_id, enderecos_id) VALUES (1, 1);
INSERT INTO Cliente_Endereco (Cliente_id, enderecos_id) VALUES (2, 2);

-- 10. Inserindo dados na tabela TelefoneCliente
INSERT INTO TelefoneCliente (codigoArea, numero) VALUES ('11', '987654321');
INSERT INTO TelefoneCliente (codigoArea, numero) VALUES ('21', '123456789');

-- 11. Inserindo dados na tabela Cliente_TelefoneCliente
INSERT INTO Cliente_TelefoneCliente (Cliente_id, telefones_id) VALUES (1, 1);
INSERT INTO Cliente_TelefoneCliente (Cliente_id, telefones_id) VALUES (2, 2);

-- 12. Inserindo dados na tabela TelefoneFornecedor
INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('11', '998877665');
INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('21', '556677889');

-- 13. Inserindo dados na tabela Fornecedor_TelefoneFornecedor
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (1, 1);
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (2, 2);

-- 17. Inserindo dados na tabela Memoria
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (8, 256, 1600, 'GDDR6');
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (16, 320, 2400, 'GDDR6');

-- 18. Inserindo dados na tabela Tamanho
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (200, 100, 50);
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (250, 120, 60);

-- 19. Inserindo dados na tabela PlacaDeVideo
INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.5, 2.0, 1, 100, 2, 1500.00, true, 1, 1, 1, 'Gaming', 'Placa high-end', 'Modelo X', '4K');
INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.6, 2.1, 2, 120, 3, 1800.00, true, 2, 2, 2, 'Professional', 'Placa profissional', 'Modelo Y', '4K');

-- 20. Inserindo dados na tabela SaidaVideo
INSERT INTO SaidaVideo (quantidade, id_saidavideo, tipo) VALUES (2, 1, 'HDMI');
INSERT INTO SaidaVideo (quantidade, id_saidavideo, tipo) VALUES (1, 2, 'DisplayPort');

-- Lote associado à PlacaDeVideo com ID 1
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-01-15', 50, 1, 'LOTE001');

-- Outro lote associado à PlacaDeVideo com ID 1
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-03-10', 30, 1, 'LOTE002');

-- Lote associado à PlacaDeVideo com ID 2
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-02-20', 40, 2, 'LOTE003');

-- Outro lote associado à PlacaDeVideo com ID 2
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-04-05', 20, 2, 'LOTE004');

INSERT INTO Usuario (username, senha, email, perfil) 
VALUES ('admin_user', 'xzp7AP+QWnPSmgzJYRBVWiUX7nsVqbfYVtuvPOPw2TRuCrd6T8+/fEhQoxtROBveRpbEyyBB/Xlxxc+rWHzmzQ==', 'admin@example.com', 1);





