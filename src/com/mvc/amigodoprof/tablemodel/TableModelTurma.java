package com.mvc.amigodoprof.tablemodel;

import java.util.*;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.gui.Alinhamento;



@SuppressWarnings("serial")
public class TableModelTurma extends AbstractTableModel {
	
	List<Turma> listaTurmas;
	
	private Alinhamento[] alinhamento = {Alinhamento.ESQUERDA,Alinhamento.CENTRO, Alinhamento.CENTRO,
								 		 Alinhamento.CENTRO, Alinhamento.CENTRO};
	
	public TableModelTurma(List<Turma> turmas) {
		this.listaTurmas = turmas;
	}
	
	@Override
	public int getRowCount() {
		return listaTurmas.size();
	}

	@Override
	public int getColumnCount() {		
		return 5;	
	}
	
	@Override	
	public Object getValueAt(int indiceLinha, int indiceColuna) {		

		Turma turma;
		Object dado = null;
		
		turma = listaTurmas.get(indiceLinha);
		
		switch(indiceColuna) {
			case 0: 
				dado = turma.getIdTurma(); 
				break;
			case 1: 
				dado = turma.toString();
				break;
			case 2:
				dado = turma.getTurno();
				break;
			case 3:
				dado = turma.getAnoLetivo();
				break;
			case 4:
				dado = turma.getListaAlunos() != null ? turma.getListaAlunos().size() : 0;
				break;

		}
		return dado;		
	}

	@Override
	public String getColumnName(int indice_coluna) {

		String nome="";
		
		switch (indice_coluna) {
			case 0: 
				nome = "Id"; 
				break;
			case 1: 
				nome = "Nome"; 
				break;		
			case 2: 
				nome = "Turno"; 
				break;
			case 3: 
				nome = "Ano letivo"; 
				break;
			case 4:
				nome = "Alunos";

		}		
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int indice_coluna) {
		
		Object obj;
		
		switch (indice_coluna) {
			case 0: 
				obj = Long.class; 
				break;
			case 1: 
				obj = String.class; 
				break;
			case 2: 
				obj = String.class; 
				break;
			case 3:
				obj = String.class;
				break;
			case 4:
				obj = String.class;
				break;
			default: 
				obj = null; 
				break;
		}		

		return obj.getClass();
	}
	
	public Alinhamento[] getAlinhamento() {
		return this.alinhamento;
	}
}

