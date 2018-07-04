package br.com.ateneu.hotel.bean;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;



import br.com.ateneu.hotel.usuario.Usuario;
import br.com.ateneu.hotel.usuario.UsuarioDAOHibernate;
import br.com.ateneu.hotel.usuario.UsuarioRN;
import br.com.ateneu.hotel.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
	private String caminho;
	private String caminhoParaArmazenarPacote;

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

		if (confirmarSenha(this.confirmarSenha)) {
			usuarioRN.salvar(this.usuario);
			lista = null;
			usuario = null;
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário cadastrado com sucesso",
					"");
			FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);
			return "cadastro-usuario";
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
		return "editar-usuario";
	}

	public String atualizarUsuario() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.atualizarUsuario(usuario);
		this.lista = null;
		this.usuario = null;
		return "administrar-usuario?faces-redirect=true";
	}

	public String excluirUsuario() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluirUsuario(usuario);
		this.lista = null;
		this.usuario = null;
		return "administrar-usuario";
	}

	/**
	 * Metodo utilizado para trazer a lista de todos os usuarios
	 * 
	 * @return
	 */
	public List<Usuario> getLista() {
		if (this.lista == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return this.lista;
	}
	
	
	public UsuarioBean() {
		this.caminho = this.getClass().getClassLoader().getResource("").getPath();
		this.caminhoParaArmazenarPacote = this.caminho + "br/com/ateneu/hotel/relatorio/";
	}
	
	/**
	 * Metodo para imprimir relatorios
	 * @throws JRException 
	 */
	public void imprimirUsuarios() throws JRException {
		this.getLista();
		JasperReport report = JasperCompileManager.compileReport(this.getCaminhoParaArmazenarPacote() + "usuarios.jrxml");
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(this.lista));
		JasperExportManager.exportReportToPdfFile(print,"C:/Users/pinhe/Desktop/RELATORIOS_HOTEL/Relatorio_de_usuarios.pdf");
		this.lista = null;
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Relatório gerado com sucesso","");
		FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);
		
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

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getCaminhoParaArmazenarPacote() {
		return caminhoParaArmazenarPacote;
	}

	public void setCaminhoParaArmazenarPacote(String caminhoParaArmazenarPacote) {
		this.caminhoParaArmazenarPacote = caminhoParaArmazenarPacote;
	}
	
	
}
