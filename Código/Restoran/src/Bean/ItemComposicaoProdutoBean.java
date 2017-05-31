package Bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import Basica.ItemComposicaoProduto;
import Basica.Usuario;
import Fachada.Fachada;

@ManagedBean(name="ItemComposicaoProdutoBean")
@SessionScoped
public class ItemComposicaoProdutoBean {

	FacesMessage msg;

	Fachada fachada = Fachada.getInstance();
	
	private ItemComposicaoProduto itemComposicaoProdutoAlterar;

	private List<ItemComposicaoProduto> listarItemComposicaoProduto = new ArrayList<ItemComposicaoProduto>();
	
	private int id;
	private String nome;
	private int quantidade;
	private String unidadeMedida;
	private double precoCusto;
	
	
	public void setListarItemComposicaoProduto(List<ItemComposicaoProduto> listarItemComposicaoProduto) {
		this.listarItemComposicaoProduto = listarItemComposicaoProduto;
	}
	
	public List<ItemComposicaoProduto> getListarItemComposicaoProduto() {
		setListarItemComposicaoProduto(fachada.ItemComposicaoProdutoListar());
		return listarItemComposicaoProduto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public ItemComposicaoProduto getItemComposicaoProdutoAlterar() {
		return itemComposicaoProdutoAlterar;
	}

	public void setItemComposicaoProdutoAlterar(ItemComposicaoProduto itemComposicaoProdutoAlterar) {
		this.itemComposicaoProdutoAlterar = itemComposicaoProdutoAlterar;
	}

	public String index() {
		return "itemcomposicaoproduto/listar";
	}
	
	public String cadastrar() {
		ItemComposicaoProduto itemComposicaoProdutoInserir = new ItemComposicaoProduto();
		itemComposicaoProdutoInserir.setNome(nome);
		itemComposicaoProdutoInserir.setQuantidade(quantidade);
		itemComposicaoProdutoInserir.setUnidadeMedida(unidadeMedida);
		itemComposicaoProdutoInserir.setPrecoCusto(precoCusto);

		try {
			fachada.ItemComposicaoProdutoInserir(itemComposicaoProdutoInserir);
			return "listar";
		} catch (Exception e) {
			msg = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage("msgErro", msg);
		}
		return null;
	}

	public String chamadaAlterar(Integer id) {
		itemComposicaoProdutoAlterar = fachada.ItemComposicaoProdutoBuscarPorId(id);
        return "alterar";
    }
	
	public String alterar() {
		try {
			fachada.ItemComposicaoProdutoAlterar(itemComposicaoProdutoAlterar);
			return "listar";
		} catch (Exception e) {
			msg = new FacesMessage(e.getMessage());
			FacesContext.getCurrentInstance().addMessage("msgErro", msg);
		}
		return null;
    }
}
