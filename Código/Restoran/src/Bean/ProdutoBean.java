package Bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import Basica.Categoria;
import Basica.ItemComposicaoProduto;
import Basica.Pedido;
import Basica.Produto;
import Fachada.Fachada;
import Utils.NegocioException;

@ManagedBean(name="ProdutoBean")
@SessionScoped
public class ProdutoBean{
	
	Fachada fachada = Fachada.getInstance();
	
	private Produto produtoAlterar;

	private List<ItemComposicaoProduto> listarItemComposicaoProduto;
	private List<Produto> listarProduto = new ArrayList<Produto>();
	private List<Categoria> listarCategoria = new ArrayList<Categoria>();
	private int id;
	private String nome;
	private double precoVenda;
	private double precoCusto;
	private int quantidade;
	private Categoria categoria;

	public Produto getProdutoAlterar() {
		return produtoAlterar;
	}

	public void setProdutoAlterar(Produto produtoAlterar) {
		this.produtoAlterar = produtoAlterar;
	}
	

	public List<Produto> getListarProduto() {
		setListarProduto(fachada.ProdutoListar());
		return listarProduto;
	}

	public void setListarProduto(List<Produto> listarProduto) {
		this.listarProduto = listarProduto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public List<Categoria> getListarCategoria() {
		setListarCategoria(fachada.CategoriaListar());
		return listarCategoria;
	}

	public void setListarCategoria(List<Categoria> listarCategoria) {
		this.listarCategoria = listarCategoria;
	}

	public List<ItemComposicaoProduto> getListarItemComposicaoProduto() {
		return listarItemComposicaoProduto;
	}

	public void setListarItemComposicaoProduto(List<ItemComposicaoProduto> listarItemComposicaoProduto) {
		this.listarItemComposicaoProduto = listarItemComposicaoProduto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String index(){
		return "listar";
	}
	
	public String cadastrar() {
		Produto produtoinserir = new Produto();
		produtoinserir.setNome(nome);
		produtoinserir.setQuantidade(quantidade);
		produtoinserir.setPrecoVenda(precoVenda);
		produtoinserir.setPrecoCusto(precoCusto);
		produtoinserir.setCategoria(categoria);
		/*List<ItemComposicaoProduto> itensComposicao = new ArrayList<ItemComposicaoProduto>();
		produtoinserir.setItensComposicao(itensComposicao);
		List<Pedido> pedidos = new ArrayList<Pedido>();
		produtoinserir.setPedidos(pedidos);*/
		/*Categoria categoria = new Categoria();
		for (int i = 0; i < listarCategoria.size(); i++) {
			if (listarCategoria.get(i).getId() == idCategoria){
				categoria = listarCategoria.get(i);
			}
		}*/
		/*List<Categoria> listainserircategoria = new ArrayList<Categoria>();
		listainserircategoria.add(categoria);
		produtoinserir.setCategoria(listainserircategoria);*/
		try {
			fachada.ProdutoInserir(produtoinserir);
			
			nome = "";
			quantidade = 0;
			precoCusto = 0;
			precoVenda = 0;
			//categoria = null;
			
			return "listar";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}
	
	public String chamadaAlterar(Integer id) {
		produtoAlterar = fachada.ProdutoBuscarPorId(id);
        return "alterar";
    }
	
	public String alterar() {
		try {
			fachada.ProdutoAlterar(produtoAlterar);
			return "listar";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
    }
}
