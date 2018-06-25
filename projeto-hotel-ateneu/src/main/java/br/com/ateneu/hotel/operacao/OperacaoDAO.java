package br.com.ateneu.hotel.operacao;

import java.util.List;

public interface OperacaoDAO {
	
	public void salvar(Operacao operacao);
	public void excluir(Operacao operacao);
	public void atualizar(Operacao operacao);
	public Operacao buscarPorCodigo(Integer codigo);
	public List<Operacao> listar();
}
