package Dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import Dados.Geral.DAOGenerico;
import Utils.DadosException;
import Basica.Categoria;

public class CategoriaDAO extends DAOGenerico<Categoria> implements ICategoriaDAO {

	public CategoriaDAO(EntityManagerFactory emf) {
		super(emf);
	}
	
	public List<Categoria> PesquisarPorNome(String nome) throws DadosException{
		try{
			String queryString = "SELECT C FROM CATEGORIA C WHERE C.nome =(:nome)";
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("nome", nome);
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}

}
