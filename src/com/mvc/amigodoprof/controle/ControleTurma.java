package com.mvc.amigodoprof.controle;

import java.util.List;

import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.gerente.GerenteTurma;

public class ControleTurma {
	
	
	public static List<Turma> pegarTodasAsTurmas() {
		return GerenteTurma.pesquisarTodos();
	}
	
	public static Turma pesquisarTurmaPorId(long id) {
		return GerenteTurma.pesquisar(id);
	}
	
	public static List<Turma> pesquisarTurmaPorTurno(String turno) {
		return GerenteTurma.pesquisarPorTurno(turno);
	}
	
	public static List<Turma> pesquisarTurmaPorNome(String nome){
		if(nome.startsWith("ano")) {
			return GerenteTurma.pesquisarPorPrefixo("ano");
		} else {
			if(nome.startsWith("série")){
				return GerenteTurma.pesquisarPorPrefixo("série");
			}
			else {
				return GerenteTurma.pesquisarPorCodigo(nome.substring(nome.length()-1));
			}
		}
	}
	
	public static List<Turma> pesquisarTurmaPorAnoLetivo(String anoLetivo){
		return GerenteTurma.pesquisarPorAnoLetivo(anoLetivo);
	}
	
	public static void apagarTurma(Turma turma) {
		GerenteTurma.remover(turma);
	}
}
