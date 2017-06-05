package Bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import Basica.Categoria;
import Basica.Mesa;
import Basica.Pedido;
import Fachada.Fachada;

@ManagedBean(name="PedidoBean")
@SessionScoped
public class PedidoBean {
	
	Fachada fachada = Fachada.getInstance();
	
	private static Pedido pedido;
	
	private List<Pedido> listarPedido = new ArrayList<Pedido>();
	
	private List<Mesa> listarMesa = new ArrayList<Mesa>();

	public List<Pedido> getListarPedido() {
		try{
		setListarPedido(fachada.PedidoListar());
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return listarPedido;
	}

	public void setListarPedido(List<Pedido> listarPedido) {
		this.listarPedido = listarPedido;
	}

	public List<Mesa> getListarMesa() {
		setListarMesa(fachada.MesaListar());
		return listarMesa;
	}

	public void setListarMesa(List<Mesa> listarMesa) {
		this.listarMesa = listarMesa;
	}
	
	
	

	
}
