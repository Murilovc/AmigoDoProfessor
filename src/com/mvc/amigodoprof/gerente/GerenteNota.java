package com.mvc.amigodoprof.gerente;

import java.util.List;

import javax.persistence.EntityManager;

import com.mvc.amigodoprof.entidade.Nota;



public class GerenteNota {

	private static EntityManager gerente = GerenteBase.getGerente();
	
	
	public static Nota pesquisar(long idNota) {
		return gerente.find(Nota.class, idNota);
		
	}
	
	public static void adicionar(Nota nota) {
		gerente.getTransaction().begin();
		gerente.persist(nota);
		gerente.getTransaction().commit();
	}
	
	public static void atualizar(Nota nota) {
		gerente.getTransaction().begin();
		gerente.merge(nota);
		gerente.getTransaction().commit();
	}
	
	public static void remover(Nota nota) {
		gerente.getTransaction().begin();
		gerente.remove(nota);
		gerente.getTransaction().commit();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static List<Nota> pesquisarTodos() {
		return gerente.createNamedQuery("Nota.todos").getResultList();
	}
	
}