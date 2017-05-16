package dados;


import javax.persistence.EntityManagerFactory;

import dados.geral.DAOGenerico;

import basicas.ItemComposicaoProduto;

public class ItemComposicaoProdutoDAO extends DAOGenerico<ItemComposicaoProduto> {

	public ItemComposicaoProdutoDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
