package Testes;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import Basica.Usuario;
import Dados.UsuarioDAO;

public class UsuarioTest {
	
	EntityManagerFactory emf;
	
	@Before
	public void IniciarHibernate(){
		emf = Persistence.createEntityManagerFactory("projetorestoran");
	}

	@Test
	public void TesteUsuarioInserir(){
		Usuario usuario = new Usuario();
		usuario.setNome("Bruno");
		usuario.setCpf("41459738799");
		usuario.setEmail("bruno@hotmail.com");
		usuario.setLogin("bruno");
		usuario.setSenha("Bruno");
		usuario.setTelefone("81999878578");
		UsuarioDAO usuariodao = new UsuarioDAO(emf);
		usuariodao.insert(usuario);
		
		List<Usuario> listausuario = usuariodao.getAll();
		
	    assertNotNull(listausuario);

	    assertEquals(1, listausuario.size());
	}
}
