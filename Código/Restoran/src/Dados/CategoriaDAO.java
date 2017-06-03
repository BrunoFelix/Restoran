package Dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import Dados.Geral.DAOGenerico;
import Utils.DadosException;
import Basica.Categoria;
import Basica.Usuario;

public class CategoriaDAO extends DAOGenerico<Categoria> implements ICategoriaDAO {

	public CategoriaDAO(EntityManagerFactory emf) {
		super(emf);
	}
	
	public List<Categoria> PesquisarCategoriaObjeto(Categoria c) throws DadosException{
		try{
			String queryString = "select object(c) from Categoria as c where c.id > 0";
			
			if (c.getNome() != null && c.getNome().trim().equals("") == false)
			{
				queryString += " and c.nome =(:nome)";
			}
		
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			
			if (c.getNome() != null && c.getNome().trim().equals("") == false)
			{
				query.setParameter("nome", c.getNome());
			}
	
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
}
