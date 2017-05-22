package Dados;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import Dados.Geral.DAOGenerico;
import Utils.DadosException;
import Utils.HibernateUtil;
import Basica.ItemComposicaoProduto;
import Basica.Pedido;

public class PedidoDAO extends DAOGenerico<Pedido> implements IPedidoDAO {

	public PedidoDAO() {
		super();
	}

	public List<Pedido> PesquisarPorStatus(String status) throws DadosException{
		try{
			String queryString = "SELECT P FROM PEDIDO P WHERE P.status =(:status)";
			
			EntityManager em = HibernateUtil.geteEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("status", status);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}	
	
	public List<Pedido> PesquisarPorData(Date data) throws DadosException{
		try{
			String queryString = "SELECT P FROM PEDIDO P WHERE P.data =(:data)";
			
			EntityManager em = HibernateUtil.geteEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("data", data);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}	
	
	public List<Pedido> PesquisarPorGarcom(int codigoGarcom) throws DadosException{
		try{
			String queryString = "SELECT P FROM PEDIDO P WHERE P.garcom.id =(:codigoGarcom)";

			EntityManager em = HibernateUtil.geteEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("codigoGarcom", codigoGarcom);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}	
	
	public List<Pedido> PesquisarPorMesa(int numeroMesa) throws DadosException{
		try{
			String queryString = "SELECT P FROM PEDIDO P WHERE P.mesa.id =(:numeroMesa)";

			EntityManager em = HibernateUtil.geteEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("numeroMesa", numeroMesa);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}	
	
	public List<Pedido> PesquisarPorProduto(int codigoProduto) throws DadosException{
		try{
			String queryString = "SELECT P FROM PEDIDO P WHERE P.produtos.id =(:codigoProduto)";

			EntityManager em = HibernateUtil.geteEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("codigoProduto", codigoProduto);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}	
	
	
}
