-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;



-- 2. Inserindo dados na tabela Estado
INSERT INTO Estado (sigla, nome) VALUES ('SP', 'São Paulo');
INSERT INTO Estado (sigla, nome) VALUES ('RJ', 'Rio de Janeiro');
INSERT INTO Estado (sigla, nome) VALUES ('AC', 'Acre');
INSERT INTO Estado (sigla, nome) VALUES ('AL', 'Alagoas');
INSERT INTO Estado (sigla, nome) VALUES ('AP', 'Amapá');
INSERT INTO Estado (sigla, nome) VALUES ('AM', 'Amazonas');
INSERT INTO Estado (sigla, nome) VALUES ('BA', 'Bahia');
INSERT INTO Estado (sigla, nome) VALUES ('CE', 'Ceará');
INSERT INTO Estado (sigla, nome) VALUES ('DF', 'Distrito Federal');
INSERT INTO Estado (sigla, nome) VALUES ('ES', 'Espírito Santo');
INSERT INTO Estado (sigla, nome) VALUES ('GO', 'Goiás');
INSERT INTO Estado (sigla, nome) VALUES ('MA', 'Maranhão');
INSERT INTO Estado (sigla, nome) VALUES ('MT', 'Mato Grosso');
INSERT INTO Estado (sigla, nome) VALUES ('MS', 'Mato Grosso do Sul');
INSERT INTO Estado (sigla, nome) VALUES ('MG', 'Minas Gerais');
INSERT INTO Estado (sigla, nome) VALUES ('PA', 'Pará');
INSERT INTO Estado (sigla, nome) VALUES ('PB', 'Paraíba');
INSERT INTO Estado (sigla, nome) VALUES ('PR', 'Paraná');
INSERT INTO Estado (sigla, nome) VALUES ('PE', 'Pernambuco');
INSERT INTO Estado (sigla, nome) VALUES ('PI', 'Piauí');
INSERT INTO Estado (sigla, nome) VALUES ('RN', 'Rio Grande do Norte');
INSERT INTO Estado (sigla, nome) VALUES ('RS', 'Rio Grande do Sul');
INSERT INTO Estado (sigla, nome) VALUES ('RO', 'Rondônia');
INSERT INTO Estado (sigla, nome) VALUES ('RR', 'Roraima');
INSERT INTO Estado (sigla, nome) VALUES ('SC', 'Santa Catarina');
INSERT INTO Estado (sigla, nome) VALUES ('SE', 'Sergipe');
INSERT INTO Estado (sigla, nome) VALUES ('TO', 'Tocantins');


-- 3. Inserindo dados na tabela Municipio
INSERT INTO Municipio (estado_id, nome) VALUES (1, 'São Paulo');
INSERT INTO Municipio (estado_id, nome) VALUES (2, 'Rio de Janeiro');

-- 4. Inserindo dados na tabela Fornecedor
INSERT INTO Fornecedor (cnpj, email, nome) VALUES ('11111111000100', 'gigabyte@gmail.com', 'Gigabyte');
INSERT INTO Fornecedor (cnpj, email, nome) VALUES ('22222222000100', 'aorus@gmail.com', 'Aorus');
INSERT INTO Fornecedor (cnpj, email, nome) VALUES ('33333333000100', 'asus@gmail.com', 'Asus');
INSERT INTO Fornecedor (cnpj, email, nome) VALUES ('44444444000100', 'palit@gmail.com', 'Palit');
INSERT INTO Fornecedor (cnpj, email, nome) VALUES ('55555555000100', 'msi@gmail.com', 'MSI');
INSERT INTO Fornecedor (cnpj, email, nome) VALUES ('66666666000100', 'galax@gmail.com', 'Galax');

INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('11', '912345678'); -- Gigabyte
INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('21', '923456789'); -- Aorus
INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('31', '934567890'); -- Asus
INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('41', '945678901'); -- Palit
INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('51', '956789012'); -- MSI
INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('61', '967890123'); -- Galax

INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (1, 1); -- Gigabyte
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (2, 2); -- Aorus
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (3, 3); -- Asus
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (4, 4); -- Palit
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (5, 5); -- MSI
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (6, 6); -- Galax

