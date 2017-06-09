package Dados;

import java.util.List;

import Basica.Mesa;
import Utils.DadosException;

public interface IMesaDAO {
	
	public List<Mesa> PesquisarMesaObjeto(Mesa m) throws DadosException;
}
