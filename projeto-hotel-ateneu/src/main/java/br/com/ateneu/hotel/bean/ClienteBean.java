package br.com.ateneu.hotel.bean;

import javax.faces.bean.ManagedBean;

import br.com.ateneu.hotel.cliente.Cliente;
import br.com.ateneu.hotel.cliente.ClienteRN;

@ManagedBean (name = "clienteBean")
public class ClienteBean {
	private Cliente cliente = new Cliente();

	public String cadastrarCliente() {
		ClienteRN clienteRN = new ClienteRN();
		clienteRN.salvar(this.cliente);
		return "";
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
