package br.com.ateneu.hotel.servico;

import java.util.List;

public interface ServicoDAO {
	
	public void salvar(Servico servico);
	public void excluir(Servico servico);
	public void atualizar(Servico servico);
	public Servico pesquisarPorCodigo(Integer id);
	public Servico pesquisarPorNomeServico(String nome);
	public List<Servico> listar();
}
