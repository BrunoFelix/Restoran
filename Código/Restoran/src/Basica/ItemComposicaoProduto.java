package Basica;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import Basica.ProdutoItem.ProdutoItemId;

@Entity
public class ItemComposicaoProduto{
	
	@Id @GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(length=50, nullable = false)
	private String nome;
  
	@Column(nullable = false)
	private int quantidade;
  
	@Column(length=5, nullable = false)
	private String unidadeMedida;
  
	@Column(nullable = false)
	private double precoCusto;
	
	@OneToMany(mappedBy = "itemComp", fetch = FetchType.EAGER)
	private Set<ProdutoItem> produtos = new HashSet<ProdutoItem>();

	//Gets & Sets
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	public Set<ProdutoItem> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<ProdutoItem> produtos) {
		this.produtos = produtos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		ItemComposicaoProduto other = (ItemComposicaoProduto) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	
    @Override
    public String toString() {
        return nome + " | " + id;
    }

	
}
