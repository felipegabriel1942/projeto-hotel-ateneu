package br.com.ateneu.hotel.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.ateneu.hotel.cliente.Cliente;
import br.com.ateneu.hotel.cliente.ClienteRN;
import br.com.ateneu.hotel.contrato.Contrato;
import br.com.ateneu.hotel.contrato.ContratoRN;

@ManagedBean(name = "clienteBean")
@RequestScoped
public class ClienteBean {
	private Cliente cliente = new Cliente();
	private Contrato contrato = new Contrato();

	public String cadastrarCliente() {

		ClienteRN clienteRN = new ClienteRN();
		ContratoRN contratoRN = new ContratoRN();

		this.contrato.setNomeCompleto(this.cliente.getNome());
		this.contrato.setCpf(this.cliente.getCpf());
		this.contrato.setStatusContrato(1);
		this.contrato.setPeriodo("Janeiro");
		this.contrato.setNumCartaoCredito("5146646464");

		contratoRN.salvar(this.contrato);
		clienteRN.salvar(this.cliente);

		this.cliente = new Cliente();
		this.contrato = new Contrato();

		return "sucesso-cadastro-cliente";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}
