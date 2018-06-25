package br.com.ateneu.hotel.operacao;

import br.com.ateneu.hotel.util.DAOFactory;

public class OperacaoRN {
	
	private OperacaoDAO operacaoDAO;
	
	public OperacaoRN() {
		this.operacaoDAO = DAOFactory.criarOperacaoDAO();
	}
	
	public void salvar(Operacao operacao) {
		Integer codigo = operacao.getNumeroOperacao();
		
		if(codigo == null || codigo == 0 ) {
			this.operacaoDAO.salvar(operacao);
		} else {
			this.operacaoDAO.atualizar(operacao);
		}
	}
}
