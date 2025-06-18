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
INSERT INTO Fornecedor (cnpj, email, nome) VALUES ('77776666000100', 'asrock@gmail.com', 'Asrock');

INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('11', '912345678'); -- Gigabyte
INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('21', '923456789'); -- Aorus
INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('31', '934567890'); -- Asus
INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('41', '945678901'); -- Palit
INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('51', '956789012'); -- MSI
INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('61', '967890123'); -- Galax
INSERT INTO TelefoneFornecedor (codigoArea, numero) VALUES ('64', '967090623'); -- Asrock 

INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (1, 1); -- Gigabyte
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (2, 2); -- Aorus
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (3, 3); -- Asus
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (4, 4); -- Palit
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (5, 5); -- MSI
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (6, 6); -- Galax
INSERT INTO Fornecedor_TelefoneFornecedor (Fornecedor_id, telefones_id) VALUES (7, 7); -- Asrock

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
-- 19. Inserindo dados na tabela PlacaDeVideo
INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.5, 2.0, 'PCIe 4.0 x8', 100, 2, 1300.00, true, 3, 1, 1, 'Gaming', 'A Placa de Vídeo AMD Radeon RX 6600 é a escolha perfeita para gamers que buscam uma experiência de jogo fluida e envolvente em 1080p com um excelente custo-benefício. Equipada com 8GB de memória GDDR6 de alta velocidade, esta GPU oferece desempenho otimizado para os títulos mais recentes e e-sports competitivos. Com um clock base de 1.5GHz e um impressionante boost de até 2.0GHz, a RX 6600 garante que seus jogos rodem com a fluidez e a responsividade que você precisa para dominar o campo de batalha virtual.\n
 Construída sobre a arquitetura AMD RDNA 2, a RX 6600 conta com um eficiente barramento PCIe 4.0 x8, proporcionando alta largura de banda para uma comunicação rápida com sua CPU. Seu consumo de energia é otimizado em apenas 100W, tornando-a uma opção eficiente para diversos sistemas, e o design de duas ventoinhas (Dual Fan) garante uma refrigeração eficaz e silenciosa, mantendo a estabilidade da placa mesmo sob cargas intensas.\n
  O suporte a Ray Tracing eleva o nível visual dos seus jogos, proporcionando iluminação, sombras e reflexos mais realistas e detalhados. Mergulhe em mundos virtuais com uma imersão sem precedentes, sem comprometer a performance. Além disso, a tecnologia AMD FidelityFX Super Resolution (FSR) está pronta para impulsionar ainda mais seus frames, garantindo visuais nítidos e jogabilidade ultra suave. Se você busca uma placa de vídeo de alta performance para 1080p, a RX 6600 é a solução ideal para elevar seu setup gamer!', 'RX6600', '1080p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.6, 2.1, 'PCIe 4.0 x16', 120, 3, 2200.00, true, 1, 2, 2, 'Gaming', 'Eleve sua experiência de jogo e criação de conteúdo ao próximo nível com a Placa de Vídeo NVIDIA GeForce RTX 4060 Ti. Projetada para dominar em 1440p, esta GPU de última geração oferece desempenho excepcional, gráficos ultrarrealistas e funcionalidades avançadas que transformam seu PC. Equipada com 8GB de memória GDDR6 de alta velocidade, a RTX 4060 Ti entrega a potência necessária para executar os títulos mais exigentes da atualidade com configurações máximas. Com um clock base de 1.6GHz e um impressionante boost de até 2.1GHz, você experimentará uma jogabilidade incrivelmente fluida e responsiva. O barramento PCIe 4.0 x16 garante a máxima largura de banda, otimizando a comunicação entre a placa e o restante do seu sistema. O consumo de energia de 120W reflete uma excelente eficiência energética, ideal para quem busca performance sem comprometer a conta de luz. Um dos grandes destaques da RTX 4060 Ti é seu suporte a Ray Tracing de segunda geração, que proporciona iluminação, sombras e reflexos hiper-realistas, mergulhando você em mundos virtuais com detalhes cinematográficos. Além disso, a revolucionária tecnologia NVIDIA DLSS 3 (Deep Learning Super Sampling) utiliza inteligência artificial para aumentar significativamente as taxas de quadros, mantendo a nitidez da imagem. O sistema de refrigeração com três ventoinhas (Triplo Fan) garante que a placa opere em temperaturas ideais mesmo sob carga intensa, oferecendo estabilidade e durabilidade. Seja para jogos competitivos, streaming ou criação de conteúdo, a RTX 4060 Ti é a escolha definitiva para quem busca o máximo em tecnologia e desempenho.', '4060ti', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.3, 1.7, 'PCIe 3.0 x16', 75, 1, 900.00, false, 1, 3, 3, 'Entry-Level', 'Entre no mundo dos games com a Placa de Vídeo NVIDIA GeForce GTX 1650. Esta GPU é a porta de entrada perfeita para uma experiência de jogo sólida em 1080p, oferecendo um excelente equilíbrio entre desempenho e economia. Com 4GB de memória GDDR5, a GTX 1650 é capaz de rodar uma vasta gama de títulos populares e e-sports com fluidez, tornando-a uma opção ideal para quem está montando seu primeiro PC gamer ou busca um upgrade acessível. A GTX 1650 opera com um clock base de 1.3GHz e alcança um boost de até 1.7GHz, garantindo uma performance consistente para suas aventuras virtuais. Seu barramento PCIe 3.0 x16 assegura compatibilidade com a maioria dos sistemas modernos e mais antigos, e o consumo de energia de apenas 75W a torna incrivelmente eficiente, dispensando na maioria dos casos a necessidade de conectores de alimentação adicionais na fonte. Com um design compacto de uma ventoinha (Single Fan), a GTX 1650 se encaixa facilmente em gabinetes menores e garante uma refrigeração eficiente para manter a estabilidade durante suas sessões de jogo. Embora não possua suporte a Ray Tracing, ela se destaca pela sua capacidade de entregar uma experiência de jogo confiável e divertida para o público que busca um setup mais econômico sem abrir mão da qualidade. A GTX 1650 é a prova de que é possível ter uma ótima experiência gamer com um investimento inteligente.', 'GTX 1650', '1080p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.8, 2.3, 'PCIe 4.0 x16', 160, 2, 2500.00, true, 4, 4, 4, 'Gaming', 'A Placa de Vídeo NVIDIA GeForce RTX 3060 é a escolha definitiva para jogadores que buscam um ótimo custo-benefício para dominar em 1440p. Equipada com impressionantes 12GB de memória GDDR6, esta GPU oferece uma capacidade de processamento de texturas e dados que a coloca em destaque para jogos modernos e tarefas de criação de conteúdo. Com um clock base de 1.8GHz e um boost de até 2.3GHz, a RTX 3060 garante uma jogabilidade incrivelmente fluida e responsiva, mesmo nos títulos mais exigentes. Construída sobre a arquitetura NVIDIA Ampere, a RTX 3060 utiliza o eficiente barramento PCIe 4.0 x16, assegurando a máxima largura de banda e comunicação ultrarrápida com seu processador. Com um consumo de energia otimizado de 160W, ela oferece um equilíbrio perfeito entre potência e eficiência. O sistema de refrigeração com duas ventoinhas (Dual Fan) foi projetado para manter a placa em temperaturas ideais, proporcionando estabilidade e longevidade mesmo durante as maratonas de jogos. O grande diferencial da RTX 3060 é seu suporte completo a Ray Tracing, que simula o comportamento físico da luz para criar gráficos incrivelmente realistas com sombras, reflexos e iluminação global de tirar o fôlego. Além disso, a tecnologia NVIDIA DLSS (Deep Learning Super Sampling) impulsiona as taxas de quadros através da renderização por IA, garantindo visuais nítidos e desempenho superior. Se você busca uma placa que entrega gráficos de ponta, alta performance e um excelente retorno sobre o investimento para jogos em 1440p, a RTX 3060 é a sua melhor aposta!', 'RTX 3060', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.9, 2.4, 'PCIe 4.0 x16', 170, 3, 3200.00, true, 5, 5, 5, 'Gaming', 'Experimente o auge da alta performance para jogos em 1440p com a Placa de Vídeo NVIDIA GeForce RTX 3070. Projetada para os jogadores mais exigentes, esta GPU oferece uma combinação poderosa de velocidade, eficiência e gráficos de tirar o fôlego. Com 8GB de memória GDDR6, a RTX 3070 garante que você tenha a capacidade de memória necessária para os jogos mais modernos e aplicativos de criação. Com um clock base de 1.9GHz e um impressionante boost de até 2.4GHz, esta placa proporciona uma jogabilidade fluida e sem interrupções, mesmo nas cenas mais complexas e cheias de ação. O barramento PCIe 4.0 x16 maximiza a taxa de transferência de dados, assegurando que não haja gargalos entre sua GPU e o restante do sistema. Com um consumo de energia de 170W, a RTX 3070 entrega toda a sua potência com uma eficiência notável. O sistema de refrigeração com três ventoinhas (Triplo Fan) é um dos seus pontos fortes, garantindo que a placa permaneça fria e estável mesmo sob cargas de trabalho intensas, prolongando a vida útil do componente e mantendo o desempenho máximo. O suporte a Ray Tracing de segunda geração permite simulações de luz incrivelmente realistas, transformando seus jogos em experiências visuais cinematográficas. E com o NVIDIA DLSS (Deep Learning Super Sampling), você obtém frames mais elevados e visuais nítidos, impulsionados pela inteligência artificial. Para quem busca uma experiência de jogo premium em 1440p e quer estar à frente da tecnologia, a RTX 3070 é a escolha perfeita.', 'RTX 3070', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.1, 2.6, 'PCIe 4.0 x16', 250, 3, 4500.00, true, 6, 6, 6, 'Gaming', 'Prepare-se para o desempenho extremo em 4K com a Placa de Vídeo NVIDIA GeForce RTX 3080. Esta GPU é a força motriz para os entusiastas e jogadores profissionais que não aceitam menos que o melhor. Equipada com 10GB de memória GDDR6X, a RTX 3080 oferece uma largura de banda de memória superior, essencial para as texturas de alta resolução e as demandas gráficas dos jogos em 4K e aplicações de ponta. Com um clock base de 2.1GHz e um boost de até 2.6GHz, a RTX 3080 é capaz de entregar taxas de quadros surpreendentes e uma jogabilidade ultra suave nos títulos mais exigentes. O barramento PCIe 4.0 x16 garante a máxima velocidade de comunicação, eliminando qualquer gargalo de dados. Com um consumo de energia de 250W, esta placa está pronta para lidar com qualquer desafio, proporcionando potência de sobra. O sistema de refrigeração com três ventoinhas (Triplo Fan) é robusto e altamente eficiente, mantendo a temperatura da GPU sob controle mesmo durante as sessões de jogo mais longas e intensas. Isso garante não apenas a estabilidade do sistema, mas também a longevidade da placa. O suporte completo a Ray Tracing de segunda geração transforma a iluminação, sombras e reflexos, criando mundos virtuais com realismo incomparável. E com o NVIDIA DLSS (Deep Learning Super Sampling), você obtém um aumento massivo na taxa de quadros sem sacrificar a qualidade da imagem, permitindo uma experiência 4K sem compromissos. A RTX 3080 é a placa ideal para quem busca o ápice da performance gráfica e o melhor em tecnologia de jogos.', 'RTX 3080', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.2, 2.7, 'PCIe 4.0 x16', 280, 3, 5500.00, true, 1, 7, 7, 'Gaming', 'A Placa de Vídeo NVIDIA GeForce RTX 3090 representa o máximo desempenho em 4K e além, sendo uma verdadeira "Big Ferocious GPU" (BFGPU) projetada para os mais exigentes gamers e criadores de conteúdo. Com uma colossal capacidade de 24GB de memória GDDR6X, esta placa é um monstro em termos de processamento de dados e renderização, ideal para jogos em altíssima resolução, edições de vídeo 8K, modelagem 3D complexa e projetos de inteligência artificial. Com um clock base de 2.2GHz e um impressionante boost de até 2.7GHz, a RTX 3090 entrega uma performance incomparável, garantindo jogabilidade ultra fluida e responsividade instantânea em qualquer cenário. O barramento PCIe 4.0 x16 assegura a mais alta velocidade de transferência de dados, otimizando cada ciclo de processamento. Com um consumo de energia de 280W, esta placa é uma usina de força que exige uma fonte de alimentação robusta para extrair todo o seu potencial. O sistema de refrigeração com três ventoinhas (Triplo Fan) é meticulosamente projetado para dissipar o calor de forma eficaz, mantendo a temperatura da GPU sob controle mesmo em cargas extremas, o que é crucial para sua estabilidade e durabilidade. O suporte completo a Ray Tracing de segunda geração eleva a imersão a um patamar cinematográfico, com iluminação e reflexos que reagem como no mundo real. E com o NVIDIA DLSS (Deep Learning Super Sampling), você desfruta de taxas de quadros absurdamente altas sem qualquer perda de qualidade visual, tornando a experiência em 4K e até 8K verdadeiramente impecável. Se você busca o que há de mais avançado em poder gráfico, a RTX 3090 é a escolha definitiva.', 'RTX 3090', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.3, 2.8, 'PCIe 5.0 x16', 320, 3, 6500.00, true, 2, 8, 8, 'Gaming', 'A Placa de Vídeo NVIDIA GeForce RTX 4090 AORUS Master é o ápice da engenharia gráfica, combinando desempenho extremo, engenharia de ponta e design premium para oferecer uma experiência visual sem precedentes em 8K. Esta GPU não é apenas uma placa, é uma obra-prima de tecnologia, projetada para os gamers mais entusiastas e profissionais que exigem o máximo em potência, eficiência e qualidade gráfica. Com uma capacidade colossal de 24GB de memória GDDR6X de altíssima velocidade e um barramento de 384 bits, a RTX 4090 AORUS Master oferece uma taxa de transferência de dados inigualável. Isso é crucial para jogos em resoluções ultra-altas como 8K, para a criação de conteúdo complexo, renderização 3D em tempo real e o desenvolvimento de aplicações exigentes em IA. Baseada na avançada arquitetura NVIDIA Ada Lovelace, esta placa traz suporte completo ao Ray Tracing de terceira geração, simulando o comportamento físico da luz para criar gráficos ultrarrealistas com iluminação, sombras e reflexos que desafiam a percepção. Além disso, a revolucionária tecnologia NVIDIA DLSS 3 (Deep Learning Super Sampling), exclusiva da série RTX 40, utiliza IA para gerar frames adicionais de alta qualidade, multiplicando suas taxas de quadros e entregando uma fluidez impressionante mesmo nas configurações mais exigentes. O sistema de refrigeração WINDFORCE é uma maravilha da engenharia térmica, projetado para manter a temperatura ideal mesmo sob carga intensa. Ele conta com três ventoinhas de alta eficiência com design de lâmina exclusivo, rotação alternada para reduzir turbulência, heatpipes compostos de cobre que tocam diretamente a GPU e uma câmara de vapor massiva para dissipação de calor superior. O backplate reforçado não só protege o PCB, mas também auxilia na refrigeração passiva. Além do desempenho bruto, a AORUS Master apresenta um design robusto e uma iluminação RGB personalizável através do software RGB Fusion 2.0, permitindo que você sincronize os efeitos de luz com outros componentes do seu setup gamer, criando um visual único e impressionante. Com sua BIOS dupla, você pode alternar entre os modos OC e Silent para otimizar a performance ou o silêncio. A Placa de Vídeo RTX 4090 AORUS Master é o investimento definitivo para quem busca potência inigualável, eficiência térmica e o máximo em qualidade gráfica, elevando sua experiência de PC a um novo patamar.', 'RTX 4090', '8K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.4, 1.9, 'PCIe 3.0 x16', 110, 2, 1900.00, false, 3, 9, 9, 'Workstation', 'A Placa de Vídeo NVIDIA Quadro P2000 é a ferramenta essencial para profissionais que buscam produtividade e precisão em suas workstations. Projetada especificamente para aplicações profissionais, esta GPU é ideal para tarefas como design assistido por computador (CAD), modelagem 3D, edição de vídeo, simulações e processamento de imagens em 1440p. Com 5GB de memória GDDR5, a Quadro P2000 oferece a capacidade de memória necessária para lidar com grandes conjuntos de dados e projetos complexos. Com um clock base de 1.4GHz e um boost de até 1.9GHz, a Quadro P2000 garante desempenho otimizado para softwares profissionais, acelerando seus fluxos de trabalho e permitindo que você trabalhe com mais eficiência. O barramento PCIe 3.0 x16 assegura uma comunicação rápida e estável com sua CPU, essencial para aplicações que exigem alta largura de banda. Seu consumo de energia de 110W a torna uma opção eficiente e confiável para longas horas de trabalho. O design de duas ventoinhas (Dual Fan) proporciona uma refrigeração eficaz, mantendo a placa estável mesmo em cargas de trabalho intensivas, o que é crucial para a durabilidade e a confiabilidade em ambientes profissionais. A série Quadro é sinônimo de estabilidade de drivers e certificações com softwares específicos, garantindo compatibilidade e desempenho otimizado para as ferramentas que você usa diariamente. Embora não tenha foco em Ray Tracing para jogos, sua arquitetura Pascal é otimizada para computação profissional, entregando resultados precisos e rápidos. Invista na Quadro P2000 e eleve a capacidade da sua workstation.', 'Quadro P2000', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.5, 2.0, 'PCIe 3.0 x16', 150, 2, 2700.00, false, 4, 10, 10, 'Workstation', 'A Placa de Vídeo NVIDIA Quadro RTX 4000 é uma solução poderosa para uso profissional e engenharia, elevando o nível de desempenho para criação de conteúdo, design e simulações em 4K. Esta GPU integra a arquitetura NVIDIA Turing, trazendo o poder do Ray Tracing em tempo real para as estações de trabalho, um avanço significativo para profissionais. Com 8GB de memória GDDR6, a Quadro RTX 4000 lida com datasets massivos e modelos complexos com facilidade. Com um clock base de 1.5GHz e um boost de até 2.0GHz, a Quadro RTX 4000 acelera drasticamente seus fluxos de trabalho, desde renderização fotorrealista até simulações de engenharia e desenvolvimento de IA. O barramento PCIe 3.0 x16 garante uma transferência de dados eficiente, crucial para as demandas de aplicações profissionais. Com um consumo de energia de 150W, a placa oferece um equilíbrio robusto entre potência e eficiência, ideal para ambientes de trabalho contínuo. O design de duas ventoinhas (Dual Fan) assegura uma refrigeração eficaz, mantendo a placa estável e confiável mesmo durante tarefas intensivas. O grande diferencial da Quadro RTX 4000 é seu suporte a Ray Tracing profissional, que permite aos designers e engenheiros criar visualizações incrivelmente realistas de seus projetos, simulando a luz de forma precisa para prototipagem virtual e revisões de design. Além disso, ela otimiza o uso de núcleos Tensor para aceleração de IA, sendo ideal para aprendizado de máquina e tarefas de computação científica. A Quadro RTX 4000 é a ferramenta que impulsiona a inovação e a criatividade em ambientes de trabalho exigentes.', 'Quadro RTX 4000', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.7, 2.1, 'PCIe 4.0 x16', 160, 2, 2900.00, true, 5, 11, 11, 'Gaming', 'A Placa de Vídeo AMD Radeon RX 6700 XT é a ótima opção para 1440p, entregando uma experiência de jogo imersiva e de alta qualidade. Com generosos 12GB de memória GDDR6, esta GPU proporciona ampla capacidade para texturas de alta resolução e para os títulos mais recentes, garantindo que você não fique sem memória VRAM no calor da batalha. Operando com um clock base de 1.7GHz e um boost de até 2.1GHz, a RX 6700 XT oferece uma performance fluida e consistente, permitindo que você desfrute dos seus jogos favoritos com altas taxas de quadros e sem gargalos. O barramento PCIe 4.0 x16 assegura uma comunicação ultrarrápida com seu sistema, maximizando a largura de banda. Com um consumo de energia de 160W, ela entrega um desempenho robusto com uma eficiência notável. O sistema de refrigeração com duas ventoinhas (Dual Fan) é projetado para dissipar o calor de forma eficiente, mantendo a placa em temperaturas ideais e garantindo a estabilidade durante longas sessões de jogo. O suporte a Ray Tracing na arquitetura RDNA 2 da AMD adiciona uma camada de realismo visual, com iluminação, sombras e reflexos mais detalhados em jogos compatíveis. Além disso, as tecnologias AMD FidelityFX e o futuro FSR (FidelityFX Super Resolution) estão prontas para otimizar ainda mais sua performance e qualidade visual. A RX 6700 XT é a escolha inteligente para gamers que buscam um upgrade significativo para suas experiências em 1440p, com desempenho, tecnologia e um excelente custo-benefício.', 'RX 6700 XT', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.8, 2.2, 'PCIe 4.0 x16', 200, 3, 3800.00, true, 6, 12, 12, 'Gaming', 'Eleve sua experiência de jogo a um desempenho premium em 4K com a Placa de Vídeo AMD Radeon RX 6800 XT. Desenvolvida para os jogadores mais exigentes, esta GPU oferece uma potência extraordinária para dominar os títulos mais recentes em ultra-alta resolução. Com massivos 16GB de memória GDDR6, a RX 6800 XT possui capacidade de memória de sobra para as texturas mais detalhadas e os cenários mais complexos que os jogos modernos podem oferecer, garantindo uma imersão sem precedentes. Com um clock base de 1.8GHz e um impressionante boost de até 2.2GHz, a RX 6800 XT entrega taxas de quadros excepcionais, proporcionando uma jogabilidade ultra suave e responsiva em qualquer cenário de jogo. O barramento PCIe 4.0 x16 garante a máxima largura de banda, otimizando a comunicação entre a placa e o restante do seu sistema. Com um consumo de energia de 200W, esta placa oferece um poder de fogo robusto com uma excelente eficiência. O sistema de refrigeração com três ventoinhas (Triplo Fan) é meticulosamente projetado para dissipar o calor de forma eficaz, mantendo a GPU em temperaturas ótimas mesmo durante as sessões de jogo mais intensas, garantindo a estabilidade e a longevidade do componente. O suporte a Ray Tracing na arquitetura RDNA 2 da AMD adiciona uma camada de realismo visual, com iluminação e reflexos que reagem como no mundo real, elevando a qualidade gráfica dos seus jogos. Além disso, a tecnologia AMD FidelityFX Super Resolution (FSR) está pronta para otimizar ainda mais o desempenho e a qualidade visual. A RX 6800 XT é a escolha ideal para quem busca uma experiência de jogo 4K sem compromissos, com desempenho premium e recursos avançados.', 'RX 6800 XT', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.0, 2.5, 'PCIe 4.0 x16', 300, 3, 4700.00, true, 1, 13, 13, 'Gaming', 'Experimente o poder extremo para jogos em 4K com a Placa de Vídeo AMD Radeon RX 6900 XT. Esta GPU de ponta é a expressão máxima da arquitetura RDNA 2 da AMD, projetada para os gamers e entusiastas que exigem o melhor em desempenho e fidelidade visual. Com impressionantes 16GB de memória GDDR6, a RX 6900 XT oferece uma vasta capacidade de memória para as texturas mais exigentes e os mundos mais detalhados que os jogos atuais podem oferecer. Com um clock base de 2.0GHz e um impressionante boost de até 2.5GHz, a RX 6900 XT entrega taxas de quadros massivas, garantindo uma jogabilidade ultra fluida e responsiva em 4K, mesmo nos títulos mais pesados. O barramento PCIe 4.0 x16 maximiza a taxa de transferência de dados, assegurando que o processamento gráfico seja feito sem gargalos. Com um consumo de energia de 300W, esta placa é uma usina de força, pronta para qualquer desafio gráfico. O sistema de refrigeração com três ventoinhas (Triplo Fan) é extremamente robusto e eficaz, garantindo que a GPU permaneça em temperaturas ótimas mesmo sob cargas de trabalho intensas e contínuas. Isso é crucial para manter a estabilidade do sistema e prolongar a vida útil da sua placa de vídeo. O suporte a Ray Tracing na arquitetura RDNA 2 proporciona uma imersão visual sem precedentes, com iluminação, sombras e reflexos que respondem de forma dinâmica e realista, elevando a qualidade gráfica dos seus jogos a um nível cinematográfico. Além disso, as tecnologias AMD FidelityFX e FSR estão prontas para otimizar ainda mais seu desempenho. A RX 6900 XT é a escolha definitiva para quem busca o ápice da performance em jogos 4K, com poder e tecnologia de ponta.', 'RX 6900 XT', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.9, 2.3, 'PCIe 3.0 x16', 140, 2, 1800.00, false, 2, 14, 14, 'Entry-Level', 'A Placa de Vídeo NVIDIA GeForce GTX 1660 Super é a boa opção de custo-benefício para gamers que buscam uma experiência de jogo sólida e consistente em 1080p. Com 6GB de memória GDDR6 de alta velocidade, esta GPU oferece uma melhoria significativa de desempenho em relação à GTX 1660 original, tornando-a uma excelente escolha para rodar os títulos mais populares e e-sports com fluidez. Com um clock base de 1.9GHz e um boost de até 2.3GHz, a GTX 1660 Super proporciona uma performance robusta para a sua resolução-alvo, garantindo que seus jogos rodem sem engasgos. O barramento PCIe 3.0 x16 assegura compatibilidade com uma ampla gama de placas-mãe e uma transferência de dados eficiente. Com um consumo de energia de 140W, a placa oferece um bom equilíbrio entre potência e eficiência, ideal para builds com orçamentos mais conscientes. O sistema de refrigeração com duas ventoinhas (Dual Fan) é eficaz na dissipação do calor, mantendo a temperatura da GPU sob controle e garantindo a estabilidade durante longas sessões de jogo. Embora não possua suporte a Ray Tracing, a GTX 1660 Super se destaca por entregar uma performance de rasterização otimizada, proporcionando visuais nítidos e taxas de quadros elevadas em jogos tradicionais. Se você está procurando uma placa de vídeo confiável, com excelente desempenho para 1080p e que se encaixe bem no seu orçamento, a GTX 1660 Super é uma escolha inteligente que não vai te decepcionar.', 'GTX 1660 Super', '1080p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.6, 2.0, 'PCIe 3.0 x16', 85, 1, 800.00, false, 3, 15, 15, 'Entry-Level', 'A Placa de Vídeo NVIDIA GeForce GTX 1050 Ti é o modelo acessível para jogos que você estava procurando para iniciar sua jornada gamer em 1080p. Ideal para quem busca um componente confiável e eficiente sem comprometer o orçamento, esta GPU oferece uma performance surpreendente para títulos mais leves, e-sports e até mesmo alguns jogos AAA com configurações ajustadas. Com 4GB de memória GDDR5, a GTX 1050 Ti oferece a capacidade de memória necessária para a maioria dos jogos em 1080p. Operando com um clock base de 1.6GHz e um boost de até 2.0GHz, a GTX 1050 Ti proporciona uma experiência de jogo fluida e consistente. Seu barramento PCIe 3.0 x16 garante ampla compatibilidade com sistemas mais antigos e atuais, e o mais impressionante é seu baixo consumo de energia de apenas 85W, o que significa que na maioria dos casos ela não requer conectores de alimentação adicionais, simplificando a montagem e reduzindo o consumo de energia da sua máquina. Com um design compacto de uma ventoinha (Single Fan), a GTX 1050 Ti se encaixa facilmente na maioria dos gabinetes, incluindo os menores, e garante uma refrigeração adequada para o seu perfil de consumo. Esta placa é perfeita para montar um PC gamer econômico, para quem busca uma opção de upgrade que não exige uma fonte de alimentação robusta, ou para revitalizar um sistema mais antigo para jogos. A GTX 1050 Ti é a prova de que diversão e desempenho podem andar lado a lado com um investimento inteligente.', 'GTX 1050 Ti', '1080p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.0, 2.5, 'PCIe 5.0 x16', 260, 3, 4200.00, true, 4, 16, 16, 'Gaming', 'Prepare-se para uma experiência de jogo sem limites com a Placa de Vídeo NVIDIA GeForce RTX 4080. Projetada para entregar potência para games AAA em 4K, esta GPU é o sonho de todo entusiasta, combinando tecnologias de ponta e desempenho bruto para uma imersão incomparável. Com impressionantes 16GB de memória GDDR6X, a RTX 4080 oferece uma capacidade de memória e largura de banda excepcionais para texturas de ultra-alta resolução e os mundos mais complexos dos jogos modernos. Com um clock base de 2.0GHz e um impressionante boost de até 2.5GHz, a RTX 4080 garante taxas de quadros altíssimas e uma jogabilidade ultra suave em qualquer cenário, mesmo nos títulos mais exigentes. O barramento PCIe 5.0 x16 representa o que há de mais avançado em conectividade, proporcionando a máxima largura de banda e otimizando a comunicação entre a placa e o restante do seu sistema. Com um consumo de energia de 260W, esta placa está pronta para lidar com as cargas de trabalho mais pesadas, exigindo uma fonte de alimentação robusta para extrair todo o seu potencial. O sistema de refrigeração com três ventoinhas (Triplo Fan) é meticulosamente projetado para dissipar o calor de forma eficaz, mantendo a GPU em temperaturas ideais mesmo durante as sessões de jogo mais longas e intensas. Isso garante não apenas a estabilidade do sistema, mas também a longevidade da placa. O suporte a Ray Tracing de terceira geração eleva a imersão a um patamar cinematográfico, com iluminação, sombras e reflexos que reagem como no mundo real. E com a revolucionária tecnologia NVIDIA DLSS 3 (Deep Learning Super Sampling), exclusiva da série RTX 40, você obtém frames adicionais de alta qualidade impulsionados pela inteligência artificial, garantindo uma fluidez impressionante em 4K sem qualquer perda de nitidez. A RTX 4080 é a escolha definitiva para quem busca o ápice da performance gráfica e o melhor em tecnologia de jogos.', 'RTX 4080', '4K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.8, 2.2, 'PCIe 4.0 x16', 120, 2, 2100.00, true, 5, 17, 17, 'Gaming', 'A Placa de Vídeo AMD Radeon RX 7600 oferece uma boa performance por um preço justo para gamers que desejam uma experiência de jogo sólida e envolvente em 1440p. Com 8GB de memória GDDR6 de alta velocidade, esta GPU é otimizada para os títulos mais recentes, entregando um equilíbrio excelente entre desempenho e acessibilidade. Com um clock base de 1.8GHz e um boost de até 2.2GHz, a RX 7600 garante uma jogabilidade fluida e responsiva em diversas categorias de jogos. O barramento PCIe 4.0 x16 assegura uma comunicação ultrarrápida com seu sistema, otimizando a largura de banda e minimizando gargalos. Com um consumo de energia de 120W, a placa oferece uma eficiência notável, sendo uma excelente opção para builds com foco em custo-benefício. O sistema de refrigeração com duas ventoinhas (Dual Fan) é projetado para dissipar o calor de forma eficaz, mantendo a temperatura da GPU sob controle e garantindo a estabilidade durante longas sessões de jogo. O suporte a Ray Tracing na arquitetura RDNA 3 da AMD proporciona um nível adicional de realismo visual, com iluminação, sombras e reflexos mais detalhados em jogos compatíveis, elevando a qualidade da sua experiência. Além disso, as tecnologias AMD FidelityFX e FSR (FidelityFX Super Resolution) estão prontas para otimizar ainda mais o desempenho e a qualidade visual. A RX 7600 é uma escolha inteligente para quem busca um upgrade para 1440p com desempenho confiável e um valor que faz a diferença.', 'RX 7600', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.7, 2.0, 'PCIe 4.0 x16', 130, 2, 2400.00, true, 6, 18, 18, 'Gaming', 'A Placa de Vídeo NVIDIA GeForce RTX 4060 é a escolha ideal para quem busca uma excelente experiência em Ray Tracing em 1440p com um ótimo equilíbrio de performance e tecnologia. Equipada com 8GB de memória GDDR6, esta GPU é otimizada para os títulos mais recentes, garantindo visuais deslumbrantes e uma jogabilidade suave. Com um clock base de 1.7GHz e um impressionante boost de até 2.0GHz, a RTX 4060 oferece um desempenho robusto, permitindo que você aproveite seus jogos favoritos com altas taxas de quadros e sem interrupções. O barramento PCIe 4.0 x16 assegura uma comunicação ultrarrápida com seu processador, otimizando a largura de banda. Com um consumo de energia de 130W, a placa entrega um desempenho eficiente e é compatível com uma ampla gama de fontes de alimentação. O sistema de refrigeração com duas ventoinhas (Dual Fan) é projetado para dissipar o calor de forma eficaz, mantendo a temperatura da GPU sob controle mesmo sob carga intensa, garantindo a estabilidade e a longevidade da placa. O grande diferencial da RTX 4060 é seu suporte a Ray Tracing de terceira geração, que eleva a iluminação, sombras e reflexos a um patamar cinematográfico, tornando os mundos virtuais incrivelmente realistas e imersivos. Além disso, a revolucionária tecnologia NVIDIA DLSS 3 (Deep Learning Super Sampling), exclusiva da série RTX 40, utiliza inteligência artificial para gerar frames adicionais de alta qualidade, impulsionando suas taxas de quadros e garantindo uma fluidez impressionante em 1440p sem qualquer perda de nitidez. A RTX 4060 é a placa perfeita para quem quer experimentar o futuro dos gráficos sem precisar de um investimento exorbitante.', 'RTX 4060', '1440p');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (2.1, 2.6, 'PCIe 5.0 x16', 275, 3, 5000.00, true, 1, 19, 19, 'Gaming', 'A Placa de Vídeo NVIDIA GeForce RTX 4090 (modelo referência/geral) é a epítome do ultra desempenho, projetada para a supremacia absoluta em 8K e para os desafios mais extremos da computação gráfica. Esta GPU não é apenas uma placa de vídeo, é um marco tecnológico, representando o que há de mais avançado em termos de poder de processamento gráfico. Com uma capacidade inigualável de 24GB de memória GDDR6X de altíssima velocidade, a RTX 4090 oferece uma largura de banda de memória sem precedentes, crucial para os jogos e aplicações mais exigentes em resoluções ultra-altas. Com um clock base de 2.1GHz e um impressionante boost de até 2.6GHz, a RTX 4090 entrega taxas de quadros exorbitantes e uma jogabilidade ultra fluida e responsiva, mesmo nos cenários mais complexos e com as configurações gráficas no máximo. O barramento PCIe 5.0 x16 é o que há de mais moderno em conectividade, proporcionando a máxima largura de banda e comunicação instantânea entre a placa e o restante do seu sistema. Com um consumo de energia de 275W, esta placa é uma verdadeira usina de força, projetada para entregar desempenho sem precedentes. O sistema de refrigeração com três ventoinhas (Triplo Fan) é massivo e extremamente eficiente, garantindo que a GPU permaneça em temperaturas ótimas mesmo sob cargas de trabalho intensas e contínuas. Isso é fundamental para manter a estabilidade do sistema e assegurar a longevidade da sua placa de vídeo. O suporte a Ray Tracing de terceira geração redefine o realismo visual, simulando o comportamento físico da luz para criar gráficos com iluminação, sombras e reflexos hiper-realistas que transformam seus jogos em experiências cinematográficas. E com a revolucionária tecnologia NVIDIA DLSS 3 (Deep Learning Super Sampling), exclusiva da série RTX 40, você obtém um aumento exponencial nas taxas de quadros impulsionado pela inteligência artificial, garantindo uma fluidez impressionante em 8K sem qualquer perda de nitidez. A RTX 4090 é o investimento definitivo para quem busca o que há de mais poderoso e avançado em tecnologia de jogos e criação de conteúdo.', 'RTX 4090', '8K');

