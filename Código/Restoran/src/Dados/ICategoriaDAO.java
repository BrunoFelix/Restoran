package Dados;

import java.util.List;

import Basica.Categoria;
import Utils.DadosException;

public interface ICategoriaDAO {
	
	public List<Categoria> PesquisarCategoriaObjeto(Categoria c) throws DadosException;
}
