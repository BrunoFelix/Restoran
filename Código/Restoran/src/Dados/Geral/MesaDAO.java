package Dados.Geral;


import javax.persistence.EntityManagerFactory;

import Dados.Geral.DAOGenerico;

import Basica.Mesa;

public class MesaDAO extends DAOGenerico<Mesa> {

	public MesaDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
