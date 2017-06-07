package Dados;

import java.util.Date;
import java.util.List;

import Basica.Pedido;
import Basica.PedidoProduto;
import Basica.ProdutoItem;
import Utils.DadosException;

public interface IPedidoDAO {
	
	public List<Pedido> PesquisarPorStatus(String status) throws DadosException;
	public List<Pedido> PesquisarPorData(Date data) throws DadosException;
	public List<Pedido> PesquisarPorGarcom(int codigoGarcom) throws DadosException;
	public Pedido PesquisarPorMesa(long numeroMesa) throws DadosException;
	public List<Pedido> PesquisarPorProduto(int codigoProduto) throws DadosException;
	public List<Pedido> ListaPedidos() throws DadosException;
	public void InserirVinculoProduto(List<PedidoProduto> pp) throws DadosException;
	public void AlterarVinculoProduto(List<PedidoProduto> pp) throws DadosException;

}
