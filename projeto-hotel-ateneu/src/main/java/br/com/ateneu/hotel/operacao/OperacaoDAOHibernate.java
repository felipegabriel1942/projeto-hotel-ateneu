package br.com.ateneu.hotel.operacao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class OperacaoDAOHibernate implements OperacaoDAO{
	
	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	@Override
	public void salvar(Operacao operacao) {
		this.session.save(operacao);
		
	}

	@Override
	public void excluir(Operacao operacao) {
		this.session.delete(operacao);
		
	}

	@Override
	public void atualizar(Operacao operacao) {
		this.session.update(operacao);
		
	}

	@Override
	public Operacao buscarPorCodigo(Integer codigo) {
		String hql = "select o from Operacao o where o.id = :id";
		Query consulta = this.session.createQuery(hql);
		consulta.setInteger("id", codigo);
		return (Operacao) consulta.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Operacao> listar() {
		return this.session.createCriteria(Operacao.class).list();
	}

}
