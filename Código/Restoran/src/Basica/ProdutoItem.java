package Basica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ProdutoItem")
@IdClass(ProdutoItemId.class)
public class ProdutoItem {

    @Id
    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    @Id
    @ManyToOne
    @JoinColumn(name = "itemcomposicaoproduto_id", referencedColumnName = "id")
    private ItemComposicaoProduto itemcomposicaoproduto;

    @Column(name = "quantidade")
    private int quantidade;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto employer) {
		this.produto = employer;
	}

	public ItemComposicaoProduto getItemcomposicaoproduto() {
		return itemcomposicaoproduto;
	}

	public void setItemcomposicaoproduto(ItemComposicaoProduto itemcomposicaoproduto) {
		this.itemcomposicaoproduto = itemcomposicaoproduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}