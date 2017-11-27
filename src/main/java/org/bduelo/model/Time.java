package org.bduelo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name="TB_TIME")
@NamedQueries({
	@NamedQuery(name="Time.selectLista", 
		        query="SELECT t FROM Time t"),
	@NamedQuery(name="Time.selectMulti", 
			    query="SELECT t FROM Time t WHERE t.nome LIKE :nome")
})
@NamedStoredProcedureQuery(
        name="Time.update",
        procedureName="UPDATE_TB_TIME",
        parameters={
            @StoredProcedureParameter(mode= ParameterMode.IN, type = Integer.class, name="id"),
            @StoredProcedureParameter(mode= ParameterMode.IN, type = String.class, name="nom"),
            @StoredProcedureParameter(mode= ParameterMode.IN, type = String.class, name="estado")
        }
)
public class Time implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2694828210454834931L;
	
	
	@Id
	@Column(name="id_time")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="TIMESEQ")
      @SequenceGenerator(name="TIMESEQ", sequenceName="TIME_SEQ")
	private int idTime;
	private String nome;
	
	@Column(name="pontuacao_total")
	private int pontuacaoTotal;
	private String status;
	
	
	/**
	 * 
	 */
	public int getIdTime() {
		return idTime;
	}
	public void setIdTime(int idTime) {
		this.idTime = idTime;
	}
	
	
	public String getNome() {
		return nome;
	}	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public int getPontuacaoTotal() {
		return pontuacaoTotal;
	}
	public void setPontuacaoTotal(int pontuacaoTotal) {
		this.pontuacaoTotal = pontuacaoTotal;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTime;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + pontuacaoTotal;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Time other = (Time) obj;
		if (idTime != other.idTime)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pontuacaoTotal != other.pontuacaoTotal)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
