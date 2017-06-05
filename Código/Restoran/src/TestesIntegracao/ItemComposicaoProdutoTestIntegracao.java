package TestesIntegracao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Basica.Categoria;
import Basica.ItemComposicaoProduto;
import Fachada.Fachada;
import Negocio.RNItemComposicaoProduto;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;

public class ItemComposicaoProdutoTestIntegracao {

	EntityManagerFactory emf;
	Fachada fachada;
	RNItemComposicaoProduto rnItemComposicaoProduto;
	ItemComposicaoProduto itemComposicaoProduto;
	
	@Before
	public void IniciarHibernate(){
		emf = Persistence.createEntityManagerFactory("projetorestoran");
		fachada = Fachada.getInstance();
		rnItemComposicaoProduto = new RNItemComposicaoProduto(emf);
	}
	
	@Test
	public void TesteItemComposicaoProdutoInserir() throws NegocioException, DadosException, ControladorException{
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("TesteInserir");
		itemComposicaoProduto.setQuantidade(10);
		itemComposicaoProduto.setPrecoCusto(10);
		itemComposicaoProduto.setUnidadeMedida("Cx");
		
		fachada.ItemComposicaoProdutoInserir(itemComposicaoProduto);
		
		List<ItemComposicaoProduto> listaitemcomposicaoproduto = fachada.CategoriaItemComposicaoProdutoPorObjeto(itemComposicaoProduto);
		
	    assertNotNull(listaitemcomposicaoproduto);

	    assertEquals(1, listaitemcomposicaoproduto.size());
	    
	}	
	
	@Test
	public void TesteItemComposicaoProdutoAlterar() throws NegocioException, DadosException, ControladorException{
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("TesteAlterar");
		itemComposicaoProduto.setQuantidade(10);
		itemComposicaoProduto.setPrecoCusto(10);
		itemComposicaoProduto.setUnidadeMedida("Cx");
		
		fachada.ItemComposicaoProdutoInserir(itemComposicaoProduto);
		
		itemComposicaoProduto.setNome("Farinha");
		
		fachada.ItemComposicaoProdutoAlterar(itemComposicaoProduto);
		
		List<ItemComposicaoProduto> listaitemcomposicaoproduto = fachada.CategoriaItemComposicaoProdutoPorObjeto(itemComposicaoProduto);

	    assertEquals(1, listaitemcomposicaoproduto.size());
	}
	
	@Test
	public void TesteItemComposicaoProdutoExcluir() throws NegocioException, DadosException, ControladorException{
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("TesteExcluir");
		itemComposicaoProduto.setQuantidade(10);
		itemComposicaoProduto.setPrecoCusto(10);
		itemComposicaoProduto.setUnidadeMedida("Cx");
		
		fachada.ItemComposicaoProdutoInserir(itemComposicaoProduto);
		
		fachada.ItemComposicaoProdutoExcluir(itemComposicaoProduto);

		List<ItemComposicaoProduto> listaitemcomposicaoproduto = fachada.CategoriaItemComposicaoProdutoPorObjeto(itemComposicaoProduto);

		assertEquals(0, listaitemcomposicaoproduto.size());
	}
	
	@Test(expected=NegocioException.class)
	public void validarDuplicidade() throws NegocioException, DadosException, ControladorException{
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("TesteDuplic");
		itemComposicaoProduto.setQuantidade(10);
		itemComposicaoProduto.setPrecoCusto(10);
		itemComposicaoProduto.setUnidadeMedida("Cx");
		
		fachada.ItemComposicaoProdutoInserir(itemComposicaoProduto);
		
		rnItemComposicaoProduto.validarDuplicidade(itemComposicaoProduto);
	}
	
	@Test(expected=NegocioException.class)
	public void validarExistencia() throws NegocioException, DadosException, ControladorException{
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("TesteExist");
		itemComposicaoProduto.setQuantidade(10);
		itemComposicaoProduto.setPrecoCusto(10);
		itemComposicaoProduto.setUnidadeMedida("Cx");
		
		rnItemComposicaoProduto.validaExistencia(itemComposicaoProduto);
	}
	
	@Test
	public void TesteListarTodos() throws NegocioException, DadosException, ControladorException{
		assertNotNull(fachada.ItemComposicaoProdutoListar());
	}
	
	@Test
	public void TesteBuscarPorId() throws NegocioException, DadosException, ControladorException{
		assertEquals(null,fachada.ItemComposicaoProdutoBuscarPorId((long) 99));
	}
	
	
	@After
	public void FinalizarHibernate(){
		//
		String queryString = "DELETE FROM ItemComposicaoProduto";
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.createQuery(queryString).executeUpdate();
		
		tx.commit();
		
		em.close();
	}
}
