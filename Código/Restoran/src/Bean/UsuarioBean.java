package Bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Basica.Usuario;
import Fachada.Fachada; 

@ManagedBean(name="UsuarioBean")
public class UsuarioBean {
		
	
	
	/*public UsuarioBean(){
		fachada.getInstance();
	}*/
	
	private String login;
	private String senha;
	
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

	public String logar(){
		
		try{

		Fachada fachada = new Fachada();
			
		Usuario usuario = new Usuario();
		
		usuario = fachada.Usuariologar(getLogin(), getSenha());
		
		
		if (usuario.getId_usuario() > 0){
			System.out.println("Encontrou usuário");
		}else{
			System.out.println("Usuário inválido");
		}
		
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
		//System.out.println("teste");
		//return "/menu.faces?faces-redirect=true";  
		/*FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		return null;*/
	}

}
