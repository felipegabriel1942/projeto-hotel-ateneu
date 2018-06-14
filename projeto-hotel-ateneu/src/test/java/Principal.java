import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.ateneu.hotel.cliente.Cliente;
import br.com.ateneu.hotel.cliente.ClienteDAOHibernate;
import br.com.ateneu.hotel.util.HibernateUtil;

public class Principal {

	public static void main(String[] args) {
		/*Session sessao = HibernateUtil.getSessionFactory().openSession();
		ClienteDAOHibernate clienteDAO = new ClienteDAOHibernate();
		clienteDAO.setSession(sessao);
		Transaction transacao = sessao.beginTransaction();
		
		
		Cliente cliente = new Cliente();
		cliente.setNome("Teste1");
		cliente.setEmail("teste1@gmail.com");
		cliente.setCelular("(85)9999-9999");
		cliente.setNascimento(new Date(System.currentTimeMillis()));
		cliente.setCpf("11111111111");
		clienteDAO.salvar(cliente);
		transacao.commit();
		
		System.out.println("Teste Cadastrou!");*/
		
		Session sessao = null;
		sessao = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Conectou!");
		sessao.close();

	}

}
