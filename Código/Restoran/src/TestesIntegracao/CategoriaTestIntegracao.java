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
import Basica.Usuario;
import Fachada.Fachada;
import Negocio.RNCategoria;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;

public class CategoriaTestIntegracao {
	
	EntityManagerFactory emf;
	Fachada fachada;
	RNCategoria rnCategoria;
	Categoria categoria;
	
	@Before
	public void IniciarHibernate(){
		emf = Persistence.createEntityManagerFactory("projetorestoran");
		fachada = Fachada.getInstance();
		rnCategoria = new RNCategoria(emf);
	}
	
	
	@Test
	public void TesteCategoriaInserir() throws NegocioException, DadosException, ControladorException{
		categoria = new Categoria();
		categoria.setNome("TesteInserir");
		
		fachada.CategoriaInserir(categoria);
		
		List<Categoria> listacategoria = fachada.CategoriaPesquisarPorObjeto(categoria);
		
	    assertNotNull(listacategoria);

	    assertEquals(1, listacategoria.size());
	    
	}	
	
	@Test
	public void TesteCategoriaAlterar() throws NegocioException, DadosException, ControladorException{
		categoria = new Categoria();
		categoria.setNome("TesteAlterar");
		
		fachada.CategoriaInserir(categoria);
		
		categoria.setNome("Bebida");
		
		fachada.CategoriaAlterar(categoria);
		
		List<Categoria> listacategoria = fachada.CategoriaPesquisarPorObjeto(categoria);

	    assertEquals(1, listacategoria.size());
	}
	
	@Test
	public void TesteCategoriaExcluir() throws NegocioException, DadosException, ControladorException{
		categoria = new Categoria();
		categoria.setNome("TesteExcluir");
		
		fachada.CategoriaInserir(categoria);
		
		fachada.CategoriaExcluir(categoria);

		List<Categoria> listacategoria = fachada.CategoriaPesquisarPorObjeto(categoria);

		assertEquals(0, listacategoria.size());
	}
	
	@Test(expected=NegocioException.class)
	public void TesteDuplicidadeCategoria() throws NegocioException, DadosException, ControladorException{
		categoria = new Categoria();
		categoria.setNome("TesteDuplic");
		
		fachada.CategoriaInserir(categoria);
		
		rnCategoria.validarDuplicidadeCategoria(categoria);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteExistenciaCategoria() throws NegocioException, DadosException, ControladorException{
		categoria = new Categoria();
		categoria.setNome("TesteDuplic");
		
		rnCategoria.validaExistencia(categoria);
	}
	
	
	@Test
	public void TesteListarTodos() throws NegocioException, DadosException, ControladorException{
		assertNotNull(fachada.CategoriaListar());
	}
	
	@Test
	public void TesteBuscarPorId() throws NegocioException, DadosException, ControladorException{
		assertEquals(null,fachada.CategoriaBuscarPorId(99));
	}
	
	@After
	public void FinalizarHibernate(){
		//
		String queryString = "DELETE FROM Categoria";
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.createQuery(queryString).executeUpdate();
		
		tx.commit();
		
		em.close();
	}

}
