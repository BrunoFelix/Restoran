package Negocio;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import Basica.Categoria;
import Basica.Usuario;
import Dados.CategoriaDAO;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;

public class RNCategoria {
	
	CategoriaDAO categoriaDAO;

	public RNCategoria(EntityManagerFactory emf) {

		categoriaDAO = new CategoriaDAO(emf);
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
	
	public List<Categoria> listar(){
		return categoriaDAO.getAll();
	}
	
	public Categoria CategoriaBuscarPorId(Integer id){
		return categoriaDAO.searchByKey(id);
	}
	
	public List<Categoria> PesquisarCategoriaObjeto(Categoria c) throws DadosException{
		return categoriaDAO.PesquisarCategoriaObjeto(c);
	}
	
	
	/*
	 * ################################## 
	 * 				VALIDAÇÕES
	 * ##################################
	 */
	public void verificarObjeto(Categoria c) throws NegocioException {
		if (c == null)
			throw new NegocioException("Objeto Categoria não preenchido");
	}

	public void validarCampos(Categoria c) throws NegocioException {
		if (c.getNome() == null)
			throw new NegocioException("Nome Inválido");
		if (c.getNome().isEmpty() == true || c.getNome().length() < 2 || c.getNome().length() > 50)
			throw new NegocioException("Nome Inválido");
		
	}

	public void validarDuplicidadeCategoria(Categoria c) throws NegocioException, DadosException {

		try {
			List<Categoria> listaduplicidade = categoriaDAO.PesquisarCategoriaObjeto(c);
			if (listaduplicidade.size() > 0) {
				throw new NegocioException("Categoria já existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados não disponivel");
		}
	}


	public void validaExistencia(Categoria c) throws NegocioException, DadosException {

		try {
			List<Categoria> listaexistencia = categoriaDAO.PesquisarCategoriaObjeto(c);
			if (listaexistencia.size() <= 0) {
				throw new NegocioException("Categoria que deseja excluir não existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados não disponivel");
		}
	}
	

}
