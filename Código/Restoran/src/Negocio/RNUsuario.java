package Negocio;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManagerFactory;

import Basica.Usuario;
import Dados.UsuarioDAO;
import Dados.Geral.DAOGenerico;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;

public class RNUsuario extends DAOGenerico<Usuario>{

	UsuarioDAO usuarioDAO;

	public RNUsuario(EntityManagerFactory emf) {
		super(emf);
		usuarioDAO = new UsuarioDAO(emf);
	}

	/*
	 * ################################## 
	 * 			FUNCIONALIDADES
	 * ##################################
	 */

	public void salvar(Usuario u) throws NegocioException, DadosException {
		preenchimentoCampos(u);
		validarCampos(u);
		validarEmail(u);
		validarDuplicidadeEmail(u);
		validarDuplicidadeLogin(u);
		usuarioDAO.insert(u);
	}

	public void alterar(Usuario u) throws ControladorException, NegocioException, DadosException {
		preenchimentoCampos(u);
		validarCampos(u);
		/*validarEmail(u);
		validarDuplicidadeEmail(u);
		validarDuplicidadeLogin(u);*/ //VALIDAR E VERIFICAR EMAIL DIFERENTES DO QUE ELE TINHA ANTES
		usuarioDAO.update(u);
	}

	public void excluir(Usuario u) throws ControladorException, NegocioException, DadosException {
		validaExistencia(u);
		usuarioDAO.remove(u);
	}
	public List<Usuario> listar(){
	 return usuarioDAO.getAll();
   }
	public Usuario logar (Usuario u)  throws NegocioException, DadosException {
		validaPreenchimentoUsuarioSenha(u);
		return usuarioDAO.logar(u.getLogin(), u.getSenha());
	}
	public List<Usuario> PesquisarUsandoObjeto(Usuario u) throws DadosException{
		return usuarioDAO.PesquisarUsandoObjeto(u); 
	}
   
	/*
	 * ################################## 
	 * 				VALIDACOES
	 * ##################################
	 */
	public void verificarObjeto(Usuario u) throws NegocioException {

		if (u == null)
			throw new NegocioException("Objeto Usuario n�o preenchido");
	}
	
	public void preenchimentoCampos(Usuario u) throws NegocioException {
		if (u.getNome() == null || u.getNome().trim().isEmpty() == true)
			throw new NegocioException("Nome precisa ser preenchido!");
		if (u.getCpf() == null || u.getCpf().trim().isEmpty() == true)
			throw new NegocioException("CPF precisa ser preenchido!");
		if (u.getTelefone() == null || u.getTelefone().trim().isEmpty() == true)
			throw new NegocioException("Telefone precisa ser preenchido!");
		if (u.getLogin() == null || u.getLogin().trim().isEmpty() == true)
			throw new NegocioException("Login precisa ser preenchido!");
		if (u.getSenha() == null || u.getSenha().trim().isEmpty() == true)
			throw new NegocioException("Senha precisa ser preenchido!");
		if (u.getTipo() == null || u.getTipo().trim().isEmpty() == true)
			throw new NegocioException("Tipo do usu�rio precisa ser preenchido!");
		if (u.getSexo() == null || u.getSexo().trim().isEmpty() == true)
			throw new NegocioException("Sexo precisa ser preenchido!");
	}

	public void validarCampos(Usuario u) throws NegocioException {
		if (u.getNome().length() < 10 || u.getNome().length() > 50)
			throw new NegocioException("Nome inv�lido!");
		if (u.getCpf().length() != 11)
			throw new NegocioException("Cpf inv�lido!");
		if (u.getTelefone().length() < 10 || u.getTelefone().length() > 11)
			throw new NegocioException("Telefone inv�lido!");
		if (u.getLogin().length() < 4)
			throw new NegocioException("Login com limite m�nimo de 4 caracteres!");
		if (u.getLogin().length() > 15)
			throw new NegocioException("Login com limite m�ximo de 15 caracteres!");
		if (u.getSenha().length() < 4)
			throw new NegocioException("Senha com limite m�nimo de 4 caracteres!");
		if (u.getSenha().length() > 15)
			throw new NegocioException("Senha com limite m�ximo de 15 caracteres!");
		if (u.getSexo().length() > 1)
			throw new NegocioException("Sexo deve ser preenchido somente com M = Masculino ou F = Feminino!");
	}

	public void validarDuplicidadeLogin(Usuario u) throws NegocioException, DadosException {

		try {
			if (usuarioDAO.PesquisarPorLogin(u.getLogin()).size() > 0) {
				throw new NegocioException("Login j� existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Erro ao pesquisar exist�ncia do login!");
		}
	}

	public void validarEmail(Usuario u) throws NegocioException {

		/*Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(u.getEmail());
		if (m.find()) {

			throw new NegocioException("O E-mail " + u.getEmail() + " e v�lido");

		} else {
			throw new NegocioException("O E-mail " + u.getEmail() + " e Inv�lido");

		}*/
	}

	public void validarDuplicidadeEmail(Usuario u) throws NegocioException, DadosException {

		try {
			if (usuarioDAO.PesquisarPorEmail(u.getEmail()).size() > 0) {
				throw new NegocioException("Email j� existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Erro ao pesquisar exist�ncia do e-mail!");
		}

	}

	public void validaExistencia(Usuario u) throws NegocioException, DadosException {

		try {
			if (usuarioDAO.PesquisarPorLogin(u.getLogin()).size() <= 0) {
				throw new NegocioException("Usuario que deseja excluir n�o existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Erro ao pesquisar exist�ncia do usu�rio!");
		}
	}
	
	public void validaPreenchimentoUsuarioSenha(Usuario u) throws NegocioException, DadosException {
		if ((u.getLogin().isEmpty()) == true || u.getLogin() == null)
			throw new NegocioException("Login precisa ser preenchido!");
		if (u.getSenha().isEmpty() == true || u.getSenha() == null)
			throw new NegocioException("Senha precisa ser preenchida!");
	}

}
