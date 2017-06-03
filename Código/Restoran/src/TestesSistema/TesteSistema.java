package TestesSistema;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import TestesIntegracao.CategoriaTestIntegracao;
import TestesIntegracao.MesaTestIntegracao;
import TestesIntegracao.UsuarioTestIntegracao;
import TestesUnitario.CategoriaTest;
import TestesUnitario.ItemComposicaoProdutoTest;
import TestesUnitario.MesaTest;
import TestesUnitario.ProdutoTest;
import TestesUnitario.UsuarioTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	UsuarioTest.class,
	CategoriaTest.class,
	ItemComposicaoProdutoTest.class,
	MesaTest.class,
	ProdutoTest.class,
	UsuarioTestIntegracao.class,
	CategoriaTestIntegracao.class,
	MesaTestIntegracao.class
})
public class TesteSistema {
}
