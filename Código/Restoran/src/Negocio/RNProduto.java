package Negocio;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Basica.Produto;
import Dados.ProdutoDAO;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;


public class RNProduto {
	
	ProdutoDAO produtoDAO;

	public RNProduto() {

		produtoDAO = new ProdutoDAO();
	}

	/*
	 * ################################## 
	 * 			FUNCIONALIDADES
	 * ##################################
	 */

	public void salvar(Produto p) throws NegocioException, DadosException {
		validarCampos(p);
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
		if ((p.getNome().isEmpty() == true) || (p.getNome())== "")
			throw new NegocioException("Nome Invalido");
		if(p.getPrecoVenda() == 0)
			throw new NegocioException("Preço de venda Invalido");
		if(p.getPrecoCusto() == 0)
			throw new NegocioException("Preço de custo Invalido");
		if((p.getItensComposicao().isEmpty()) || p.getItensComposicao() == null)
			throw new NegocioException("Quantidade Invalida");
			
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
