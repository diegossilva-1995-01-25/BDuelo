CREATE TABLE tb_time (
 id_time INT NOT NULL,
 nome VARCHAR(25),
 pontuacao_total INT,
 status VARCHAR(15)
);

ALTER TABLE tb_time ADD CONSTRAINT PK_tb_time PRIMARY KEY (id_time);


CREATE TABLE tb_torneio (
 id_torneio INT NOT NULL,
 data DATE,
 hora_inicio TIMESTAMP,
 hora_fim TIMESTAMP,
 jogo VARCHAR(25)
);

ALTER TABLE tb_torneio ADD CONSTRAINT PK_tb_torneio PRIMARY KEY (id_torneio);


CREATE TABLE tb_classificacao (
 id_classificacao INT NOT NULL,
 id_time INT NOT NULL,
 id_torneio INT NOT NULL,
 posicao INT,
 pontuacao_time INT
);

ALTER TABLE tb_classificacao ADD CONSTRAINT PK_tb_classificacao PRIMARY KEY (id_classificacao,id_time,id_torneio);


ALTER TABLE tb_classificacao ADD CONSTRAINT FK_tb_classificacao_0 FOREIGN KEY (id_time) REFERENCES tb_time (id_time);
ALTER TABLE tb_classificacao ADD CONSTRAINT FK_tb_classificacao_1 FOREIGN KEY (id_torneio) REFERENCES tb_torneio (id_torneio);