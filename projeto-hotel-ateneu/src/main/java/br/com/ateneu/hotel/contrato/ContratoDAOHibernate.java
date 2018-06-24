package br.com.ateneu.hotel.contrato;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


public class ContratoDAOHibernate implements ContratoDAO{
	
	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	@Override
	public void salvar(Contrato contrato) {
		this.session.save(contrato);
		
	}

	@Override
	public void excluir(Contrato contrato) {
		this.session.delete(contrato);
		
	}

	@Override
	public void atualizar(Contrato contrato) {
		this.session.update(contrato);
		
	}

	@Override
	public Contrato pesquisarPorContrato(Integer contrato) {
		String hql = "select c from Contrato c where c.contrato = :contrato";
		Query consulta = this.session.createQuery(hql);
		consulta.setInteger("contrato", contrato);
		return (Contrato) consulta.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contrato> contrato(Contrato contrato) {
		return this.session.createCriteria(Contrato.class).list();
	}

}
