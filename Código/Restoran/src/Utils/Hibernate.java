package Utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Basica.Usuario;
import Dados.UsuarioDAO;

public class Hibernate {

	public static EntityManagerFactory emf;

	public static void main(String[] args) throws DadosException {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetorestoran"); 
		
		UsuarioDAO usuariodao = new  UsuarioDAO(emf);
		
		Usuario usuario = usuariodao.logar("aa", "bb"); 
		
		System.out.println(usuario.getId_usuario());
		
	}

}
