CREATE OR REPLACE PROCEDURE UPDATE_TB_TIME(id IN INT, nom IN VARCHAR, estado IN VARCHAR)
AS
BEGIN
	
    UPDATE tb_time 
    SET nome = nom, status = estado
    WHERE id_time = id;
END;


CREATE OR REPLACE PROCEDURE UPDATE_TB_TORNEIO(id IN INT, dataTorn IN DATE, 
                                        inicio IN TIMESTAMP, fim IN TIMESTAMP, 
                                        partida IN VARCHAR)
AS
BEGIN
	
	UPDATE tb_torneio 
    SET data = dataTorn, hora_inicio = inicio, hora_fim = fim, jogo = partida 
    WHERE id_torneio = id;
	
END;


CREATE OR REPLACE PROCEDURE UPDATE_TB_CLASSIFICACAO(idClassif IN INT, idTime IN INT,
		                                    idTorn IN INT, posic IN INT, points IN INT)
AS
BEGIN
	
	UPDATE tb_classificacao 
    SET posicao = posic, pontuacao_time = points
    WHERE id_classificacao = idClassif AND id_time = idTime AND id_torneio = idTorn;
END;