-- 17. Inserindo dados na tabela Memoria
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (8, 256, 1600, 'GDDR6');
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (16, 320, 2400, 'GDDR6');
-- Inserindo memórias a partir do ID 3
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (8, 256, 1600, 'GDDR6'); -- ID 3
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (16, 320, 2400, 'GDDR6'); -- ID 4
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (8, 256, 1600, 'GDDR6'); -- ID 5
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (16, 320, 2400, 'GDDR6'); -- ID 6
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (8, 256, 1600, 'GDDR6'); -- ID 7
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (16, 320, 2400, 'GDDR6'); -- ID 8
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (8, 256, 1600, 'GDDR6'); -- ID 9
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (16, 320, 2400, 'GDDR6'); -- ID 10
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (8, 256, 1600, 'GDDR6'); -- ID 11
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (16, 320, 2400, 'GDDR6'); -- ID 12
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (8, 256, 1600, 'GDDR6'); -- ID 13
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (16, 320, 2400, 'GDDR6'); -- ID 14
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (8, 256, 1600, 'GDDR6'); -- ID 15
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (16, 320, 2400, 'GDDR6'); -- ID 16
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (8, 256, 1600, 'GDDR6'); -- ID 17
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (16, 320, 2400, 'GDDR6'); -- ID 18
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (8, 256, 1600, 'GDDR6'); -- ID 19
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (16, 320, 2400, 'GDDR6'); -- ID 20
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (8, 256, 1600, 'GDDR6'); -- ID 21
INSERT INTO Memoria (capacidade, larguraBanda, velocidadeMemoria, tipoMemoria) VALUES (16, 320, 2400, 'GDDR6'); -- ID 22

-- 18. Inserindo dados na tabela Tamanho
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (200, 100, 50);
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (250, 120, 60);
-- Inserindo tamanhos a partir do ID 3
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (200, 100, 50); -- ID 3
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (250, 120, 60); -- ID 4
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (220, 110, 55); -- ID 5
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (240, 130, 70); -- ID 6
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (210, 115, 65); -- ID 7
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (230, 125, 75); -- ID 8
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (250, 130, 80); -- ID 9
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (240, 120, 65); -- ID 10
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (260, 135, 85); -- ID 11
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (250, 140, 90); -- ID 12
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (220, 120, 60); -- ID 13
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (210, 115, 55); -- ID 14
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (230, 125, 65); -- ID 15
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (240, 130, 75); -- ID 16
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (250, 135, 80); -- ID 17
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (220, 110, 65); -- ID 18
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (240, 120, 70); -- ID 19
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (250, 130, 80); -- ID 20
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (260, 140, 90); -- ID 21
INSERT INTO Tamanho (altura, comprimento, largura) VALUES (230, 120, 75); -- ID 22


-- 20. Inserindo dados na tabela SaidaVideo
INSERT INTO SaidaVideo (quantidade, tipo) VALUES (3, 'HDMI');
INSERT INTO SaidaVideo (quantidade, tipo) VALUES (4, 'DisplayPort');

