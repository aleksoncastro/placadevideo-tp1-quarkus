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
INSERT INTO Fornecedor (cnpj, email, nome) VALUES ('12345678000100', 'fornecedor1@example.com', 'Fornecedor 1');
INSERT INTO Fornecedor (cnpj, email, nome) VALUES ('98765432000199', 'fornecedor2@example.com', 'Fornecedor 2');

INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('11', '998877665');
INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('21', '556677889');

-- 13. Inserindo dados na tabela Fornecedor_TelefoneFornecedor
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (1, 1);
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (2, 2);

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
INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.5, 2.0, 1, 100, 2, 1300.00, true, 1, 1, 1, 'Gaming', 'Placa high-end', 'rx6600', '1080p');
INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.6, 2.1, 2, 120, 3, 2200.00, true, 2, 2, 2, 'Gaming', 'Placa profissional', '4060ti', '1440p');
-- Inserindo placas de vídeo a partir do ID 3
INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.3, 1.7, 1, 75, 1, 900.00, false, 1, 3, 3, 'Entry-Level', 'Placa de entrada econômica', 'GTX 1650', '1080p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.8, 2.3, 2, 160, 2, 2500.00, true, 2, 4, 4, 'Gaming', 'Ótimo custo-benefício', 'RTX 3060', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.9, 2.4, 2, 170, 3, 3200.00, true, 1, 5, 5, 'Gaming', 'Alta performance para jogos', 'RTX 3070', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.1, 2.6, 3, 250, 3, 4500.00, true, 2, 6, 6, 'Gaming', 'Desempenho extremo', 'RTX 3080', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.2, 2.7, 3, 280, 3, 5500.00, true, 1, 7, 7, 'Gaming', 'Máximo desempenho em 4K', 'RTX 3090', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.3, 2.8, 3, 320, 3, 6500.00, true, 2, 8, 8, 'Gaming', 'Placa de elite para entusiastas', 'RTX 4090', '8K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.4, 1.9, 2, 110, 2, 1900.00, false, 1, 9, 9, 'Workstation', 'Voltada para produtividade', 'Quadro P2000', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.5, 2.0, 2, 150, 2, 2700.00, false, 2, 10, 10, 'Workstation', 'Uso profissional e engenharia', 'Quadro RTX 4000', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.7, 2.1, 2, 160, 2, 2900.00, true, 1, 11, 11, 'Gaming', 'Ótima opção para 1440p', 'RX 6700 XT', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.8, 2.2, 3, 200, 3, 3800.00, true, 2, 12, 12, 'Gaming', 'Desempenho premium', 'RX 6800 XT', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.0, 2.5, 3, 300, 3, 4700.00, true, 1, 13, 13, 'Gaming', 'Poder extremo para jogos', 'RX 6900 XT', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.9, 2.3, 2, 140, 2, 1800.00, false, 2, 14, 14, 'Entry-Level', 'Boa opção de custo-benefício', 'GTX 1660 Super', '1080p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.6, 2.0, 1, 85, 1, 800.00, false, 1, 15, 15, 'Entry-Level', 'Modelo acessível para jogos', 'GTX 1050 Ti', '1080p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.0, 2.5, 3, 260, 3, 4200.00, true, 2, 16, 16, 'Gaming', 'Potência para games AAA', 'RTX 4080', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.8, 2.2, 2, 120, 2, 2100.00, true, 1, 17, 17, 'Gaming', 'Boa performance por um preço justo', 'RX 7600', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.7, 2.0, 2, 130, 2, 2400.00, true, 2, 18, 18, 'Gaming', 'Excelente para Ray Tracing', 'RTX 4060', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.1, 2.6, 3, 275, 3, 5000.00, true, 1, 19, 19, 'Gaming', 'Ultra desempenho', 'RTX 4090', '8K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, compatibilidade, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.5, 2.0, 2, 105, 2, 1600.00, true, 2, 20, 20, 'Gaming', 'Placa intermediária com bom custo', 'RX 6650 XT', '1080p');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem) 
VALUES (1, 'https://img.terabyteshop.com.br/produto/g/placa-de-video-asrock-radeon-rx-6600-challenger-d-8gb-gddr6-fsr-ray-tracing-90-ga2rzz-00uanf_133352.jpg');



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

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
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
