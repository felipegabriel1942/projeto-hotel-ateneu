package br.com.ateneu.hotel.util;

import br.com.ateneu.hotel.cliente.ClienteDAO;
import br.com.ateneu.hotel.cliente.ClienteDAOHibernate;
import br.com.ateneu.hotel.contrato.ContratoDAO;
import br.com.ateneu.hotel.contrato.ContratoDAOHibernate;
import br.com.ateneu.hotel.servico.ServicoDAO;
import br.com.ateneu.hotel.servico.ServicoDAOHibernate;
import br.com.ateneu.hotel.usuario.UsuarioDAO;
import br.com.ateneu.hotel.usuario.UsuarioDAOHibernate;

public class DAOFactory {
	
	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}
	
	public static ClienteDAO criarClienteDAO() {
		ClienteDAOHibernate clienteDAO = new ClienteDAOHibernate();
		clienteDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return clienteDAO;
	}
	
	public static ServicoDAO criarServicoDAO() {
		ServicoDAOHibernate servicoDAO = new ServicoDAOHibernate();
		servicoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return servicoDAO;
	}
	
	public static ContratoDAO criarContratoDAO() {
		ContratoDAOHibernate contratoDAO = new ContratoDAOHibernate();
		contratoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return contratoDAO;
	}
}
