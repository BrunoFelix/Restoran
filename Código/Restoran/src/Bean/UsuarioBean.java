package Bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import Basica.Usuario;
import Fachada.Fachada; 

@ManagedBean(name="UsuarioBean")
public class UsuarioBean {
		
	
	
	/*public UsuarioBean(){
		fachada.getInstance();
	}*/
	
	private static Usuario usuario;
	
	private Boolean usuarioLogado;
	
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

	public void logar(){
		
		try{

			Fachada fachada = new Fachada();
				
			usuario = new Usuario();
			
			usuario = fachada.Usuariologar(getLogin(), getSenha());
				
			if (usuario != null){
				/*HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true );
				session.setAttribute( "nome", usuario.getNome());*/
				usuarioLogado = true;
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");		
			}else{
				msg = new FacesMessage("Usuário ou senha inválido, tente novamente!");
				FacesContext.getCurrentInstance().addMessage("msgErro", msg);	
			}
			
		}catch (Exception e) {
			msg = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage("msgErro", msg);	
		}
	}
	
	public void logout()
    {
        this.usuario = null;
        getNomeUsuario();
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
	
	public String getNomeUsuario()
    {
		if (usuario != null){
            return usuario.getNome() + "(" + usuario.getLogin() + ")" + " - Tipo: " + usuario.getTipo();
    	}else{
    		try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return e.getMessage();
			}
    	}
		return null;

    }
	
    public Usuario getUser() {
        return usuario;
    }
    public void setUser(Usuario usuario) {
        this.usuario = usuario;
    }

}
