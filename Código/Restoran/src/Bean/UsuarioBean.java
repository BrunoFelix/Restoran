package Bean;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import Basica.Produto;
import Basica.Usuario;
import Fachada.Fachada;

@ManagedBean(name = "UsuarioBean")
@SessionScoped
public class UsuarioBean {

	Fachada fachada = Fachada.getInstance();

	private static Usuario usuario;
	
	private Usuario usuarioAlterar;

	private List<Usuario> listarUsuario = new ArrayList<Usuario>();
	
	private String login;
	private String senha;

	private String nome;
	private String email;
	private String cpf;
	private String sexo;
	private String tipo;
	private String telefone;

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

	public Usuario getUsuarioAlterar() {
		return usuarioAlterar;
	}

	public void setUsuarioAlterar(Usuario usuarioAlterar) {
		this.usuarioAlterar = usuarioAlterar;
	}

	public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		UsuarioBean.usuario = usuario;
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
				FacesContext.getCurrentInstance().addMessage("msgErro", new FacesMessage("Usuário ou senha inválido, tente novamente!"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("msgErro", new FacesMessage(e.getMessage()));
		}
		return null;
	}

	public String cadastrar() {
		System.out.println("Entrou alterar");
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
			
			nome="";
			login="";
			senha="";
			email="";
			cpf="";
			telefone="";
			sexo="";
			tipo="";
			
			return "listar";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}

	public String getLogout() throws IOException {
		UsuarioBean.usuario = null;
		FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		return null;
	}

	public String getUsuarioLogado() {
		if ((usuario != null)) {
			return usuario.getNome() + " (" + usuario.getLogin() + ")" + " - Tipo: " + usuario.getTipo();
		} else {
			return "login";
		}
	}
	
	public boolean getUsuarioGerente(){
		if (usuario != null && usuario.getTipo().equals("Gerente")){
			return true;
		}
		return false;
	}
	
	public boolean getUsuarioGarcom(){
		if (usuario != null && usuario.getTipo().equals("Garçom")){
			return true;
		}
		return false;
	}
	
	public boolean getUsuarioCozinheiro(){
		if (usuario != null && usuario.getTipo().equals("Cozinheiro")){
			return true;
		}
		return false;
	}
	
	public String chamadaAlterar(Integer id) {
		usuarioAlterar = fachada.UsuarioBuscarPorId(id);
        return "alterar";
    }
	
	public String alterar() {
		try {
			fachada.UsuarioAlterar(usuarioAlterar);
			return "listar";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
    }
	
	public void excluir(Usuario u){
		try{
			fachada.UsuarioExcluir(u);
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
	}
	
	/*public boolean temPermissao (List<String> permissoes){
		
		for (String permissao :permissoes){
			
			if(usuario.getTipo() ==permissao){
		return true;
			
			}
		}
		
		return false;
	}*/
}