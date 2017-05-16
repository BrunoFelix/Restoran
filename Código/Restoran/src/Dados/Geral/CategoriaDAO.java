package dados;


import javax.persistence.EntityManagerFactory;

import dados.geral.DAOGenerico;

import basicas.Categoria;

public class CategoriaDAO extends DAOGenerico<Categoria> {

	public CategoriaDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
