package com.mvc.amigodoprof.gerente;

import java.util.List;

import javax.persistence.EntityManager;

import com.mvc.amigodoprof.entidade.Atividade;
import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.entidade.Turma;

public class GerenteAtividade extends GerenteBase{
	private static EntityManager gerente = GerenteBase.getGerente();
	
	
	public static Atividade pesquisar(long idAtividade) {
		return gerente.find(Atividade.class, idAtividade);
		
	}
	
	public static void adicionar(Atividade atividade) {
		gerente.getTransaction().begin();
		gerente.persist(atividade);
		gerente.getTransaction().commit();
	}
	
	public static void atualizar(Atividade atividade) {
		gerente.getTransaction().begin();
		gerente.merge(atividade);
		gerente.getTransaction().commit();
	}
	
	public static void remover(Atividade atividade) {
		gerente.getTransaction().begin();
		gerente.remove(atividade);
		gerente.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Atividade> pesquisarPorTurma(Turma turma) {
		return gerente.createNamedQuery("Atividade.porTurma").setParameter("turma", turma).getResultList();
	}
}
