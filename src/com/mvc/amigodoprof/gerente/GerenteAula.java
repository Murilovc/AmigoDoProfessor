package com.mvc.amigodoprof.gerente;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.entidade.Turma;


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
	public static List<Aula> pesquisarPorData(Date data) {
		return gerente.createNamedQuery("Aula.porData").setParameter("data", data).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Aula> pesquisarPorFrequenciaLancada() {
		return gerente.createNamedQuery("Aula.porFrequenciaLancada").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Aula> pesquisarPorFrequenciaNaoLancada() {
		return gerente.createNamedQuery("Aula.porFrequenciaNaoLancada").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Aula> pesquisarPorTurma(Turma turma) {
		return gerente.createNamedQuery("Aula.porTurma").setParameter("turma", turma).getResultList();
	}
	
	
}
