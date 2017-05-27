package Basica;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Historico {
	
	@Id @GeneratedValue
	private int id;
	
	@Column(length=15, nullable = false)
	private String statusAntigo;
	
	@Column(length=15, nullable = false)
	private String statusNovo;
	
	@OneToOne(targetEntity = Pedido.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="id_pedido")
	private Pedido pedido;
	
	//Gets & Sets
	public int getId_historico() {
		return id;
	}

	public void setId_historico(int id_historico) {
		this.id = id_historico;
	}

	public String getStatusAntigo() {
		return statusAntigo;
	}

	public void setStatusAntigo(String statusAntigo) {
		this.statusAntigo = statusAntigo;
	}

	public String getStatusNovo() {
		return statusNovo;
	}

	public void setStatusNovo(String statusNovo) {
		this.statusNovo = statusNovo;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
}
