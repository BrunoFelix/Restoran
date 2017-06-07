package Bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Basica.Categoria;
import Basica.Usuario;
import Fachada.Fachada;

@ManagedBean(name="CategoriaBean")
@SessionScoped
public class CategoriaBean {

	Fachada fachada = Fachada.getInstance();
	
	private Categoria categoriaAlterar;

	private List<Categoria> listarCategoria = new ArrayList<Categoria>();

	private Long id;
	private String nome;
	
	public Categoria getCategoriaAlterar() {
		return categoriaAlterar;
	}

	public void setCategoriaAlterar(Categoria categoriaAlterar) {
		this.categoriaAlterar = categoriaAlterar;
	}

	public List<Categoria> getListarCategoria() {
		setListarCategoria(fachada.CategoriaListar());
		return listarCategoria;
	}

	public void setListarCategoria(List<Categoria> listaCategoria) {
		this.listarCategoria = listaCategoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String index(){
		return "listar";
	}
	
	public String cadastrar() {
		Categoria categoriainserir = new Categoria();
		categoriainserir.setNome(nome);

		try {
			fachada.CategoriaInserir(categoriainserir);
			
			nome="";
			
			return "listar";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}
	
	public String chamadaAlterar(Integer id) {
		categoriaAlterar = fachada.CategoriaBuscarPorId((long) id);
        return "alterar";
    }
	
	public String alterar() {

		try {
			fachada.CategoriaAlterar(categoriaAlterar);
			return "listar";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
    }
	
	public void excluir(Categoria c){
		try{
			fachada.CategoriaExcluir(c);
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
	}

}
