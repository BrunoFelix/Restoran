package Testes;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import Basica.Usuario;
import Dados.UsuarioDAO;
import Fachada.Fachada;
import Negocio.RNUsuario;
import Utils.DadosException;
import Utils.NegocioException;

public class UsuarioTest {
	
	EntityManagerFactory emf;
	Fachada fachada;
	RNUsuario rnUsuario;
	
	@Before
	public void IniciarHibernate(){
		emf = Persistence.createEntityManagerFactory("projetorestoran");
		fachada = Fachada.getInstance();
		rnUsuario = new RNUsuario(emf);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeInvalido(){
		Usuario usuario = new Usuario();
		usuario.setNome("nome");
		
		try {
			rnUsuario.validarCampos(usuario);
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			assertEquals("Nome inválido!", e.getMessage());
		}
	}

	
	@Test
	public void TesteUsuarioInserir() throws NegocioException, DadosException{
		Usuario usuario = new Usuario();
		usuario.setNome("Bruno felix");
		usuario.setCpf("41459738799");
		usuario.setEmail("bruno@hotmail.com");
		usuario.setLogin("bruno");
		usuario.setSenha("Bruno");
		usuario.setTelefone("81999878578");
		usuario.setSexo("M");
		
		fachada.UsuarioInserir(usuario);
		
		List<Usuario> listausuario = fachada.UsuarioListar();
		
	    assertNotNull(listausuario);

	    assertEquals(1, listausuario.size());
	}
	
	@After
	public void FinalizarHibernate(){
		//
	}
}