INSERT INTO PlacaDeVideo (clockBase, clockBoost, barramento, energia, fan, preco, suporteRayTracing, fornecedor_id, id_memoria, id_tamanho, categoria, descricao, modelo, resolucao)
VALUES (1.5, 2.0, 'PCIe 4.0 x8', 105, 2, 1600.00, true, 7, 20, 20, 'Gaming', 'A Placa de Vídeo AMD Radeon RX 6650 XT é a placa intermediária com bom custo para gamers que buscam uma experiência de jogo otimizada em 1080p. Sendo uma versão aprimorada da RX 6600 XT, esta GPU oferece um desempenho ainda mais refinado e rápido, com 8GB de memória GDDR6 de alta velocidade, pronta para lidar com os títulos mais recentes e e-sports competitivos com fluidez. Com um clock base de 1.5GHz e um impressionante boost de até 2.0GHz, a RX 6650 XT garante que seus jogos rodem com a fluidez e a responsividade que você precisa para dominar o campo de batalha virtual, com um desempenho ligeiramente superior em relação à sua antecessora. Construída sobre a arquitetura AMD RDNA 2, a RX 6650 XT conta com um eficiente barramento PCIe 4.0 x8, proporcionando alta largura de banda para uma comunicação rápida com sua CPU. Seu consumo de energia é otimizado em apenas 105W, tornando-a uma opção eficiente para diversos sistemas, e o design de duas ventoinhas (Dual Fan) garante uma refrigeração eficaz e silenciosa, mantendo a estabilidade da placa mesmo sob cargas intensas. O suporte a Ray Tracing proporciona gráficos mais realistas e detalhados em títulos compatíveis, elevando o nível visual dos seus games favoritos. Mergulhe em mundos virtuais com uma imersão sem precedentes, sem comprometer a performance. Além disso, a tecnologia AMD FidelityFX Super Resolution (FSR) está pronta para impulsionar ainda mais seus frames, garantindo visuais nítidos e jogabilidade ultra suave. Se você busca uma placa de vídeo de alta performance para 1080p com um excelente custo-benefício e um pouco mais de fôlego, a RX 6650 XT é a solução ideal para elevar seu setup gamer!', 'RX 6650 XT', '1080p');

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

