package TestesUnitario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import Basica.Categoria;
import Dados.CategoriaDAO;
import Fachada.Fachada;
import Negocio.RNCategoria;
import Utils.NegocioException;

public class CategoriaTest {

	
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
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo DESCRIÇÃO
	 * Usuário 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteNomeVazio() throws NegocioException{
		categoria = new Categoria();
		categoria.setNome("");
		rnCategoria.validarCampos(categoria);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeNull() throws NegocioException{
		categoria = new Categoria();
		categoria.setNome(null);
		rnCategoria.validarCampos(categoria);

	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeMaiorQueOPermitido() throws NegocioException{	
		categoria = new Categoria();
		categoria.setNome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		rnCategoria.validarCampos(categoria);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeMenorQueOPermitido() throws NegocioException{	
		categoria = new Categoria();
		categoria.setNome("a");
		rnCategoria.validarCampos(categoria);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteObjetoExite() throws NegocioException{	
		categoria = null;
		rnCategoria.verificarObjeto(categoria);
	}

}
