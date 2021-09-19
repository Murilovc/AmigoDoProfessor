package com.mvc.amigodoprof.gerente;

import java.util.List;

import javax.persistence.EntityManager;

import com.mvc.amigodoprof.entidade.Aula;


public class GerenteAula {

	private static EntityManager gerente = GerenteBase.getGerente();
	
	
	public static Aula pesquisar(long idAula) {
		return gerente.find(Aula.class, idAula);
		
	}
	
	public static void adicionar(Aula aula) {
		gerente.getTransaction().begin();
		gerente.persist(aula);
		gerente.getTransaction().commit();
	}
	
	public static void atualizar(Aula aula) {
		gerente.getTransaction().begin();
		gerente.merge(aula);
		gerente.getTransaction().commit();
	}
	
	public static void remover(Aula aula) {
		gerente.getTransaction().begin();
		gerente.remove(aula);
		gerente.getTransaction().commit();
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Aula> pesquisarTodas() {
		return gerente.createNamedQuery("Aula.todas").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Aula> pesquisarTodasPorData() {
		return gerente.createNamedQuery("Aula.todasPorData").getResultList();
	}
}
