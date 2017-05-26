package Negocio;

import Basica.Categoria;
import Dados.CategoriaDAO;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;

public class RNCategoria {
	
	CategoriaDAO categoriaDAO;

	public RNCategoria() {

		categoriaDAO = new CategoriaDAO();
	}

	/*
	 * ################################## 
	 * 			FUNCIONALIDADES
	 * ##################################
	 */

	public void salvar(Categoria c) throws NegocioException, DadosException {
		validarCampos(c);
		validarDuplicidadeCategoria(c);
		categoriaDAO.insert(c);

	}

	public void alterar(Categoria c) throws ControladorException, NegocioException, DadosException {
		validarCampos(c);
		validarDuplicidadeCategoria(c);
		categoriaDAO.update(c);
	}

	public void excluir(Categoria c) throws ControladorException, NegocioException, DadosException {
		validaExistencia(c);
		categoriaDAO.remove(c);
	}
	
	
	/*
	 * ################################## 
	 * 				VALIDA��ES
	 * ##################################
	 */
	public void verificarObjeto(Categoria c) throws NegocioException {

		if (c == null)
			throw new NegocioException("Objeto Categoria n�o preenchido");
	}

	public void validarCampos(Categoria c) throws NegocioException {
		if ((c.getNome().isEmpty() == true) || (c.getNome()).length() < 2)
			throw new NegocioException("Nome Inv�lido");
		
	}

	public void validarDuplicidadeCategoria(Categoria c) throws NegocioException, DadosException {

		try {
			if (categoriaDAO.PesquisarPorNome(c.getNome()) != null) {
				throw new NegocioException("Categoria j� existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados n�o disponivel");
		}
	}


	public void validaExistencia(Categoria c) throws NegocioException, DadosException {

		try {
			if (categoriaDAO.PesquisarPorNome(c.getNome()) == null) {
				throw new NegocioException("Categoria que deseja excluir n�o existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados n�o disponivel");
		}
	}
	

}
