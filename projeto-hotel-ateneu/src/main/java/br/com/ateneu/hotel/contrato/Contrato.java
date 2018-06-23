package br.com.ateneu.hotel.contrato;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Contrato implements Serializable{

	private static final long serialVersionUID = 5718146817431351664L;
	
	//Atributos
	@Id
	@GeneratedValue
	private Integer numeroContrato;
	private String nomeCompleto;
	private String cpf;
	private Date dataInicial;
	private Date dataFinal;
	private String periodo;
	private Integer statusContrato;
	private String numCartaoCredito;
	
	//Getters e Setters
	public Integer getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(Integer numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Integer getStatusContrato() {
		return statusContrato;
	}
	public void setStatusContrato(Integer statusContrato) {
		this.statusContrato = statusContrato;
	}
	public String getNumCartaoCredito() {
		return numCartaoCredito;
	}
	public void setNumCartaoCredito(String numCartaoCredito) {
		this.numCartaoCredito = numCartaoCredito;
	}
	
	//Equals e Hash Code
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataFinal == null) ? 0 : dataFinal.hashCode());
		result = prime * result + ((dataInicial == null) ? 0 : dataInicial.hashCode());
		result = prime * result + ((nomeCompleto == null) ? 0 : nomeCompleto.hashCode());
		result = prime * result + ((numCartaoCredito == null) ? 0 : numCartaoCredito.hashCode());
		result = prime * result + ((numeroContrato == null) ? 0 : numeroContrato.hashCode());
		result = prime * result + ((periodo == null) ? 0 : periodo.hashCode());
		result = prime * result + ((statusContrato == null) ? 0 : statusContrato.hashCode());
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
		Contrato other = (Contrato) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataFinal == null) {
			if (other.dataFinal != null)
				return false;
		} else if (!dataFinal.equals(other.dataFinal))
			return false;
		if (dataInicial == null) {
			if (other.dataInicial != null)
				return false;
		} else if (!dataInicial.equals(other.dataInicial))
			return false;
		if (nomeCompleto == null) {
			if (other.nomeCompleto != null)
				return false;
		} else if (!nomeCompleto.equals(other.nomeCompleto))
			return false;
		if (numCartaoCredito == null) {
			if (other.numCartaoCredito != null)
				return false;
		} else if (!numCartaoCredito.equals(other.numCartaoCredito))
			return false;
		if (numeroContrato == null) {
			if (other.numeroContrato != null)
				return false;
		} else if (!numeroContrato.equals(other.numeroContrato))
			return false;
		if (periodo == null) {
			if (other.periodo != null)
				return false;
		} else if (!periodo.equals(other.periodo))
			return false;
		if (statusContrato == null) {
			if (other.statusContrato != null)
				return false;
		} else if (!statusContrato.equals(other.statusContrato))
			return false;
		return true;
	}
	
	
}