-- Lotes associados às PlacaDeVideo de ID 3 a 20 (exceto 1 e 2)

-- PlacaDeVideo ID 3
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-01-10', 45, 3, 'LOTE005'),
       ('2023-03-15', 25, 3, 'LOTE006');

-- PlacaDeVideo ID 4
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-02-12', 60, 4, 'LOTE007'),
       ('2023-04-18', 35, 4, 'LOTE008');

-- PlacaDeVideo ID 5
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-01-25', 50, 5, 'LOTE009'),
       ('2023-05-02', 40, 5, 'LOTE010');

-- PlacaDeVideo ID 6
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-02-28', 30, 6, 'LOTE011'),
       ('2023-05-15', 20, 6, 'LOTE012');

-- PlacaDeVideo ID 7
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-03-05', 55, 7, 'LOTE013'),
       ('2023-06-01', 45, 7, 'LOTE014');

-- PlacaDeVideo ID 8
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-01-30', 35, 8, 'LOTE015'),
       ('2023-04-10', 25, 8, 'LOTE016');

-- PlacaDeVideo ID 9
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-02-15', 40, 9, 'LOTE017'),
       ('2023-05-22', 30, 9, 'LOTE018');

-- PlacaDeVideo ID 10
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-03-01', 60, 10, 'LOTE019'),
       ('2023-06-10', 50, 10, 'LOTE020');

