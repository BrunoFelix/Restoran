package Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Basica.ItemComposicaoProduto;
import Basica.Mesa;
import Basica.Pedido;
import Basica.PedidoProduto;
import Basica.Produto;
import Basica.ProdutoItem;
import Basica.Usuario;
import Fachada.Fachada;
import Utils.DadosException;
import Utils.NegocioException;

@ManagedBean(name="PedidoBean")
@SessionScoped
public class PedidoBean {
	
	Fachada fachada = Fachada.getInstance();
	
	private static Pedido pedido;
	
	private static Pedido pedidoAlterar;
	
	private List<Pedido> listarPedido = new ArrayList<Pedido>();
	
	private List<Mesa> listarMesa = new ArrayList<Mesa>();
	
	private List<Produto> listarProduto = new ArrayList<Produto>();
	
	private List<PedidoProduto> listarPedidoProduto = new ArrayList<PedidoProduto>();
	
	private String NumeroMesa;
	private String nomeGarcom;
	private String Status;
	
	private Mesa mesa;
	private Usuario usuario;
	private List<Produto> listaProdutosJaAdicionados = new ArrayList<Produto>();
	
	private int qtdProduto;
	private int idProduto;
	
	private double totalPedido;
	private int qtdProdutosAdicionados;

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

	public List<Produto> getListarProduto() {
		setListarProduto(fachada.ProdutoListar());
		return listarProduto;
	}

	public void setListarProduto(List<Produto> listarProduto) {
		this.listarProduto = listarProduto;
	}
	
	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Produto> getListaProdutosJaAdicionados() {
		return listaProdutosJaAdicionados;
	}

	public void setListaProdutosJaAdicionados(List<Produto> listaProdutosJaAdicionados) {
		this.listaProdutosJaAdicionados = listaProdutosJaAdicionados;
	}

	public Pedido getPedidoAlterar() {
		return pedidoAlterar;
	}

	public void setPedidoAlterar(Pedido pedidoAlterar) {
		this.pedidoAlterar = pedidoAlterar;
	}

	public int getQtdProduto() {
		return qtdProduto;
	}

