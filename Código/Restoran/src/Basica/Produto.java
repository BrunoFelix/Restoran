package Basica;


import java.util.List;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Produto implements Serializable {

	@Id @GeneratedValue
	private Long id;
	
	@Column(length=50, nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private double precoVenda;
	
	@Column(nullable = false)
	private double precoCusto;
	
	@Column(nullable = false)
	private int quantidade;
		
	@OneToMany(cascade=javax.persistence.CascadeType.PERSIST, mappedBy = "produto")
	public List<Pedido_produto> pedido_produto;
	
	@OneToMany(mappedBy = "produto")
    private List<ProdutoItem> itensComp;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_categoria", insertable=true, updatable=true) //Chave Estrangeira
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Categoria categoria;

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
	
	public List<ProdutoItem> getItensComp() {
		return itensComp;
	}


	public void setItensComp(List<ProdutoItem> itensComp) {
		this.itensComp = itensComp;
	}


	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Produto other = (Produto) obj;
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