-- PlacaDeVideo ID 11
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-01-05', 20, 11, 'LOTE021'),
       ('2023-03-20', 15, 11, 'LOTE022');

-- PlacaDeVideo ID 12
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-02-10', 25, 12, 'LOTE023'),
       ('2023-04-25', 35, 12, 'LOTE024');

-- PlacaDeVideo ID 13
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-03-12', 50, 13, 'LOTE025'),
       ('2023-06-15', 40, 13, 'LOTE026');

-- PlacaDeVideo ID 14
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-01-18', 60, 14, 'LOTE027'),
       ('2023-05-05', 55, 14, 'LOTE028');

-- PlacaDeVideo ID 15
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-02-08', 30, 15, 'LOTE029'),
       ('2023-04-20', 25, 15, 'LOTE030');

-- PlacaDeVideo ID 16
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-03-03', 40, 16, 'LOTE031'),
       ('2023-06-20', 35, 16, 'LOTE032');

-- PlacaDeVideo ID 17
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-01-22', 50, 17, 'LOTE033'),
       ('2023-05-18', 45, 17, 'LOTE034');

-- PlacaDeVideo ID 18
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-02-25', 30, 18, 'LOTE035'),
       ('2023-04-28', 20, 18, 'LOTE036');

-- PlacaDeVideo ID 19
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-03-08', 55, 19, 'LOTE037'),
       ('2023-06-25', 50, 19, 'LOTE038');

