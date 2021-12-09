package br.com.fabrica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fabrica.modelo.Categoria;
import br.com.fabrica.modelo.Produto;

public class CategoriaDAO {
	
	private EntityManager em;

	public CategoriaDAO(EntityManager em) {
		this.em = em;
	}
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}
	public void remover(Categoria categoria) {
		categoria = em.merge(categoria);
		this.em.remove(categoria);
	}
	public List<Categoria> buscarTodasCategorias(){
		String spql = "SELECT c FROM Categoria c";
		return em.createQuery(spql, Categoria.class).getResultList();
	}
	
}

