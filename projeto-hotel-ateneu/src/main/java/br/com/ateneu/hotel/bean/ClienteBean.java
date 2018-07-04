package br.com.ateneu.hotel.bean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.ateneu.hotel.cliente.Cliente;
import br.com.ateneu.hotel.cliente.ClienteRN;
import br.com.ateneu.hotel.contrato.Contrato;
import br.com.ateneu.hotel.contrato.ContratoRN;
import br.com.ateneu.hotel.operacao.Operacao;
import br.com.ateneu.hotel.operacao.OperacaoRN;
import br.com.ateneu.hotel.servico.ServicoRN;
import br.com.ateneu.hotel.util.RNUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean(name = "clienteBean")
@ViewScoped
public class ClienteBean {
	private Cliente cliente = new Cliente();
	private Contrato contrato = new Contrato();
	private Operacao operacaoQuarto = new Operacao();
	private Operacao operacaoBabysitter = new Operacao();
	private Operacao operacaoCarro = new Operacao();
	private Contrato contratoCheckout = new Contrato();
	private Contrato buscarContrato = new Contrato();
	private Contrato contratoRestaurante = new Contrato();
	List<Operacao> operacoesCliente = new ArrayList<Operacao>();
	private List<Operacao> operacoesRestauranteTemporario = new ArrayList<Operacao>();
	private float totalPagar;
	private List<String> opcoesCarroAlugado = new ArrayList<String>();
	public float valorAdicionalCarro;
	private String dataNascimento;
	private String dataInicio;
	private String dataFinal;
	private Integer contratoPesquisado;
	private String caminho;
	private String caminhoParaArmazenarPacote;
	private List<Contrato> listaContrato;

	// Atributos das operacoes do restaurante
	private Operacao cafe1 = new Operacao();
	private Operacao cafe2 = new Operacao();
	private Operacao cafe3 = new Operacao();
	private Operacao almoco1 = new Operacao();
	private Operacao almoco2 = new Operacao();
	private Operacao almoco3 = new Operacao();
	private Operacao jantar1 = new Operacao();
	private Operacao jantar2 = new Operacao();
	private Operacao jantar3 = new Operacao();
	private Integer quantidade1;
	private Integer quantidade2;
	private Integer quantidade3;
	private Integer quantidade4;
	private Integer quantidade5;
	private Integer quantidade6;
	private Integer quantidade7;
	private Integer quantidade8;
	private Integer quantidade9;

