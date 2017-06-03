package Dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import Basica.Produto;
import Utils.DadosException;

public class ProdutoDAO extends Dados.Geral.DAOGenerico<Produto> implements IProdutoDAO {

	public ProdutoDAO(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public List<Produto> PesquisarProdutoObjeto(Produto p) throws DadosException {
		try{
			String queryString = "select object(p) from Produto as p where p.id > 0";
			
			if (p.getNome() != null && p.getNome().trim().equals("") == false)
			{
				queryString += " and p.nome =(:nome)";
			}
			
			if (p.getQuantidade() > 0)
			{
				queryString += " and p.quantidade =(:quantidade)";
			}
			
			if (p.getPrecoVenda() > 0)
			{
				queryString += " and p.precoVenda =(:precoVenda)";
			}
			
			if (p.getPrecoCusto() > 0)
			{
				queryString += " and p.precoCusto =(:precoCusto)";
			}
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			
			if (p.getNome() != null && p.getNome().trim().equals("") == false)
			{
				query.setParameter("nome", p.getNome());
			}
			
			if (p.getQuantidade() > 0)
			{
				query.setParameter("quantidade", p.getQuantidade());
			}
			
			if (p.getPrecoVenda() > 0)
			{
				query.setParameter("precoVenda", p.getPrecoVenda());
			}
			
			if (p.getPrecoCusto() > 0)
			{
				query.setParameter("precoCusto", p.getPrecoCusto());
			}
	
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
	


}
