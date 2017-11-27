package org.bduelo.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TB_TORNEIO")
@NamedQueries({
	@NamedQuery(name="Torneio.selectLista", 
		        query="SELECT t FROM Torneio t"),
	@NamedQuery(name="Torneio.selectMulti", 
			    query="SELECT t FROM Torneio t WHERE t.data LIKE :data")	
})
@NamedStoredProcedureQuery(
        name="Torneio.update",
        procedureName="UPDATE_TB_TORNEIO",
        parameters={
            @StoredProcedureParameter(mode= ParameterMode.IN, type = Integer.class, name="id"),
            @StoredProcedureParameter(mode= ParameterMode.IN, type = Date.class, name="dataTorn"),
            @StoredProcedureParameter(mode= ParameterMode.IN, type = Date.class, name="inicio"),
            @StoredProcedureParameter(mode= ParameterMode.IN, type = Date.class, name="fim"),
            @StoredProcedureParameter(mode= ParameterMode.IN, type = String.class, name="partida")
        }
)
public class Torneio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1887757646690086120L;

	
	@Id
	@Column(name="id_torneio")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="TORNSEQ")
      @SequenceGenerator(name="TORNSEQ", sequenceName="TORN_SEQ")
	private int idTorneio;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Column(name="hora_inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaInicio;
	
	@Column(name="hora_fim")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaFim;
	private String jogo;
	
	
	/**
	 * 
	 */
	public int getIdTorneio() {
		return idTorneio;
	}
	public void setIdTorneio(int idTorneio) {
		this.idTorneio = idTorneio;
	}
	
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	
	public Date getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}
	
	
	public String getJogo() {
		return jogo;
	}
	public void setJogo(String jogo) {
		this.jogo = jogo;
	}
	
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((horaFim == null) ? 0 : horaFim.hashCode());
		result = prime * result + ((horaInicio == null) ? 0 : horaInicio.hashCode());
		result = prime * result + idTorneio;
		result = prime * result + ((jogo == null) ? 0 : jogo.hashCode());
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
		Torneio other = (Torneio) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (horaFim == null) {
			if (other.horaFim != null)
				return false;
		} else if (!horaFim.equals(other.horaFim))
			return false;
		if (horaInicio == null) {
			if (other.horaInicio != null)
				return false;
		} else if (!horaInicio.equals(other.horaInicio))
			return false;
		if (idTorneio != other.idTorneio)
			return false;
		if (jogo == null) {
			if (other.jogo != null)
				return false;
		} else if (!jogo.equals(other.jogo))
			return false;
		return true;
	}

}
