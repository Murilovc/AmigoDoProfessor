package com.mvc.amigodoprof.model.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.entidade.Turma;


public class TableModelAluno extends AbstractTableModel {

	List<Aluno> listaAlunos;
	List<JButton> listaBotoesResolucao;
	List<JButton> listaBotoesFrequencia;
	
	public TableModelAluno(List<Aluno> aulas, List<JButton> listaBotoesFreq,
			List<JButton> listaBotoesResolucao) {
		
		this.listaAlunos = aulas;

		
		this.listaBotoesResolucao = listaBotoesResolucao;
		this.listaBotoesFrequencia = listaBotoesFreq;
	}
	
	@Override
	public int getRowCount() {
		return listaAlunos.size();
	}

	@Override
	public int getColumnCount() {		
		return 6;	
	}
	
	@Override	
	public Object getValueAt(int indiceLinha, int indiceColuna) {		

		Aluno aluno;
		Object dado = null;
		
		aluno = listaAlunos.get(indiceLinha);
		
		switch(indiceColuna) {
			case 0: 
				dado = aluno.getNumeroChamada();
				break;
			case 1: 
				dado = aluno.getNome();
				break;
			case 2:
				dado = aluno.getTurma().toString();
				break;
			case 3:
				dado = listaBotoesResolucao.get(indiceLinha);
				break;
			case 4:
				dado = listaBotoesFrequencia.get(indiceLinha);
				break;
			case 5:
				dado = aluno.getIdAluno();
				break;

		}
		return dado;		
	}

	@Override
	public String getColumnName(int indice_coluna) {

		String nome="";
		
		switch (indice_coluna) {
			case 0: 
				nome = "Nº"; 
				break;
			case 1: 
				nome = "Nome do aluno"; 
				break;		
			case 2: 
				nome = "Turma"; 
				break;
			case 3: 
				nome = "Atv. entregues"; 
				break;
			case 4:
				nome = "Frequência";
				break;
			case 5:
				nome = "Id";
				break;

		}		
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int indice_coluna) {
		
		Object obj;
		
		switch (indice_coluna) {
			case 0: 
				obj = Integer.class; 
				break;
			case 1: 
				obj = String.class; 
				break;
			case 2: 
				obj = Turma.class; 
				break;
			case 3:
				obj = JButton.class;
				break;
			case 4:
				obj = JButton.class;
				break;
			case 5:
				obj = Long.class;
				break;
			default: 
				obj = null; 
				break;
		}		

		return obj.getClass();
	}
	
//	public boolean isCellEditable(int linha, int coluna) {
//		if(coluna == 3 || coluna == 4) {
//			return true;
//		}
//		else
//			return false;
//	}

}

