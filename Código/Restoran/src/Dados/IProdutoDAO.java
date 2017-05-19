package Dados;

import java.util.Date;
import java.util.List;

import Basica.Pedido;
import Basica.Produto;
import Utils.DadosException;

public interface IProdutoDAO {
	
	public List<Produto> PesquisarPorNome(String nome) throws DadosException;
	public List<Produto> PesquisarPorQuantidade(int quantidade) throws DadosException;

}
