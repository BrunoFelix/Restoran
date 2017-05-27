package Basica;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="pedido_produto", joinColumns=@JoinColumn(name="id_produto"), inverseJoinColumns=@JoinColumn(name="id_pedido"))
	private Collection<Pedido> pedidos;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="produto_item", joinColumns=@JoinColumn(name="id_produto"), inverseJoinColumns=@JoinColumn(name="id_item"))
	private Collection<ItemComposicaoProduto> itensComposicao;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="produto_categoria", joinColumns=@JoinColumn(name="id_produto"), inverseJoinColumns=@JoinColumn(name="id_categoria"))
	private Collection<Categoria> categoria;

	//Gets & Sets
	public int getId_produto() {
		return id;
	}


	public void setId_produto(int id_produto) {
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


	public Collection<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(Collection<Pedido> pedidos) {
		this.pedidos = pedidos;
	}


	public Collection<ItemComposicaoProduto> getItensComposicao() {
		return itensComposicao;
	}


	public void setItensComposicao(Collection<ItemComposicaoProduto> itensComposicao) {
		this.itensComposicao = itensComposicao;
	}


	public Collection<Categoria> getCategoria() {
		return categoria;
	}


	public void setCategoria(Collection<Categoria> categoria) {
		this.categoria = categoria;
	}


	
	
}
