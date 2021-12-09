package br.com.fabrica.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fabrica.modelo.Produto;

public class ProdutoDAO {
	
	private EntityManager em;

	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}
	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}
	public Produto buscarPorId(Long id) {
		return this.em.find(Produto.class, id);
	}
	public List<Produto> buscarTodos(){
		String spql = "SELECT p FROM Produto p";
		return em.createQuery(spql, Produto.class).getResultList();
	}
	public List<Produto> buscarNome(String nome){
		String spql = "SELECT p FROM Produto p WHERE p.nome = ?1"; // :nome ou ?1 
		return em.createQuery(spql, Produto.class)
				.setParameter(1, nome)
				.getResultList();
	}
	public List<Produto> buscarNomePorCategoria(String nome){
		String spql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome"; 
		return em.createQuery(spql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	public BigDecimal buscarPrecoDoProdutoComNome(String nome){
		String spql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome"; // :nome ou ?1 
		return em.createQuery(spql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
}
