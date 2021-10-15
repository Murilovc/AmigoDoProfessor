package com.mvc.amigodoprof.controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	
	/* Junto com a Aula, é passada também a Turma
	 * a que a Aula pertence*/
	public static void adicionarAula(Aula aula, Turma turma) {
		List<Aula> listaAulas = turma.getListaAula();
		listaAulas.add(aula);
		turma.setListaAula(listaAulas);
		GerenteTurma.atualizar(turma);
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
	
	/* Métodos
	 * úteis
	 * */
	
	public static void cadastrarAula(String data, String conteudo,
			String planejamento, String frequenciaLancada, Turma turma) {
		
		Aula aula = new Aula();
		SimpleDateFormat a = new SimpleDateFormat("dd/MM/yyyy");
		Date dataConvertida = new Date();
		try {
			dataConvertida = a.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		aula.setData(dataConvertida);
		aula.setConteudo(conteudo);
		aula.setPlanejamento(planejamento);
		
		if(frequenciaLancada.equals("Não")) {
			aula.setFrequenciaLancada(false);
		} else {
			aula.setFrequenciaLancada(true);
		}
		
		//List<Aula> listaAulasDaTurma = new ArrayList<Aula>();//turma.getListaAula();
		//listaAulasDaTurma.add(aula);
		//turma.setListaAula(listaAulasDaTurma);
		//GerenteTurma.atualizar(turma);
		
		aula.setTurma(turma);
		
		ControleAula.adicionarAula(aula, turma);
	}
	

}
