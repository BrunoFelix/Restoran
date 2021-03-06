package Dados;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import Basica.Usuario;
import Dados.Geral.DAOGenerico;
import Utils.DadosException;

public class UsuarioDAO extends DAOGenerico<Usuario> implements IUsuarioDAO {
	
	public UsuarioDAO(EntityManagerFactory emf) {
		super(emf);
	}
	
	public Usuario logar(String login, String senha) throws DadosException{
		try {
			String queryString = "select object(u) from Usuario as u where u.login=(:login) and u.senha=(:senha)";
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
			
			Query query = em.createQuery(queryString);
			query.setParameter("login", login);
			query.setParameter("senha", senha);
	
			if (query.getResultList().size() <= 0){
				throw new DadosException("Usu�rio inv�lido ou inexistente!");
			}

			return (Usuario) query.getSingleResult();
			
		} catch (Exception e) {
			throw new DadosException(e.getMessage());
		}
	}
	
	public List<Usuario> PesquisarUsandoObjeto(Usuario u) throws DadosException{
		try{
			String queryString = "select object(u) from Usuario as u where u.id > 0";
			
			if (u.getNome() != null && u.getNome().trim().equals("") == false)
			{
				queryString += " and u.nome =(:nome)";
			}
			
			if (u.getLogin() != null && u.getLogin().trim().equals("") == false)
			{
				queryString += " and u.login =(:login)";
			}
			
			if (u.getSenha() != null && u.getSenha().trim().equals("") == false)
			{
				queryString += " and u.senha =(:senha)";
			}
			
			if (u.getCpf() != null && u.getCpf().trim().equals("") == false)
			{
				queryString += " and u.cpf =(:cpf)";
			}
			
			if (u.getEmail() != null && u.getEmail().trim().equals("") == false)
			{
				queryString += " and u.email =(:email)";
			}
			
			if (u.getSexo() != null && u.getSexo().trim().equals("") == false)
			{
				queryString += " and u.sexo =(:sexo)";
			}
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			
			if (u.getNome() != null && u.getNome().trim().equals("") == false)
			{
				query.setParameter("nome", u.getNome());
			}
			
			if (u.getLogin() != null && u.getLogin().trim().equals("") == false)
			{
				query.setParameter("login", u.getLogin());
			}
			
			if (u.getSenha() != null && u.getSenha().trim().equals("") == false)
			{
				query.setParameter("senha", u.getSenha());
			}
		
			if (u.getCpf() != null && u.getCpf().trim().equals("") == false)
			{
				query.setParameter("cpf", u.getCpf());
			}
			
			if (u.getEmail() != null && u.getEmail().trim().equals("") == false)
			{
				query.setParameter("email", u.getEmail());
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
	
	public List<Usuario> PesquisarPorEmail(String email) throws DadosException{
		try{
			String queryString = "select object(u) from Usuario as u where u.email =(:email)";
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("email", email);
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	    
	}
	
	public List<Usuario> PesquisarPorLogin(String login) throws DadosException{
		try{
			String queryString = "select object(u) from Usuario as u where u.login =(:login)";
			
			EntityManager em = getEntityManagerFactory().createEntityManager();
					
			Query query = em.createQuery(queryString);
			query.setParameter("login", login);
		    return query.getResultList();
		} catch (Exception e) {
			throw new DadosException(e);
		}
	}

}
