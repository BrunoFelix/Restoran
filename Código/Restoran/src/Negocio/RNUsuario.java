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
			throw new NegocioException("Objeto Usuario não preenchido");
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
			throw new NegocioException("Tipo do usuário precisa ser preenchido!");
		if (u.getSexo() == null || u.getSexo().trim().isEmpty() == true)
			throw new NegocioException("Sexo precisa ser preenchido!");
	}

	public void validarCampos(Usuario u) throws NegocioException {
		if (u.getNome().trim().length() < 8 || u.getNome().trim().length() > 50)
			throw new NegocioException("Nome inválido!");
		if (u.getCpf().trim().length() != 11)
			throw new NegocioException("Cpf inválido!");
		if (u.getTelefone().trim().length() < 10 || u.getTelefone().trim().length() > 11)
			throw new NegocioException("Telefone inválido!");
		if (u.getLogin().trim().length() < 4)
			throw new NegocioException("Login com limite mínimo de 4 caracteres!");
		if (u.getLogin().trim().length() > 15)
			throw new NegocioException("Login com limite máximo de 15 caracteres!");
		if (u.getSenha().trim().length() < 4)
			throw new NegocioException("Senha com limite mínimo de 4 caracteres!");
		if (u.getSenha().trim().length() > 15)
			throw new NegocioException("Senha com limite máximo de 15 caracteres!");
		if (u.getSexo().trim().length() > 1)
			throw new NegocioException("Sexo deve ser preenchido somente com M = Masculino ou F = Feminino!");
		if (!u.getTipo().equals("Gerente") && !u.getTipo().equals("Garçom") && !u.getTipo().equals("Cozinheiro"))
			throw new NegocioException("Tipo deve ser preenchido com Gerente ou Garçom ou Cozinheiro!");
	}

	public void validarDuplicidadeLogin(Usuario u) throws NegocioException, DadosException {

		try {
			List<Usuario> listalogin = usuarioDAO.PesquisarPorLogin(u.getLogin());
			if (listalogin.size() > 0) {
				throw new NegocioException("Login já existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Erro ao pesquisar existência do login!");
		}
	}

	public void validarEmail(Usuario u) throws NegocioException {

		/*Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(u.getEmail());
		if (m.find()) {

			throw new NegocioException("O E-mail " + u.getEmail() + " e válido");

		} else {
			throw new NegocioException("O E-mail " + u.getEmail() + " e Inválido");

		}*/
		
		if (u.getEmail() != null && u.getEmail().length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(u.getEmail());
            if (!matcher.matches()) {
            	throw new NegocioException("O E-mail " + u.getEmail() + " e inválido!");
            }
            
        }else{
        	throw new NegocioException("O E-mail " + u.getEmail() + " e inválido!");
        }
		
	}

	public void validarDuplicidadeEmail(Usuario u) throws NegocioException, DadosException {

		try {
			List<Usuario> listaemail = usuarioDAO.PesquisarPorEmail(u.getEmail());
			if (listaemail.size() > 0) {
				throw new NegocioException("Email já existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Erro ao pesquisar existência do e-mail!");
		}

	}

	public void validaExistencia(Usuario u) throws NegocioException, DadosException {

		try {
			List<Usuario> listaexitencia = usuarioDAO.PesquisarPorLogin(u.getLogin());
			if (listaexitencia.size() <= 0) {
				throw new NegocioException("Usuario que deseja excluir não existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Erro ao pesquisar existência do usuário!");
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
