package org.bduelo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name="TB_CLASSIFICACAO")
@NamedQueries({
	@NamedQuery(name="Classificacao.selectLista", 
			    query="SELECT c FROM Classificacao c"),
	@NamedQuery(name="Classificacao.selectMulti", 
    			query="SELECT c FROM Classificacao c WHERE c.posicao LIKE :posicao")
})
@NamedStoredProcedureQuery(
        name="Classificacao.update",
        procedureName="UPDATE_TB_CLASSIFICACAO",
        parameters={
            @StoredProcedureParameter(mode= ParameterMode.IN, type = Integer.class, name="idClassif"),
            @StoredProcedureParameter(mode= ParameterMode.IN, type = Integer.class, name="idTime"),
            @StoredProcedureParameter(mode= ParameterMode.IN, type = Integer.class, name="idTorn"),
            @StoredProcedureParameter(mode= ParameterMode.IN, type = Integer.class, name="posic"),
            @StoredProcedureParameter(mode= ParameterMode.IN, type = Integer.class, name="points")
        }
)
public class Classificacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8767500750868234645L;

	
	@Id
	@Column(name="id_classificacao")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="CLASSEQ")
      @SequenceGenerator(name="CLASSEQ", sequenceName="CLAS_SEQ")
	private int idClassificacao;
	
	private int posicao;
	
	@Column(name="pontuacao_time")
	private int pontuacaoTime;
	
	@ManyToOne
	@JoinColumn(name="id_time")
	private Time time;
	
	@ManyToOne
	@JoinColumn(name="id_torneio")
	private Torneio torneio;

	
	/**
	 * 
	 */
	public int getIdClassificacao() {
		return idClassificacao;
	}
	public void setIdClassificacao(int idClassificacao) {
		this.idClassificacao = idClassificacao;
	}

	
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	

	public int getPontuacaoTime() {
            
		return pontuacaoTime;
	}
	public void setPontuacaoTime(int pontuacaoTime) {
          
		this.pontuacaoTime = pontuacaoTime;
	}

	
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}

	
	public Torneio getTorneio() {
		return torneio;
	}
	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
	}

	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idClassificacao;
		result = prime * result + pontuacaoTime;
		result = prime * result + posicao;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((torneio == null) ? 0 : torneio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Classificacao other = (Classificacao) obj;
		if (idClassificacao != other.idClassificacao)
			return false;
		if (pontuacaoTime != other.pontuacaoTime)
			return false;
		if (posicao != other.posicao)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (torneio == null) {
			if (other.torneio != null)
				return false;
		} else if (!torneio.equals(other.torneio))
			return false;
		return true;
	}

}
