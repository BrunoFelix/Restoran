package dados;


import javax.persistence.EntityManagerFactory;

import dados.geral.DAOGenerico;

import basicas.Historico;

public class HistoricoDAO extends DAOGenerico<Historico> {

	public HistoricoDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
