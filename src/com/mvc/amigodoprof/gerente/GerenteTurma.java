package com.mvc.amigodoprof.gerente;

import java.util.List;

import javax.persistence.EntityManager;

import com.mvc.amigodoprof.entidade.Turma;


public class GerenteTurma {

	private static EntityManager gerente = GerenteBase.getGerente();
	
	
	public static Turma pesquisar(long idTurma) {
		return gerente.find(Turma.class, idTurma);
		
	}
	
	public static void adicionar(Turma turma) {
		gerente.getTransaction().begin();
		gerente.persist(turma);
		gerente.getTransaction().commit();
	}
	
	public static void atualizar(Turma turma) {
		gerente.getTransaction().begin();
		gerente.merge(turma);
		gerente.getTransaction().commit();
	}
	
	public static void remover(Turma turma) {
		gerente.getTransaction().begin();
		gerente.remove(turma);
		gerente.getTransaction().commit();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static List<Turma> pesquisarTodos() {
		return gerente.createNamedQuery("Turma.todas").getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Turma> pesquisarPorPrefixo(String prefixo) {
		return gerente.createNamedQuery("Turma.porPrefixo").setParameter("prefixo", prefixo).getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Turma> pesquisarPorValor(String valor) {
		return gerente.createNamedQuery("Turma.porValor").setParameter("valor", valor).getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Turma> pesquisarPorCodigo(String codigo) {
		return gerente.createNamedQuery("Turma.porCodigo").setParameter("codigo", codigo).getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Turma> pesquisarPorAnoLetivo(String anoLetivo) {
		return gerente.createNamedQuery("Turma.porAnoLetivo").setParameter("ano_letivo", anoLetivo).getResultList();
	}
	
}
