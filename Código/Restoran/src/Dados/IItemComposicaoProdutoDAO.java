package Dados;

import java.util.List;

import Basica.ItemComposicaoProduto;
import Utils.DadosException;

public interface IItemComposicaoProdutoDAO {
	
	public List<ItemComposicaoProduto> PesquisarItemComposicaoProdutoObjeto(ItemComposicaoProduto icp) throws DadosException;
	
}
