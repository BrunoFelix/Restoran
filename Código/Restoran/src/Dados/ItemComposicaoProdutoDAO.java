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

	@Override
	public List<ItemComposicaoProduto> PesquisarItemComposicaoProdutoObjeto(ItemComposicaoProduto icp) throws DadosException{
		try{
			String queryString = "select object(icp) from ItemComposicaoProduto as icp where icp.id > 0";
			
			if (icp.getNome() != null && icp.getNome().trim().equals("") == false)
			{
				queryString += " and icp.nome =(:nome)";
			}
			
			if (icp.getQuantidade() > 0)
			{
				queryString += " and icp.quantidade =(:quantidade)";
			}
			
			if (icp.getPrecoCusto() > 0)
			{
				queryString += " and icp.precoCusto =(:precoCusto)";
			}
			
			if (icp.getUnidadeMedida() != null && icp.getUnidadeMedida().trim().equals("") == false)
			{
				queryString += " and icp.unidadeMedida =(:unidadeMedida)";
			}
		
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			
			if (icp.getNome() != null && icp.getNome().trim().equals("") == false)
			{
				query.setParameter("nome", icp.getNome());
			}
			
			if (icp.getQuantidade() > 0)
			{
				query.setParameter("quantidade", icp.getQuantidade());
			}
			
			if (icp.getPrecoCusto() > 0)
			{
				query.setParameter("precoCusto", icp.getPrecoCusto());
			}
			
			if (icp.getUnidadeMedida() != null && icp.getUnidadeMedida().trim().equals("") == false)
			{
				query.setParameter("unidadeMedida", icp.getUnidadeMedida());
			}
	
		    return query.getResultList();
		    
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}

	
	
}
