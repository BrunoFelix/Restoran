package dados;


import javax.persistence.EntityManagerFactory;

import dados.geral.DAOGenerico;

import basicas.Mesa;

public class MesaDAO extends DAOGenerico<Mesa> {

	public MesaDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
