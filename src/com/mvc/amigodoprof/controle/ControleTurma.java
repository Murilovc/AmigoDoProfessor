package com.mvc.amigodoprof.controle;

import java.util.List;

import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.gerente.GerenteTurma;

public class ControleTurma {
	
	
	public static List<Turma> pegarTodasAsTurmas() {
		return GerenteTurma.pesquisarTodos();
	}
}
