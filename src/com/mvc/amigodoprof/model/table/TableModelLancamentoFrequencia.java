package com.mvc.amigodoprof.model.table;

import java.util.*;


import javax.swing.JRadioButton;
import javax.swing.table.AbstractTableModel;

import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.gui.Alinhamento;




@SuppressWarnings("serial")
public class TableModelLancamentoFrequencia extends AbstractTableModel {
	
	List<Aluno> listaAlunos;
	List<JRadioButton> listaBotoesPresente;
	List<JRadioButton> listaBotoesFalta;
	List<JRadioButton> listaBotoesJustificado;
	
	private Alinhamento[] alinhamento = {Alinhamento.ESQUERDA, Alinhamento.ESQUERDA,
										 Alinhamento.CENTRO, Alinhamento.CENTRO,
										 Alinhamento.CENTRO};
	
	public TableModelLancamentoFrequencia(List<Aluno> alunos, List<JRadioButton> listaBotoesPresente,
			List<JRadioButton> listaBotoesFalta, List<JRadioButton> listaBotoesJustificado) {
		
		this.listaAlunos = alunos;
		this.listaBotoesPresente = listaBotoesPresente;
		this.listaBotoesFalta = listaBotoesFalta;
		this.listaBotoesJustificado = listaBotoesJustificado;
		
	}
	
	@Override
	public int getRowCount() {
		return listaAlunos.size();
	}

	@Override
	public int getColumnCount() {		
		return 5;	
	}
	
	@Override	
	public Object getValueAt(int indiceLinha, int indiceColuna) {		

		Aluno aluno;
		Object dado = null;
		
		aluno = listaAlunos.get(indiceLinha);
		
		switch(indiceColuna) {
			
			case 0: 
				dado = aluno.getIdAluno();
				break;
			case 1: 
				dado = aluno.getNome();
				break;
			case 2: 
				dado = listaBotoesPresente.get(indiceLinha);
				break;
			case 3:
				dado = listaBotoesFalta.get(indiceLinha);
				break;
			case 4:
				dado = listaBotoesJustificado.get(indiceLinha);
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
				nome = "Presente"; 
				break;		
			case 3: 
				nome = "Falta"; 
				break;
			case 4: 
				nome = "Falta justificada"; 
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
				obj = JRadioButton.class; 
				break;
			case 3: 
				obj = JRadioButton.class; 
				break;
			case 4:
				obj = JRadioButton.class;
				break;
			default: 
				obj = null; 
				break;
		}		

		return obj.getClass();
	}
	
//	public boolean isCellEditable(int linha, int coluna) {
//		if(coluna > 0)
//			return true;
//		else
//			return false;
//	}
	
	public Alinhamento[] getAlinhamento() {
		return alinhamento;
	}
	
	
}


