package Dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import Basica.ItemComposicaoProduto;
import Dados.Geral.DAOGenerico;
import Utils.DadosException;

public class ItemComposicaoProdutoDAO extends DAOGenerico<ItemComposicaoProduto> implements IItemComposicaoProdutoDAO {

	public ItemComposicaoProdutoDAO(EntityManagerFactory emf) {
		super(emf);
	}

	public List<ItemComposicaoProduto> PesquisarPorNome(String nome) throws DadosException{
		try{
			String queryString = "SELECT ICP FROM ITEMCOMPOSICAOPRODUTO ICP WHERE ICP.nome =(:nome)";
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("nome", nome);
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
	
	public List<ItemComposicaoProduto> PesquisarPorQuantidade(int quantidade) throws DadosException{
		try{
			String queryString = "SELECT ICP FROM ITEMCOMPOSICAOPRODUTO ICP WHERE ICP.quantidade =(:quantidade)";
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("quantidade", quantidade);
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
	
	
}
