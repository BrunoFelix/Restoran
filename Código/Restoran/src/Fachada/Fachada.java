package Fachada;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Basica.Produto;
import Basica.Usuario;
import Dados.UsuarioDAO;
import Negocio.RNProduto;
import Negocio.RNUsuario;
import Utils.DadosException;
import Utils.NegocioException;

public class Fachada {
	
	
	private static Fachada fachada;
	
	UsuarioDAO usuariodao;
	
	/*
	 * ################################## 
	 * 	  VARIAVEIS REGRA DE NEGOCIO
	 * ##################################
	 */
	RNUsuario rnusuario;
	RNProduto  rnproduto;
	
	
	/*public static Fachada getInstance() {
        if(fachada == null ){
            fachada = new Fachada(); 
	}
            return fachada;
    }*/
	
	public Fachada(){
		
		usuariodao = new UsuarioDAO();
		rnusuario = new RNUsuario();
		
		rnproduto = new RNProduto();
	}
	
	
	/*
	 * ################################## 
	 * 				USUARIO
	 * ##################################
	 */
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
		 //rnusuario.listar();
		
	}
	
	
	public String mensagem(String mensagem){
		return mensagem;
	}
	
	/*
	 * ################################## 
	 * 				PRODUTO
	 * ##################################
	 */
	  public void CadastrarProduto (Produto p) throws NegocioException, DadosException{
		  
		  rnproduto.salvar(p);
	  }
	
	/*
	 * ################################## 
	 * 				PEDIDO
	 * ##################################
	 */
	

}
