package Dados;

import java.util.List;

import Basica.Mesa;
import Utils.DadosException;

public interface IMesaDAO {
	
	public List<Mesa> PesquisarPorStatus(String status) throws DadosException;
	public List<Mesa> PesquisarPorCapacidade(int capacidade) throws DadosException;

}
