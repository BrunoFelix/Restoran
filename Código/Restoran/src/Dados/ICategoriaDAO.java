package Dados;

import java.util.List;

import Basica.Categoria;
import Utils.DadosException;

public interface ICategoriaDAO {
	
	public List<Categoria> PesquisarPorNome(String nome) throws DadosException;
}
