package Basica;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;;


@SuppressWarnings("serial")
@Entity
@Table(name = "pedido_produto")
@AssociationOverrides({
	@AssociationOverride(name = "id_pedido",
		joinColumns = @JoinColumn(name = "id_pedido")),
	@AssociationOverride(name = "id_produto",
		joinColumns = @JoinColumn(name = "id_produto")) })
public class Pedido_produto implements Serializable {
	
	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pedido", nullable = false, insertable = false,
	updatable = false)
	public Pedido pedido;

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_produto", nullable = false, insertable = false,
	updatable = false)
	public Produto produto;

	@Column(name = "quantidade")
	private int quantidade;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
