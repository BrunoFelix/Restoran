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
public class Categoria {
	
	@Id @GeneratedValue
	private int id;
	
	@Column(length=50, nullable = false)
	private String nome;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="produto_categoria", joinColumns=@JoinColumn(name="id_categoria"), inverseJoinColumns=@JoinColumn(name="id_produto"))
	private Collection<Produto> produtos;
	
	//Gets & Sets
	public int getId() {
		return id;
	}
	
	public void setId(int id_categoria) {
		this.id = id_categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Collection<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Collection<Produto> produtos) {
		this.produtos = produtos;
	}
  
}
