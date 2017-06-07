package Dados;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import Dados.Geral.DAOGenerico;
import Utils.DadosException;
import Basica.ItemComposicaoProduto;
import Basica.Pedido;
import Basica.PedidoProduto;
import Basica.ProdutoItem;

public class PedidoDAO extends DAOGenerico<Pedido> implements IPedidoDAO {

	public PedidoDAO(EntityManagerFactory emf) {
		super(emf);
	}

	public List<Pedido> PesquisarPorStatus(String status) throws DadosException{
		try{
			String queryString = "SELECT P FROM PEDIDO P WHERE P.status =(:status)";
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
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
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
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

			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("codigoGarcom", codigoGarcom);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}	
	
	public Pedido PesquisarPorMesa(long numeroMesa) throws DadosException{
		try{
			String queryString = "select object(p) from Pedido as p where p.mesa.id =(:numeroMesa)";

			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("numeroMesa", numeroMesa);
			return (Pedido) query.getSingleResult();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}	
	
	public List<Pedido> PesquisarPorProduto(int codigoProduto) throws DadosException{
		try{
			String queryString = "SELECT P FROM PEDIDO P WHERE P.produtos.id =(:codigoProduto)";

			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("codigoProduto", codigoProduto);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}

	@Override
	public List<Pedido> ListaPedidos() throws DadosException {
		try{
			String queryString = "select object(p) from Pedido as p where p.status = 'Produção' order by p.data asc, p.id asc";

			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}	
	
	@Override
	public void InserirVinculoProduto(List<PedidoProduto> pp) throws DadosException {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		PedidoProduto bp;
		for (int i = 0; i < pp.size(); i++) {
			bp = pp.get(i);
			em.persist(bp);
		}
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void AlterarVinculoProduto(List<PedidoProduto> pp) throws DadosException {
		// TODO Auto-generated method stub
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		PedidoProduto bp;
		for (int i = 0; i < pp.size(); i++) {
			bp = pp.get(i);
			em.merge(bp);
		}
		em.getTransaction().commit();
		em.close();
	}

}
