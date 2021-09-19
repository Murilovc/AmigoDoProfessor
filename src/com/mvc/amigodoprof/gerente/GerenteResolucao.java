package com.mvc.amigodoprof.gerente;

import java.util.List;

import javax.persistence.EntityManager;

import com.mvc.amigodoprof.entidade.Resolucao;


public class GerenteResolucao {

	private static EntityManager gerente = GerenteBase.getGerente();
	
	
	public static Resolucao pesquisar(long idResolucao) {
		return gerente.find(Resolucao.class, idResolucao);
		
	}
	
	public static void adicionar(Resolucao resolucao) {
		gerente.getTransaction().begin();
		gerente.persist(resolucao);
		gerente.getTransaction().commit();
	}
	
	public static void atualizar(Resolucao resolucao) {
		gerente.getTransaction().begin();
		gerente.merge(resolucao);
		gerente.getTransaction().commit();
	}
	
	public static void remover(Resolucao resolucao) {
		gerente.getTransaction().begin();
		gerente.remove(resolucao);
		gerente.getTransaction().commit();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static List<Resolucao> pesquisarTodos() {
		return gerente.createNamedQuery("Resolucao.todas").getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Resolucao> pesquisarPorAtividade(long idAtividade) {
		return gerente.createNamedQuery("Resolucao.porAtividade").setParameter("atividade",
				idAtividade).getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Resolucao> pesquisarPorAluno(long idAluno) {
		return gerente.createNamedQuery("Resolucao.porAluno").setParameter("aluno", idAluno).getResultList();
	}
	
}

