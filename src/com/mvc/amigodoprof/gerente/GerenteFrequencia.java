package com.mvc.amigodoprof.gerente;

import java.util.List;

import javax.persistence.EntityManager;

import com.mvc.amigodoprof.entidade.Frequencia;



public class GerenteFrequencia {

	private static EntityManager gerente = GerenteBase.getGerente();
	
	
	public static Frequencia pesquisar(long idFrequencia) {
		return gerente.find(Frequencia.class, idFrequencia);
		
	}
	
	public static void adicionar(Frequencia frequencia) {
		gerente.getTransaction().begin();
		gerente.persist(frequencia);
		gerente.getTransaction().commit();
	}
	
	public static void atualizar(Frequencia frequencia) {
		gerente.getTransaction().begin();
		gerente.merge(frequencia);
		gerente.getTransaction().commit();
	}
	
	public static void remover(Frequencia frequencia) {
		gerente.getTransaction().begin();
		gerente.remove(frequencia);
		gerente.getTransaction().commit();
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Frequencia> pesquisarTodas() {
		return gerente.createNamedQuery("Frequencia.todas").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Frequencia> pesquisarPorChamada(long idAluno) {
		return gerente.createNamedQuery("Frequencia.porAluno").setParameter("aluno", idAluno).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Frequencia> pesquisarPorAula(long idAula) {
		return gerente.createNamedQuery("Frequencia.porAula").setParameter("aula", idAula).getResultList();
	}
}


