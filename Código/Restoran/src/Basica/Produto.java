package Basica;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Produto {

	@Id @GeneratedValue
	private int id;
	
	@Column(length=50, nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private double precoVenda;
	
	@Column(nullable = false)
	private double precoCusto;
	
	@Column(nullable = false)
	private int quantidade;
		
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto")
	public List<Pedido_produto> pedido_produto;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto")
	public List<Produto_item> produto_item;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_categoria", insertable=true, updatable=true) //Chave Estrangeira
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Categoria categoria;

	//Gets & Sets
	public int getId() {
		return id;
	}


	public void setId(int id_produto) {
		this.id = id_produto;
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

	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public List<Pedido_produto> getPedido_produto() {
		return pedido_produto;
	}


	public void setPedido_produto(List<Pedido_produto> pedido_produto) {
		this.pedido_produto = pedido_produto;
	}


	public List<Produto_item> getProduto_item() {
		return produto_item;
	}


	public void setProduto_item(List<Produto_item> produto_item) {
		this.produto_item = produto_item;
	}

	
}
