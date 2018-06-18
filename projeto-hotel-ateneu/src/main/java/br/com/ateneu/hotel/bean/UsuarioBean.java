package br.com.ateneu.hotel.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.com.ateneu.hotel.usuario.Usuario;
import br.com.ateneu.hotel.usuario.UsuarioDAOHibernate;
import br.com.ateneu.hotel.usuario.UsuarioRN;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();

	/*
	 * public String logar() { UsuarioRN usuarioRN = new UsuarioRN();
	 * System.out.println("Login RN " +
	 * usuarioRN.buscarPorLogin(usuario.getLogin()).getLogin());
	 * System.out.println("Senha RN " +
	 * usuarioRN.buscarPorLogin(usuario.getLogin()).getSenha());
	 * System.out.println("Login passado " + usuario.getLogin());
	 * System.out.println("Senha passada " + usuario.getSenha());
	 * if((usuario.getLogin() ==
	 * usuarioRN.buscarPorLogin(usuario.getLogin()).getLogin()) &&
	 * (usuario.getSenha() ==
	 * usuarioRN.buscarPorLogin(usuario.getLogin()).getSenha())){
	 * mensagemSucesso("Sucesso", "Usuario logado com sucesso"); return "/sucesso";
	 * } else { mensagemErro("Erro", "Login ou senha incorretas!"); return ""; } }
	 * 
	 * public void mensagemSucesso(String summary,String detail) { FacesMessage
	 * message = new FacesMessage(FacesMessage.SEVERITY_INFO,summary,detail);
	 * FacesContext.getCurrentInstance().addMessage(null, message); }
	 * 
	 * public void mensagemErro(String summary,String detail) { FacesMessage message
	 * = new FacesMessage(FacesMessage.SEVERITY_ERROR,summary,detail);
	 * FacesContext.getCurrentInstance().addMessage(null, message); }
	 */

	public String logar() {
		UsuarioRN usuarioRN = new UsuarioRN();
		boolean resultado = usuarioRN.verificarLoginSenha(this.usuario.getLogin(), this.usuario.getSenha());
		
		if(resultado) {
			return "/menu-principal";
		} else {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
	                    "Login inválido!",
	                    "Por-favor tente novamente!"));
			 return "/login";
		}
	}

	public String cadastroUsuario() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		return "";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAOHibernate getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAOHibernate usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}
