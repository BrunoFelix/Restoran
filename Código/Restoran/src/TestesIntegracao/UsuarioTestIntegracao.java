package TestesIntegracao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Basica.ItemComposicaoProduto;
import Basica.Usuario;
import Dados.UsuarioDAO;
import Dados.Geral.DAOGenerico;
import Fachada.Fachada;
import Negocio.RNUsuario;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;

public class UsuarioTestIntegracao{
	

	EntityManagerFactory emf;
	Fachada fachada;
	RNUsuario rnUsuario;
	Usuario usuario;
	
	@Before
	public void IniciarHibernate(){
		emf = Persistence.createEntityManagerFactory("projetorestoran");
		fachada = Fachada.getInstance();
		rnUsuario = new RNUsuario(emf);
	}
	
	@Test
	public void TesteUsuarioInserir() throws NegocioException, DadosException, ControladorException{
		usuario = new Usuario();
		usuario.setNome("TesteInserir");
		usuario.setCpf("12345678909");
		usuario.setEmail("emailinserir@hotmail.com");
		usuario.setLogin("testeinserir");
		usuario.setSenha("123456");
		usuario.setTelefone("81999878578");
		usuario.setTipo("Garçom");
		usuario.setSexo("M");
		
		fachada.UsuarioInserir(usuario);
		
		List<Usuario> listausuario = fachada.UsuarioPesquisarPorObjeto(usuario);
		
	    assertNotNull(listausuario);

	    //assertEquals(1, listausuario.size());
	    
	}	
	
	@Test
	public void TesteUsuarioExcluir() throws NegocioException, DadosException, ControladorException{
		usuario = new Usuario();
		usuario.setNome("TesteExcluir");
		usuario.setCpf("98765432100");
		usuario.setEmail("emailexcluir@hotmail.com");
		usuario.setLogin("testeexcluir");
		usuario.setSenha("123456");
		usuario.setTelefone("81999878578");
		usuario.setTipo("Garçom");
		usuario.setSexo("M");
		
		fachada.UsuarioInserir(usuario);
		
		fachada.UsuarioExcluir(usuario);

				
		List<Usuario> listausuario = fachada.UsuarioPesquisarPorObjeto(usuario);

		assertEquals(0, listausuario.size());
	}

	@Test
	public void TesteUsuarioAlterar() throws NegocioException, DadosException, ControladorException{
		usuario = new Usuario();
		usuario.setNome("TesteAlterar");
		usuario.setCpf("45697812309");
		usuario.setEmail("emailAlterar@hotmail.com");
		usuario.setLogin("testealterar");
		usuario.setSenha("123456");
		usuario.setTelefone("81999878578");
		usuario.setTipo("Garçom");
		usuario.setSexo("M");
		
		fachada.UsuarioInserir(usuario);
		
		usuario.setNome("Bruno Barbosa");
		
		fachada.UsuarioAlterar(usuario);
		
		Usuario pesquisar = new Usuario();
		
		pesquisar.setNome("Bruno Barbosa");
		
		List<Usuario> listausuario = fachada.UsuarioPesquisarPorObjeto(pesquisar);

	    assertEquals(1, listausuario.size());
	}

	
	@Test
	public void TesteUsuarioListarTodos() throws NegocioException, DadosException, ControladorException{
		List<Usuario> listausuario = fachada.UsuarioListar();

		assertNotNull(listausuario.size());
	}
	
	@Test
	public void TesteUsuarioLogar() throws NegocioException, DadosException, ControladorException{
		usuario = new Usuario();
		usuario.setNome("TesteLogar");
		usuario.setCpf("78935451508");
		usuario.setEmail("testelogar@hotmail.com");
		usuario.setLogin("testelogar");
		usuario.setSenha("123456");
		usuario.setTelefone("81999878578");
		usuario.setTipo("Garçom");
		usuario.setSexo("M");
		
		fachada.UsuarioInserir(usuario);
		
		assertNotNull(fachada.Usuariologar(usuario));
	}
	
	
	@Test(expected=NegocioException.class)
	public void TesteDuplicidadeEmail() throws NegocioException, DadosException, ControladorException{
		usuario = new Usuario();
		usuario.setNome("TesteDuplicidadeEmail");
		usuario.setCpf("74878978978");
		usuario.setEmail("testeduplicidadeemail@hotmail.com");
		usuario.setLogin("testedupemail");
		usuario.setSenha("123456");
		usuario.setTelefone("81999878578");
		usuario.setTipo("Garçom");
		usuario.setSexo("M");
		
		fachada.UsuarioInserir(usuario);
		
		rnUsuario.validarDuplicidadeEmail(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteDuplicidadeLogin() throws NegocioException, DadosException, ControladorException{
		usuario = new Usuario();
		usuario.setNome("TesteDuplicLogin");
		usuario.setCpf("45646012348");
		usuario.setEmail("testedupliclogin@hotmail.com");
		usuario.setLogin("testeduplogin");
		usuario.setSenha("123456");
		usuario.setTelefone("81999878578");
		usuario.setTipo("Garçom");
		usuario.setSexo("M");
		
		fachada.UsuarioInserir(usuario);
		
		rnUsuario.validarDuplicidadeLogin(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteValidaExistencia() throws NegocioException, DadosException, ControladorException{
		usuario = new Usuario();
		usuario.setNome("TesteValidExit");
		usuario.setCpf("47874897809");
		usuario.setEmail("testevalidext@hotmail.com");
		usuario.setLogin("testevalidexit");
		usuario.setSenha("123456");
		usuario.setTelefone("81999878578");
		usuario.setTipo("Garçom");
		usuario.setSexo("M");
		
		fachada.UsuarioInserir(usuario);

		usuario.setLogin("haha");
		
		rnUsuario.validaExistencia(usuario);
	}
	
	@Test
	public void TesteUsuarioPorID() throws NegocioException, DadosException, ControladorException{	
		assertEquals(null, fachada.UsuarioBuscarPorId(99)); 
		
	}
	
	
	@After
	public void FinalizarHibernate(){
		//
		String queryString = "DELETE FROM Usuario";
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.createQuery(queryString).executeUpdate();
		
		tx.commit();
		
		em.close();
	}
}
