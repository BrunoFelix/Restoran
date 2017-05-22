package Bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="ProdutoBean")
public class ProdutoBean {

	public void index() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().redirect("produto.jsf");
	}
}
