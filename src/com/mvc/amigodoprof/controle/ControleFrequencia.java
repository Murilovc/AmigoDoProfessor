package com.mvc.amigodoprof.controle;

import java.util.List;

import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.entidade.Aula;
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
	
	/*
	 * Métodos de
	 * manipulação de entidades
	 * */
	
	public static void cadastrarFrequencia(boolean presente, String justificativa, Aluno aluno,  Aula aula) {
		
		Frequencia frequencia = new Frequencia();
		frequencia.setPresente(presente);
		frequencia.setJustificativa(justificativa);
		frequencia.setAluno(aluno);
		frequencia.setAula(aula);
		
		
		GerenteFrequencia.adicionar(frequencia);
	}

	public static void editarFrequencia(Frequencia aluno, String nome, String numeroChamada, String anotacoes) {
		
//		aluno.setNome(nome);
//		aluno.setNumeroChamada(Integer.valueOf(numeroChamada));
//		aluno.setAnotacao(anotacoes);
		
		GerenteFrequencia.atualizar(aluno);
	}
}

