package TestesIntegracao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Basica.Categoria;
import Basica.Mesa;
import Fachada.Fachada;
import Negocio.RNMesa;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;

public class MesaTestIntegracao {
	
	EntityManagerFactory emf;
	Fachada fachada;
	RNMesa rnMesa;
	Mesa mesa;
	
	@Before
	public void IniciarHibernate(){
		emf = Persistence.createEntityManagerFactory("projetorestoran");
		fachada = Fachada.getInstance();
		rnMesa = new RNMesa(emf);
	}
	
	
	@Test
	public void TesteMesaInserir() throws NegocioException, DadosException, ControladorException{
		mesa = new Mesa();
		mesa.setNumeroMesa(1);
		mesa.setCapacidadeMesa(5);
		
		fachada.MesaInserir(mesa);
		
		List<Mesa> listamesa = fachada.MesaPesquisarPorObjeto(mesa);
		
	    assertNotNull(listamesa);

	    assertEquals(1, listamesa.size());
	    
	}	
	
	
	@Test
	public void TesteMesaAlterar() throws NegocioException, DadosException, ControladorException{
		mesa = new Mesa();
		mesa.setNumeroMesa(2);
		mesa.setCapacidadeMesa(5);

		fachada.MesaInserir(mesa);
		
		mesa.setNumeroMesa(100);
		
		fachada.MesaAlterar(mesa);
		
		List<Mesa> listamesa = fachada.MesaPesquisarPorObjeto(mesa);

	    assertEquals(1, listamesa.size());
	}
	
	@Test
	public void TesteMesaExcluir() throws NegocioException, DadosException, ControladorException{
		mesa = new Mesa();
		mesa.setNumeroMesa(3);
		mesa.setCapacidadeMesa(5);
		
		fachada.MesaInserir(mesa);
		
		fachada.MesaExcluir(mesa);

		List<Mesa> lisatamesa = fachada.MesaPesquisarPorObjeto(mesa);

		assertEquals(0, lisatamesa.size());
	}
	
	@Test(expected=NegocioException.class)
	public void TesteDuplicidadeNumeroMesa() throws NegocioException, DadosException, ControladorException{
		mesa = new Mesa();
		mesa.setNumeroMesa(3);
		mesa.setCapacidadeMesa(5);
		
		fachada.MesaInserir(mesa);
		
		rnMesa.validarDuplicidadeNumeroMesa(mesa);
	}
	
	@Test
	public void TesteListarTodos() throws NegocioException, DadosException, ControladorException{
		assertNotNull(fachada.MesaListar());
	}
	
	@Test
	public void TesteBuscarPorId() throws NegocioException, DadosException, ControladorException{
		assertEquals(null,fachada.MesaBuscarPorId(99));
	}
	
	@After
	public void FinalizarHibernate(){
		//
		String queryString = "DELETE FROM Mesa";
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.createQuery(queryString).executeUpdate();
		
		tx.commit();
		
		em.close();
	}

}
