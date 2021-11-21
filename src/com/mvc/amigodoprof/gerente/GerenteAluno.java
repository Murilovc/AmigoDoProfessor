package com.mvc.amigodoprof.gerente;

import java.util.List;

import javax.persistence.EntityManager;

import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.entidade.Turma;



public class GerenteAluno {

	private static EntityManager gerente = GerenteBase.getGerente();
	
	
	public static Aluno pesquisar(long idTurma) {
		return gerente.find(Aluno.class, idTurma);
		
	}
	
	public static void adicionar(Aluno aluno) {
		gerente.getTransaction().begin();
		gerente.persist(aluno);
		gerente.getTransaction().commit();
	}
	
	public static void atualizar(Aluno aluno) {
		gerente.getTransaction().begin();
		gerente.merge(aluno);
		gerente.getTransaction().commit();
	}
	
	public static void remover(Aluno aluno) {
		gerente.getTransaction().begin();
		gerente.remove(aluno);
		gerente.getTransaction().commit();
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Aluno> pesquisarTodos() {
		return gerente.createNamedQuery("Aluno.todos").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Aluno> pesquisarPorChamada(int chamada) {
		return gerente.createNamedQuery("Aluno.porChamada").setParameter("numero_chamada", chamada).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Aluno> pesquisarPorTurma(Turma turma) {
		return gerente.createNamedQuery("Aluno.porTurma").setParameter("turma", turma).getResultList();
	}
}

