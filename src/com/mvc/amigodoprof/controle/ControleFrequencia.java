package com.mvc.amigodoprof.controle;

import java.util.List;

import com.mvc.amigodoprof.entidade.Frequencia;
import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.gerente.GerenteFrequencia;

public class ControleFrequencia {
	
	/*
	 * Transações básicas
	 * */
	public static Frequencia pesquisarFrequenciaPorId(long idFrequencia) {
		return GerenteFrequencia.pesquisar(idFrequencia);
		
	}
	
	public static void adicionarFrequencia(Frequencia frequencia) {
		GerenteFrequencia.adicionar(frequencia);
	}
	
	public static void apagarFrequencia(Frequencia frequencia) {
		GerenteFrequencia.remover(frequencia);
	}
	
	public static void atualizarFrequencia(Frequencia frequencia) {
		GerenteFrequencia.atualizar(frequencia);
	}
	
	/* Transações
	 * customizadas
	 * */
	
//	public static List<Frequencia>pegarTodosOsAlunos() {
//		return GerenteFrequencia.pesquisarTodos();
//	}
//	
//	public static List<Frequencia> pesquisarAlunoPorTurma(Turma turma){
//		return GerenteFrequencia.pesquisarPorTurma(turma);
//	}
//	
//	public static List<Frequencia> pesquisarAlunoPorNumeroChamada(int numeroChamada){
//		return GerenteFrequencia.pesquisarPorChamada(numeroChamada);
//	}
	
	
	
	public static void cadastrarFrequencia(String nome, String numeroChamada, String anotacoes, Turma turma) {
		
		Frequencia aluno = new Frequencia();
//		aluno.setNome(nome);
//		aluno.setNumeroChamada(Integer.valueOf(numeroChamada));
//		aluno.setAnotacao(anotacoes);
//		aluno.setTurma(turma);
		
		
		GerenteFrequencia.adicionar(aluno);
	}

	public static void editarFrequencia(Frequencia aluno, String nome, String numeroChamada, String anotacoes) {
		
//		aluno.setNome(nome);
//		aluno.setNumeroChamada(Integer.valueOf(numeroChamada));
//		aluno.setAnotacao(anotacoes);
		
		GerenteFrequencia.atualizar(aluno);
	}
}

