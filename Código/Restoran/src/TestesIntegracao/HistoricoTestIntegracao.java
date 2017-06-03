package TestesIntegracao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;

import Basica.Historico;
import Fachada.Fachada;
import Negocio.RNHistorico;

public class HistoricoTestIntegracao {
	
	EntityManagerFactory emf;
	Fachada fachada;
	RNHistorico rnHistorico;
	Historico historico;
	
	@Before
	public void IniciarHibernate(){
		emf = Persistence.createEntityManagerFactory("projetorestoran");
		fachada = Fachada.getInstance();
		rnHistorico = new RNHistorico(emf);
	}

}
