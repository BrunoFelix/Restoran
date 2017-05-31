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
public class ItemComposicaoProduto {
	
	@Id @GeneratedValue
	private int id;
	
	@Column(length=50, nullable = false)
	private String nome;
  
	@Column(nullable = false)
	private int quantidade;
  
	@Column(length=5, nullable = false)
	private String unidadeMedida;
  
	@Column(nullable = false)
	private double precoCusto;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="produto_item", joinColumns=@JoinColumn(name="id_item"), inverseJoinColumns=@JoinColumn(name="id_produto"))
	private Collection<Produto> produtos;
	
	//Gets & Sets
	public int getId() {
		return id;
	}

	public void setId(int id_itemComposicaoProduto) {
		this.id = id_itemComposicaoProduto;
	}

  public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
  public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
  
  public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
  
  public double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public Collection<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Collection<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
