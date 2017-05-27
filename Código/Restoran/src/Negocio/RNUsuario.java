package Negocio;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManagerFactory;

import Basica.Usuario;
import Dados.UsuarioDAO;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;

public class RNUsuario {

	UsuarioDAO usuarioDAO;

	public RNUsuario(EntityManagerFactory emf) {

		usuarioDAO = new UsuarioDAO(emf);
	}

	/*
	 * ################################## 
	 * 			FUNCIONALIDADES
	 * ##################################
	 */

	public void salvar(Usuario u) throws NegocioException, DadosException {
		validarCampos(u);
		validarEmail(u);
		validarDuplicidadeEmail(u);
		validarDuplicidadeLogin(u);
		usuarioDAO.insert(u);

	}

	public void alterar(Usuario u) throws ControladorException, NegocioException, DadosException {
		validarCampos(u);
		validarEmail(u);
		validarDuplicidadeEmail(u);
		validarDuplicidadeLogin(u);
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
			throw new NegocioException("Objeto Usuario não preenchido");
	}

	public void validarCampos(Usuario u) throws NegocioException {
		if ((u.getNome().isEmpty() == true) || (u.getNome()).length() < 10)
			throw new NegocioException("Nome Invalido");
		if ((u.getCpf().isEmpty() == true) || (u.getCpf().length() < 10))
			throw new NegocioException("Cpf Invalido");
		if ((u.getTelefone().isEmpty() == true) || (u.getTelefone().length() < 8))
			throw new NegocioException("Telefone Invalido");
		if ((u.getLogin().isEmpty()) == true || u.getLogin() == null)
			throw new NegocioException("Login Invalido");
		if (u.getSenha().isEmpty() == true || u.getSenha() == null)
			throw new NegocioException("senha invÃ¡lida");
		if (u.getSenha().length() <= 4)
			throw new NegocioException("senha invalida deve possuir no minimo quatro caracteres.");
		if (u.getTipo().isEmpty() || u.getTipo() == null)
			throw new NegocioException("Tipo de Usuario invalido");
		if (u.getSexo().isEmpty() || u.getSexo() == null)
			throw new NegocioException("Sexo invalido");
	}

	public void validarDuplicidadeLogin(Usuario u) throws NegocioException, DadosException {

		try {
			if (usuarioDAO.PesquisarPorLogin(u.getLogin()) != null) {
				throw new NegocioException("Login já existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados não disponivel");
		}
	}

	public void validarEmail(Usuario u) throws NegocioException {

		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(u.getEmail());
		if (m.find()) {

			throw new NegocioException("O E-mail " + u.getEmail() + " e valido");

		} else {
			throw new NegocioException("O E-mail " + u.getEmail() + " e Invalido");

		}
	}

	public void validarDuplicidadeEmail(Usuario u) throws NegocioException, DadosException {

		try {
			if (usuarioDAO.PesquisarPorEmail(u.getEmail()) != null) {
				throw new NegocioException("Email já existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados não disponivel");
		}

	}

	public void validaExistencia(Usuario u) throws NegocioException, DadosException {

		try {
			if (usuarioDAO.PesquisarPorLogin(u.getLogin()) == null) {
				throw new NegocioException("Usuario que deseja excluir não existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados não disponivel");
		}
	}
	
	public void validaPreenchimentoUsuarioSenha(Usuario u) throws NegocioException, DadosException {
		if ((u.getLogin().isEmpty()) == true || u.getLogin() == null)
			throw new NegocioException("Login precisa ser preenchido!");
		if (u.getSenha().isEmpty() == true || u.getSenha() == null)
			throw new NegocioException("Senha precisa ser preenchida!");
	}

}
