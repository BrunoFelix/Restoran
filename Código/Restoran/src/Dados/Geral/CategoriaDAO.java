package Dados.Geral;


import javax.persistence.EntityManagerFactory;

import Dados.Geral.DAOGenerico;

import Basica.Categoria;

public class CategoriaDAO extends DAOGenerico<Categoria> {

	public CategoriaDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
