package Fachada;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Basica.Usuario;
import Dados.UsuarioDAO;
import Utils.DadosException;

public class Fachada {
	
	
	private static Fachada fachada;
	
	UsuarioDAO usuariodao;
	
	/*public static Fachada getInstance() {
        if(fachada == null ){
            fachada = new Fachada(); 
	}
            return fachada;
    }*/
	
	public Fachada(){
		
		usuariodao = new UsuarioDAO();
	}
	
	
	/**************** usuário *******************/
	public Usuario Usuariologar(String login, String senha){
		try {
			return usuariodao.logar(login, senha);
		} catch (DadosException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao pesquisar usuário");
		}
		return null;
	}
	
	public List<Usuario> UsuarioListar(){
		return usuariodao.getAll();
	}
	
	
	public String mensagem(String mensagem){
		return mensagem;
	}
	

}
