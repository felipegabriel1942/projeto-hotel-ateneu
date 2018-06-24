package br.com.ateneu.hotel.cliente;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
/**
 * Esta classe implementa os metodos da interface ClienteDAO dando funcionabilidade aos metodos
 * @author felipe
 *
 */
public class ClienteDAOHibernate implements ClienteDAO{
	
	
	//Session é o responsavel por fazer as operações do hibernate chegarem ao banco de dados
	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	
	//Metodo para salvar cliente no banco de dados
	@Override
	public void salvar(Cliente cliente) {
		this.session.save(cliente);
		
	}

	//Metodo para excluir cliente no banco de dados
	@Override
	public void excluir(Cliente cliente) {
		this.session.delete(cliente);
		
	}
	
	//Metodo para atualizar cliente no banco de dados
	@Override
	public void atualizar(Cliente cliente) {
		this.session.update(cliente);
		
	}
	
	//Metodo para trazer informaçõs de um cliente do banco de dados
	@Override
	public Cliente pesquisarPorCpf(String cpf) {
		String hql = "select c from Cliente c where c.cpf = :cpf";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("cpf", cpf);
		return (Cliente) consulta.uniqueResult();
	}
	
	//Metodo para listar todos os clientes do banco de dados
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listar(Cliente cliente) {
		return this.session.createCriteria(Cliente.class).list();
	}

}
