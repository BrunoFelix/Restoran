package Fachada;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import Basica.Categoria;
import Basica.ItemComposicaoProduto;
import Basica.Mesa;
import Basica.Pedido;
import Basica.PedidoProduto;
import Basica.Produto;
import Basica.ProdutoItem;
import Basica.Usuario;
import Dados.UsuarioDAO;
import Negocio.RNCategoria;
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
        rnItemComposicaoProduto = new RNItemComposicaoProduto(emf);
        rnMesa = new RNMesa(emf);
        rnPedido = new RNPedido(emf);
        rnUsuario = new RNUsuario(emf);
		rnProduto = new RNProduto(emf);
	}
	
	public void inserirprodutoitem(ProdutoItem pi){
		EntityManager em =  emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();		
		try {
			tx.begin();
			em.persist(pi);
			tx.commit();
			em.close();
		} catch (PersistenceException e) {
			tx.rollback();
		}
	}
	
	/*
	 * ################################## 
	 * 				PEDIDO
	 * ##################################
	 */
	
	public void PedidoInserir(Pedido p) throws NegocioException, DadosException{
		rnPedido.salvar(p);
	}
	
	public void PedidoAlterar(Pedido p) throws NegocioException, DadosException{
		rnPedido.alterar(p);
	}
	
	public Pedido PedidoPesquisarPorMesa(long numeroMesa) throws DadosException{
		return rnPedido.PesquisarPorMesa(numeroMesa);
	}
	
	public List<Pedido> PedidoListar() throws DadosException{
		return rnPedido.PedidoListar();
	}

	public void PedidoInserirVinculoProduto(List<PedidoProduto> pp) throws DadosException{
		rnPedido.InserirVinculoProduto(pp);
	}
	  
	  public void PedidoAlterarVinculoProduto(List<PedidoProduto> pp) throws DadosException{
		 rnPedido.AlterarVinculoProduto(pp);
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
		return rnUsuario.UsuarioBuscarPorId(id);
	}
	
	public List<Usuario> UsuarioPesquisarPorObjeto(Usuario u) throws DadosException{
		return rnUsuario.PesquisarUsandoObjeto(u);
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
	  
	  public Produto ProdutoBuscarPorId(Long id){
		  return rnProduto.ProdutoBuscarPorId(id);
	  }
	  
	  public List<Produto> ProdutoPesquisarPorObjeto(Produto p) throws DadosException{
		  return rnProduto.PesquisarProdutoObjeto(p);
	  }
	  
	  public void ProdutoInserirVinculoProdutoItemComp(List<ProdutoItem> pi) throws DadosException{
		  rnProduto.InserirVinculoProdutoItemComp(pi);
	  }
	  
	  public void ProdutoAlterarVinculoProdutoItemComp(List<ProdutoItem> pi) throws DadosException{
		  rnProduto.AlterarVinculoProdutoItemComp(pi);
	  }
	  
	/*
	 * ################################## 
	 * 	 ITEM DE COMPOSICAO DO PRODUTO
	 * ##################################
	 */
	  
	  public void ItemComposicaoProdutoInserir(ItemComposicaoProduto i) throws NegocioException, DadosException{
		  rnItemComposicaoProduto.salvar(i);
	  }
	  
	  public void ItemComposicaoProdutoAlterar(ItemComposicaoProduto i) throws NegocioException, DadosException, ControladorException{
		  rnItemComposicaoProduto.alterar(i);
	  }
	  
	  public void ItemComposicaoProdutoExcluir(ItemComposicaoProduto i) throws NegocioException, DadosException, ControladorException{
		  rnItemComposicaoProduto.excluir(i);
	  }
	  
	  public List<ItemComposicaoProduto> ItemComposicaoProdutoListar(){
		  return rnItemComposicaoProduto.listar();
	  }
	  
	  public ItemComposicaoProduto ItemComposicaoProdutoBuscarPorId(Long id){
			return rnItemComposicaoProduto.ItemComposicaoProdutoBuscarPorId(id);
	  }
	  
	  public List<ItemComposicaoProduto> CategoriaItemComposicaoProdutoPorObjeto(ItemComposicaoProduto icp) throws DadosException{
			return rnItemComposicaoProduto.PesquisarItemComposicaoProdutoObjeto(icp);
	  }
	  
	  /*
		 * ################################## 
		 * 				CATEGORIA
		 * ##################################
		 */
	  public void CategoriaInserir(Categoria c) throws NegocioException, DadosException{
		  rnCategoria.salvar(c);
	  }
	  
	  public void CategoriaAlterar(Categoria c) throws NegocioException, DadosException, ControladorException{
		  rnCategoria.alterar(c);
	  }
	  
	  public void CategoriaExcluir(Categoria c) throws NegocioException, DadosException, ControladorException{
		  rnCategoria.excluir(c);
	  }
	  
	  public List<Categoria> CategoriaListar(){
		  return rnCategoria.listar();
	  }
	  
	  public Categoria CategoriaBuscarPorId(Integer id){
			return rnCategoria.CategoriaBuscarPorId(id);
	  }
	  
	  public List<Categoria> CategoriaPesquisarPorObjeto(Categoria c) throws DadosException{
			return rnCategoria.PesquisarCategoriaObjeto(c);
	  }
	  
	  /*
		 * ################################## 
		 * 				 MESA
		 * ##################################
		 */
	  public void MesaInserir(Mesa m) throws NegocioException, DadosException{
		  rnMesa.salvar(m);
	  }
	  
	  public void MesaAlterar(Mesa m) throws NegocioException, DadosException, ControladorException{
		  rnMesa.alterar(m);
	  }
	 
	  public void MesaExcluir(Mesa m) throws NegocioException, DadosException, ControladorException{
		  rnMesa.excluir(m);
	  }
	  
	  public List<Mesa> MesaListar(){
		  return rnMesa.listar();
	  }
	  
	  public Mesa MesaBuscarPorId(Long id){
			return rnMesa.MesaBuscarPorId(id);
	  }
	  
	  public List<Mesa> MesaPesquisarPorObjeto(Mesa m) throws DadosException{
			return rnMesa.PesquisarMesaObjeto(m);
	  }

}
