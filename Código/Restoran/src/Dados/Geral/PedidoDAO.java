package Dados.Geral;


import javax.persistence.EntityManagerFactory;

import Dados.Geral.DAOGenerico;

import Basica.Pedido;

public class PedidoDAO extends DAOGenerico<Pedido> {

	public PedidoDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
