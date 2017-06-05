package Dados;

import java.util.Date;
import java.util.List;

import Basica.Categoria;
import Basica.Pedido;
import Basica.Produto;
import Basica.ProdutoItem;
import Utils.DadosException;

public interface IProdutoDAO {
	
	public List<Produto> PesquisarProdutoObjeto(Produto p) throws DadosException;
	public void InserirVinculoProdutoItemComp(List<ProdutoItem> pi) throws DadosException;
	public void AlterarVinculoProdutoItemComp(List<ProdutoItem> pi) throws DadosException;
}
