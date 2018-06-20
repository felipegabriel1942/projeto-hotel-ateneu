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
@SessionScoped
public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();

	
	public String logar() {
		UsuarioRN usuarioRN = new UsuarioRN();
		boolean resultado = usuarioRN.verificarLoginSenha(this.usuario.getLogin(), this.usuario.getSenha());
		
		if(resultado) {
			return "menu-principal-bootstrap?faces-redirect=true";
		} else {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
	                    "Login inválido!",
	                    "Por-favor tente novamente!"));
			 return "login-bootstrap";
		}
	}

	public String cadastroUsuario() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		return "menu-principal-bootstrap?faces-redirect=true";
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
