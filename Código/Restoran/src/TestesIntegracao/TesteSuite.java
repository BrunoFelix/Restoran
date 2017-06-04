package TestesIntegracao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	UsuarioTestIntegracao.class,
	CategoriaTestIntegracao.class,
	MesaTestIntegracao.class,
	ItemComposicaoProdutoTestIntegracao.class,
	ProdutoTestIntegracao.class
})
public class TesteSuite {
}
