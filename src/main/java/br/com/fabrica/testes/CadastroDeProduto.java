package br.com.fabrica.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fabrica.dao.CategoriaDAO;
import br.com.fabrica.dao.ProdutoDAO;
import br.com.fabrica.modelo.Categoria;
import br.com.fabrica.modelo.Produto;
import br.com.fabrica.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		
	cadastrarProduto();
	Long id = 42l;
		
	EntityManager em = JPAUtil.getEntityManager(); 
	ProdutoDAO produtoDao = new ProdutoDAO(em);
		
	Produto p = produtoDao.buscarPorId(id);
	System.out.println(p.getPreco());
	
	List<Produto> todos = produtoDao.buscarNome("pia");
	todos.forEach(p2 -> System.out.println(p2.getNome()));
		
	List<Produto> todos2 = produtoDao.buscarNomePorCategoria("PIAS");
	todos2.forEach(p3 -> System.out.println(p3.getNome()));
		
	BigDecimal precoDoProd = produtoDao.buscarPrecoDoProdutoComNome("pia");
	System.out.println(precoDoProd);
	}

	private static void cadastrarProduto() {
		
		//Categoria pias = new Categoria("PIAS");
		Categoria piasItapora = new Categoria("PIAS_ITAPORA");
		Categoria tanques = new Categoria("TANQUES");
		Categoria lavatorios = new Categoria("LAVATORIOS");
		Categoria vasos = new Categoria("VASOS");
		
		//Produto pia120 = new Produto("pia", "Preto Granitado", 1.20
		//	, new BigDecimal("71.5"), pias);
//
		EntityManager em = JPAUtil.getEntityManager(); 
		//ProdutoDAO produtoDao = new ProdutoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(piasItapora);
		categoriaDao.cadastrar(tanques);
		categoriaDao.cadastrar(lavatorios);
		categoriaDao.cadastrar(vasos);
		//produtoDao.cadastrar(pia120);
	
		em.getTransaction();
		em.close();
	}

}
