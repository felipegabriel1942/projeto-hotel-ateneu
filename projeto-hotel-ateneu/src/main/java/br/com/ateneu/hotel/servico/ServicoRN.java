package br.com.ateneu.hotel.servico;

import br.com.ateneu.hotel.util.DAOFactory;

public class ServicoRN {
	
	private ServicoDAO servicoDAO;
	
	public ServicoRN() {
		this.servicoDAO = DAOFactory.criarServicoDAO();
	}
	
	
}
