package Bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Basica.Mesa;
import Fachada.Fachada;

@ManagedBean(name="MesaBean")
@SessionScoped
public class MesaBean {

	Fachada fachada = Fachada.getInstance();

	private Mesa mesaAlterar;

	private List<Mesa> listarMesa = new ArrayList<Mesa>();
	
	private int id;
	private int numeroMesa;
	private int capacidadeMesa;
	private String status;
	
	public Mesa getMesaAlterar() {
		return mesaAlterar;
	}

	public void setMesaAlterar(Mesa mesaAlterar) {
		this.mesaAlterar = mesaAlterar;
	}

	public List<Mesa> getListarMesa() {
		setListarMesa(fachada.MesaListar());
		return listarMesa;
	}

	public void setListarMesa(List<Mesa> listarMesa) {
		this.listarMesa = listarMesa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(int numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public int getCapacidadeMesa() {
		return capacidadeMesa;
	}

	public void setCapacidadeMesa(int capacidadeMesa) {
		this.capacidadeMesa = capacidadeMesa;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String index(){
		return "listar";
	}

	public String cadastrar() {
		Mesa mesainserir = new Mesa();
		mesainserir.setNumeroMesa(numeroMesa);
		mesainserir.setCapacidadeMesa(capacidadeMesa);

		try {
			fachada.MesaInserir(mesainserir);
			
			numeroMesa = 0;
			capacidadeMesa = 0;
			
			return "listar";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}
	
	public String chamadaAlterar(Integer id) {
		mesaAlterar = fachada.MesaBuscarPorId((long) id);
        return "alterar";
    }

	public String alterar() {	
		try {
			fachada.MesaAlterar(mesaAlterar);
			return "listar";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
    }
}