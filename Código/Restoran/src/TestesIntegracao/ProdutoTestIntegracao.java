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
import Basica.Produto;
import Fachada.Fachada;
import Negocio.RNProduto;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;

public class ProdutoTestIntegracao {
	EntityManagerFactory emf;
	Fachada fachada;
	RNProduto rnProduto;
	Produto produto;
	
	@Before
	public void IniciarHibernate(){
		emf = Persistence.createEntityManagerFactory("projetorestoran");
		fachada = Fachada.getInstance();
		rnProduto = new RNProduto(emf);
	}
	
	@Test
	public void TesteProdutoInserir() throws NegocioException, DadosException, ControladorException{
		produto = new Produto();
		produto.setNome("TesteInserir");
		produto.setQuantidade(10);
		produto.setPrecoVenda(10);
		produto.setPrecoCusto(10);
		
		//fachada.ProdutoInserir(produto);
		
		List<Produto> listaproduto = fachada.ProdutoPesquisarPorObjeto(produto);
		
	    assertNotNull(listaproduto);

	    assertEquals(1, listaproduto.size());
	    
	}	
	
	@Test
	public void TesteCategoriaAlterar() throws NegocioException, DadosException, ControladorException{
		produto = new Produto();
		produto.setNome("TesteAlterar");
		produto.setQuantidade(10);
		produto.setPrecoVenda(10);
		produto.setPrecoCusto(10);
		
		//fachada.ProdutoInserir(produto);
		
		produto.setNome("Bebida");
		
		fachada.ProdutoAlterar(produto);
		
		List<Produto> listaproduto = fachada.ProdutoPesquisarPorObjeto(produto);

	    assertEquals(1, listaproduto.size());
	}
	
	@Test
	public void TesteCategoriaExcluir() throws NegocioException, DadosException, ControladorException{
		produto = new Produto();
		produto.setNome("TesteExcluir");
		produto.setQuantidade(10);
		produto.setPrecoVenda(10);
		produto.setPrecoCusto(10);
		
		//fachada.ProdutoInserir(produto);
		
		fachada.ProdutoExcluir(produto);

		List<Produto> listaproduto = fachada.ProdutoPesquisarPorObjeto(produto);

		assertEquals(0, listaproduto.size());
	}
	
	@Test(expected=NegocioException.class)
	public void TesteDuplicidadeCategoria() throws NegocioException, DadosException, ControladorException{
		produto = new Produto();
		produto.setNome("TesteDuplic");
		produto.setQuantidade(10);
		produto.setPrecoVenda(10);
		produto.setPrecoCusto(10);
		
		//fachada.ProdutoInserir(produto);

		rnProduto.validarDuplicidade(produto);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteExistenciaCategoria() throws NegocioException, DadosException, ControladorException{
		produto = new Produto();
		produto.setNome("TesteExist");
		produto.setQuantidade(10);
		produto.setPrecoVenda(10);
		produto.setPrecoCusto(10);
		
		rnProduto.validaExistencia(produto);
	}
	
	@Test
	public void TesteListarTodos() throws NegocioException, DadosException, ControladorException{
		assertNotNull(fachada.ProdutoListar());
	}
	
	@Test
	public void TesteBuscarPorId() throws NegocioException, DadosException, ControladorException{
		assertEquals(null,fachada.ProdutoBuscarPorId((long) 99));
	}
	
	
	@After
	public void FinalizarHibernate(){
		//
		String queryString = "DELETE FROM Produto";
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.createQuery(queryString).executeUpdate();
		
		tx.commit();
		
		em.close();
	}
}
