package Negocio;

import Basica.Mesa;
import Dados.MesaDAO;
import Utils.NegocioException;
import Utils.DadosException;

public class RNMesa {
  
  
	MesaDAO mesaDAO;

	public RNMesa() {

		mesaDAO = new MesaDAO();
	}
  
    public void salvar(Mesa m) throws NegocioException, DadosException{
        
    	validarCampos(m);
      validarDuplicidadeNumeroMesa(m);
        
    }
  
  public void verificarObjeto(Mesa m) throws NegocioException {

		if (m == null)
			throw new NegocioException("Objeto Mesa não preenchido");
	}
  
  public void validarCampos(Mesa m) throws NegocioException {
		if ((m.getNumeroMesa().isEmpty() == true) || (m.getNumeroMesa() == null))
			throw new NegocioException("Numero de mesa Inválido");
		if(m.getCapacidadeMesa().isEmpty() == true)||(m.getCapacidadeMesa()==null))
      throw new NegocioException("Capacidade da mesa não informada");
    if(m.getStatus().isEmpty()== true)||(m.getStatus()== null)
      throw new NegocioException("Status da mesa Inválido");
	}
public void validarDuplicidadeNumeroMesa(Mesa m) throws NegocioException, DadosException {

		try {
			if (mesaDAO.PesquisarPorNumeroMesa(m.getNumeroMesa()) != null) {
				throw new NegocioException("Mesa com numero "+m.getNumeroMesa()+" já existe");
			}
		} catch (DadosException e) {
			throw new DadosException("Banco de dados não disponível");
		}
	}

}
