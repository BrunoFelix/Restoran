package Negocio;

import javax.persistence.EntityManagerFactory;

import Dados.MesaDAO;
import Dados.PedidoDAO;

public class RNPedido {
	
	PedidoDAO pedidoDAO;
	
	public RNPedido(EntityManagerFactory emf) {

		pedidoDAO = new PedidoDAO(emf);
	}
}
