package br.com.ateneu.hotel.cliente;

import org.hibernate.Session;

import br.com.ateneu.hotel.util.DAOFactory;

public class ClienteRN {
	private ClienteDAO clienteDAO;
	
	public ClienteRN() {
		this.clienteDAO = DAOFactory.criarClienteDAO();
	}
	
	public void salvar(Cliente cliente) {
		Integer codigo = cliente.getId();
		
		if(codigo == 0 || codigo == null) {
			this.clienteDAO.salvar(cliente);
		} else {
			this.clienteDAO.atualizar(cliente);
		}
	}
}
