package Dados;

import java.util.Date;
import java.util.List;

import Basica.Usuario;
import Utils.DadosException;

public interface IUsuarioDAO {
	
	public List<Usuario> PesquisarUsandoObjeto(Usuario u) throws DadosException;
	public List<Usuario> PesquisarPorCpf(String cpf) throws DadosException;
	public List<Usuario> PesquisarPorNome(String nome) throws DadosException;
	public List<Usuario> PesquisarPorEmail(String email) throws DadosException;
	public List<Usuario> PesquisarPorSexo(String sexo) throws DadosException;
	public List<Usuario> PesquisarPorDataNasc(Date dataNasc) throws DadosException;
	public List<Usuario> PesquisarPorLogin(String login) throws DadosException;

}
