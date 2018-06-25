package br.com.ateneu.hotel.contrato;

import br.com.ateneu.hotel.util.DAOFactory;

public class ContratoRN {
	
	private ContratoDAO contratoDAO;
	
	public ContratoRN() {
		this.contratoDAO = DAOFactory.criarContratoDAO();
	}
	
	public void salvar(Contrato contrato) {
		Integer codigo = contrato.getNumeroContrato();
		
		if(codigo == null || codigo == 0) {
			this.contratoDAO.salvar(contrato);
		} else {
			this.contratoDAO.atualizar(contrato);
		}
	}
	
	public Contrato buscarContratoPorCodigo(Integer codigo) {
		if(this.contratoDAO.pesquisarPorContrato(codigo) == null) {
			return null;
		} else {
			return this.contratoDAO.pesquisarPorContrato(codigo);
		}
	}
}
