package Dados;

import java.util.List;

import Basica.ItemComposicaoProduto;
import Utils.DadosException;

public interface IItemComposicaoProdutoDAO {
	
	public List<ItemComposicaoProduto> PesquisarPorNome(String nome) throws DadosException;
	public List<ItemComposicaoProduto> PesquisarPorQuantidade(int quantidade) throws DadosException;
	
}
