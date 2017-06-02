package TestesUnitario;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	UsuarioTest.class,
	CategoriaTest.class,
	ItemComposicaoProdutoTest.class,
	MesaTest.class,
	ProdutoTest.class
	
})
public class TesteSuite {
}