-- PlacaDeVideo ID 20
INSERT INTO Lote (datafabricacao, estoque, id_placadevideo, codigo)
VALUES ('2023-01-12', 35, 20, 'LOTE039'),
       ('2023-05-30', 40, 20, 'LOTE040');



INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('joao123', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'cliente@gmail.com', '3333333333', 2);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('maria456', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'maria@example.com', '4444444444', 2);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('pedro789', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'pedro@example.com', '5555555555', 2);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('ana321', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'ana@example.com', '6666666666', 2);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('carlos654', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'carlos@example.com', '7777777777', 2);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('beatriz987', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'beatriz@example.com', '8888888888', 2);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('fernando111', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'fernando@example.com', '9999999999', 2);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('juliana222', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'juliana@example.com', '1010101010', 2);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('roberto333', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'roberto@example.com', '1212121212', 2);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('paula444', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'paula@example.com', '1313131313', 2);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('admin_user', 'xzp7AP+QWnPSmgzJYRBVWiUX7nsVqbfYVtuvPOPw2TRuCrd6T8+/fEhQoxtROBveRpbEyyBB/Xlxxc+rWHzmzQ==', 'admin@example.com', '554455555', 1);
                                                                                                                                       
INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('funcionario1', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'funcionario1@gmail.com', '1111111111', 1);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('funcionario2', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'funcionario2@gmail.com', '2222222222', 1);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('cliente', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'funcionario3@gmail.com', '33333333333', 2);

