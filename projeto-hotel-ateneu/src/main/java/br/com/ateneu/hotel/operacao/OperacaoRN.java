package br.com.ateneu.hotel.operacao;

import java.util.ArrayList;
import java.util.List;

import br.com.ateneu.hotel.contrato.Contrato;
import br.com.ateneu.hotel.util.DAOFactory;

public class OperacaoRN {

	private OperacaoDAO operacaoDAO;

	public OperacaoRN() {
		this.operacaoDAO = DAOFactory.criarOperacaoDAO();
	}

	public void salvar(Operacao operacao) {
		Integer codigo = operacao.getNumeroOperacao();

		if (codigo == null || codigo == 0) {
			this.operacaoDAO.salvar(operacao);
		} else {
			this.operacaoDAO.atualizar(operacao);
		}
	}
	
	
	//Metodo para listar operações do cliente
	public List<Operacao> listarOperacoesUsuario(Contrato contrato){
		List<Operacao> listaOperacoes = new ArrayList<Operacao>();
		List<Operacao> operacoesCliente = new ArrayList<Operacao>();
		listaOperacoes = operacaoDAO.listar();
		
		for (int i = 0; i < listaOperacoes.size(); i++) {
			if(listaOperacoes.get(i).getContrato().getNumeroContrato() == contrato.getNumeroContrato()) {
				operacoesCliente.add(listaOperacoes.get(i));
			}
		}
		
		listaOperacoes.clear();
		return operacoesCliente;
	}
}
