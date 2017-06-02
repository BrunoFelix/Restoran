package TestesUnitario;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import Basica.ItemComposicaoProduto;
import Basica.Mesa;
import Basica.Usuario;
import Fachada.Fachada;
import Negocio.RNItemComposicaoProduto;
import Utils.NegocioException;

public class ItemComposicaoProdutoTest {

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
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo NOME
	 * ItemComposicaoProduto 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteNomeVazio() throws NegocioException{
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("");
		rnItemComposicaoProduto.validarCampos(itemComposicaoProduto);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeNull() throws NegocioException{
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome(null);
		rnItemComposicaoProduto.validarCampos(itemComposicaoProduto);

	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeMaiorQueOPermitido() throws NegocioException{	
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		rnItemComposicaoProduto.validarCampos(itemComposicaoProduto);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeMenorQueOPermitido() throws NegocioException{	
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("a");
		rnItemComposicaoProduto.validarCampos(itemComposicaoProduto);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeSomenteComEspacos() throws NegocioException{	
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("      ");
		rnItemComposicaoProduto.validarCampos(itemComposicaoProduto);
	}
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo QUANTIDADE
	 * ItemComposicaoProduto 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteQuantidadeZero() throws NegocioException{
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("Teste");
		itemComposicaoProduto.setQuantidade(0);
		rnItemComposicaoProduto.validarCampos(itemComposicaoProduto);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteQuantidadeNegativo() throws NegocioException{
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("Teste");
		itemComposicaoProduto.setQuantidade(-1);
		rnItemComposicaoProduto.validarCampos(itemComposicaoProduto);

	}
	
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo UNIDADEMEDIDA
	 * ItemComposicaoProduto 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteUnidadeMedidaVazio() throws NegocioException{
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("Teste");
		itemComposicaoProduto.setQuantidade(1);
		itemComposicaoProduto.setUnidadeMedida("");
		rnItemComposicaoProduto.validarCampos(itemComposicaoProduto);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteUnidadeMedidaNull() throws NegocioException{
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("Teste");
		itemComposicaoProduto.setQuantidade(1);
		itemComposicaoProduto.setUnidadeMedida(null);
		rnItemComposicaoProduto.validarCampos(itemComposicaoProduto);

	}
	
	@Test(expected=NegocioException.class)
	public void TesteUnidadeMedidaMaiorQueOPermitido() throws NegocioException{	
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("Teste");
		itemComposicaoProduto.setQuantidade(1);
		itemComposicaoProduto.setUnidadeMedida("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		rnItemComposicaoProduto.validarCampos(itemComposicaoProduto);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteUnidadeMedidaMenorQueOPermitido() throws NegocioException{	
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("Teste");
		itemComposicaoProduto.setQuantidade(1);
		itemComposicaoProduto.setUnidadeMedida("a");
		rnItemComposicaoProduto.validarCampos(itemComposicaoProduto);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteUnidadeMedidaSomenteComEspacos() throws NegocioException{	
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("Teste");
		itemComposicaoProduto.setQuantidade(1);
		itemComposicaoProduto.setUnidadeMedida("      ");
		rnItemComposicaoProduto.validarCampos(itemComposicaoProduto);
	}
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo PRECOCUSTO
	 * ItemComposicaoProduto 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TestePrecoCustoZero() throws NegocioException{
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("Teste");
		itemComposicaoProduto.setQuantidade(1);
		itemComposicaoProduto.setUnidadeMedida("Caixa");
		itemComposicaoProduto.setPrecoCusto(0);
		rnItemComposicaoProduto.validarCampos(itemComposicaoProduto);
	}
	
	@Test(expected=NegocioException.class)
	public void TestePrecoCustoNegativo() throws NegocioException{
		itemComposicaoProduto = new ItemComposicaoProduto();
		itemComposicaoProduto.setNome("Teste");
		itemComposicaoProduto.setQuantidade(1);
		itemComposicaoProduto.setUnidadeMedida("Caixa");
		itemComposicaoProduto.setPrecoCusto(-1);
		rnItemComposicaoProduto.validarCampos(itemComposicaoProduto);

	}
	
	@Test(expected=NegocioException.class)
	public void TesteObjetoExite() throws NegocioException{	
		itemComposicaoProduto = null;
		rnItemComposicaoProduto.verificarObjeto(itemComposicaoProduto);
	}
}
