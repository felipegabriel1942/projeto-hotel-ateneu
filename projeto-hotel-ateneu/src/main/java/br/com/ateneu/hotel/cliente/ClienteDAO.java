package br.com.ateneu.hotel.cliente;

import java.util.List;

public interface ClienteDAO {
	
	public void salvar(Cliente cliente);
	public void excluir(Cliente cliente);
	public void atualizar(Cliente cliente);
	public Cliente pesquisarPorCpf(String cpf);
	public List<Cliente> listar(Cliente cliente);
	
}
