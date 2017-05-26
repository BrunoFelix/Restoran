package Negocio;

import java.util.List;

import Basica.Mesa;
import Basica.Usuario;
import Dados.CategoriaDAO;
import Dados.MesaDAO;
import Utils.NegocioException;
import Utils.ControladorException;
import Utils.DadosException;

public class RNMesa {

	MesaDAO mesaDAO;

	public RNMesa() {

		mesaDAO = new MesaDAO();
	}

	/*
	 * ################################## FUNCIONALIDADES
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

	/*
	 * ##################################
	 * 			 VALIDACÕES
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
			if (mesaDAO.PesquisarPorNumeroMesa(m.getNumeroMesa()) != null) {
				throw new NegocioException("Mesa com numero " + m.getNumeroMesa() + " ja existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados nao disponivel");
		}
	}

}
