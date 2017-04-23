package Basica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id @GeneratedValue
	private int id;
	private String descricao;
	private double valor;
	private double valor_custo;
	private int quantidade;
}
