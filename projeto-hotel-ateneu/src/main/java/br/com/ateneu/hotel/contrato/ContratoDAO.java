package br.com.ateneu.hotel.contrato;

import java.util.List;

public interface ContratoDAO {
	
	public void salvar(Contrato contrato);
	public void excluir(Contrato contrato);
	public void atualizar(Contrato contrato);
	public Contrato pesquisarPorCpf(String cpf);
	public List<Contrato> contrato(Contrato contrato);
}
