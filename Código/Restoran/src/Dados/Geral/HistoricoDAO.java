package Dados.Geral;


import javax.persistence.EntityManagerFactory;

import Dados.Geral.DAOGenerico;

import Basica.Historico;

public class HistoricoDAO extends DAOGenerico<Historico> {

	public HistoricoDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
