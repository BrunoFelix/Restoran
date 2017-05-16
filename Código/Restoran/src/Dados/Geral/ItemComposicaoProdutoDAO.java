package Dados.Geral;


import javax.persistence.EntityManagerFactory;

import Dados.Geral.DAOGenerico;

import Basica.ItemComposicaoProduto;

public class ItemComposicaoProdutoDAO extends DAOGenerico<ItemComposicaoProduto> {

	public ItemComposicaoProdutoDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
