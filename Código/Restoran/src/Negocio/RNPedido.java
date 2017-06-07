package Negocio;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import Basica.Pedido;
import Basica.PedidoProduto;
import Basica.ProdutoItem;
import Dados.PedidoDAO;
import Utils.DadosException;
import Utils.NegocioException;

public class RNPedido {
	
	PedidoDAO pedidoDAO;
	
	public RNPedido(EntityManagerFactory emf) {

		pedidoDAO = new PedidoDAO(emf);
	}
	
	public void salvar(Pedido p) throws NegocioException, DadosException {
		pedidoDAO.insert(p);
	}
	
	public void alterar(Pedido p) throws NegocioException, DadosException {
		pedidoDAO.update(p);
	}
	
	public Pedido PesquisarPorMesa(long numeroMesa) throws DadosException{
		return pedidoDAO.PesquisarPorMesa(numeroMesa);
	}
	
	public List<Pedido> PedidoListar() throws DadosException{
		return pedidoDAO.ListaPedidos();
	}
	
	public void InserirVinculoProduto(List<PedidoProduto> pp) throws DadosException{
		pedidoDAO.InserirVinculoProduto(pp);
	}
	
	public void AlterarVinculoProduto(List<PedidoProduto> pp) throws DadosException{
		pedidoDAO.AlterarVinculoProduto(pp);
	}
	
	
	/*
	if (m.getStatus() == null)
		throw new NegocioException("Status da mesa Invalido");
	if (m.getStatus().trim().isEmpty() == true)
		throw new NegocioException("Status da mesa Invalido");
	if (m.getStatus().length() > 10 || m.getStatus().length() < 4)
		throw new NegocioException("Status da mesa Invalido");*/
}
