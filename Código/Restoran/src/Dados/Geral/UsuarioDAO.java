package Dados.Geral;


import javax.persistence.EntityManagerFactory;

import Dados.Geral.DAOGenerico;

import Basica.Usuario;

public class UsuarioDAO extends DAOGenerico<Usuario> {

	public UsuarioDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
