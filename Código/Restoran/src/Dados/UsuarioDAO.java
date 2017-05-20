package Dados;


import javax.persistence.EntityManagerFactory;

import Dados.Geral.DAOGenerico;
import Utils.DadosException;
import Basica.Produto;
import Basica.Usuario;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UsuarioDAO extends DAOGenerico<Usuario> implements IUsuarioDAO {

	public UsuarioDAO(EntityManagerFactory emf) {
		super(emf);
	}
	
	public Usuario logar(String login, String senha) throws DadosException{
		try {
			String queryString = "SELECT U FROM USUARIO U WHERE U.login =(:login) and U.senha =(:senha)";
			
			EntityManager em = this.entityManagerFactory.createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
	
	public List<Usuario> PesquisarUsandoObjeto(Usuario u) throws DadosException{
		try{
			String queryString = "SELECT U FROM USUARIO U WHERE U.id > 0";
			
			if (u.getCpf() != null && u.getCpf().trim().equals("") == false)
			{
				queryString += " and U.cpf =(:cpf)";
			}
			
			if (u.getEmail() != null && u.getEmail().trim().equals("") == false)
			{
				queryString += " and U.email =(:email)";
			}
			
			if (u.getDataNas() != null)
			{
				queryString += " and U.dataNas =(:dataNas)";
			}
			
			if (u.getSexo() != null && u.getSexo().trim().equals("") == false)
			{
				queryString += " and U.sexo =(:sexo)";
			}
			
			EntityManager em = this.entityManagerFactory.createEntityManager();
					
			Query query = em.createQuery(queryString);
			if (u.getCpf() != null && u.getCpf().trim().equals("") == false)
			{
				query.setParameter("cpf", u.getCpf());
			}
			
			if (u.getEmail() != null && u.getEmail().trim().equals("") == false)
			{
				query.setParameter("email", u.getEmail());
			}
			
			if (u.getDataNas() != null)
			{
				query.setParameter("dataNas", u.getDataNas());
			}
			
			if (u.getSexo() != null && u.getSexo().trim().equals("") == false)
			{
				query.setParameter("sexo", u.getCpf());
			}
	
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
	
	public List<Usuario> PesquisarPorCpf(String cpf) throws DadosException{
		try{
			String queryString = "SELECT U FROM USUARIO U WHERE U.cpf =(:cpf)";
			
			EntityManager em = this.entityManagerFactory.createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("cpf", cpf);
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
	
	public List<Usuario> PesquisarPorNome(String nome) throws DadosException{
		try{
			String queryString = "SELECT U FROM USUARIO U WHERE U.nome =(:nome)";
			
			EntityManager em = this.entityManagerFactory.createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("nome", nome);
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
	
	public List<Usuario> PesquisarPorEmail(String email) throws DadosException{
		try{
			String queryString = "SELECT U FROM USUARIO U WHERE U.email =(:email)";
			
			EntityManager em = this.entityManagerFactory.createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("email", email);
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	    
	}
	
	public List<Usuario> PesquisarPorSexo(String sexo) throws DadosException{
		try{
			String queryString = "SELECT U FROM USUARIO U WHERE U.sexo =(:sexo)";
			
			EntityManager em = this.entityManagerFactory.createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("sexo", sexo);
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
	
	public List<Usuario> PesquisarPorDataNasc(Date dataNasc) throws DadosException{
		try{
			String queryString = "SELECT U FROM USUARIO U WHERE U.dataNas =(:dataNas)";
			
			EntityManager em = this.entityManagerFactory.createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("dataNas", dataNasc);
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}
	
	public List<Usuario> PesquisarPorLogin(String login) throws DadosException{
		try{
			String queryString = "SELECT U FROM USUARIO U WHERE U.nome =(:login)";
			
			EntityManager em = this.entityManagerFactory.createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("nome", login);
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}

}