-- 19. Inserindo dados na tabela PlacaDeVideo
INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.5, 2.0, 'PCIe 4.0 x8', 100, 2, 1300.00, true, 3, 1, 1, 'Gaming', 'Placa high-end', 'RX6600', '1080p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.6, 2.1, 'PCIe 4.0 x16', 120, 3, 2200.00, true, 1, 2, 2, 'Gaming', 'Placa profissional', '4060ti', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.3, 1.7, 'PCIe 3.0 x16', 75, 1, 900.00, false, 3, 3, 3, 'Entry-Level', 'Placa de entrada econômica', 'GTX 1650', '1080p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.8, 2.3, 'PCIe 4.0 x16', 160, 2, 2500.00, true, 4, 4, 4, 'Gaming', 'Ótimo custo-benefício', 'RTX 3060', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.9, 2.4, 'PCIe 4.0 x16', 170, 3, 3200.00, true, 5, 5, 5, 'Gaming', 'Alta performance para jogos', 'RTX 3070', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.1, 2.6, 'PCIe 4.0 x16', 250, 3, 4500.00, true, 6, 6, 6, 'Gaming', 'Desempenho extremo', 'RTX 3080', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.2, 2.7, 'PCIe 4.0 x16', 280, 3, 5500.00, true, 1, 7, 7, 'Gaming', 'Máximo desempenho em 4K', 'RTX 3090', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.3, 2.8, 'PCIe 5.0 x16', 320, 3, 6500.00, true, 2, 8, 8, 'Gaming', 'Placa de elite para entusiastas', 'RTX 4090', '8K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.4, 1.9, 'PCIe 3.0 x16', 110, 2, 1900.00, false, 3, 9, 9, 'Workstation', 'Voltada para produtividade', 'Quadro P2000', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.5, 2.0, 'PCIe 3.0 x16', 150, 2, 2700.00, false, 4, 10, 10, 'Workstation', 'Uso profissional e engenharia', 'Quadro RTX 4000', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.7, 2.1, 'PCIe 4.0 x16', 160, 2, 2900.00, true, 5, 11, 11, 'Gaming', 'Ótima opção para 1440p', 'RX 6700 XT', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.8, 2.2, 'PCIe 4.0 x16', 200, 3, 3800.00, true, 6, 12, 12, 'Gaming', 'Desempenho premium', 'RX 6800 XT', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.0, 2.5, 'PCIe 4.0 x16', 300, 3, 4700.00, true, 1, 13, 13, 'Gaming', 'Poder extremo para jogos', 'RX 6900 XT', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.9, 2.3, 'PCIe 3.0 x16', 140, 2, 1800.00, false, 2, 14, 14, 'Entry-Level', 'Boa opção de custo-benefício', 'GTX 1660 Super', '1080p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.6, 2.0, 'PCIe 3.0 x16', 85, 1, 800.00, false, 3, 15, 15, 'Entry-Level', 'Modelo acessível para jogos', 'GTX 1050 Ti', '1080p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.0, 2.5, 'PCIe 5.0 x16', 260, 3, 4200.00, true, 4, 16, 16, 'Gaming', 'Potência para games AAA', 'RTX 4080', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.8, 2.2, 'PCIe 4.0 x16', 120, 2, 2100.00, true, 5, 17, 17, 'Gaming', 'Boa performance por um preço justo', 'RX 7600', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.7, 2.0, 'PCIe 4.0 x16', 130, 2, 2400.00, true, 6, 18, 18, 'Gaming', 'Excelente para Ray Tracing', 'RTX 4060', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.1, 2.6, 'PCIe 5.0 x16', 275, 3, 5000.00, true, 1, 19, 19, 'Gaming', 'Ultra desempenho', 'RTX 4090', '8K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.5, 2.0, 'PCIe 4.0 x8', 105, 2, 1600.00, true, 2, 20, 20, 'Gaming', 'Placa intermediária com bom custo', 'RX 6650 XT', '1080p');

-- Associando saídas de vídeo para a placa RX 6600 (ID 1)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 2, 1), ('DisplayPort 1.4', 1, 1);

-- Associando saídas de vídeo para a placa 4060 Ti (ID 2)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 2, 2), ('DisplayPort 1.4', 1, 2);

-- Associando saídas de vídeo para a placa GTX 1650 (ID 3)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.0', 1, 3), ('DisplayPort 1.4', 1, 3);

-- Associando saídas de vídeo para a placa RTX 3060 (ID 4)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 1, 4), ('DisplayPort 1.4', 2, 4);

-- Associando saídas de vídeo para a placa RTX 3070 (ID 5)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 1, 5), ('DisplayPort 1.4', 3, 5);

-- Associando saídas de vídeo para a placa RTX 3080 (ID 6)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 2, 6), ('DisplayPort 1.4', 2, 6);

-- Associando saídas de vídeo para a placa RTX 3090 (ID 7)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 2, 7), ('DisplayPort 1.4', 2, 7);

-- Associando saídas de vídeo para a placa RTX 4090 (ID 8)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 2, 8), ('DisplayPort 1.4', 2, 8);

-- Associando saídas de vídeo para a placa Quadro P2000 (ID 9)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('DisplayPort 1.2', 4, 9);

-- Associando saídas de vídeo para a placa Quadro RTX 4000 (ID 10)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('DisplayPort 1.4', 4, 10);

