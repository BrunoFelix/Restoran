package Testes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Basica.Categoria;
import Basica.ItemComposicaoProduto;
import Basica.Pedido;
import Basica.PedidoProduto;
import Basica.Produto;
import Basica.ProdutoItem;
import Bean.UsuarioBean;
import Fachada.Fachada;
import Utils.DadosException;
import Utils.NegocioException;

public class Main {

	public static void main(String[] args) throws NegocioException, DadosException, ParseException {
		// TODO Auto-generated method stub
		/*UsuarioBean usuariobean = new UsuarioBean();
		System.out.println(usuariobean.getListarUsuario().size());*/		
		
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("projetorestoran");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		/*Produto b = new Produto();
		b.setNome("Produto1");
		b.setPrecoCusto(10);
		b.setPrecoVenda(10);
		b.setQuantidade(10);
		em.persist(b);

		ItemComposicaoProduto p = new ItemComposicaoProduto();
		p.setNome("ItemComp1");
		p.setQuantidade(10);
		p.setPrecoCusto(10);
		p.setUnidadeMedida("Cx");
		em.persist(p);

		ProdutoItem bp = new ProdutoItem(b, p, 10);
		em.persist(bp);*/
		
		Pedido p = new Pedido();
		p.setQtdProdutos(1);
		p.setTotalPedido(10);
		p.setData(new SimpleDateFormat( "dd/MM/yyyy" ).parse( "23/11/2015" ));
		em.persist(p);
		
		Produto f = new Produto();
		f.setNome("novo");
		f.setPrecoCusto(40);
		f.setPrecoVenda(30);
		f.setQuantidade(10);
		em.persist(f);

		PedidoProduto pb = new PedidoProduto(p, f, 2, "Aberto");
		em.persist(pb);
		
		em.getTransaction().commit();
		em.close();
		
		/*Fachada fachada = Fachada.getInstance();
		
		Produto produtoinserir = new Produto();
		produtoinserir.setNome("brunofelix");
		produtoinserir.setQuantidade(10);
		produtoinserir.setPrecoVenda(10);
		produtoinserir.setPrecoCusto(10);
		
		List<Categoria> listacategoria = fachada.CategoriaListar();
		
		for (int i = 0; i < listacategoria.size(); i++) {
			produtoinserir.setCategoria(listacategoria.get(i));
		}
		
		List<ItemComposicaoProduto> itemComp = fachada.ItemComposicaoProdutoListar();
		
		List<ProdutoItem> listaprodutoitem = new ArrayList<ProdutoItem>();
		
		ProdutoItem produtoitem = new ProdutoItem();
		
		for (int i = 0; i < itemComp.size(); i++) {
			produtoitem.setItemcomposicaoproduto(itemComp.get(i));
		}
		produtoitem.setProduto(produtoinserir);
		produtoitem.setQuantidade(10);
		
		
		listaprodutoitem.add(produtoitem);
		
		produtoinserir.setItensComp(listaprodutoitem);
		
		fachada.ProdutoInserir(produtoinserir);
		
		
		for (int i = 0; i < listaprodutoitem.size(); i++) {
			fachada.inserirprodutoitem(listaprodutoitem.get(i));
		}*/
		
		

	}

}
