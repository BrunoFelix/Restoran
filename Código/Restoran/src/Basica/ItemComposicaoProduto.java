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
import javax.persistence.OneToMany;

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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "itemcomposicaoproduto")
	public List<Produto_item> produto_item;
	
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

	public List<Produto_item> getProduto_item() {
		return produto_item;
	}

	public void setProduto_item(List<Produto_item> produto_item) {
		this.produto_item = produto_item;
	}

	
}
