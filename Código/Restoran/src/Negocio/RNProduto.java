package Negocio;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import Basica.Produto;
import Basica.ProdutoItem;
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
		validarCampos(p);
		validarDuplicidade(p);
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
	public Produto ProdutoBuscarPorId(Long id){
		return produtoDAO.searchByKey(id);
	}
	public List<Produto> PesquisarProdutoObjeto(Produto p) throws DadosException{
		return produtoDAO.PesquisarProdutoObjeto(p);
	}
	
	public void InserirVinculoProdutoItemComp(List<ProdutoItem> pi) throws DadosException{
		produtoDAO.InserirVinculoProdutoItemComp(pi);
	}
	
	public void AlterarVinculoProdutoItemComp(List<ProdutoItem> pi) throws DadosException{
		produtoDAO.AlterarVinculoProdutoItemComp(pi);
	}
		   
	/*
	 * ################################## 
	 * 				VALIDACOES
	 * ##################################
	 */
	public void verificarObjeto(Produto p ) throws NegocioException {

		if (p == null)
			throw new NegocioException("Objeto Produto n�o preenchido");
	}

	public void validarCampos(Produto p ) throws NegocioException {
		if (p.getNome() == null)
			throw new NegocioException("Nome inv�lido!");
		if (p.getNome().trim().isEmpty() == true || p.getNome().trim().length() < 2 || p.getNome().trim().length() > 50)
			throw new NegocioException("Nome inv�lido!");
		if(p.getQuantidade() <= 0)
			throw new NegocioException("Quantidade inv�lida!");
		if(p.getPrecoVenda() <= 0)
			throw new NegocioException("Pre�o de venda inv�lido!");
		if(p.getPrecoCusto() <= 0)
			throw new NegocioException("Pre�o de custo inv�lido");
		if(p.getCategoria() == null)
			throw new NegocioException("Categoria inv�lida!");
			
	}

	public void validarDuplicidade(Produto p ) throws NegocioException, DadosException {

		try {
			Produto produto = new Produto();
			produto.setNome(p.getNome());
			List<Produto> listaduplicidade = produtoDAO.PesquisarProdutoObjeto(produto);
			if (listaduplicidade.size() > 0) {
				throw new NegocioException("Produto j� existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados n�o disponivel");
		}
	}
	public void validaExistencia(Produto p ) throws NegocioException, DadosException {

		try {
			Produto produto = new Produto();
			produto.setNome(p.getNome());
			List<Produto> listaduplicidade = produtoDAO.PesquisarProdutoObjeto(produto);
			if (listaduplicidade.size() <= 0) {
				throw new NegocioException("Produto que deseja excluir n�o existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados n�o disponivel");
		}
	}

}
