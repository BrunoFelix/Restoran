package Negocio;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import Basica.Mesa;
import Dados.MesaDAO;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;

public class RNMesa {

	MesaDAO mesaDAO;

	public RNMesa(EntityManagerFactory emf) {

		mesaDAO = new MesaDAO(emf);
	}

	/*
	 * ################################## 
	 * 			FUNCIONALIDADES
	 * ##################################
	 */

	public void salvar(Mesa m) throws NegocioException, DadosException {

		validarCampos(m);
		validarDuplicidadeNumeroMesa(m);
		mesaDAO.insert(m);

	}

	public void alterar(Mesa m) throws ControladorException, NegocioException, DadosException {

		validarCampos(m);
		validarDuplicidadeNumeroMesa(m);
		mesaDAO.update(m);

	}

	public void excluir(Mesa m) throws ControladorException {

		mesaDAO.remove(m);
	}

	public List<Mesa> listar() {
		return mesaDAO.getAll();
	}
	
	public Mesa MesaBuscarPorId(Integer id){
		return mesaDAO.searchByKey(id);
    }

	/*
	 * ##################################
	 * 			 VALIDAC�ES
	 * ##################################
	 */

	public void verificarObjeto(Mesa m) throws NegocioException {

		if (m == null)
			throw new NegocioException("Objeto Mesa nao preenchido");
	}

	public void validarCampos(Mesa m) throws NegocioException {
		if (m.getNumeroMesa() == 0)
			throw new NegocioException("Numero de mesa Invalido");
		if (m.getCapacidadeMesa() == 0)
			throw new NegocioException("Capacidade da mesa nao informada");
		if ((m.getStatus().isEmpty() == true) || (m.getStatus() == ""))
			throw new NegocioException("Status da mesa Invalido");
	}

	public void validarDuplicidadeNumeroMesa(Mesa m) throws NegocioException, DadosException {

		try {
			if (mesaDAO.PesquisarPorNumeroMesa(m.getNumeroMesa()).size() > 0) {
				throw new NegocioException("Mesa com numero " + m.getNumeroMesa() + " ja existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados nao disponivel");
		}
	}

}
