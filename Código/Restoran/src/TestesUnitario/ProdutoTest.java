package TestesUnitario;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import Basica.ItemComposicaoProduto;
import Basica.Produto;
import Fachada.Fachada;
import Negocio.RNProduto;
import Utils.NegocioException;

public class ProdutoTest {
	
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
	
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo NOME
	 * ItemComposicaoProduto 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteNomeVazio() throws NegocioException{
		produto = new Produto();
		produto.setNome("");
		rnProduto.validarCampos(produto);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeNull() throws NegocioException{
		produto = new Produto();
		produto.setNome(null);
		rnProduto.validarCampos(produto);

	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeMaiorQueOPermitido() throws NegocioException{	
		produto = new Produto();
		produto.setNome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		rnProduto.validarCampos(produto);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeMenorQueOPermitido() throws NegocioException{	
		produto = new Produto();
		produto.setNome("a");
		rnProduto.validarCampos(produto);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeSomenteComEspacos() throws NegocioException{	
		produto = new Produto();
		produto.setNome("      ");
		rnProduto.validarCampos(produto);
	}
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo PRECOVENDA
	 * ItemComposicaoProduto 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TestePrecoVendaZero() throws NegocioException{
		produto = new Produto();
		produto.setNome("Teste");
		produto.setPrecoVenda(0);
		rnProduto.validarCampos(produto);
	}
	
	@Test(expected=NegocioException.class)
	public void TestePrecoVendaQuantidadeNegativo() throws NegocioException{
		produto = new Produto();
		produto.setNome("Teste");
		produto.setPrecoVenda(-1);
		rnProduto.validarCampos(produto);

	}
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo PRECOCUSTO
	 * ItemComposicaoProduto 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TestePrecoCustoZero() throws NegocioException{
		produto = new Produto();
		produto.setNome("Teste");
		produto.setPrecoVenda(1);
		produto.setPrecoCusto(0);
		rnProduto.validarCampos(produto);
	}
	
	@Test(expected=NegocioException.class)
	public void TestePrecoCustoNegativo() throws NegocioException{
		produto = new Produto();
		produto.setNome("Teste");
		produto.setPrecoVenda(1);
		produto.setPrecoCusto(-1);
		rnProduto.validarCampos(produto);

	}
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo QUANTIDADE
	 * ItemComposicaoProduto 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteQuantidadeZero() throws NegocioException{
		produto = new Produto();
		produto.setNome("Teste");
		produto.setPrecoVenda(1);
		produto.setPrecoCusto(1);
		produto.setQuantidade(0);
		rnProduto.validarCampos(produto);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteQuantidadeNegativo() throws NegocioException{
		produto = new Produto();
		produto.setNome("Teste");
		produto.setPrecoVenda(1);
		produto.setPrecoCusto(1);
		produto.setQuantidade(-1);
		rnProduto.validarCampos(produto);

	}
	
	@Test(expected=NegocioException.class)
	public void TesteObjetoExite() throws NegocioException{	
		produto = null;
		rnProduto.verificarObjeto(produto);
	}
}
