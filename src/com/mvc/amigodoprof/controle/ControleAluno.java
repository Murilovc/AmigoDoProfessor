package com.mvc.amigodoprof.controle;

import java.util.List;

import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.gerente.GerenteAluno;

public class ControleAluno {
	
	/*
	 * Transações básicas
	 * */
	public static Aluno pesquisarAlunoPorId(long idAluno) {
		return GerenteAluno.pesquisar(idAluno);
		
	}
	
	public static void adicionarAluno(Aluno aluno) {
		GerenteAluno.adicionar(aluno);
	}
	
	public static void apagarAluno(Aluno aluno) {
		GerenteAluno.remover(aluno);
	}
	
	public static void atualizarAula(Aluno aluno) {
		GerenteAluno.atualizar(aluno);
	}
	
	/* Transações
	 * customizadas
	 * */
	
	public static List<Aluno>pegarTodosOsAlunos() {
		return GerenteAluno.pesquisarTodos();
	}
	
	public static List<Aluno> pesquisarAlunoPorTurma(long idTurma){
		return GerenteAluno.pesquisarPorTurma(idTurma);
	}
	
	public static List<Aluno> pesquisarAlunoPorNumeroChamada(int numeroChamada){
		return GerenteAluno.pesquisarPorChamada(numeroChamada);
	}

}
