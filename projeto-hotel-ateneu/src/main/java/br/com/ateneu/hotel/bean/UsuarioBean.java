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
@RequestScoped
public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();

	public String logarSistema(ActionEvent actionEvent) {
		
		UsuarioRN usuarioRN = new UsuarioRN();
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;

		boolean logado = false;
		
		if (usuario.getLogin() == usuarioRN.buscarPorLogin(usuario.getLogin()).getLogin()
				&& usuario.getSenha() == usuarioRN.buscarPorLogin(usuario.getLogin()).getSenha()) {
			logado = true;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Seja bem-vindo", "Seja bem-vindo" + usuario.getLogin());
						
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao realizar login", "Login ou senha incorretos");
			Usuario usuario = new Usuario();
			
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("logado", logado);
		Usuario usuario = new Usuario();
		return "";
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
