package Bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import Basica.Categoria;
import Basica.ItemComposicaoProduto;
import Basica.Pedido;
import Basica.Produto;
import Basica.ProdutoItem;
import Fachada.Fachada;
import Utils.NegocioException;

@ManagedBean(name="ProdutoBean")
@SessionScoped
public class ProdutoBean{
	
	Fachada fachada = Fachada.getInstance();
	
	private static Produto produto;
	
	private Produto produtoAlterar;

	private List<ItemComposicaoProduto> listarItemComposicaoProduto = new ArrayList<ItemComposicaoProduto>();
	private List<ItemComposicaoProduto> listaDeItensDeComposicaoJaAdicionados = new ArrayList<ItemComposicaoProduto>();
	private List<Produto> listarProduto = new ArrayList<Produto>();
	private List<Categoria> listarCategoria = new ArrayList<Categoria>();
	private int id;
	private String nome;
	private double precoVenda;
	private double precoCusto;
	private int quantidade;
	private Categoria categoria;
	private int qtdItemComposicaoProduto;
	private ItemComposicaoProduto inserirItemComposicaoProduto;
	private int idItemComp;
	

	public Produto getProdutoAlterar() {
		return produtoAlterar;
	}

	public void setProdutoAlterar(Produto produtoAlterar) {
		this.produtoAlterar = produtoAlterar;
	}
	

	public List<Produto> getListarProduto() {
		setListarProduto(fachada.ProdutoListar());
		return listarProduto;
	}

	public void setListarProduto(List<Produto> listarProduto) {
		this.listarProduto = listarProduto;
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

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public List<Categoria> getListarCategoria() {
		setListarCategoria(fachada.CategoriaListar());
		return listarCategoria;
	}

	public void setListarCategoria(List<Categoria> listarCategoria) {
		this.listarCategoria = listarCategoria;
	}

	public List<ItemComposicaoProduto> getListarItemComposicaoProduto() {
		setListarItemComposicaoProduto(fachada.ItemComposicaoProdutoListar());
		return listarItemComposicaoProduto;
	}

	public void setListarItemComposicaoProduto(List<ItemComposicaoProduto> listarItemComposicaoProduto) {
		this.listarItemComposicaoProduto = listarItemComposicaoProduto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getQtdItemComposicaoProduto() {
		return qtdItemComposicaoProduto;
	}

	public void setQtdItemComposicaoProduto(int qtdItemComposicaoProduto) {
		this.qtdItemComposicaoProduto = qtdItemComposicaoProduto;
	}

	public ItemComposicaoProduto getInserirItemComposicaoProduto() {
		return inserirItemComposicaoProduto;
	}

	public void setInserirItemComposicaoProduto(ItemComposicaoProduto inserirItemComposicaoProduto) {
		this.inserirItemComposicaoProduto = inserirItemComposicaoProduto;
	}

	public List<ItemComposicaoProduto> getListaDeItensDeComposicaoJaAdicionados() {
		return listaDeItensDeComposicaoJaAdicionados;
	}

	public void setListaDeItensDeComposicaoJaAdicionados(List<ItemComposicaoProduto> listaDeItensDeComposicaoJaAdicionados) {
		this.listaDeItensDeComposicaoJaAdicionados = listaDeItensDeComposicaoJaAdicionados;
	}

	public int getIdItemComp() {
		return idItemComp;
	}

	public void setIdItemComp(int idItemComp) {
		this.idItemComp = idItemComp;
	}

	public String index(){
		return "listar";
	}

	public void adicionarItemComp(){
		try{
			if (qtdItemComposicaoProduto > 0){
			boolean achou = false;
			
				for (int i = 0; i < listaDeItensDeComposicaoJaAdicionados.size(); i++) {
					if (listaDeItensDeComposicaoJaAdicionados.get(i).getId() == idItemComp){
						listaDeItensDeComposicaoJaAdicionados.get(i).setQuantidade(listaDeItensDeComposicaoJaAdicionados.get(i).getQuantidade() + qtdItemComposicaoProduto);
						achou = true;
					}
				}
				
				if(!achou){
					ItemComposicaoProduto item = fachada.ItemComposicaoProdutoBuscarPorId((long) idItemComp);
					item.setQuantidade(qtdItemComposicaoProduto);
					listaDeItensDeComposicaoJaAdicionados.add(item);
					
				}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Como o procedimento já havia sido adicionado, foi aumentada a quantidade!"));
				}	
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Quantidade inválida"));
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Id inválido/inexistente"));
		}
		
	}
	
	public void removerItemComp(int id){
		for (int i = 0; i < listaDeItensDeComposicaoJaAdicionados.size(); i++) {
			if (listaDeItensDeComposicaoJaAdicionados.get(i).getId() == id){
				listaDeItensDeComposicaoJaAdicionados.remove(i);
			}
		}
	}
	
	public void diminuirItemComp(int id){
		for (int i = 0; i < listaDeItensDeComposicaoJaAdicionados.size(); i++) {
			if (listaDeItensDeComposicaoJaAdicionados.get(i).getId() == id){
				listaDeItensDeComposicaoJaAdicionados.get(i).setQuantidade(listaDeItensDeComposicaoJaAdicionados.get(i).getQuantidade() - 1);
			}
		}
	}
	
	public String cadastrar() {
		Produto produtoinserir = new Produto();
		produtoinserir.setNome(nome);
		produtoinserir.setQuantidade(quantidade);
		produtoinserir.setPrecoVenda(precoVenda);
		produtoinserir.setPrecoCusto(precoCusto);
		produtoinserir.setCategoria(categoria);		
		
		try {
			fachada.ProdutoInserir(produtoinserir);
			
			List<ProdutoItem> pi = new ArrayList<ProdutoItem>();
			
			Iterator<ItemComposicaoProduto> itemCompAsIterator = listaDeItensDeComposicaoJaAdicionados.iterator();
	        while (itemCompAsIterator.hasNext()){
	        	ItemComposicaoProduto it = itemCompAsIterator.next();
	        	ProdutoItem produtoitem = new ProdutoItem(produtoinserir,it,it.getQuantidade());
	        	pi.add(produtoitem);
	        }
	        
			fachada.ProdutoInserirVinculoProdutoItemComp(pi);
							
			nome = "";
			quantidade = 0;
			precoCusto = 0;
			precoVenda = 0;
			categoria = null;
			
			return "listar";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}
	
	public String chamadaAlterar(Integer id) {
		produtoAlterar = fachada.ProdutoBuscarPorId((long) id);
		
		for (ProdutoItem s : produtoAlterar.getItemComps()) {
			s.getItemComp().setQuantidade(s.getQuantidade());
			listaDeItensDeComposicaoJaAdicionados.add(s.getItemComp());
		}
        return "alterar";
    }
	
	public String alterar() {
		try {
			fachada.ProdutoAlterar(produtoAlterar);
			
			
			List<ProdutoItem> pi = new ArrayList<ProdutoItem>();
			
			Iterator<ItemComposicaoProduto> itemCompAsIterator = listaDeItensDeComposicaoJaAdicionados.iterator();
	        while (itemCompAsIterator.hasNext()){
	        	ItemComposicaoProduto it = itemCompAsIterator.next();
	        	ProdutoItem produtoitem = new ProdutoItem(produtoAlterar,it,it.getQuantidade());
	        	pi.add(produtoitem);
	        }
	        
	        fachada.ProdutoAlterarVinculoProdutoItemComp(pi);
			
			return "listar";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
    }
}
