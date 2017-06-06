package Basica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PedidoProduto {

	@Embeddable
	public static class PedidoProdutoId implements Serializable {

		@Column(name = "fk_pedido")
		protected Long pedidoId;

		@Column(name = "fk_produto")
		protected Long produtoId;

		public PedidoProdutoId() {
			
		}
		
		public PedidoProdutoId(Long pedidoId, Long produtoId) {
			this.pedidoId = pedidoId;
			this.produtoId = produtoId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((pedidoId == null) ? 0 : pedidoId.hashCode());
			result = prime * result
					+ ((produtoId == null) ? 0 : produtoId.hashCode());
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
			
			PedidoProdutoId other = (PedidoProdutoId) obj;
			
			if (pedidoId == null) {
				if (other.pedidoId != null)
					return false;
			} else if (!pedidoId.equals(other.pedidoId))
				return false;
			
			if (produtoId == null) {
				if (other.produtoId != null)
					return false;
			} else if (!produtoId.equals(other.produtoId))
				return false;
			
			return true;
		}
	}


	@EmbeddedId
	private PedidoProdutoId id;

	@ManyToOne
	@JoinColumn(name = "fk_pedido", insertable = false, updatable = false)
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "fk_produto", insertable = false, updatable = false)
	private Produto produto;

	@Column
	private Integer quantidade;
	
	public PedidoProduto(){
		
	}

	public PedidoProduto(Pedido pedido, Produto produto, Integer quantidade) {
		// create primary key
		this.id = new PedidoProdutoId(pedido.getId(), produto.getId());
		
		// initialize attributes
		this.pedido = pedido;
		this.produto = produto;
		this.quantidade = quantidade;
		
		// update relationships to assure referential integrity
		pedido.getProdutos().add(this);
		produto.getPedidos().add(this);
	}
	
}