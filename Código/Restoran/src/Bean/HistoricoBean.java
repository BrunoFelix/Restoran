package Bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="HistoricoBean")
public class HistoricoBean {

	public void index() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().redirect("historico.xhtml");
	}
}
