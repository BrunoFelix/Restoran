package Bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="ItemComposicaoProdutoBean")
public class ItemComposicaoProdutoBean {

	public void index() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().redirect("itemComposicaoProduto.xhtml");
	}
}
