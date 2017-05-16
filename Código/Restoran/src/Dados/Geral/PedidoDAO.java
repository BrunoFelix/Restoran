package dados;


import javax.persistence.EntityManagerFactory;

import dados.geral.DAOGenerico;

import basicas.Pedido;

public class PedidoDAO extends DAOGenerico<Pedido> {

	public PedidoDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
