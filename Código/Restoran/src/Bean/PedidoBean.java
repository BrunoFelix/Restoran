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
	
	private Pedido pedidoAlterar;
	
	private List<Pedido> listarPedido = new ArrayList<Pedido>();
	
	private List<Mesa> listarMesa = new ArrayList<Mesa>();
	
	private List<Produto> listarProduto = new ArrayList<Produto>();
	
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

	public String chamadaCadastrar(Integer id) throws NegocioException, DadosException{
		this.mesa = fachada.MesaBuscarPorId((long) id);
		this.usuario = UsuarioBean.getUsuario();
		
		Pedido pedido = new Pedido();
		pedido.setGarcom(usuario);
		pedido.setMesa(mesa);
		Date data = new Date();
		pedido.setData(data);
		this.Status = "Aberto";
		
		fachada.PedidoInserir(pedido);
		
		return "cadastrar";
	}
	
	public String cadastrar(Integer id) {
		
		try{
			System.out.println("vai pesquisar pedidos da mesa: " + id);
			Pedido pedidoInserir = fachada.PedidoPesquisarPorMesa((long) id);
			System.out.println("passou");
			List<PedidoProduto> pp = new ArrayList<PedidoProduto>();
			
			Iterator<Produto> ProdutoAsIterator = listaProdutosJaAdicionados.iterator();
	        while (ProdutoAsIterator.hasNext()){
	        	Produto it = ProdutoAsIterator.next();
	        	PedidoProduto produtoitem = new PedidoProduto(pedidoInserir,it,it.getQuantidade(), it.getStatus());
	        	pp.add(produtoitem);
	        }
	        
        
        fachada.PedidoInserirVinculoProduto(pp);
        
        return "mesas";
        } catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
        
	}

	
	public String chamadaAlterar(Integer id) throws NegocioException, DadosException{
		pedidoAlterar = fachada.PedidoPesquisarPorMesa((long) id);
		
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
	

	
}
