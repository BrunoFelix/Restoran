package Basica;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Pedido{
	
	@Id @GeneratedValue
	@Column()
	private int id_pedido;
	
	@Column(nullable=false)
	private double totalPedido;
	
	@Column(nullable=false)
	private int qtdProdutos;
	
	@Column(nullable=false)
	private Date data;
	
	@Column(nullable=false)
	private Usuario garçom;
	
	@OneToMany(mappedBy="pedido", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private Collection<Produto> produtos;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_mesa", insertable=true, updatable=true) //Chave Estrangeira
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Mesa mesa;
	
	
	
	
	//Gets & Sets
	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public double getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(double totalPedido) {
		this.totalPedido = totalPedido;
	}

	public int getQtdProdutos() {
		return qtdProdutos;
	}

	public void setQtdProdutos(int qtdProdutos) {
		this.qtdProdutos = qtdProdutos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getGarçom() {
		return garçom;
	}

	public void setGarçom(Usuario garçom) {
		this.garçom = garçom;
	}

	public Collection<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Collection<Produto> produtos) {
		this.produtos = produtos;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	

	
	
}