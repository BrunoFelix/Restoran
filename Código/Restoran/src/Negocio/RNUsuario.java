package Negocio;

import java.util.regex.*;

import Basica.Usuario;
import Dados.UsuarioDAO;
import Utils.NegocioException;
import Utils.DadosException;

public class RNUsuario {

	UsuarioDAO usuarioDAO;

	public RNUsuario() {

		usuarioDAO = new UsuarioDAO(null);
	}

    public void salvar(Usuario u) throws NegocioException, DadosException{
        
    	validarCampos(u); 
    	validarEmail(u);
    	validarDuplicidadeEmail(u);
    	validarDuplicidadeLogin(u);
               
        
    }

	
	
	
	/*
	 * Validações Usuario
	 */

	public void verificarObjeto(Usuario u) throws NegocioException {

		if (u == null)
			throw new NegocioException("Objeto Usuario não preenchido");
	}

	public void validarCampos(Usuario u) throws NegocioException {
		if ((u.getNome().isEmpty() == true) || (u.getNome()).length() < 5)
			throw new NegocioException("Nome Inválido");
		if ((u.getCpf().isEmpty() == true) || (u.getCpf().length() < 10))
			throw new NegocioException("Cpf Inválido");
		if ((u.getTelefone().isEmpty() == true) || (u.getTelefone().length() < 8))
			throw new NegocioException("Telefone Inválido");
		if ((u.getLogin().isEmpty()) == true || u.getLogin() == null)
			throw new NegocioException("Login inválido");
		if (u.getSenha().isEmpty() == true || u.getSenha() == null)
			throw new NegocioException("senha inválida");
		if (u.getSenha().length() <= 4)
			throw new NegocioException("senha inválida deve possuir no minimo quatro caracteres.");
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
			throw new DadosException("Banco de dados não disponível");
		}
	}

	public void validarEmail(Usuario u) throws NegocioException {
		System.out.println("Metodo de validacao de email");
		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(u.getEmail());
		if (m.find()) {

			throw new NegocioException("O E-mail " + u.getEmail() + " e valido");

		} else {
			throw new NegocioException("O E-mail " + u.getEmail() + " é inválido");

		}
	}

	public void validarDuplicidadeEmail(Usuario u) throws NegocioException, DadosException {

		try {
			if (usuarioDAO.PesquisarPorEmail(u.getEmail()) != null) {
				throw new NegocioException("Email já existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados não disponível");
		}

	}
}
