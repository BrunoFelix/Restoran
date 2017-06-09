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
import Basica.Produto;
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
			
			if (query.getResultList().size() <= 0){
				throw new DadosException("nenhum pedido!");
			}
			
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
			String queryString = "select object(p) from Pedido as p where order by p.data asc, p.id asc";

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
		try{
			EntityManager em = getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			PedidoProduto bp;
			for (int i = 0; i < pp.size(); i++) {
				bp = pp.get(i);
				em.persist(bp);
			}
			em.getTransaction().commit();
			em.close();
		}catch(Exception e){
			throw new DadosException(e.getMessage());
		}
	}

	@Override
	public void AlterarVinculoProduto(List<PedidoProduto> pp) throws DadosException {
		// TODO Auto-generated method stub
		try{
			EntityManager em = getEntityManagerFactory().createEntityManager();
			for (int i = 0; i < pp.size(); i++) {
				AlterarVinculoPedidoProduto(pp.get(i));
			}
		}catch(Exception e){
			throw new DadosException(e.getMessage());
		}
	}

	@Override
	public List<PedidoProduto> PedidoProdutoListar() throws DadosException {
		try{
			String queryString = "select object(pp) from PedidoProduto as pp where pp.status <> 'Pronto' order by status desc";

			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}

	@Override
	public PedidoProduto PesquisarPedidoProduto(Pedido pedido, Produto produto) throws DadosException {
		try{
			String queryString = "select object(pp) from PedidoProduto as pp where pp.pedido =(:pedido) and pp.produto =(:produto)";

			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("pedido", pedido);
			query.setParameter("produto", produto);
			return (PedidoProduto) query.getSingleResult();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}

	@Override
	public void AlterarVinculoPedidoProduto(PedidoProduto pp) throws DadosException {
		try{
			EntityManager em = getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			em.merge(pp);
			em.getTransaction().commit();
			em.close();
		}catch(Exception e){
			throw new DadosException(e.getMessage());
		}
		
	}

}
