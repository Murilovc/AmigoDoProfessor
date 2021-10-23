package com.mvc.amigodoprof.controle;

import java.util.List;

import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.entidade.Turma;
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
	
	
	
	public static void cadastrarAluno(String nome, String numeroChamada, String anotacoes, Turma turma) {
		
		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setNumeroChamada(Integer.valueOf(numeroChamada));
		aluno.setAnotacao(anotacoes);
		aluno.setTurma(turma);
		
		
		GerenteAluno.adicionar(aluno);
	}

	public static void editarAluno(Aluno aluno, String nome, String numeroChamada, String anotacoes) {
		
		aluno.setNome(nome);
		aluno.setNumeroChamada(Integer.valueOf(numeroChamada));
		aluno.setAnotacao(anotacoes);
		
		GerenteAluno.atualizar(aluno);
	}
}
