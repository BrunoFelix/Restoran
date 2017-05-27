package Negocio;


import java.util.List;

import javax.persistence.EntityManagerFactory;

import Basica.Historico;
import Dados.HistoricoDAO;
import Utils.DadosException;
import Utils.NegocioException;


public class RNHistorico {
	
	HistoricoDAO rnhistoricoDAO;

	public RNHistorico(EntityManagerFactory emf) {

		rnhistoricoDAO = new HistoricoDAO(emf);
	}
	
	public void salvar(Historico i) throws NegocioException, DadosException {
		rnhistoricoDAO.insert(i);
	}
	  

	/*
	 * ################################## 
	 * 			FUNCIONALIDADES
	 * ##################################
	 */

}
