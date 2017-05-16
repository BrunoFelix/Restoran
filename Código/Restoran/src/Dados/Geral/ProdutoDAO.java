package Dados;

import javax.persistence.EntityManagerFactory;

import Basica.Produto;

public class ProdutoDAO extends Dados.Geral.DAOGenerico<Produto> {

	public ProdutoDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
