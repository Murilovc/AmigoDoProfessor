package com.mvc.amigodoprof.tablemodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.gui.MenuTurma;

public class TableModelAula extends AbstractTableModel {

	List<Aula> listaAulas;
	List<JButton> listaBotoes;
	
	public TableModelAula(List<Aula> aulas) {
		this.listaAulas = aulas;
		Aula aula = new Aula();
		aula.setIdAula(78964123L);
		listaAulas.add(aula);
		
		listaBotoes = new ArrayList<JButton>();
		
		for(Aula a : aulas) {
			listaBotoes.add(new JButton(new MenuTurma.AcaoLancarFrequencia()));
		}
	}
	
	@Override
	public int getRowCount() {
		return listaAulas.size();
	}

	@Override
	public int getColumnCount() {		
		return 5;	
	}
	
	@Override	
	public Object getValueAt(int indiceLinha, int indiceColuna) {		

		Aula aula;
		Object dado = null;
		
		aula = listaAulas.get(indiceLinha);
		
		switch(indiceColuna) {
			case 0: 
				dado = aula.getIdAula();
				break;
			case 1: 
				dado = aula.getData();
				break;
			case 2:
				dado = aula.getConteudo();
				break;
			case 3:
				dado = new JButton("Lançar");//listaBotoes.get(indiceLinha);
				break;
			case 4:
				if(aula.isFrequenciaLancada() == true) {
					dado = "Frequência lançada";
				} else {
					dado = "Frequência pendente";
				}
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
				nome = "Data"; 
				break;		
			case 2: 
				nome = "Conteúdo"; 
				break;
			case 3: 
				nome = "Presença"; 
				break;
			case 4:
				nome = "Status";
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
				obj = Date.class; 
				break;
			case 2: 
				obj = String.class; 
				break;
			case 3:
				obj = JButton.class;
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
	
	public boolean isCellEditable(int linha, int coluna) {
		if(coluna == 3) {
			return true;
		}
		else
			return false;
	}

}
