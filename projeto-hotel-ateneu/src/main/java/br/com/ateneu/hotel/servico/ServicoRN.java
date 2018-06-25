package br.com.ateneu.hotel.servico;

import java.util.List;

import br.com.ateneu.hotel.util.DAOFactory;

public class ServicoRN {
	
	private ServicoDAO servicoDAO;
	
	public ServicoRN() {
		this.servicoDAO = DAOFactory.criarServicoDAO();
	}
	
	public void cadastrarServico(Servico servico) {
		Integer codigo = servico.getId();
		
		if(codigo == null || codigo == 0) {
			this.servicoDAO.salvar(servico);
		} else {
			this.servicoDAO.atualizar(servico);
		}
	}
	
	public List<Servico> listar(){
		return this.servicoDAO.listar();
	}
	
	public void excluir(Servico servico) {
		this.servicoDAO.excluir(servico);
	}
	
	public Servico buscarServicoPorNome(String nome) {
		return this.servicoDAO.pesquisarPorNomeServico(nome);
	}
	
}
