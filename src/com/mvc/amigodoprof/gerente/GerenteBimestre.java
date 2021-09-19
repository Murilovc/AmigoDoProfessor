package com.mvc.amigodoprof.gerente;

import java.util.List;

import javax.persistence.EntityManager;

import com.mvc.amigodoprof.entidade.Bimestre;



public class GerenteBimestre {

	private static EntityManager gerente = GerenteBase.getGerente();
	
	
	public static Bimestre pesquisar(long idBimestre) {
		return gerente.find(Bimestre.class, idBimestre);
		
	}
	
	public static void adicionar(Bimestre bimestre) {
		gerente.getTransaction().begin();
		gerente.persist(bimestre);
		gerente.getTransaction().commit();
	}
	
	public static void atualizar(Bimestre bimestre) {
		gerente.getTransaction().begin();
		gerente.merge(bimestre);
		gerente.getTransaction().commit();
	}
	
	public static void remover(Bimestre bimestre) {
		gerente.getTransaction().begin();
		gerente.remove(bimestre);
		gerente.getTransaction().commit();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static List<Bimestre> pesquisarTodos() {
		return gerente.createNamedQuery("Bimestre.todos").getResultList();
	}
	
}