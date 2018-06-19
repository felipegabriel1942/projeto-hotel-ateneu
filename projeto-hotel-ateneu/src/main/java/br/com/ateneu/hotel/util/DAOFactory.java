package br.com.ateneu.hotel.util;

import br.com.ateneu.hotel.cliente.ClienteDAO;
import br.com.ateneu.hotel.cliente.ClienteDAOHibernate;
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
}
