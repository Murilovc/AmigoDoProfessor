package com.mvc.amigodoprof.model.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.table.AbstractTableModel;

import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.gui.Alinhamento;

public class TableModelRegistroResolucao extends AbstractTableModel{
	
	List<Aluno> listaAlunos;
	List<JCheckBox> listaCheckBoxEntregue;
	List<String> listaPontuacoes;
	
	private Alinhamento[] alinhamento = { Alinhamento.CENTRO, Alinhamento.CENTRO,
										  Alinhamento.CENTRO};
	
	public TableModelRegistroResolucao(List<Aluno> listaAlunos,
			List<JCheckBox> listaCheckBoxEntregue, List<String> listaPontuacoes) {
		
		this.listaAlunos = listaAlunos;
		
		this.listaCheckBoxEntregue = listaCheckBoxEntregue;
		
		if(listaPontuacoes != null) {
			this.listaPontuacoes = listaPontuacoes;
		} else {
			this.listaPontuacoes = new ArrayList<String>();
			for(Aluno aluno : listaAlunos) {
				this.listaPontuacoes.add("");
			}	
		}
		
	}
	
	@Override
	public int getRowCount() {
		return listaAlunos.size();
	}

	@Override
	public int getColumnCount() {		
		return 3;	
	}
	
	@Override	
	public Object getValueAt(int indiceLinha, int indiceColuna) {		

		Aluno aluno;
		Object dado = null;
		
		aluno = listaAlunos.get(indiceLinha);
		
		switch(indiceColuna) {
			
			case 0: 
				dado = aluno.getNome();
				break;
			case 1:  
				dado = listaCheckBoxEntregue.get(indiceLinha);
				break;
			case 2: 
				dado = listaPontuacoes.get(indiceLinha);
				break;

		}
		return dado;		
	}

	@Override
	public String getColumnName(int indice_coluna) {

		String nome="";
		
		switch (indice_coluna) {
			case 0: 
				nome = "Nome"; 
				break;
			case 1: 
				nome = "Entregue";
				break;
			case 2: 
				nome = "Pontuação"; 
				break;

		}		
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int indice_coluna) {
		
		Object obj;
		
		switch (indice_coluna) {
			case 0: 
				obj = String.class; 
				break;
			case 1: 
				obj = JButton.class; 
				break;
			case 2: 
				obj = String.class; 
				break;

			default: 
				obj = null; 
				break;
		}		

		return obj.getClass();
	}
	
	public boolean isCellEditable(int linha, int coluna) {
		if(coluna == 2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Alinhamento[] getAlinhamento() {
		return alinhamento;
	}
}
