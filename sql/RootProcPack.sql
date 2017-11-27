CREATE OR REPLACE 
PACKAGE PROCEDURES AS 

  PROCEDURE UPDATE_TB_TIME(id IN INT, nom IN VARCHAR, estado IN VARCHAR);
  PROCEDURE UPDATE_TB_TORNEIO(id IN INT, dataTorn IN DATE, inicio IN TIMESTAMP, 
                              fim IN TIMESTAMP, partida IN VARCHAR);
  PROCEDURE UPDATE_TB_CLASSIFICACAO(idClassif IN INT, idTime IN INT, idTorn IN INT, 
                                    posic IN INT, points IN INT);  

END PROCEDURES;