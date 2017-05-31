package Dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import Dados.Geral.DAOGenerico;
import Utils.DadosException;
import Basica.Mesa;

public class MesaDAO extends DAOGenerico<Mesa> implements IMesaDAO {

	public MesaDAO(EntityManagerFactory emf) {
		super(emf);
	}
	
	public List<Mesa> PesquisarPorStatus(String status) throws DadosException{
		try{
			String queryString = "select object(m) from Mesa as m where m.status=(:status)";
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("status", status);
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
	
	public List<Mesa> PesquisarPorCapacidade(int capacidade) throws DadosException{
		try{
			String queryString = "select object(m) from Mesa as m where m.capacidade=(:capacidade)";
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("capacidade", capacidade);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
	public List<Mesa> PesquisarPorNumeroMesa(int numeromesa) throws DadosException{
		
			try{
			String queryString = "select object(m) from Mesa as m where m.numeroMesa=(:numeromesa)";
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("numeromesa", numeromesa);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	
	}
	
 
}
