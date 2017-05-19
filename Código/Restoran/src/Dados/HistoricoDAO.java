package Dados;


import javax.persistence.EntityManagerFactory;

import Dados.Geral.DAOGenerico;

import Basica.Historico;

public class HistoricoDAO extends DAOGenerico<Historico> implements IHistoricoDAO {

	public HistoricoDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
