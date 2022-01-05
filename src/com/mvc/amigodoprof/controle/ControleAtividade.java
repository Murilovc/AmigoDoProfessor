package com.mvc.amigodoprof.controle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.mvc.amigodoprof.entidade.Atividade;
import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.gerente.GerenteAtividade;

public class ControleAtividade {
	
	/*
	 * Transações básicas
	 * */
	public static Atividade pesquisarAtividadePorId(long idAtividade) {
		return GerenteAtividade.pesquisar(idAtividade);
		
	}
	
	
	public static List<Atividade>pesquisarPorTurma(Turma turma) {
		return GerenteAtividade.pesquisarPorTurma(turma);
	}
	
	
	public static void cadastrarAtividade(String valor, String descricao, String caminhoArquivo, Aula aula) {
		
		Atividade atividade = new Atividade();
		
		double valorDouble = Double.valueOf(valor);
		atividade.setValorMaximo(valorDouble);
		atividade.setDescricao(descricao);
		atividade.setArquivo(caminhoArquivo);
		atividade.setAula(aula);
		
		
		GerenteAtividade.adicionar(atividade);
	}

	public static void editarAtividade(Atividade atividade, String valor, String descricao) {
		
		double valorDouble = Double.valueOf(valor);
		atividade.setValorMaximo(valorDouble);
		atividade.setDescricao(descricao);
		
		GerenteAtividade.atualizar(atividade);
	}
	
	public static void apagarAtividade(Atividade atividade) {
		
		/*Deletando o arquivo associado a essa atividade*/
		try {
			Files.deleteIfExists(Paths.get("./atividades/"+atividade.getArquivo()));
		} catch (IOException e) {
			System.err.print("Não foi possível deletar o arquivo ou ele não existe");
			e.printStackTrace();
		}
		
		/*Deletando a atividade propriamente dita do banco de dados*/
		GerenteAtividade.remover(atividade);
	}
}
