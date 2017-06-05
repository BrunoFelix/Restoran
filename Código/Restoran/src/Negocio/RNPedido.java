package Negocio;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import Basica.Pedido;
import Dados.PedidoDAO;
import Utils.DadosException;
import Utils.NegocioException;

public class RNPedido {
	
	PedidoDAO pedidoDAO;
	
	public RNPedido(EntityManagerFactory emf) {

		pedidoDAO = new PedidoDAO(emf);
	}
	
	public List<Pedido> PesquisarPorMesa(Integer numeroMesa) throws DadosException{
		return pedidoDAO.PesquisarPorMesa(numeroMesa);
	}
	
	public List<Pedido> PedidoListar() throws DadosException{
		return pedidoDAO.getAll();
	}
	
	/*
	if (m.getStatus() == null)
		throw new NegocioException("Status da mesa Invalido");
	if (m.getStatus().trim().isEmpty() == true)
		throw new NegocioException("Status da mesa Invalido");
	if (m.getStatus().length() > 10 || m.getStatus().length() < 4)
		throw new NegocioException("Status da mesa Invalido");*/
}
