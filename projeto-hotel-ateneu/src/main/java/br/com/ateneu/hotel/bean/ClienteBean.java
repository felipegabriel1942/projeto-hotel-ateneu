package br.com.ateneu.hotel.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.ateneu.hotel.cliente.Cliente;
import br.com.ateneu.hotel.cliente.ClienteRN;

@ManagedBean (name = "clienteBean")
@RequestScoped
public class ClienteBean {
	private Cliente cliente = new Cliente();

	public String cadastrarCliente() {
		ClienteRN clienteRN = new ClienteRN();
		clienteRN.salvar(this.cliente);
		this.cliente = new Cliente();		
		return "cadastro-cliente-bootstrap";
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
