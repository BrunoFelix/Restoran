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
@Table(name = "produto_item")
@AssociationOverrides({
	@AssociationOverride(name = "id_produto",
		joinColumns = @JoinColumn(name = "id_produto")),
	@AssociationOverride(name = "id_item",
		joinColumns = @JoinColumn(name = "id_item")) })
public class Produto_item implements Serializable {
	
	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_produto", nullable = false, insertable = false,
	updatable = false)
	public Produto produto;

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_item", nullable = false, insertable = false,
	updatable = false)
	public ItemComposicaoProduto itemcomposicaoproduto;

	@Column(name = "quantidade")
	private int quantidade;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
