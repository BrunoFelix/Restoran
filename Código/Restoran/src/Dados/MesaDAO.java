package Dados;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import Dados.Geral.DAOGenerico;
import Utils.DadosException;
import Utils.HibernateUtil;
import Basica.Mesa;

public class MesaDAO extends DAOGenerico<Mesa> implements IMesaDAO {

	public MesaDAO() {
		super();
	}
	
	public List<Mesa> PesquisarPorStatus(String status) throws DadosException{
		try{
			String queryString = "SELECT M FROM MESA M WHERE M.status =(:status)";
			
			EntityManager em = HibernateUtil.geteEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("status", status);
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
	
	public List<Mesa> PesquisarPorCapacidade(int capacidade) throws DadosException{
		try{
			String queryString = "SELECT M FROM MESA M WHERE M.capacidade =(:capacidade)";
			
			EntityManager em = HibernateUtil.geteEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("capacidade", capacidade);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
	public List<Mesa> PesquisarPorNumeroMesa(int numeromesa) throws DadosException{
		
			try{
			String queryString = "SELECT M FROM MESA M WHERE M.numeromesa =(:numeromesa)";
			
			EntityManager em = HibernateUtil.geteEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("numeromesa", numeromesa);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	
	}
	
 
}
