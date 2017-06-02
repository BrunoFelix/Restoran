package Negocio;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import Basica.ItemComposicaoProduto;
import Basica.Usuario;
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
	
	public ItemComposicaoProduto ItemComposicaoProdutoBuscarPorId(Integer id){
		return itemComposicaoProdutoDAO.searchByKey(id);
	}
	   
	/*
	 * ################################## 
	 * 				VALIDACOES
	 * ##################################
	 */
	public void verificarObjeto(ItemComposicaoProduto i ) throws NegocioException {

		if (i == null)
			throw new NegocioException("Objeto Item de Composição do Produto não preenchido");
	}

	public void validarCampos(ItemComposicaoProduto i ) throws NegocioException {
		if (i.getNome() == null)
			throw new NegocioException("Nome inválido!");
		if (i.getNome().trim().isEmpty() == true || i.getNome().trim().length() < 2 || i.getNome().trim().length() > 50)
			throw new NegocioException("Nome inválido!");
		if (i.getQuantidade() <= 0)
			throw new NegocioException("Quantidade inválida!");
		if (i.getUnidadeMedida() == null)
			throw new NegocioException("Unidade de medida inválido!");
		if (i.getUnidadeMedida().trim().isEmpty() == true || i.getUnidadeMedida().trim().length() < 2 || i.getUnidadeMedida().trim().length() > 50)
			throw new NegocioException("Unidade de medida inválido!");
		if ((i.getPrecoCusto() <= 0))
			throw new NegocioException("Preço de custo inválido!");
	}

	public void validarDuplicidade(ItemComposicaoProduto i ) throws NegocioException, DadosException {

		try {
			if (itemComposicaoProdutoDAO.PesquisarPorNome(i.getNome()) != null) {
				throw new NegocioException("Item de Composição do Produto já existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados não disponivel");
		}
	}
	public void validaExistencia(ItemComposicaoProduto i ) throws NegocioException, DadosException {

		try {
			if (itemComposicaoProdutoDAO.PesquisarPorNome(i.getNome()) == null) {
				throw new NegocioException("Item de Composição do Produto que deseja excluir não existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados não disponivel");
		}
	}

}
