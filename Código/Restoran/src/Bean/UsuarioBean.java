package Bean;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import Basica.Usuario;
import Fachada.Fachada;

@ManagedBean(name = "UsuarioBean")
@SessionScoped
public class UsuarioBean {

	FacesMessage msg;

	Fachada fachada = Fachada.getInstance();

	private static Usuario usuario;

	private List<Usuario> listarUsuario = new ArrayList<Usuario>();

	private String login;
	private String senha;

	private String nome;
	private String email;
	private String cpf;
	private String sexo;
	private String tipo;
	private String telefone;
	private Date dataNas;

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

	public List<Usuario> getListarUsuario() {
		setListarUsuario(fachada.UsuarioListar());
		return listarUsuario;
	}

	public void setListarUsuario(List<Usuario> listarUsuario) {
		this.listarUsuario = listarUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String index() {
		return "usuario";
	}

	public String logar() {

		try {
			usuario = new Usuario();
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario = fachada.Usuariologar(usuario);

			if (usuario != null) {
				return "index";
			} else {
				msg = new FacesMessage("Usuário ou senha inválido, tente novamente!");
				FacesContext.getCurrentInstance().addMessage("msgErro", msg);
			}
		} catch (Exception e) {
			msg = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage("msgErro", msg);
		}
		return null;
	}

	public String inserir() {
		Usuario usuarioinserir = new Usuario();
		usuarioinserir.setNome(nome);
		usuarioinserir.setLogin(login);
		usuarioinserir.setSenha(senha);
		usuarioinserir.setEmail(email);
		usuarioinserir.setCpf(cpf);
		usuarioinserir.setTelefone(telefone);
		usuarioinserir.setSexo(sexo);
		usuarioinserir.setTipo(tipo);

		try {
			fachada.UsuarioInserir(usuarioinserir);
			return "usuario";
		} catch (Exception e) {
			msg = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage("msgErro", msg);
		}
		return null;
	}

	public String logout() {
		UsuarioBean.usuario = null;
		return "login";

	}

	public String getLogout() {
		return logout();
	}

	public String getUsuarioGerente() {
		if ((usuario != null) && (usuario.getTipo().equals("Gerente"))) {
			return usuario.getNome() + " (" + usuario.getLogin() + ")" + " - Tipo: " + usuario.getTipo();
		} else {
			return "login";
		}
	}

	public String getUsuarioNormal() {
		if (usuario != null) {
			return usuario.getNome() + " (" + usuario.getLogin() + ")" + " - Tipo: " + usuario.getTipo();
		} else {
			return "login";
		}
	}

}
