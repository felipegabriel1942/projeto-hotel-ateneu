package br.com.ateneu.hotel.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import br.com.ateneu.hotel.cliente.Cliente;
import br.com.ateneu.hotel.cliente.ClienteRN;

@ManagedBean (name = "clienteBean")
@SessionScoped
public class ClienteBean {
	private Cliente cliente = new Cliente();

	public String cadastrarCliente() {
		ClienteRN clienteRN = new ClienteRN();
		clienteRN.salvar(this.cliente);
		
		System.out.println("Nome:" + this.cliente.getNome());
		System.out.println("Celular:" + this.cliente.getCelular());
		System.out.println("Data de nascimento " + this.cliente.getNascimento());
		System.out.println("Email " + this.cliente.getEmail());
		return "";
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
