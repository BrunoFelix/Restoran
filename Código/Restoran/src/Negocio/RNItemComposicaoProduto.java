package Negocio;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import Basica.ItemComposicaoProduto;
import Dados.ItemComposicaoProdutoDAO;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;

public class RNItemComposicaoProduto {

	ItemComposicaoProdutoDAO itemComposicaoProdutoDAO;

	public RNItemComposicaoProduto(EntityManagerFactory emf) {

		itemComposicaoProdutoDAO = new ItemComposicaoProdutoDAO(emf);
	}

	/*
	 * ################################## 
	 * 			FUNCIONALIDADES
	 * ##################################
	 */


	public void salvar(ItemComposicaoProduto i) throws NegocioException, DadosException {
		validarCampos(i);
		itemComposicaoProdutoDAO.insert(i);

	}

	public void alterar(ItemComposicaoProduto i) throws ControladorException, NegocioException, DadosException {
		validarCampos(i);
		itemComposicaoProdutoDAO.update(i);
	}

	public void excluir(ItemComposicaoProduto i ) throws ControladorException, NegocioException, DadosException {
		validaExistencia(i);
		itemComposicaoProdutoDAO.remove(i);
	}
	public List<ItemComposicaoProduto> listar(){
		return itemComposicaoProdutoDAO.getAll();
   }
	   
	/*
	 * ################################## 
	 * 				VALIDACOES
	 * ##################################
	 */
	public void verificarObjeto(ItemComposicaoProduto i ) throws NegocioException {

		if (i == null)
			throw new NegocioException("Objeto Item de Composi��o do Produto n�o preenchido");
	}

	public void validarCampos(ItemComposicaoProduto i ) throws NegocioException {
		if ((i.getNome().isEmpty() == true) || (i.getNome()).length() < 5)
			throw new NegocioException("Nome Invalido");
		if ((i.getUnidadeMedida().isEmpty() == true))
			throw new NegocioException("Unidade de medida Inv�lida");
		if ((i.getPrecoCusto() == 0))
			throw new NegocioException("Insira o Pre�o de Custo");
	}

	public void validarDuplicidade(ItemComposicaoProduto i ) throws NegocioException, DadosException {

		try {
			if (itemComposicaoProdutoDAO.PesquisarPorNome(i.getNome()) != null) {
				throw new NegocioException("Item de Composi��o do Produto j� existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados n�o disponivel");
		}
	}
	public void validaExistencia(ItemComposicaoProduto i ) throws NegocioException, DadosException {

		try {
			if (itemComposicaoProdutoDAO.PesquisarPorNome(i.getNome()) == null) {
				throw new NegocioException("Item de Composi��o do Produto que deseja excluir n�o existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados n�o disponivel");
		}
	}

}
