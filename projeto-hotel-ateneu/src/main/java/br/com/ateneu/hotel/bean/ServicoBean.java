package br.com.ateneu.hotel.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.ateneu.hotel.servico.Servico;
import br.com.ateneu.hotel.servico.ServicoRN;

@ManagedBean(name = "servicoBean")
@RequestScoped
public class ServicoBean {
	private Servico servico =  new Servico();
	private List<Servico> lista = null;
	
	
	public String cadastrarServico() {
		ServicoRN servicoRN = new ServicoRN();
		servicoRN.cadastrarServico(this.servico);
		this.servico = new Servico();
		return "cadastro-servico";
	}
	
	public List<Servico> getLista() {
		if(this.lista == null) {
			ServicoRN servicoRN = new ServicoRN();
			this.lista = servicoRN.listar();
		}
		
		return this.lista;
	}
	
	public String editarServico() {
		ServicoRN servicoRN = new ServicoRN();
		servicoRN.cadastrarServico(this.servico);
		this.servico = new Servico();
		return "editar-servico";
	}
	
	public String excluirServico() {
		ServicoRN servicoRN = new ServicoRN();
		servicoRN.excluir(this.servico);
		this.lista = null;
		return null;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	
}
