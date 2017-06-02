package Bean;

import java.io.IOException;
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
public class ProdutoBean {
	
	FacesMessage msg;

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
		listarItemComposicaoProduto = listarItemComposicaoProduto;
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
		/*List<ItemComposicaoProduto> itensComposicao = new ArrayList<ItemComposicaoProduto>();
		produtoinserir.setItensComposicao(itensComposicao);
		List<Pedido> pedidos = new ArrayList<Pedido>();
		produtoinserir.setPedidos(pedidos);
		List<Categoria> categoria = new ArrayList<Categoria>();
		produtoinserir.setCategoria(categoria);*/
		try {
			fachada.ProdutoInserir(produtoinserir);
			return "listar";
		} catch (Exception e) {
			msg = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage("msgErro", msg);
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
			msg = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage("msgErro", msg);
		}
		return null;
    }
}
