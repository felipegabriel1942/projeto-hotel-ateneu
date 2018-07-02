package br.com.ateneu.hotel.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.ateneu.hotel.cliente.Cliente;
import br.com.ateneu.hotel.cliente.ClienteRN;
import br.com.ateneu.hotel.contrato.Contrato;
import br.com.ateneu.hotel.contrato.ContratoRN;
import br.com.ateneu.hotel.operacao.Operacao;
import br.com.ateneu.hotel.operacao.OperacaoRN;
import br.com.ateneu.hotel.servico.ServicoRN;

@ManagedBean(name = "clienteBean")
@RequestScoped
public class ClienteBean {
	private Cliente cliente = new Cliente();
	private Contrato contrato = new Contrato();
	private Operacao operacaoQuarto = new Operacao();
	private Operacao operacaoBabysitter = new Operacao();
	private Operacao operacaoCarro = new Operacao();
	private Contrato contratoCheckout = new Contrato();
	private Contrato buscarContrato = new Contrato();
	List<Operacao> operacoesCliente = new ArrayList<Operacao>();
	private float totalPagar;
	private List<String> opcoesCarroAlugado = new ArrayList<String>();
	public float valorAdicionalCarro;
	

	public String cadastrarCliente() {

		ClienteRN clienteRN = new ClienteRN();
		ContratoRN contratoRN = new ContratoRN();
		OperacaoRN operacaoRN = new OperacaoRN();
		ServicoRN servicoRN = new ServicoRN();

		this.contrato.setNomeCompleto(this.cliente.getNome());
		this.contrato.setCpf(this.cliente.getCpf());
		this.contrato.setStatusContrato(1);
		this.contrato.setPeriodo("Janeiro");
		this.contrato.setCliente(cliente);

		// Settar as informações de operação do quarto
		this.operacaoQuarto.setTipoServico(servicoRN.buscarServicoPorNome(operacaoQuarto.getNomeServico()).getTipo());
		this.operacaoQuarto
				.setValorOperacao(servicoRN.buscarServicoPorNome(operacaoQuarto.getNomeServico()).getValor());
		this.operacaoQuarto.setDataOperacao(new Date(System.currentTimeMillis()));
		this.operacaoQuarto.setContrato(contrato);

		// Settar as informações de operação da babysitter
		this.operacaoBabysitter
				.setTipoServico(servicoRN.buscarServicoPorNome(operacaoBabysitter.getNomeServico()).getTipo());
		this.operacaoBabysitter
				.setValorOperacao(servicoRN.buscarServicoPorNome(operacaoBabysitter.getNomeServico()).getValor());
		this.operacaoBabysitter.setDataOperacao(new Date(System.currentTimeMillis()));
		this.operacaoBabysitter.setContrato(contrato);

		// Settar as informações de operação do carro
		this.operacaoCarro.setTipoServico(servicoRN.buscarServicoPorNome(operacaoCarro.getNomeServico()).getTipo());
		this.operacaoCarro.setValorOperacao(servicoRN.buscarServicoPorNome(operacaoCarro.getNomeServico()).getValor() + valorAdicionalCarro);
		this.operacaoCarro.setDataOperacao(new Date(System.currentTimeMillis()));
		this.operacaoCarro.setContrato(contrato);
		
		

		// salvar as informações referentes ao cliente, contrato e serviços solicitados
		contratoRN.salvar(this.contrato);
		clienteRN.salvar(this.cliente);
		operacaoRN.salvar(this.operacaoQuarto);
		operacaoRN.salvar(this.operacaoBabysitter);
		operacaoRN.salvar(this.operacaoCarro);

		this.cliente = new Cliente();
		this.contrato = new Contrato();

		return "sucesso-cadastro-cliente";
	}
	
	//Preenche a pagina com os dados do cliente
	public String buscarCliente() {
		ContratoRN contratoRN = new ContratoRN();
		OperacaoRN operacaoRN = new OperacaoRN();
			
		contratoCheckout = contratoRN.buscarContratoPorCodigo(this.contrato.getNumeroContrato());
		
		//Verificação da situação do contrato pesquisado ou se está cadastrado
		if(contratoCheckout == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Contrato não existe","");
			FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);
			
		} else if(contratoCheckout.getStatusContrato() == 0) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,"Contrato está inativo","");
			FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);
			this.contratoCheckout = null;
			
		} else {
			operacoesCliente = operacaoRN.listarOperacoesUsuario(contratoCheckout);
			
			//Calculo do valor do total do cliente deve pagar
			for (int i = 0; i < operacoesCliente.size(); i++) {
				this.setTotalPagar(this.getTotalPagar() + operacoesCliente.get(i).getValorOperacao());
				System.out.println("Valor a pagar " + i + " " + this.getTotalPagar());
			}
						
		}
		
		return "checkout";
	}
	
	//Metodo para buscar e editar um contrato
	public String buscarEditarContrato() {
		ContratoRN contratoRN = new ContratoRN();
		OperacaoRN operacaoRN = new OperacaoRN();
		
		
		buscarContrato = contratoRN.buscarContratoPorCodigo(this.contrato.getNumeroContrato());
		
		//Verificação da situação do contrato pesquisado ou se está cadastrado
		if(buscarContrato == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Contrato não existe","");
			FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);
			
		} else if(buscarContrato.getStatusContrato() == 0) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,"Contrato está inativo","");
			FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);
			this.buscarContrato = null;
			
		} else {
			operacoesCliente = operacaoRN.listarOperacoesUsuario(buscarContrato);
			
			//Calculo do valor do total do cliente deve pagar
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
		
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Checkout efetuado com sucesso","");
		FacesContext.getCurrentInstance().addMessage("mensagens", facesMessage);
		
		this.contratoCheckout = null;
		this.contrato = null;
		return "checkout";
		
	}
	
	//Getters e Setters
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

	
	
	
}
