package Basica;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Mesa {
	
	@Id
	@GeneratedValue
	@Column(name="id_mesa")
	private int id;
	
	@Column(nullable=false)
	private int numeroMesa;
	
	@Column(nullable=false)
	private int CapacidadeMesa;
	
	@Column(length=10, nullable=false)
	private String status;
	
	@OneToMany(mappedBy="mesa", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private Collection<Pedido> pedidos;
	
	
	
	
	//Gets & Sets
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(int numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public int getCapacidadeMesa() {
		return CapacidadeMesa;
	}

	public void setCapacidadeMesa(int capacidadeMesa) {
		CapacidadeMesa = capacidadeMesa;
	}

	public Collection<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Collection<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	
	
}