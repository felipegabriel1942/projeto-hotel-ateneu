package br.com.ateneu.hotel.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import br.com.ateneu.hotel.servico.Servico;
import br.com.ateneu.hotel.servico.ServicoRN;

@ManagedBean(name = "servicoBean")
@RequestScoped
public class ServicoBean {
	private Servico servico = new Servico();
	private List<Servico> lista = null;
	private List<SelectItem> listaQuarto = popularNomeQuarto(getLista());
	private List<SelectItem> listaBabysitter = popularListaBabySitter(getLista());
	private List<SelectItem> listaCarro = popularListaCarro(getLista());
	

	public String cadastrarServico() {
		ServicoRN servicoRN = new ServicoRN();
		servicoRN.cadastrarServico(this.servico);
		this.servico = new Servico();
		return "cadastro-servico";
	}

	public List<Servico> getLista() {
		if (this.lista == null) {
			ServicoRN servicoRN = new ServicoRN();
			this.lista = servicoRN.listar();
		}

		return this.lista;
	}

	// Metodo utilizado para popular combobox em pagina jsf
	public static List<SelectItem> popularNomeQuarto(List<Servico> lista) {

		List<SelectItem> s = new ArrayList<>();
		s.add(new SelectItem("Selecione..."));
		for (Servico obj : lista) {
			if (obj.getTipo().equals("Quarto")) {
				s.add(new SelectItem(obj.getNomeServico()));
			}
		}

		return s;
	}
	
	public static List<SelectItem> popularListaBabySitter(List<Servico> lista) {

		List<SelectItem> s = new ArrayList<>();
		s.add(new SelectItem("Selecione..."));
		for (Servico obj : lista) {
			if (obj.getTipo().equals("Babysitter")) {
				s.add(new SelectItem(obj.getNomeServico()));
			}
		}

		return s;
	}
	
	public static List<SelectItem> popularListaCarro(List<Servico> lista) {

		List<SelectItem> s = new ArrayList<>();
		s.add(new SelectItem("Selecione..."));
		for (Servico obj : lista) {
			if (obj.getTipo().equals("Carro")) {
				s.add(new SelectItem(obj.getNomeServico()));
			}
		}

		return s;
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

	public List<SelectItem> getListaQuarto() {
		return listaQuarto;
	}

	public void setListaQuarto(List<SelectItem> listaQuarto) {
		this.listaQuarto = listaQuarto;
	}

	public void setLista(List<Servico> lista) {
		this.lista = lista;
	}

	public List<SelectItem> getListaBabysitter() {
		return listaBabysitter;
	}

	public void setListaBabysitter(List<SelectItem> listaBabysitter) {
		this.listaBabysitter = listaBabysitter;
	}

	public List<SelectItem> getListaCarro() {
		return listaCarro;
	}

	public void setListaCarro(List<SelectItem> listaCarro) {
		this.listaCarro = listaCarro;
	}
	
	

}