-- Associando saídas de vídeo para a placa RX 6700 XT (ID 11)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 1, 11), ('DisplayPort 1.4', 2, 11);

-- Associando saídas de vídeo para a placa RX 6800 XT (ID 12)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 1, 12), ('DisplayPort 1.4', 3, 12);

-- Associando saídas de vídeo para a placa RX 6900 XT (ID 13)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 1, 13), ('DisplayPort 1.4', 3, 13);

-- Associando saídas de vídeo para a placa GTX 1660 Super (ID 14)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.0', 1, 14), ('DisplayPort 1.4', 1, 14);

-- Associando saídas de vídeo para a placa GTX 1050 Ti (ID 15)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.0', 1, 15), ('DisplayPort 1.4', 1, 15);

-- Associando saídas de vídeo para a placa RTX 4080 (ID 16)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 2, 16), ('DisplayPort 1.4', 2, 16);

-- Associando saídas de vídeo para a placa RX 7600 (ID 17)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 1, 17), ('DisplayPort 1.4', 1, 17);

-- Associando saídas de vídeo para a placa RTX 4060 (ID 18)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 1, 18), ('DisplayPort 1.4', 2, 18);

-- Associando saídas de vídeo para a placa RTX 4090 (ID 19)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 2, 19), ('DisplayPort 1.4', 2, 19);

-- Associando saídas de vídeo para a placa RX 6650 XT (ID 20)
INSERT INTO SaidaVideo (tipo, quantidade, placadevideo_id)
VALUES ('HDMI 2.1', 1, 20), ('DisplayPort 1.4', 1, 20);



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

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('admin_user', 'xzp7AP+QWnPSmgzJYRBVWiUX7nsVqbfYVtuvPOPw2TRuCrd6T8+/fEhQoxtROBveRpbEyyBB/Xlxxc+rWHzmzQ==', 'admin@example.com', '5555555555', 1);
                                                                                                                                       
INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('funcionario1', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'funcionario1@gmail.com', '1111111111', 1);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('funcionario2', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'funcionario2@gmail.com', '2222222222', 1);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('funcionario3', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'funcionario3@gmail.com', '3333333333', 1);

INSERT INTO funcionario (nome,  datanascimento, salario, id_usuario, statusfuncionario)
VALUES ('Carlos Silva', '1990-05-15', 3500.00, 1, 1);

INSERT INTO funcionario (nome, datanascimento,  salario, id_usuario, statusfuncionario)
VALUES ('Mariana Oliveira', '1985-10-22',  4200.00, 2, 1);

INSERT INTO funcionario (nome, datanascimento, salario, id_usuario, statusfuncionario)
VALUES ('João Mendes', '1992-07-08', 3100.00, 3, 1);

INSERT INTO funcionario (nome, datanascimento, salario, id_usuario, statusfuncionario)
VALUES ('Ana Paula Souza', '1988-12-03', 4800.00, 4, 1);

/*INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (1, '/placasdevideo_imagens/rx6600.jpeg'),
  (1, '/placasdevideo_imagens/rx6600.jpeg'),
  (1, '/placasdevideo_imagens/rx6600.jpeg');
  (1, '/placasdevideo_imagens/rx6600.jpeg');
INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (2, '/placasdevideo_imagens/4060ti.jpeg'),
  (2, '/placasdevideo_imagens/4060ti.jpeg'),
  (2, '/placasdevideo_imagens/4060ti.jpeg'),
  (2, '/placasdevideo_imagens/4060ti.jpeg');
*/


/* INSERT INTO telefone_funcionario (ddd, numero, funcionario_id)
VALUES ('63', '99988-7766', 1);

INSERT INTO telefone_funcionario (ddd, numero, funcionario_id)
VALUES ('11', '98877-6655', 2);

INSERT INTO telefone_funcionario (ddd, numero, funcionario_id)
VALUES ('62', '99888-1122', 3);

INSERT INTO telefone_funcionario (ddd, numero, funcionario_id)
VALUES ('62', '98765-4321', 3);

INSERT INTO telefone_funcionario (ddd, numero, funcionario_id)
VALUES ('21', '99666-3344', 4);

INSERT INTO telefone_funcionario (ddd, numero, funcionario_id)
VALUES ('21', '99444-5566', 4);*/
