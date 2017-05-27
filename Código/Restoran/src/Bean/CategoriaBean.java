package Bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="CategoriaBean")
public class CategoriaBean {

	public void index() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().redirect("categoria.xhtml");
	}
}
