package Bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="PedidoBean")
public class PedidoBean {

	public void index() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().redirect("pedido.xhtml");
	}
}
