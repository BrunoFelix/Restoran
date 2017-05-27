package Bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="MesaBean")
public class MesaBean {

	public void index() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().redirect("mesa.xhtml");
	}
}
