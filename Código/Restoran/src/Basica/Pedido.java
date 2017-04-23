package Basica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pedido{
	@Id @GeneratedValue
	private int id;
}