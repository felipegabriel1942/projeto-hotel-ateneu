package br.com.ateneu.hotel.usuario;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.ateneu.hotel.util.DAOFactory;

public class UsuarioRN {

	private UsuarioDAO usuarioDAO;

	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}

	public void salvar(Usuario usuario) {
		Integer codigo = usuario.getId();

		if (codigo == null || codigo == 0) {
			this.usuarioDAO.salvar(usuario);
		} else {
			this.usuarioDAO.atualizar(usuario);
		}
	}

	public Usuario buscarPorLogin(String login) {
		if (this.usuarioDAO.buscarPorLogin(login) == null) {
			System.out.println(this.usuarioDAO.buscarPorLogin(login));
			return null;
		} else {
			return this.usuarioDAO.buscarPorLogin(login);
		}
	}

	public boolean verificarLoginSenha(String login, String senha) {
		boolean encontrado = false;

		if (buscarPorLogin(login) != null) {
			if (buscarPorLogin(login).getLogin().equals(login)
					&& buscarPorLogin(login).getSenha().equals(senha)) {
				System.out.println(login);
				System.out.println(senha);
				System.out.println(buscarPorLogin(login).getLogin());
				System.out.println(buscarPorLogin(login).getSenha());
				return encontrado = true;

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Password errado!"));
				
				return encontrado = false;
			}

		}
		return encontrado;

	}

}
