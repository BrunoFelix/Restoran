package Basica;

import java.sql.Date;
import java.util.Collection;

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
	@Column()
	private int id;
	
	@Column(nullable=false)
	private double totalPedido;
	
	@Column(nullable=false)
	private int qtdProdutos;
	
	@Column(nullable=false)
	private Date data;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_usuario", insertable=true, updatable=true) //Chave Estrangeira
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Usuario garcom;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="pedido_produto", joinColumns=@JoinColumn(name="id_pedido"), inverseJoinColumns=@JoinColumn(name="id_produto"))
	private Collection<Produto> produtos;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_mesa", insertable=true, updatable=true) //Chave Estrangeira
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Mesa mesa;
	
	@OneToOne(mappedBy="pedido", targetEntity = Historico.class, fetch = FetchType.LAZY)
	@JoinColumn(name="id_historico")
	private Historico historico;
	
	//Gets & Sets
	public int getId_pedido() {
		return id;
	}

	public void setId_pedido(int id_pedido) {
		this.id = id_pedido;
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

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	
}