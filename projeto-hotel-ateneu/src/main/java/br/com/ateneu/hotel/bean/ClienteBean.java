package br.com.ateneu.hotel.bean;


import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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

	public String cadastrarCliente() {

		ClienteRN clienteRN = new ClienteRN();
		ContratoRN contratoRN = new ContratoRN();
		OperacaoRN operacaoRN = new OperacaoRN();
		ServicoRN servicoRN = new ServicoRN();

		this.contrato.setNomeCompleto(this.cliente.getNome());
		this.contrato.setCpf(this.cliente.getCpf());
		this.contrato.setStatusContrato(1);
		this.contrato.setPeriodo("Janeiro");
		this.contrato.setNumCartaoCredito("5146646464");
		this.contrato.setCliente(cliente);
		
		

		// Settar as informações de operação do quarto
		this.operacaoQuarto.setTipoServico(servicoRN.buscarServicoPorNome(operacaoQuarto.getNomeServico()).getTipo());
		this.operacaoQuarto.setValorOperacao(servicoRN.buscarServicoPorNome(operacaoQuarto.getNomeServico()).getValor());
		this.operacaoQuarto.setDataOperacao(new Date(System.currentTimeMillis()));
		this.operacaoQuarto.setContrato(contrato);
		
		// Settar as informações de operação da babysitter
		this.operacaoBabysitter.setTipoServico(servicoRN.buscarServicoPorNome(operacaoBabysitter.getNomeServico()).getTipo());
		this.operacaoBabysitter.setValorOperacao(servicoRN.buscarServicoPorNome(operacaoBabysitter.getNomeServico()).getValor());
		this.operacaoBabysitter.setDataOperacao(new Date(System.currentTimeMillis()));
		this.operacaoBabysitter.setContrato(contrato);
		
		
		// Settar as informações de operação do carro
		this.operacaoCarro.setTipoServico(servicoRN.buscarServicoPorNome(operacaoCarro.getNomeServico()).getTipo());
		this.operacaoCarro.setValorOperacao(servicoRN.buscarServicoPorNome(operacaoCarro.getNomeServico()).getValor());
		this.operacaoCarro.setDataOperacao(new Date(System.currentTimeMillis()));
		this.operacaoCarro.setContrato(contrato);
		
		
		//salvar as informações referentes ao cliente, contrato e serviços solicitados
		contratoRN.salvar(this.contrato);
		clienteRN.salvar(this.cliente);
		operacaoRN.salvar(this.operacaoQuarto);
		operacaoRN.salvar(this.operacaoBabysitter);
		operacaoRN.salvar(this.operacaoCarro);
		
		
		this.cliente = new Cliente();
		this.contrato = new Contrato();
		
		return "sucesso-cadastro-cliente";
	}
	
	
	
	public Contrato buscarCliente() {
		ContratoRN contratoRN = new ContratoRN();
		return contratoRN.buscarContratoPorCodigo(contrato.getNumeroContrato());
		
	}
	
	
	
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


	
	
}
