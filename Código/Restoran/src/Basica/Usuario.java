package Basica;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {
	@Id @GeneratedValue
	private int id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String login;
	@Column(nullable = false)
	private String senha;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String cpf;
	@Column(nullable = false)
	private String sexo;
	@Column(nullable = false)
	private String tipo;
	@Column(nullable = false)
	private long telefone;
	@Column(nullable = false)
	private Date dataNas;
}