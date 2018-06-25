package br.com.ateneu.hotel.servico;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class ServicoDAOHibernate implements ServicoDAO{
	
	//Session � o responsavel por fazer as opera��es do hibernate chegarem ao banco de dados
	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	
	//Metodo para salvar servi�o no banco de dados
	@Override
	public void salvar(Servico servico) {
		
		this.session.save(servico);
	}
	
	//Metodo para excluir servi�o no banco de dados
	@Override
	public void excluir(Servico servico) {
		this.session.delete(servico);
		
	}

	//Metodo para atualizar servi�o no banco de dados
	@Override
	public void atualizar(Servico servico) {
		this.session.update(servico);
		
	}
	
	//Metodo para trazer informa��s de um servi�o do banco de dados
	@Override
	public Servico pesquisarPorCodigo(Integer id) {
		String hql = "select s from Servico s where s.id = :id";
		Query consulta = this.session.createQuery(hql);
		consulta.setInteger("id", id);
		return (Servico) consulta.uniqueResult();
	}
	
	//Metodo para listar todos os servicos do banco de dados
	@SuppressWarnings("unchecked")
	@Override
	public List<Servico> listar() {
		return this.session.createCriteria(Servico.class).list();
	}


	@Override
	public Servico pesquisarPorNomeServico(String nome) {
		String hql = "select s from Servico s where s.nomeServico = :nomeServico";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("nomeServico", nome);
		return (Servico) consulta.uniqueResult();
	}

}
