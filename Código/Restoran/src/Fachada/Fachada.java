package Fachada;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Basica.Produto;
import Basica.Usuario;
import Dados.UsuarioDAO;
import Negocio.RNCategoria;
import Negocio.RNHistorico;
import Negocio.RNItemComposicaoProduto;
import Negocio.RNMesa;
import Negocio.RNPedido;
import Negocio.RNProduto;
import Negocio.RNUsuario;
import Utils.ControladorException;
import Utils.DadosException;
import Utils.NegocioException;

public class Fachada {
	
	
	private static Fachada fachada;
	
	UsuarioDAO usuariodao;
	
	private static EntityManagerFactory emf;
	/*
	 * ################################## 
	 * 	  VARIAVEIS REGRA DE NEGOCIO
	 * ##################################
	 */
	RNCategoria rnCategoria;
	RNHistorico rnHistorico;
	RNItemComposicaoProduto rnItemComposicaoProduto;
	RNMesa rnMesa;
	RNPedido rnPedido;
	RNProduto rnProduto;	
	RNUsuario rnUsuario;
	
	public static Fachada getInstance() {
        if(fachada == null ){
            fachada = new Fachada(); 
	}
            return fachada;
    }
	
	public Fachada(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
        emf = Persistence.createEntityManagerFactory("projetorestoran");
        rnCategoria = new RNCategoria(emf);
        rnHistorico = new RNHistorico(emf);
        rnItemComposicaoProduto = new RNItemComposicaoProduto(emf);
        rnMesa = new RNMesa(emf);
        rnPedido = new RNPedido(emf);
        rnUsuario = new RNUsuario(emf);
		rnProduto = new RNProduto(emf);
	}
	
	/*
	 * ################################## 
	 * 				USUARIO
	 * ##################################
	 */
	public Usuario Usuariologar(Usuario u) throws NegocioException, DadosException{
		return rnUsuario.logar(u);
	}
	
	public void UsuarioInserir(Usuario u) throws NegocioException, DadosException{
		rnUsuario.salvar(u);
	}
	
	public void UsuarioAlterar(Usuario u) throws NegocioException, DadosException, ControladorException{
		rnUsuario.alterar(u);
	}
	
	public void UsuarioExcluir(Usuario u) throws NegocioException, DadosException, ControladorException{
		rnUsuario.excluir(u);
	}
	
	public List<Usuario> UsuarioListar(){
		return rnUsuario.listar();
	}
	
	public Usuario UsuarioBuscarPorId(Integer id){
		return rnUsuario.searchByKey(id);
	}
	

	/*
	 * ################################## 
	 * 				PRODUTO
	 * ##################################
	 */
	  public void ProdutoInserir(Produto p) throws NegocioException, DadosException{
		  rnProduto.salvar(p);
	  }
	  
	  public void ProdutoAlterar(Produto p) throws NegocioException, DadosException, ControladorException{
		  rnProduto.alterar(p);
	  }
	  
	  public void ProdutoExcluir(Produto p) throws NegocioException, DadosException, ControladorException{
		  rnProduto.excluir(p);
	  }
	  
	  public List<Produto> ProdutoListar(){
		  return rnProduto.listar();
	  }
	/*
	 * ################################## 
	 * 				PEDIDO
	 * ##################################
	 */
	

}
