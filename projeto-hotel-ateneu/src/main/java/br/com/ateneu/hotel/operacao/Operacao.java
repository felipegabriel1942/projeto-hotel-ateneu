package br.com.ateneu.hotel.operacao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import br.com.ateneu.hotel.contrato.Contrato;

@Entity
@Table (name = "operacao")
public class Operacao implements Serializable{

	private static final long serialVersionUID = 1949901851872491514L;
	
	//Atributos
	@Id
	@GeneratedValue
	@Column(name = "cod_operacao")
	private Integer numeroOperacao;
	
	private Date dataOperacao;
	private float valorOperacao;
	private String tipoServico;
	private String nomeServico;
	
	@ManyToOne
	@JoinColumn(name = "cod_contrato")
	private Contrato contrato;
	
	
	
	//Getters e Setters
	public Integer getNumeroOperacao() {
		return numeroOperacao;
	}

	public void setNumeroOperacao(Integer numeroOperacao) {
		this.numeroOperacao = numeroOperacao;
	}

	public Date getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public float getValorOperacao() {
		return valorOperacao;
	}

	public void setValorOperacao(float valorOperacao) {
		this.valorOperacao = valorOperacao;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getNomeServico() {
		return nomeServico;
	}

	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	
	//Equals e Hash Code
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contrato == null) ? 0 : contrato.hashCode());
		result = prime * result + ((dataOperacao == null) ? 0 : dataOperacao.hashCode());
		result = prime * result + ((nomeServico == null) ? 0 : nomeServico.hashCode());
		result = prime * result + ((numeroOperacao == null) ? 0 : numeroOperacao.hashCode());
		result = prime * result + ((tipoServico == null) ? 0 : tipoServico.hashCode());
		result = prime * result + Float.floatToIntBits(valorOperacao);
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
		Operacao other = (Operacao) obj;
		if (contrato == null) {
			if (other.contrato != null)
				return false;
		} else if (!contrato.equals(other.contrato))
			return false;
		if (dataOperacao == null) {
			if (other.dataOperacao != null)
				return false;
		} else if (!dataOperacao.equals(other.dataOperacao))
			return false;
		if (nomeServico == null) {
			if (other.nomeServico != null)
				return false;
		} else if (!nomeServico.equals(other.nomeServico))
			return false;
		if (numeroOperacao == null) {
			if (other.numeroOperacao != null)
				return false;
		} else if (!numeroOperacao.equals(other.numeroOperacao))
			return false;
		if (tipoServico == null) {
			if (other.tipoServico != null)
				return false;
		} else if (!tipoServico.equals(other.tipoServico))
			return false;
		if (Float.floatToIntBits(valorOperacao) != Float.floatToIntBits(other.valorOperacao))
			return false;
		return true;
	}
	
	
	
	
	

}
