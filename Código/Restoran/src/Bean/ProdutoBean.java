package Bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import Basica.Produto;
import Fachada.Fachada;

@ManagedBean(name="ProdutoBean")
public class ProdutoBean {
	
	FacesMessage msg;
	
	Fachada fachada = Fachada.getInstance();
	
	private List<Produto> listarProduto = new ArrayList<Produto>();

	private static Produto  produto;
	
	private String nome;
	private double precoVenda;
	private double precoCusto;
	private int quantidade;

	

	/*
	 * ################################## 
	 * 			GETS E SETS
	 * ##################################
	 */
	
	public List<Produto> getListarProduto() {
		
		setListarProduto(fachada.ProdutoListar());
		return listarProduto;
	}


	public void setListarProduto(List<Produto> listarProduto) {
		this.listarProduto = listarProduto;
		
	}


	public static Produto getProduto() {
		return produto;
	}


	public static void setProduto(Produto produto) {
		ProdutoBean.produto = produto;
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
	

	public void index() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().redirect("produto.xhtml");
	}
	
		
	
}
