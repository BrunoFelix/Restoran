package Basica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mesa {
	@Id @GeneratedValue
	private int id;
}