package dados;


import javax.persistence.EntityManagerFactory;

import dados.geral.DAOGenerico;

import basicas.Usuario;

public class UsuarioDAO extends DAOGenerico<Usuario> {

	public UsuarioDAO(EntityManagerFactory emf) {
		super(emf);
	}

}
