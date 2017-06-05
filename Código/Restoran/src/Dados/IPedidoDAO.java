package Dados;

import java.util.Date;
import java.util.List;

import Basica.Pedido;
import Utils.DadosException;

public interface IPedidoDAO {
	
	public List<Pedido> PesquisarPorStatus(String status) throws DadosException;
	public List<Pedido> PesquisarPorData(Date data) throws DadosException;
	public List<Pedido> PesquisarPorGarcom(int codigoGarcom) throws DadosException;
	public List<Pedido> PesquisarPorMesa(int numeroMesa) throws DadosException;
	public List<Pedido> PesquisarPorProduto(int codigoProduto) throws DadosException;
	public List<Pedido> ListaPedidos() throws DadosException;

}
