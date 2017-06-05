package Dados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import Dados.Geral.DAOGenerico;
import Utils.DadosException;
import Basica.Categoria;
import Basica.Mesa;

public class MesaDAO extends DAOGenerico<Mesa> implements IMesaDAO {

	public MesaDAO(EntityManagerFactory emf) {
		super(emf);
	}
		
	public List<Mesa> PesquisarMesaObjeto(Mesa m) throws DadosException{
		try{
			String queryString = "select object(m) from Mesa as m where m.id > 0";
			
			if (m.getNumeroMesa() > 0)
			{
				queryString += " and m.numeroMesa =(:numeroMesa)";
			}
			
			if (m.getCapacidadeMesa() > 0)
			{
				queryString += " and m.capacidadeMesa =(:capacidadeMesa)";
			}
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			
			if (m.getNumeroMesa() > 0)
			{
				query.setParameter("numeroMesa", m.getNumeroMesa());
			}
	
			if (m.getCapacidadeMesa() > 0)
			{
				query.setParameter("capacidadeMesa", m.getCapacidadeMesa());
			}
			
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
 
}
