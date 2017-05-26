package Bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import Basica.Usuario;
import Fachada.Fachada; 

@ManagedBean(name="UsuarioBean")
@SessionScoped
public class UsuarioBean {
		
	
	
	/*public UsuarioBean(){
		fachada.getInstance();
	}*/
	
	private static Usuario usuario;
	
	private Boolean usuarioLogado;
	
	private Usuario usuarioparalistar;
	private List<Usuario> listarUsuario = new ArrayList<Usuario>();
	
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

	FacesMessage msg;
	
	public String logar(){
		
		try{

			Fachada fachada = new Fachada();
				
			usuario = new Usuario();
			
			usuario = fachada.Usuariologar(getLogin(), getSenha());
				
			if (usuario != null){
				usuarioLogado = true;
				return "index";	
			}else{
				msg = new FacesMessage("Usuário ou senha inválido, tente novamente!");
				FacesContext.getCurrentInstance().addMessage("msgErro", msg);	
			}
		}catch (Exception e) {
			msg = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage("msgErro", msg);	
		}
		return null;
	}
	
	public String logout()
    {
        this.usuario = null;
        return "login";

    }
		
	public String getUsuarioGerente()
    {
		if ((usuario != null) && (usuario.getTipo().equals("Gerente"))){
	        return usuario.getNome() + "(" + usuario.getLogin() + ")" + " - Tipo: " + usuario.getTipo();
		}else{
			return "login";
		}
    }
	
	public String getUsuarioNormal()
    {
		if (usuario != null){
            return usuario.getNome() + "(" + usuario.getLogin() + ")" + " - Tipo: " + usuario.getTipo();
    	}else{
    		return "login";
    	}
    }
	
	public List<Usuario> getlistarUsuario() {
		Fachada fachada = new Fachada();
		listarUsuario = fachada.UsuarioListar();
		return listarUsuario;
	}
	
}
