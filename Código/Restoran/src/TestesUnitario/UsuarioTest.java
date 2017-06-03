package TestesUnitario;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Basica.ItemComposicaoProduto;
import Basica.Usuario;
import Dados.UsuarioDAO;
import Fachada.Fachada;
import Negocio.RNUsuario;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;

public class UsuarioTest {
	
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
	
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo NOME
	 * Usuário 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteNomeVazio() throws NegocioException{
		usuario = new Usuario();
		usuario.setNome("");
		rnUsuario.preenchimentoCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeNull() throws NegocioException{
		usuario = new Usuario();
		usuario.setNome(null);
		rnUsuario.preenchimentoCampos(usuario);

	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeMaiorQueOPermitido() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		rnUsuario.validarCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteNomeMenorQueOPermitido() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("a");
		rnUsuario.validarCampos(usuario);
	}
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo CPF
	 * Usuário 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteCpfVazio() throws NegocioException{
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("");
		rnUsuario.preenchimentoCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteCpfNull() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf(null);
		rnUsuario.preenchimentoCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteCpfMaiorQueOPermitido() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("123456789111");
		rnUsuario.validarCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteCpfMenorQueOPermitido() throws NegocioException{
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("1");
		rnUsuario.validarCampos(usuario);
	}
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo TELEFONE
	 * Usuário 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteTelefoneVazio() throws NegocioException{
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("");
		rnUsuario.preenchimentoCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteTelefoneNull() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone(null);
		rnUsuario.preenchimentoCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteTelefoneMaiorQueOPermitido() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("819999999999");
		rnUsuario.validarCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteTelefoneMenorQueOPermitido() throws NegocioException{
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("8195788");
		rnUsuario.validarCampos(usuario);
	}
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo LOGIN
	 * Usuário 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteLoginVazio() throws NegocioException{
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("");
		rnUsuario.preenchimentoCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteLoginVazio2() throws NegocioException{
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("");
		rnUsuario.validaPreenchimentoUsuarioSenha(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteLoginNull() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin(null);
		rnUsuario.preenchimentoCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteLoginNull2() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin(null);
		rnUsuario.validaPreenchimentoUsuarioSenha(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteLoginMaiorQueOPermitido() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("testeloginmaiorqueoresultadoesperado");
		rnUsuario.validarCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteLoginMenorQueOPermitido() throws NegocioException{
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("a");
		rnUsuario.validarCampos(usuario);
	}
		
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo SENHA
	 * Usuário 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteSenhaVazio() throws NegocioException{
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("ADMIN");
		usuario.setSenha("");
		rnUsuario.preenchimentoCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteSenhaVazio2() throws NegocioException{
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("ADMIN");
		usuario.setSenha("");
		rnUsuario.validaPreenchimentoUsuarioSenha(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteSenhaNull() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("ADMIN");
		usuario.setSenha(null);
		rnUsuario.preenchimentoCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteSenhaNull2() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("ADMIN");
		usuario.setSenha(null);
		rnUsuario.validaPreenchimentoUsuarioSenha(usuario);
	}
	
	
	@Test(expected=NegocioException.class)
	public void TesteSenhaMaiorQueOPermitido() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("ADMIN");
		usuario.setSenha("testeloginmaiorqueoresultadoesperado");
		rnUsuario.validarCampos(usuario);
	}
		
	@Test(expected=NegocioException.class)
	public void TesteSenhaMenorQueOPermitido() throws NegocioException{
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("ADMIN");
		usuario.setSenha("a");
		rnUsuario.validarCampos(usuario);
	}
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo Tipo
	 * Usuário 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteTipoVazio() throws NegocioException{
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("ADMIN");
		usuario.setSenha("ADMIN");
		usuario.setTipo("");
		rnUsuario.preenchimentoCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteTipoNull() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("ADMIN");
		usuario.setSenha("ADMIN");
		usuario.setTipo(null);
		rnUsuario.preenchimentoCampos(usuario);
	}
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo SEXO
	 * Usuário 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteSexoVazio() throws NegocioException{
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("ADMIN");
		usuario.setSenha("ADMIN");
		usuario.setTipo("Garçom");
		usuario.setSexo("");
		rnUsuario.preenchimentoCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteSexoNull() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("ADMIN");
		usuario.setSenha("ADMIN");
		usuario.setTipo("Garçom");
		usuario.setSexo(null);
		rnUsuario.preenchimentoCampos(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteSexoMaiorQueOPermitido() throws NegocioException{	
		usuario = new Usuario();
		usuario.setNome("Bruno Felix");
		usuario.setCpf("10357897808");
		usuario.setTelefone("81995782171");
		usuario.setLogin("ADMIN");
		usuario.setSenha("ADMIN");
		usuario.setTipo("Garçom");
		usuario.setSexo("Masculino");
		rnUsuario.validarCampos(usuario);
	}
	
	/***********************************
	 * 
	 * Testes da regra de negocio com relação ao campo EMAIL
	 * Usuário 
	 * @throws NegocioException
	 */
	@Test(expected=NegocioException.class)
	public void TesteEmailVazio() throws NegocioException{
		usuario = new Usuario();
		usuario.setEmail("");
		rnUsuario.validarEmail(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteEmailNull() throws NegocioException{	
		usuario = new Usuario();
		usuario.setEmail(null);
		rnUsuario.validarEmail(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteEmailInvalido() throws NegocioException{	
		usuario = new Usuario();
		usuario.setEmail("b@fggdf@xed");
		rnUsuario.validarEmail(usuario);
	}
	
	@Test(expected=NegocioException.class)
	public void TesteObjetoExite() throws NegocioException{	
		usuario = null;
		rnUsuario.verificarObjeto(usuario);
	}

	@After
	public void FinalizarHibernate(){
		//
	}
}
