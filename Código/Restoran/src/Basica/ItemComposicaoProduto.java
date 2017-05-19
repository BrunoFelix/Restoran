package Basica;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	
	//Gets & Sets
	public int getId_itemComposicaoProduto() {
		return id;
	}

	public void setId_itemComposicaoProduto(int id_itemComposicaoProduto) {
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

	public void setPrecoCusto(int precoCusto) {
		this.precoCusto = precoCusto;
	}
	
}
