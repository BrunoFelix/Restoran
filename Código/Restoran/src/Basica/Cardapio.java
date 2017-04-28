package Basica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cardapio {
	
	@Id
	@GeneratedValue
	@Column
	private int id_cardapio;
	
	
}