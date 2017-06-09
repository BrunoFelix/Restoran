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

public class RNUsuario{

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
	
	public Usuario UsuarioBuscarPorId(Integer id){
		return usuarioDAO.searchByKey(id);
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
		if (u.getNome().trim().length() < 8 || u.getNome().trim().length() > 50)
			throw new NegocioException("Nome inv�lido!");
		if (u.getCpf().trim().length() != 11)
			throw new NegocioException("Cpf inv�lido!");
		if (u.getTelefone().trim().length() < 10 || u.getTelefone().trim().length() > 11)
			throw new NegocioException("Telefone inv�lido!");
		if (u.getLogin().trim().length() < 4)
			throw new NegocioException("Login com limite m�nimo de 4 caracteres!");
		if (u.getLogin().trim().length() > 15)
			throw new NegocioException("Login com limite m�ximo de 15 caracteres!");
		if (u.getSenha().trim().length() < 4)
			throw new NegocioException("Senha com limite m�nimo de 4 caracteres!");
		if (u.getSenha().trim().length() > 15)
			throw new NegocioException("Senha com limite m�ximo de 15 caracteres!");
		if (u.getSexo().trim().length() > 1)
			throw new NegocioException("Sexo deve ser preenchido somente com M = Masculino ou F = Feminino!");
		if (!u.getTipo().equals("Gerente") && !u.getTipo().equals("Gar�om") && !u.getTipo().equals("Cozinheiro"))
			throw new NegocioException("Tipo deve ser preenchido com Gerente ou Gar�om ou Cozinheiro!");
	}

	public void validarDuplicidadeLogin(Usuario u) throws NegocioException, DadosException {

		try {
			List<Usuario> listalogin = usuarioDAO.PesquisarPorLogin(u.getLogin());
			if (listalogin.size() > 0) {
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
		
		if (u.getEmail() != null && u.getEmail().length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(u.getEmail());
            if (!matcher.matches()) {
            	throw new NegocioException("O E-mail " + u.getEmail() + " e inv�lido!");
            }
            
        }else{
        	throw new NegocioException("O E-mail " + u.getEmail() + " e inv�lido!");
        }
		
	}

	public void validarDuplicidadeEmail(Usuario u) throws NegocioException, DadosException {

		try {
			List<Usuario> listaemail = usuarioDAO.PesquisarPorEmail(u.getEmail());
			if (listaemail.size() > 0) {
				throw new NegocioException("Email j� existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Erro ao pesquisar exist�ncia do e-mail!");
		}

	}

	public void validaExistencia(Usuario u) throws NegocioException, DadosException {

		try {
			List<Usuario> listaexitencia = usuarioDAO.PesquisarPorLogin(u.getLogin());
			if (listaexitencia.size() <= 0) {
				throw new NegocioException("Usuario que deseja excluir n�o existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Erro ao pesquisar exist�ncia do usu�rio!");
		}
	}
	
	public void validaPreenchimentoUsuarioSenha(Usuario u) throws NegocioException {
		if (u.getLogin() == null)
			throw new NegocioException("Login precisa ser preenchido!");
		if (u.getLogin().trim().isEmpty() == true)
			throw new NegocioException("Login precisa ser preenchido!");
		if (u.getSenha() == null)
			throw new NegocioException("Senha precisa ser preenchida!");
		if (u.getSenha().trim().isEmpty() == true)
			throw new NegocioException("Senha precisa ser preenchida!");
	}

}
