package br.com.ateneu.hotel.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import br.com.ateneu.hotel.usuario.Usuario;
import br.com.ateneu.hotel.usuario.UsuarioDAOHibernate;
import br.com.ateneu.hotel.usuario.UsuarioRN;

@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean {

	// Atributos
	private Usuario usuario = new Usuario();
	private Usuario usuarioLogado = new Usuario();
	private Usuario usuarioEdicao = new Usuario();
	private UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
	private String confirmarSenha;
	private String nomeDoUsuario;
	private List<Usuario> lista;

	// Metodo utilizado para realizar o login no sistema
	public String logar() {
		FacesContext context = FacesContext.getCurrentInstance();
		UsuarioRN usuarioRN = new UsuarioRN();
		boolean resultado = usuarioRN.verificarLoginSenha(this.usuario.getLogin(), this.usuario.getSenha());

		if (resultado) {
			usuarioLogado = usuarioRN.buscarPorLogin(this.usuario.getLogin());
			this.usuario = new Usuario();
			return "pagina-principal?faces-redirect=true";

		} else {
			FacesMessage facesMessage = new FacesMessage("Login ou Senha inválidos");
			context.addMessage(null, facesMessage);
			return "index";
		}
	}

	/**
	 * Metodo para cadastrar o usuario Verifica se a senha escolhida é igual a
	 * confirmação da senha
	 * 
	 * @return
	 */
	public String cadastroUsuario() {
		FacesContext context = FacesContext.getCurrentInstance();
		UsuarioRN usuarioRN = new UsuarioRN();
		
		System.out.println("Deu certo? " + confirmarSenha(this.confirmarSenha));
		
		if (confirmarSenha(this.confirmarSenha)) {
			usuarioRN.salvar(this.usuario);
			return "sucesso-cadastro-usuario?faces-redirect=true";
		} else {
			FacesMessage facesMessage = new FacesMessage("A senha deve ser igual a confirmação de senha");
			context.addMessage(null, facesMessage);
			return "cadastro-usuario";
		}
	}

	// Confirmar senha
	public boolean confirmarSenha(String senha) {
		if (this.usuario.getSenha().equals(senha)) {
			return true;
		} else {
			return false;
		}

	}
	
	public String deslogar() {
		usuarioLogado = new Usuario();
		return "index?faces-redirect=true";
	}
	
	public String editarUsuario() {
		
		UsuarioRN usuarioRN = new UsuarioRN();
		this.usuarioEdicao = usuarioRN.buscarPorLogin(usuario.getLogin());
		return "editar-usuario";
	}
	
	/**
	 * Metodo utilizado para trazer a lista de todos os usuarios
	 * 
	 * @return
	 */
	public List<Usuario> getLista(){
		if(this.lista == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return this.lista;
	}

	// Getters e Setters
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

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getNomeDoUsuario() {
		return nomeDoUsuario;
	}

	public void setNomeDoUsuario(String nomeDoUsuario) {
		this.nomeDoUsuario = nomeDoUsuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Usuario getUsuarioEdicao() {
		return usuarioEdicao;
	}

	public void setUsuarioEdicao(Usuario usuarioEdicao) {
		this.usuarioEdicao = usuarioEdicao;
	}

	
	
}
