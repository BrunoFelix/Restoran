package Basica;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Usuario {
	
	@Id @GeneratedValue
	private int id_Usuario;
	
	@Column(length=50, nullable = false)
	private String nome;
	
	@Column(length=15, nullable = false)
	private String login;
	
	@Column(length=6, nullable = false)
	private String senha;
	
	@Column(length=30, nullable = false)
	private String email;
	
	@Column(length=14, nullable = false)
	private String cpf;
	
	@Column(length=1, nullable = false)
	private String sexo;
	
	@Column(length=8, nullable = false)
	private String tipo;
	
	@Column(length=10, nullable = false)
	private String telefone;
	
	@Column(nullable = false)
	private Date dataNas;
	
	@OneToMany(mappedBy="garcom", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private Collection<Pedido> pedidos;
	
	//Gets & Sets
	public int getId_usuario() {
		return id_Usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_Usuario = id_usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNas() {
		return dataNas;
	}

	public void setDataNas(Date dataNas) {
		this.dataNas = dataNas;
	}

	public Collection<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
		
	
	
	
}