package com.mvc.amigodoprof.tablemodel;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import com.mvc.amigodoprof.entidade.Turma;



@SuppressWarnings("serial")
public class TableModelTurma extends AbstractTableModel {
	
	List<Turma> listaTurmas;
	
	public TableModelTurma(List<Turma> turmas) {
		this.listaTurmas = turmas;
	}
	
	@Override
	public int getRowCount() {
		return listaTurmas.size();
	}

	@Override
	public int getColumnCount() {		
		return 6;	
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
				dado = turma.getPrefixo();
				break;		
			case 2: 
				dado = turma.getValor(); 
				break;
			case 3: 
				dado = turma.getCodigo(); 
				break;
			case 4:
				dado = turma.getLembrete();
				break;
			case 5:
				dado = turma.getAnoLetivo();
				break;

		}
		return dado;		
	}

	@Override
	public String getColumnName(int indice_coluna) {

		String nome="";
		
		switch (indice_coluna) {
			case 0: 
				nome = "Id da turma"; 
				break;
			case 1: 
				nome = "Prefixo"; 
				break;		
			case 2: 
				nome = "Valor"; 
				break;
			case 3: 
				nome = "Código"; 
				break;
			case 4: 
				nome = "Lembrete"; 
				break;
			case 5: 
				nome = "Ano letivo"; 
				break;

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
			case 5:
				obj = String.class;
				break;
			default: 
				obj = null; 
				break;
		}		

		return obj.getClass();
	}
}

