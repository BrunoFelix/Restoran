package Basica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProdutoItem {

	@Embeddable
	public static class ProdutoItemId implements Serializable {

		@Column(name = "fk_produto")
		protected Long ProdutoId;

		@Column(name = "fk_itemComp")
		protected Long itemCompId;

		public ProdutoItemId() {
			
		}
		
		public ProdutoItemId(Long ProdutoId, Long itemCompId) {
			this.ProdutoId = ProdutoId;
			this.itemCompId = itemCompId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((ProdutoId == null) ? 0 : ProdutoId.hashCode());
			result = prime * result
					+ ((itemCompId == null) ? 0 : itemCompId.hashCode());
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
			
			ProdutoItemId other = (ProdutoItemId) obj;
			
			if (ProdutoId == null) {
				if (other.ProdutoId != null)
					return false;
			} else if (!ProdutoId.equals(other.ProdutoId))
				return false;
			
			if (itemCompId == null) {
				if (other.itemCompId != null)
					return false;
			} else if (!itemCompId.equals(other.itemCompId))
				return false;
			
			return true;
		}
	}
	
	public ProdutoItemId getId() {
		return id;
	}

	public void setId(ProdutoItemId id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ItemComposicaoProduto getItemComp() {
		return itemComp;
	}

	public void setItemComp(ItemComposicaoProduto itemComp) {
		this.itemComp = itemComp;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@EmbeddedId
	private ProdutoItemId id;

	@ManyToOne
	@JoinColumn(name = "fk_produto", insertable = false, updatable = false)
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "fk_itemComp", insertable = false, updatable = false)
	private ItemComposicaoProduto itemComp;

	@Column
	private Integer quantidade;
	
	public ProdutoItem(){
		
	}

	public ProdutoItem(Produto produto, ItemComposicaoProduto itemComp, Integer quantidade) {
		// create primary key
		this.id = new ProdutoItemId(produto.getId(), itemComp.getId());
		
		// initialize attributes
		this.produto = produto;
		this.itemComp = itemComp;
		this.quantidade = quantidade;
		
		// update relationships to assure referential integrity
		produto.getItemComps().add(this);
		itemComp.getProdutos().add(this);	
	}
	
}