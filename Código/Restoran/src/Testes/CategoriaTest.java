package Testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import Basica.Categoria;
import Basica.Usuario;
import Dados.CategoriaDAO;
import Dados.UsuarioDAO;

public class CategoriaTest {

	@Test
	public void TesteCategoriaInserir(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetorestoran");
		Categoria categoria = new Categoria();
		categoria.setNome("Bebida");
		CategoriaDAO categoriadao = new CategoriaDAO(emf);
		categoriadao.insert(categoria);
		
		List<Categoria> listacategoria = categoriadao.getAll();
		
	    assertNotNull(listacategoria);

	    assertEquals(1, listacategoria.size());
	}
}
