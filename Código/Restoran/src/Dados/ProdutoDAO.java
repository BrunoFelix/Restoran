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
	
	public List<Produto> PesquisarPorNome(String nome) throws DadosException{
		try{
			String queryString = "SELECT P FROM PRODUTO P WHERE P.nome =(:nome)";
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("nome", nome);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}	
	
	public List<Produto> PesquisarPorQuantidade(int quantidade) throws DadosException{
		try{
			String queryString = "SELECT P FROM PRODUTO P WHERE P.quantidade =(:quantidade)";
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("quantidade", quantidade);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}

}