	public void setQtdProduto(int qtdProduto) {
		this.qtdProduto = qtdProduto;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public double getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(double totalPedido) {
		this.totalPedido = totalPedido;
	}

	public int getQtdProdutosAdicionados() {
		return qtdProdutosAdicionados;
	}

	public void setQtdProdutosAdicionados(int qtdProdutosAdicionados) {
		this.qtdProdutosAdicionados = qtdProdutosAdicionados;
	}

	public List<PedidoProduto> getListarPedidoProduto() {
		try {
			setListarPedidoProduto(fachada.PedidoProdutoListar());
		} catch (DadosException e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return listarPedidoProduto;
	}

	public void setListarPedidoProduto(List<PedidoProduto> listarPedidoProduto) {
		this.listarPedidoProduto = listarPedidoProduto;
	}

	public String chamadaCadastrar(Integer id) throws NegocioException, DadosException{
		listaProdutosJaAdicionados = new ArrayList<Produto>();
		
		this.mesa = fachada.MesaBuscarPorId((long) id);
		this.usuario = UsuarioBean.getUsuario();
		
		Pedido pedido = new Pedido();
		pedido.setGarcom(usuario);
		pedido.setMesa(mesa);
		Date data = new Date();
		pedido.setData(data);
		this.Status = "Aguardando Produção";
		
		fachada.PedidoInserir(pedido);
		
		return "cadastrar";
	}
	
	public String cadastrar(Integer id) {
		
		try{
			Pedido pedidoInserir = fachada.PedidoPesquisarPorMesa((long) id);
			List<PedidoProduto> pp = new ArrayList<PedidoProduto>();
			
			double total = 0;
			
			Iterator<Produto> ProdutoAsIterator = listaProdutosJaAdicionados.iterator();
	        while (ProdutoAsIterator.hasNext()){
	        	Produto it = ProdutoAsIterator.next();
	        	PedidoProduto produtoitem = new PedidoProduto(pedidoInserir,it,it.getQuantidade(), it.getStatus());
	        	total += (it.getPrecoVenda() * it.getQuantidade());
	        	pp.add(produtoitem);
	        }
	        
	    pedidoInserir.setTotalPedido(total);
	    pedidoInserir.setQtdProdutos(listaProdutosJaAdicionados.size());
	    pedidoInserir.setProdutos(null);
        fachada.PedidoAlterar(pedidoInserir);
        fachada.PedidoInserirVinculoProduto(pp);
        
        listaProdutosJaAdicionados = new ArrayList<Produto>();
        
        return "mesas";
        } catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
        
	}
	
	public String alterar(Integer id) {
		try {
			List<PedidoProduto> pp = new ArrayList<PedidoProduto>();
			
			Iterator<Produto> itemCompAsIterator = listaProdutosJaAdicionados.iterator();
	        while (itemCompAsIterator.hasNext()){
	        	Produto it = itemCompAsIterator.next();
	        	PedidoProduto produtoitem = new PedidoProduto(pedidoAlterar,it,it.getQuantidade(), it.getStatus());
	        	pp.add(produtoitem);
	        }
	        
	        fachada.PedidoAlterarVinculoProduto(pp);
	        
	        /*pedidoAlterar.setTotalPedido(total);
		    pedidoAlterar.setQtdProdutos(listaProdutosJaAdicionados.size());
		    pedidoAlterar.setProdutos(null);*/
	        /*fachada.PedidoAlterar(pedidoAlterar);
	        fachada.PedidoAlterarVinculoProduto(pp);*/
			
			return "listar";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
        
	}

	
	public String chamadaAlterar(Integer id) throws NegocioException, DadosException{
		pedidoAlterar = fachada.PedidoPesquisarPorMesa((long) id);
		
		listaProdutosJaAdicionados = new ArrayList<Produto>();
		
		for (PedidoProduto s : pedidoAlterar.getProdutos()) {
			s.getProduto().setQuantidade(s.getQuantidade());
			s.getProduto().setStatus(s.getStatus());
			listaProdutosJaAdicionados.add(s.getProduto());
		}	
		
		return "alterar";
	}
	
	
	public void adicionarProduto(){
		try{
			if (qtdProduto > 0){
			boolean achou = false;
			
				for (int i = 0; i < listaProdutosJaAdicionados.size(); i++) {
					if (listaProdutosJaAdicionados.get(i).getId() == idProduto){
						listaProdutosJaAdicionados.get(i).setQuantidade(listaProdutosJaAdicionados.get(i).getQuantidade() + qtdProduto);
						achou = true;
					}
				}
				
				if(!achou){
					Produto item = fachada.ProdutoBuscarPorId((long) idProduto);
					item.setQuantidade(qtdProduto);
					item.setStatus("Aberto");
					listaProdutosJaAdicionados.add(item);
					
				}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Como o produto já havia sido adicionado, foi aumentada a quantidade!"));
				}	
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Quantidade inválida"));
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Id inválido/inexistente"));
		}
	}
	
	
	public void AlterarStatus(Integer idPedido, Integer idProduto, String status){
		try {
		System.out.println("Entrou no alterar");
		Pedido pedido = fachada.PedidoBuscarPorId((long) idPedido);
		System.out.println("Pesquisou pedido");
		Produto produto = fachada.ProdutoBuscarPorId((long) idProduto); 
		System.out.println("Pesquisou produto");
		
		PedidoProduto pedidoProduto = fachada.PesquisarPedidoProduto(pedido, produto);
		
		System.out.println("Pesquisou pedido produto");
	
		pedidoProduto.setStatus(status);
		
		System.out.println("Antes do alterar");
		fachada.AlterarVinculoPedidoProduto(pedidoProduto);
		System.out.println("Alterou!");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		
		
		
	}

	
}
