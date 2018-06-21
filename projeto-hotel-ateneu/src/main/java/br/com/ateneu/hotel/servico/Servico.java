package br.com.ateneu.hotel.servico;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Servico implements Serializable{

	private static final long serialVersionUID = 191897259760879960L;
	
	
	//Atributos
	@Id
	@GeneratedValue
	private Integer id;
	
	private String tipo;
	private float valor;
	private Integer quantidadeTotal;
	private Integer quantidadeDisponivel;
	private String nomeServico; 
	private Integer maxOcupacao;
	
	//Getters e Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}
	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}
	public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}
	public String getNomeServico() {
		return nomeServico;
	}
	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
	public Integer getMaxOcupacao() {
		return maxOcupacao;
	}
	public void setMaxOcupacao(Integer maxOcupacao) {
		this.maxOcupacao = maxOcupacao;
	}
	
	
	//Hash code e equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((maxOcupacao == null) ? 0 : maxOcupacao.hashCode());
		result = prime * result + ((nomeServico == null) ? 0 : nomeServico.hashCode());
		result = prime * result + ((quantidadeDisponivel == null) ? 0 : quantidadeDisponivel.hashCode());
		result = prime * result + ((quantidadeTotal == null) ? 0 : quantidadeTotal.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + Float.floatToIntBits(valor);
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
		Servico other = (Servico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maxOcupacao == null) {
			if (other.maxOcupacao != null)
				return false;
		} else if (!maxOcupacao.equals(other.maxOcupacao))
			return false;
		if (nomeServico == null) {
			if (other.nomeServico != null)
				return false;
		} else if (!nomeServico.equals(other.nomeServico))
			return false;
		if (quantidadeDisponivel == null) {
			if (other.quantidadeDisponivel != null)
				return false;
		} else if (!quantidadeDisponivel.equals(other.quantidadeDisponivel))
			return false;
		if (quantidadeTotal == null) {
			if (other.quantidadeTotal != null)
				return false;
		} else if (!quantidadeTotal.equals(other.quantidadeTotal))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (Float.floatToIntBits(valor) != Float.floatToIntBits(other.valor))
			return false;
		return true;
	}
	
	
	
	

}