INSERT INTO Usuario (username, senha, email, cpf, perfil) 
VALUES ('teste', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 'teste@gmail.com', '33333893333', 2);

INSERT INTO funcionario (nome,  datanascimento, salario, id_usuario, statusfuncionario)
VALUES ('Carlos Silva', '1990-05-15', 3500.00, 1, 1);

INSERT INTO funcionario (nome, datanascimento,  salario, id_usuario, statusfuncionario)
VALUES ('Mariana Oliveira', '1985-10-22',  4200.00, 2, 1);

INSERT INTO funcionario (nome, datanascimento, salario, id_usuario, statusfuncionario)
VALUES ('João Mendes', '1992-07-08', 3100.00, 3, 1);

INSERT INTO funcionario (nome, datanascimento, salario, id_usuario, statusfuncionario)
VALUES ('Ana Paula Souza', '1988-12-03', 4800.00, 4, 1);

-- Pagamentos correspondentes aos itens pedidos (id 1 a 40)


-- Inserir cliente
-- Clientes vinculados aos usuários gerados

/*INSERT INTO cliente (id, nome, datanascimento, id_usuario) 
VALUES (1, 'João Silva', '1990-05-15', 1);

INSERT INTO cliente (id, nome, datanascimento, id_usuario) 
VALUES (2, 'Maria Oliveira', '1985-08-22', 2);

INSERT INTO cliente (id, nome, datanascimento, id_usuario) 
VALUES (3, 'Pedro Santos', '1992-11-03', 3);

INSERT INTO cliente (id, nome, datanascimento, id_usuario) 
VALUES (4, 'Ana Costa', '1988-04-17', 4);

INSERT INTO cliente (id, nome, datanascimento, id_usuario) 
VALUES (5, 'Carlos Almeida', '1975-09-30', 5);

INSERT INTO cliente (id, nome, datanascimento, id_usuario) 
VALUES (6, 'Beatriz Ferreira', '1993-07-12', 6);

INSERT INTO cliente (id, nome, datanascimento, id_usuario) 
VALUES (7, 'Fernando Rocha', '1980-12-25', 7);

INSERT INTO cliente (id, nome, datanascimento, id_usuario) 
VALUES (8, 'Juliana Martins', '1995-06-10', 8);

INSERT INTO cliente (id, nome, datanascimento, id_usuario) 
VALUES (9, 'Roberto Lima', '1983-03-05', 9);

INSERT INTO cliente (id, nome, datanascimento, id_usuario) 
VALUES (10, 'Paula Ribeiro', '1991-01-20', 10);


-- Inserir endereços
INSERT INTO endereco (id, cep, cidade, estado, bairro, rua, numero) 
VALUES 
(1, '12345-678', 'São Paulo', 'SP', 'Centro', 'Rua das Flores', '100'),
(2, '98765-432', 'Rio de Janeiro', 'RJ', 'Copacabana', 'Avenida Atlântica', '2000');


-- Inserir telefones
INSERT INTO telefonecliente (id, codigoarea, numero) 
VALUES 
(1, '11', '912345678'),
(2, '21', '998765432');

INSERT INTO enderecoentrega (id, cep, cidade, estado, bairro, rua, numero) VALUES
(1, '70000-000', 'Brasília', 'DF', 'Asa Norte', 'Rua das Flores', '10'),
(2, '80000-000', 'Curitiba', 'PR', 'Centro', 'Av. Brasil', '100'),
(3, '01000-000', 'São Paulo', 'SP', 'Bela Vista', 'Rua Augusta', '300'),
(4, '20000-000', 'Rio de Janeiro', 'RJ', 'Copacabana', 'Av. Atlântica', '200'),
(5, '60000-000', 'Fortaleza', 'CE', 'Meireles', 'Rua dos Coqueiros', '500'),
(6, '40000-000', 'Salvador', 'BA', 'Barra', 'Rua da Praia', '123'),
(7, '30000-000', 'Belo Horizonte', 'MG', 'Savassi', 'Rua Minas', '45'),
(8, '50000-000', 'Recife', 'PE', 'Boa Viagem', 'Rua Mar', '87'),
(9, '65000-000', 'São Luís', 'MA', 'Cohama', 'Av. São Luís', '110'),
(10, '77000-000', 'Palmas', 'TO', 'Plano Diretor Sul', 'Rua 10', '25');


-- Tabela associativa cliente_enderecos
INSERT INTO cliente_endereco (cliente_id, enderecos_id) 
VALUES 
(1, 1),
(1, 2);
-- Tabela associativa cliente_telefones
INSERT INTO cliente_telefonecliente (cliente_id, telefones_id) 
VALUES 
(1, 1),
(1, 2);
*/






INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (1, 'rx66001.jpeg'),
  (1, 'rx66002.jpg'),
  (1, 'rx66003.jpg'),
  (1, 'rx66004.jpg'),
  (1, 'rx66005.jpg');


INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (2, '4060ti1.jpg'),
  (2, '4060ti2.jpg'),
  (2, '4060ti3.jpg'),
  (2, '4060ti4.jpg'),
  (2, '4060ti5.jpg');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (4, '30601.jpg'),
  (4, '30602.jpg'),
  (4, '30603.jpg'),
  (4, '30604.jpg'),
  (4, '30605.jpg');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (5, '30701.jpg'),
  (5, '30702.jpg'),
  (5, '30703.jpg'),
  (5, '30704.jpg'),
  (5, '30705.jpg');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (8, '40901.jpg'),
  (8, '40902.jpg'),
  (8, '40903.jpg'),
  (8, '40904.jpg'),
  (8, '40905.jpg');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (16, '40801.jpg'),
  (16, '40802.jpg'),
  (16, '40803.jpg'),
  (16, '40804.jpg'),
  (16, '40805.jpg');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (3, 'gtx16501.jpg'),
  (3, 'gtx16502.jpg'),
  (3, 'gtx16503.jpg'),
  (3, 'gtx16504.jpg'),
  (3, 'gtx16505.jpg');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (18, '40601.jpg'),
  (18, '40602.jpg'),
  (18, '40603.jpg'),
  (18, '40604.jpg'),
  (18, '40605.jpg');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (19, '4090GB1.jpg'),
  (19, '4090GB2.jpg'),
  (19, '4090GB3.jpg'),
  (19, '4090GB4.jpg'),
  (19, '4090GB5.jpg');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (11, 'RX6700XT1.jpg'),
  (11, 'RX6700XT2.jpg'),
  (11, 'RX6700XT3.jpg'),
  (11, 'RX6700XT4.jpg'),
  (11, 'RX6700XT5.jpg');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (12, 'RX6800XT1.jpg'),
  (12, 'RX6800XT2.jpg'),
  (12, 'RX6800XT3.jpg'),
  (12, 'RX6800XT4.jpg'),
  (12, 'RX6800XT5.jpg');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (13, 'RX6900XTGB1.jpg'),
  (13, 'RX6900XTGB2.jpg'),
  (13, 'RX6900XTGB3.jpg'),
  (13, 'RX6900XTGB4.jpg'),
  (13, 'RX6900XTGB5.jpg');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (20, 'RX6650XTAR1.jpg'),
  (20, 'RX6650XTAR2.jpg'),
  (20, 'RX6650XTAR3.jpg'),
  (20, 'RX6650XTAR4.jpg'),
  (20, 'RX6650XTAR5.jpg');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (6, '3080GALAX1.jpg'),
  (6, '3080GALAX2.jpg'),
  (6, '3080GALAX3.jpg'),
  (6, '3080GALAX4.jpg'),
  (6, '3080GALAX5.jpg');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (7, '3090GIGABYTE1.jpg'),
  (7, '3090GIGABYTE2.jpg'),
  (7, '3090GIGABYTE3.jpg'),
  (7, '3090GIGABYTE4.jpg'),
  (7, '3090GIGABYTE5.jpg');


INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (9, 'P20001.jpg'),
  (9, 'P20002.jpg'),
  (9, 'P20003.jpg'),
  (9, 'P20004.jpg'),
  (9, 'P20005.jpg');

INSERT INTO imagem_placadevideo (id_placadevideo, listaImagem)
VALUES 
  (10, '40001.jpg'),
  (10, '40002.jpg'),
  (10, '40003.jpg');



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