	public String cadastrarCliente() throws ParseException {

		ClienteRN clienteRN = new ClienteRN();
		ContratoRN contratoRN = new ContratoRN();
		OperacaoRN operacaoRN = new OperacaoRN();
		ServicoRN servicoRN = new ServicoRN();
		RNUtil rnUtil = new RNUtil();

		// Setando informações diversas iniciais
		this.contrato.setNomeCompleto(this.cliente.getNome());
		this.contrato.setCpf(this.cliente.getCpf());
		this.contrato.setStatusContrato(1);
		this.contrato.setCliente(cliente);
		this.contrato.setPeriodo(rnUtil.definirPeriodo(this.dataInicio));

		// Conversao de datas
		this.cliente.setNascimento(rnUtil.stringParaData(this.dataNascimento));
		this.contrato.setDataInicial(rnUtil.stringParaData(this.dataInicio));
		this.contrato.setDataFinal(rnUtil.stringParaData(this.dataFinal));

		// Settar as informações de operação do quarto
		this.operacaoQuarto.setTipoServico(servicoRN.buscarServicoPorNome(operacaoQuarto.getNomeServico()).getTipo());
		this.operacaoQuarto.setValorOperacao(servicoRN.buscarServicoPorNome(operacaoQuarto.getNomeServico()).getValor()
				* rnUtil.diferençaDatas(contrato.getDataFinal(), contrato.getDataInicial()));
		this.operacaoQuarto.setDataOperacao(new Date(System.currentTimeMillis()));
		this.operacaoQuarto.setContrato(contrato);

		// Settar as informações de operação da babysitter

		if (this.operacaoBabysitter.getNomeServico().equals("Selecione...")) {

		} else {
			this.operacaoBabysitter
					.setTipoServico(servicoRN.buscarServicoPorNome(operacaoBabysitter.getNomeServico()).getTipo());
			this.operacaoBabysitter
					.setValorOperacao(servicoRN.buscarServicoPorNome(operacaoBabysitter.getNomeServico()).getValor());
			this.operacaoBabysitter.setDataOperacao(new Date(System.currentTimeMillis()));
			this.operacaoBabysitter.setContrato(contrato);

		}

		// Settar as informações de operação do carro

		if (this.operacaoCarro.getNomeServico().equals("Selecione...")) {

		} else {
			this.operacaoCarro.setTipoServico(servicoRN.buscarServicoPorNome(operacaoCarro.getNomeServico()).getTipo());
			this.operacaoCarro.setValorOperacao(
					servicoRN.buscarServicoPorNome(operacaoCarro.getNomeServico()).getValor() + valorAdicionalCarro);
			this.operacaoCarro.setDataOperacao(new Date(System.currentTimeMillis()));
			this.operacaoCarro.setContrato(contrato);

		}
		
		
		
		if(this.contrato.getQtdPessoas() > 3) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "O limite máximo de pessoas por quarto é 3", "");
			FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);
			return "check-in";
		} else {
			
			// salvar as informações referentes ao cliente, contrato e serviços solicitados
			contratoRN.salvar(this.contrato);
			clienteRN.salvar(this.cliente);
			operacaoRN.salvar(this.operacaoQuarto);
			operacaoRN.salvar(this.operacaoBabysitter);
			operacaoRN.salvar(this.operacaoCarro);

			this.cliente = new Cliente();
			this.contrato = new Contrato();

			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Checkin efetuado com sucesso", "");
			FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);

			// Limpar campos
			this.dataInicio = null;
			this.dataFinal = null;
			this.dataNascimento = null;
			this.operacaoQuarto = null;
			this.operacaoBabysitter = null;
			this.opcoesCarroAlugado = null;

			return "check-in";
			
		}
		
		
	}

	// Preenche a pagina com os dados do cliente
	public String buscarCliente() {
		ContratoRN contratoRN = new ContratoRN();
		OperacaoRN operacaoRN = new OperacaoRN();

		contratoCheckout = contratoRN.buscarContratoPorCodigo(this.contrato.getNumeroContrato());

		// Verificação da situação do contrato pesquisado ou se está cadastrado
		if (contratoCheckout == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contrato não existe", "");
			FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);

		} else if (contratoCheckout.getStatusContrato() == 0) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Contrato está inativo", "");
			FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);
			this.contratoCheckout = null;

		} else {
			operacoesCliente = operacaoRN.listarOperacoesUsuario(contratoCheckout);

			// Calculo do valor do total do cliente deve pagar
			for (int i = 0; i < operacoesCliente.size(); i++) {
				this.setTotalPagar(this.getTotalPagar() + operacoesCliente.get(i).getValorOperacao());
				System.out.println("Valor a pagar " + i + " " + this.getTotalPagar());
			}

		}

		return "checkout";
	}

	// Metodo para buscar e editar um contrato
	public String buscarEditarContrato() {
		ContratoRN contratoRN = new ContratoRN();
		OperacaoRN operacaoRN = new OperacaoRN();

		buscarContrato = contratoRN.buscarContratoPorCodigo(this.contrato.getNumeroContrato());

		// Verificação da situação do contrato pesquisado ou se está cadastrado
		if (buscarContrato == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contrato não existe", "");
			FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);

		} else if (buscarContrato.getStatusContrato() == 0) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Contrato está inativo", "");
			FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);
			this.buscarContrato = null;

		} else {
			operacoesCliente = operacaoRN.listarOperacoesUsuario(buscarContrato);

			// Calculo do valor do total do cliente deve pagar
			for (int i = 0; i < operacoesCliente.size(); i++) {
				this.setTotalPagar(this.getTotalPagar() + operacoesCliente.get(i).getValorOperacao());
				System.out.println("Valor a pagar " + i + " " + this.getTotalPagar());
			}

		}

		return "pesquisar-contrato";
	}

	public String realizarCheckout() {
		ContratoRN contratoRN = new ContratoRN();

		contratoCheckout = contratoRN.buscarContratoPorCodigo(this.contrato.getNumeroContrato());
		contratoCheckout.setStatusContrato(0);

		contratoRN.atualizar(contratoCheckout);

		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Checkout efetuado com sucesso", "");
		FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);

		this.operacoesCliente = null;
		this.contratoCheckout = null;
		this.contrato = null;
		this.totalPagar = 0;

		return "checkout";

	}

	public void pesquisarContrato() {
		ContratoRN contratoRN = new ContratoRN();
		this.setContratoRestaurante(contratoRN.buscarContratoPorCodigo(this.contratoPesquisado));
		this.contratoPesquisado = null;

	}

	public String verificarPedido() {
		ContratoRN contratoRN = new ContratoRN();
		return "servico-alimentacao";

	}

	public String fecharPedido() {
		ServicoRN servicoRN = new ServicoRN();
		OperacaoRN operacaoRN = new OperacaoRN();

		if (this.cafe1.getNomeServico() != "Selecione...") {
			if (this.quantidade1 != null) {
				this.cafe1.setTipoServico(servicoRN.buscarServicoPorNome(cafe1.getNomeServico()).getTipo());
				this.cafe1.setValorOperacao(
						servicoRN.buscarServicoPorNome(cafe1.getNomeServico()).getValor() * this.quantidade1);
				this.cafe1.setDataOperacao(new Date(System.currentTimeMillis()));
				this.cafe1.setContrato(this.contratoRestaurante);
				operacaoRN.salvar(cafe1);
			}
		}

		if (this.cafe2.getNomeServico() != "Selecione...") {
			if (this.quantidade2 != null) {
				this.cafe2.setTipoServico(servicoRN.buscarServicoPorNome(cafe2.getNomeServico()).getTipo());
				this.cafe2.setValorOperacao(
						servicoRN.buscarServicoPorNome(cafe2.getNomeServico()).getValor() * this.quantidade2);
				this.cafe2.setDataOperacao(new Date(System.currentTimeMillis()));
				this.cafe2.setContrato(this.contratoRestaurante);
				operacaoRN.salvar(cafe2);
			}
		}

		if (this.cafe3.getNomeServico() != "Selecione...") {
			if (this.quantidade3 != null) {
				this.cafe3.setTipoServico(servicoRN.buscarServicoPorNome(cafe3.getNomeServico()).getTipo());
				this.cafe3.setValorOperacao(
						servicoRN.buscarServicoPorNome(cafe3.getNomeServico()).getValor() * this.quantidade3);
				this.cafe3.setDataOperacao(new Date(System.currentTimeMillis()));
				this.cafe3.setContrato(this.contratoRestaurante);
				operacaoRN.salvar(cafe3);
			}
		}

		if (this.almoco1.getNomeServico() != "Selecione...") {
			if (this.quantidade4 != null) {
				this.almoco1.setTipoServico(servicoRN.buscarServicoPorNome(almoco1.getNomeServico()).getTipo());
				this.almoco1.setValorOperacao(
						servicoRN.buscarServicoPorNome(almoco1.getNomeServico()).getValor() * this.quantidade4);
				this.almoco1.setDataOperacao(new Date(System.currentTimeMillis()));
				this.almoco1.setContrato(this.contratoRestaurante);
				operacaoRN.salvar(almoco1);
			}
		}

		if (this.almoco2.getNomeServico() != "Selecione...") {
			if (this.quantidade5 != null) {
				this.almoco2.setTipoServico(servicoRN.buscarServicoPorNome(almoco2.getNomeServico()).getTipo());
				this.almoco2.setValorOperacao(
						servicoRN.buscarServicoPorNome(almoco2.getNomeServico()).getValor() * this.quantidade5);
				this.almoco2.setDataOperacao(new Date(System.currentTimeMillis()));
				this.almoco2.setContrato(this.contratoRestaurante);
				operacaoRN.salvar(almoco2);
			}
		}

		if (this.almoco3.getNomeServico() != "Selecione...") {
			if (this.quantidade6 != null) {
				this.almoco3.setTipoServico(servicoRN.buscarServicoPorNome(almoco3.getNomeServico()).getTipo());
				this.almoco3.setValorOperacao(
						servicoRN.buscarServicoPorNome(almoco3.getNomeServico()).getValor() * this.quantidade6);
				this.almoco3.setDataOperacao(new Date(System.currentTimeMillis()));
				this.almoco3.setContrato(this.contratoRestaurante);
				operacaoRN.salvar(almoco3);
			}
		}

		if (this.jantar1.getNomeServico() != "Selecione...") {
			if (this.quantidade7 != null) {
				this.jantar1.setTipoServico(servicoRN.buscarServicoPorNome(jantar1.getNomeServico()).getTipo());
				this.jantar1.setValorOperacao(
						servicoRN.buscarServicoPorNome(jantar1.getNomeServico()).getValor() * this.quantidade7);
				this.jantar1.setDataOperacao(new Date(System.currentTimeMillis()));
				this.jantar1.setContrato(this.contratoRestaurante);
				operacaoRN.salvar(jantar1);
			}
		}

		if (this.jantar2.getNomeServico() != "Selecione...") {
			if (this.quantidade8 != null) {
				this.jantar2.setTipoServico(servicoRN.buscarServicoPorNome(jantar2.getNomeServico()).getTipo());
				this.jantar2.setValorOperacao(
						servicoRN.buscarServicoPorNome(jantar2.getNomeServico()).getValor() * this.quantidade8);
				this.jantar2.setDataOperacao(new Date(System.currentTimeMillis()));
				this.jantar2.setContrato(this.contratoRestaurante);
				operacaoRN.salvar(jantar2);
			}
		}

		if (this.jantar3.getNomeServico() != "Selecione...") {
			if (this.quantidade9 != null) {
				this.jantar3.setTipoServico(servicoRN.buscarServicoPorNome(jantar3.getNomeServico()).getTipo());
				this.jantar3.setValorOperacao(
						servicoRN.buscarServicoPorNome(jantar3.getNomeServico()).getValor() * this.quantidade9);
				this.jantar3.setDataOperacao(new Date(System.currentTimeMillis()));
				this.jantar3.setContrato(this.contratoRestaurante);
				operacaoRN.salvar(jantar3);
			}
		}

		// Limpar campos
		this.cafe1 = null;
		this.cafe2 = null;
		this.cafe3 = null;
		this.almoco1 = null;
		this.almoco2 = null;
		this.almoco3 = null;
		this.jantar1 = null;
		this.jantar2 = null;
		this.jantar3 = null;

		this.quantidade1 = null;
		this.quantidade2 = null;
		this.quantidade3 = null;
		this.quantidade4 = null;
		this.quantidade5 = null;
		this.quantidade6 = null;
		this.quantidade7 = null;
		this.quantidade8 = null;
		this.quantidade9 = null;

		this.contratoRestaurante = null;

		// Mensagem de sucesso
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido efetuado com sucesso", "");
		FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);

		// Recarregar a pagina
		return "servico-alimentacao";

	}

	public String adicionarServicoBabysitter() {
		OperacaoRN operacaoRN = new OperacaoRN();
		ServicoRN servicoRN = new ServicoRN();

		if (this.operacaoBabysitter.getNomeServico().equals("Selecione...")) {

		} else {
			this.operacaoBabysitter
					.setTipoServico(servicoRN.buscarServicoPorNome(operacaoBabysitter.getNomeServico()).getTipo());
			this.operacaoBabysitter
					.setValorOperacao(servicoRN.buscarServicoPorNome(operacaoBabysitter.getNomeServico()).getValor());
			this.operacaoBabysitter.setDataOperacao(new Date(System.currentTimeMillis()));
			this.operacaoBabysitter.setContrato(this.contratoRestaurante);
			operacaoRN.salvar(this.operacaoBabysitter);

		}

		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Serviço adicionado com sucesso", "");
		FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);

		this.operacaoBabysitter = null;
		this.contratoRestaurante = null;

		return "servico-babysitter";

	}

	public String adicionarServicoCarro() {
		OperacaoRN operacaoRN = new OperacaoRN();
		ServicoRN servicoRN = new ServicoRN();

		if (this.operacaoCarro.getNomeServico().equals("Selecione...")) {

		} else {
			this.operacaoCarro.setTipoServico(servicoRN.buscarServicoPorNome(operacaoCarro.getNomeServico()).getTipo());
			this.operacaoCarro
					.setValorOperacao(servicoRN.buscarServicoPorNome(operacaoCarro.getNomeServico()).getValor());
			this.operacaoCarro.setDataOperacao(new Date(System.currentTimeMillis()));
			this.operacaoCarro.setContrato(this.contratoRestaurante);
			operacaoRN.salvar(this.operacaoCarro);

		}

		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Serviço adicionado com sucesso", "");
		FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);

		this.operacaoCarro = null;
		this.contratoRestaurante = null;

		return "servico-aluguel-carro";

	}

	/*
	 * public ClienteBean() { this.caminho =
	 * this.getClass().getClassLoader().getResource("").getPath();
	 * this.caminhoParaArmazenarPacote = this.caminho +
	 * "br/com/ateneu/hotel/relatorio/"; }
	 * 
	 * 
	 * public void imprimirContratosAtivos() throws JRException {
	 * this.getListaContrato(); JasperReport report =
	 * JasperCompileManager.compileReport(this.getCaminhoParaArmazenarPacote() +
	 * "contratos.jrxml"); JasperPrint print = JasperFillManager.fillReport(report,
	 * null, new JRBeanCollectionDataSource(this.listaContrato));
	 * JasperExportManager.exportReportToPdfFile(print,
	 * "C:/Users/pinhe/Desktop/RELATORIOS_HOTEL/Relatorio_de_contratos_ativos.pdf");
	 * this.listaContrato = null; FacesMessage facesMessage = new
	 * FacesMessage(FacesMessage.SEVERITY_INFO,"Relatório gerado com sucesso","");
	 * FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);
	 * 
	 * }
	 * 
	 */

	// Getters e Setters
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Operacao getOperacaoQuarto() {
		return operacaoQuarto;
	}

	public void setOperacaoQuarto(Operacao operacaoQuarto) {
		this.operacaoQuarto = operacaoQuarto;
	}

	public Operacao getOperacaoBabysitter() {
		return operacaoBabysitter;
	}

	public void setOperacaoBabysitter(Operacao operacaoBabysitter) {
		this.operacaoBabysitter = operacaoBabysitter;
	}

	public Operacao getOperacaoCarro() {
		return operacaoCarro;
	}

	public void setOperacaoCarro(Operacao operacaoCarro) {
		this.operacaoCarro = operacaoCarro;
	}

	public Contrato getContratoCheckout() {
		return contratoCheckout;
	}

	public void setContratoCheckout(Contrato contratoCheckout) {
		this.contratoCheckout = contratoCheckout;
	}

	public List<Operacao> getOperacoesCliente() {
		return operacoesCliente;
	}

	public void setOperacoesCliente(List<Operacao> operacoesCliente) {
		this.operacoesCliente = operacoesCliente;
	}

	public float getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(float totalPagar) {
		this.totalPagar = totalPagar;
	}

	public List<String> getOpcoesCarroAlugado() {
		return opcoesCarroAlugado;
	}

	public void setOpcoesCarroAlugado(List<String> opcoesCarroAlugado) {
		this.opcoesCarroAlugado = opcoesCarroAlugado;
	}

	public Contrato getBuscarContrato() {
		return buscarContrato;
	}

	public void setBuscarContrato(Contrato buscarContrato) {
		this.buscarContrato = buscarContrato;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Integer getContratoPesquisado() {
		return contratoPesquisado;
	}

	public void setContratoPesquisado(Integer contratoPesquisado) {
		this.contratoPesquisado = contratoPesquisado;
	}

	public Contrato getContratoRestaurante() {
		return contratoRestaurante;
	}

	public void setContratoRestaurante(Contrato contratoRestaurante) {
		this.contratoRestaurante = contratoRestaurante;
	}

	public List<Operacao> getOperacoesRestauranteTemporario() {
		return operacoesRestauranteTemporario;
	}

	public void setOperacoesRestauranteTemporario(List<Operacao> operacoesRestauranteTemporario) {
		this.operacoesRestauranteTemporario = operacoesRestauranteTemporario;
	}

	public Operacao getCafe1() {
		return cafe1;
	}

	public void setCafe1(Operacao cafe1) {
		this.cafe1 = cafe1;
	}

	public Operacao getCafe2() {
		return cafe2;
	}

	public void setCafe2(Operacao cafe2) {
		this.cafe2 = cafe2;
	}

	public Operacao getCafe3() {
		return cafe3;
	}

	public void setCafe3(Operacao cafe3) {
		this.cafe3 = cafe3;
	}

	public Operacao getAlmoco1() {
		return almoco1;
	}

	public void setAlmoco1(Operacao almoco1) {
		this.almoco1 = almoco1;
	}

	public Operacao getAlmoco2() {
		return almoco2;
	}

	public void setAlmoco2(Operacao almoco2) {
		this.almoco2 = almoco2;
	}

	public Operacao getAlmoco3() {
		return almoco3;
	}

	public void setAlmoco3(Operacao almoco3) {
		this.almoco3 = almoco3;
	}

	public Operacao getJantar1() {
		return jantar1;
	}

	public void setJantar1(Operacao jantar1) {
		this.jantar1 = jantar1;
	}

	public Operacao getJantar2() {
		return jantar2;
	}

	public void setJantar2(Operacao jantar2) {
		this.jantar2 = jantar2;
	}

	public Operacao getJantar3() {
		return jantar3;
	}

	public void setJantar3(Operacao jantar3) {
		this.jantar3 = jantar3;
	}

	public Integer getQuantidade1() {
		return quantidade1;
	}

	public void setQuantidade1(Integer quantidade1) {
		this.quantidade1 = quantidade1;
	}

	public Integer getQuantidade2() {
		return quantidade2;
	}

	public void setQuantidade2(Integer quantidade2) {
		this.quantidade2 = quantidade2;
	}

	public Integer getQuantidade3() {
		return quantidade3;
	}

	public void setQuantidade3(Integer quantidade3) {
		this.quantidade3 = quantidade3;
	}

	public Integer getQuantidade4() {
		return quantidade4;
	}

	public void setQuantidade4(Integer quantidade4) {
		this.quantidade4 = quantidade4;
	}

	public Integer getQuantidade5() {
		return quantidade5;
	}

	public void setQuantidade5(Integer quantidade5) {
		this.quantidade5 = quantidade5;
	}

	public Integer getQuantidade6() {
		return quantidade6;
	}

	public void setQuantidade6(Integer quantidade6) {
		this.quantidade6 = quantidade6;
	}

	public Integer getQuantidade7() {
		return quantidade7;
	}

	public void setQuantidade7(Integer quantidade7) {
		this.quantidade7 = quantidade7;
	}

	public Integer getQuantidade8() {
		return quantidade8;
	}

	public void setQuantidade8(Integer quantidade8) {
		this.quantidade8 = quantidade8;
	}

	public Integer getQuantidade9() {
		return quantidade9;
	}

	public void setQuantidade9(Integer quantidade9) {
		this.quantidade9 = quantidade9;
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
