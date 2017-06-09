package Basica;

import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Pedido{
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private double totalPedido;
	
	@Column(nullable=false)
	private int qtdProdutos;
	
	@Column(nullable=false)
	private Date data;
	
	@Column(length = 10, nullable=false)
	private String status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_usuario", insertable=true, updatable=true) //Chave Estrangeira
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Usuario garcom;
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
	private Set<PedidoProduto> produtos = new HashSet<PedidoProduto>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_mesa", insertable=true, updatable=true) //Chave Estrangeira
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Mesa mesa;
	
	//Gets & Sets
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Usuario getGarcom() {
		return garcom;
	}

	public void setGarcom(Usuario garcom) {
		this.garcom = garcom;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	public Set<PedidoProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<PedidoProduto> produtos) {
		this.produtos = produtos;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}