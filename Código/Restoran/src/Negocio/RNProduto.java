package Negocio;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import Basica.Produto;
import Basica.Usuario;
import Dados.ProdutoDAO;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;


public class RNProduto {
	
	ProdutoDAO produtoDAO;

	public RNProduto(EntityManagerFactory emf) {

		produtoDAO = new ProdutoDAO(emf);
	}

	/*
	 * ################################## 
	 * 			FUNCIONALIDADES
	 * ##################################
	 */

	public void salvar(Produto p) throws NegocioException, DadosException {
		//validarCampos(p);
		produtoDAO.insert(p);

	}

	public void alterar(Produto p ) throws ControladorException, NegocioException, DadosException {
		validarCampos(p);
		produtoDAO.update(p);
	}

	public void excluir(Produto p ) throws ControladorException, NegocioException, DadosException {
		validaExistencia(p);
		produtoDAO.remove(p);
	}
	public List<Produto> listar(){
	 return produtoDAO.getAll();
   }
	public List<Produto> PesquisarPorNome(String nome) throws DadosException{
		
		return produtoDAO.PesquisarPorNome(nome);
	}
	public List<Produto> PesquisarPorQuantidade(int quantidade) throws DadosException{
		return produtoDAO.PesquisarPorQuantidade(quantidade);
	}
	public Produto ProdutoBuscarPorId(Integer id){
		return produtoDAO.searchByKey(id);
	}
		   
	/*
	 * ################################## 
	 * 				VALIDACOES
	 * ##################################
	 */
	public void verificarObjeto(Produto p ) throws NegocioException {

		if (p == null)
			throw new NegocioException("Objeto Produto não preenchido");
	}

	public void validarCampos(Produto p ) throws NegocioException {
		if (p.getNome() == null)
			throw new NegocioException("Nome inválido!");
		if (p.getNome().trim().isEmpty() == true || p.getNome().trim().length() < 2 || p.getNome().trim().length() > 50)
			throw new NegocioException("Nome inválido!");
		if(p.getPrecoVenda() <= 0)
			throw new NegocioException("Preço de venda inválido!");
		if(p.getPrecoCusto() <= 0)
			throw new NegocioException("Preço de custo inválido");
		if(p.getQuantidade() <= 0)
			throw new NegocioException("Quantidade inválida!");
			
	}

	public void validarDuplicidade(Produto p ) throws NegocioException, DadosException {

		try {
			if (produtoDAO.PesquisarPorNome(p.getNome()) != null) {
				throw new NegocioException("Produto já existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados não disponivel");
		}
	}
	public void validaExistencia(Produto p ) throws NegocioException, DadosException {

		try {
			if (produtoDAO.PesquisarPorNome(p.getNome()) == null) {
				throw new NegocioException("Produto que deseja excluir não existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados não disponivel");
		}
	}

}
