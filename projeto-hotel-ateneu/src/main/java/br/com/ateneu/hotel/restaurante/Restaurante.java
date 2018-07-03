package br.com.ateneu.hotel.restaurante;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
	private float valorOperacao;
	private String nomeServico;
	private List<Restaurante> restaurante = new ArrayList<>(); 
	
	
	
	
	public Restaurante(float valorOperacao, String nomeServico) {
		this.valorOperacao = valorOperacao;
		this.nomeServico = nomeServico;
	}

	public List<Restaurante> carregarOperacao(){
		restaurante.add(new Restaurante(3, "cafe"));
		restaurante.add(new Restaurante(2, "pao"));
		restaurante.add(new Restaurante(3, "chocolate"));
		
		
		return restaurante;
		
	}
	
	
	
	public float getValorOperacao() {
		return valorOperacao;
	}
	public void setValorOperacao(float valorOperacao) {
		this.valorOperacao = valorOperacao;
	}
	public String getNomeServico() {
		return nomeServico;
	}
	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}

	public List<Restaurante> getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(List<Restaurante> restaurante) {
		this.restaurante = restaurante;
	}
	
	
	
}
