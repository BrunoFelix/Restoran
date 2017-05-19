package Basica;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Historico {
	
	@Id @GeneratedValue
	private int id;
	
	@Column(length=15, nullable = false)
	private String statusAntigo;
	
	@Column(length=15, nullable = false)
	private String statusNovo;
	
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
	
	
}
