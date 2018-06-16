package br.com.ateneu.hotel.usuario;

import br.com.ateneu.hotel.util.DAOFactory;

public class UsuarioRN {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}
	
	public void salvar(Usuario usuario) {
		Integer codigo = usuario.getId();
		
		if(codigo == null || codigo == 0) {
			this.usuarioDAO.salvar(usuario);
		} else {
			this.usuarioDAO.atualizar(usuario);
		}
	}
	
	public Usuario buscarPorLogin(String login) {
		return this.usuarioDAO.buscarPorLogin(login);
	}
}
