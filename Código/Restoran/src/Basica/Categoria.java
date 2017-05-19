package Basica;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Categoria {
	
	@Id @GeneratedValue
	private int id;
	
	@Column(length=50, nullable = false)
	private String nome;
	
	//Gets & Sets
	public int getId_categoria() {
		return id;
	}

	public void setId_categoria(int id_categoria) {
		this.id = id_categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
  
}
