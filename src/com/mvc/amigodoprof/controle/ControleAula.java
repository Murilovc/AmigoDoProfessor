package com.mvc.amigodoprof.controle;

import java.util.List;

import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.gerente.GerenteAula;
import com.mvc.amigodoprof.gerente.GerenteTurma;

public class ControleAula {
	
	
	/*
	 * Transações básicas
	 * */
	public static Aula pesquisarAulaPorId(long idAula) {
		return GerenteAula.pesquisar(idAula);
		
	}
	
	public static void adicionarAula(Aula aula) {
		GerenteAula.adicionar(aula);
	}
	
	public static void apagarAula(Aula aula) {
		GerenteAula.remover(aula);
	}
	
	public static void atualizarAula(Aula aula) {
		GerenteAula.atualizar(aula);
	}
	
	/* Transações
	 * customizadas
	 * */
	
	public static List<Aula>pegarTodasAsAulas() {
		return GerenteAula.pesquisarTodas();
	}
	
	public static List<Aula> pesquisarAulaPorData(String data){
		return GerenteAula.pesquisarPorData(data);
	}
	
	public static List<Aula> pesquisarAulaPorFrequenciaLancada(){
		return GerenteAula.pesquisarPorFrequenciaLancada();
	}
	
	public static List<Aula> pesquisarAulaPorFrequenciaNaoLancada(){
		return GerenteAula.pesquisarPorFrequenciaNaoLancada();
	}
	

}
