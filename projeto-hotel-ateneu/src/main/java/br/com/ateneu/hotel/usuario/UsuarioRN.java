package br.com.ateneu.hotel.usuario;

import java.util.List;

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
	
	//Buscar um usuario por login
	public Usuario buscarPorLogin(String login) {
		if (this.usuarioDAO.buscarPorLogin(login) == null) {
			System.out.println(this.usuarioDAO.buscarPorLogin(login));
			return null;
		} else {
			return this.usuarioDAO.buscarPorLogin(login);
		}
	}
	
	
	
	//Metodo para verificar se a senhar digitada ao logar esta cadastrada no banco de dados
	public boolean verificarLoginSenha(String login, String senha) {
		boolean encontrado = false;

		if (buscarPorLogin(login) != null) {
			if (buscarPorLogin(login).getLogin().equals(login) && buscarPorLogin(login).getSenha().equals(senha)) {
				
				return encontrado = true;

			} else {

				return encontrado = false;
			}

		}
		return encontrado;

	}
	
	//Metodo para listar todos os usuarios
	public List<Usuario> listar(){
		return this.usuarioDAO.listar();
	}

}
