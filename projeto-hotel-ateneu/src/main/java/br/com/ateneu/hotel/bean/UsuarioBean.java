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
	private String mensagem = null;
	
	public String logar() {
		UsuarioRN usuarioRN = new UsuarioRN();
		boolean resultado = usuarioRN.verificarLoginSenha(this.usuario.getLogin(), this.usuario.getSenha());
		
		if(resultado) {
			return "menu-principal-bootstrap?faces-redirect=true";
		} else {
			
			 return "login-bootstrap";
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

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
