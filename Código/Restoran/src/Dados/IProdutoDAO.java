package Dados;

import java.util.Date;
import java.util.List;

import Basica.Categoria;
import Basica.Pedido;
import Basica.Produto;
import Utils.DadosException;

public interface IProdutoDAO {
	
	public List<Produto> PesquisarProdutoObjeto(Produto p) throws DadosException;

}
